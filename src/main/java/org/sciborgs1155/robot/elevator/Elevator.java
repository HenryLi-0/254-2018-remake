package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
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
  }

  @Override
  public void close() throws Exception {}
}
