package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Annotations.Log;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Elevator extends SubsystemBase implements AutoCloseable, Logged {
  private final ElevatorIO hardware;

  @Log.NT
  private final ProfiledPIDController pid =
      new ProfiledPIDController(
          kP, kI, kD, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCELERATION));
      
  @Log.NT
  private final ElevatorFeedforward feedforward = new ElevatorFeedforward(kS, kG, kV, kA);

  // Visualizer
  @Log.NT private final ElevatorVisualizer positionVisualizer = new ElevatorVisualizer(new Color8Bit("#ff8000"));
  @Log.NT private final ElevatorVisualizer setpointVisualizer = new ElevatorVisualizer(new Color8Bit("#ff00ff"));

  public static Elevator create() {
    return Robot.isReal() ? new Elevator(new RealElevator()) : new Elevator(new SimElevator());
  }

  public static Elevator none() {
    return new Elevator(new NoElevator());
  }

  public Elevator(ElevatorIO elevator) {
    this.hardware = elevator;
  }
  
  public Command setGoal(double goal) {
    return runOnce(() -> pid.setGoal(goal));
  }

  @Log.NT
  public double position() {
    return hardware.getPosition();
  }

  public boolean atGoal() {
    return pid.atGoal();
  }

  @Override
  public void periodic() {
    double feedback = pid.calculate(position(), pid.getGoal());
    hardware.setVoltage(feedback);
    positionVisualizer.setState(hardware.getPosition());
    setpointVisualizer.setState(pid.getGoal().position);
  }

  @Override
  public void close() throws Exception {
    hardware.close();
    positionVisualizer.close();
    setpointVisualizer.close();
  }
}
