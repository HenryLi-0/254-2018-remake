package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;
import static org.sciborgs1155.robot.Constants.PERIOD;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import java.util.Optional;
import java.util.function.DoubleSupplier;
import monologue.Annotations.Log;
import monologue.Logged;
import org.sciborgs1155.lib.InputStream;
import org.sciborgs1155.robot.Robot;

public class Elevator extends SubsystemBase implements AutoCloseable, Logged {
  private final ElevatorIO hardware;
  private final SysIdRoutine sysIdRoutine;

  @Log.NT
  private final ProfiledPIDController pid =
      new ProfiledPIDController(
          kP, kI, kD, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCELERATION));

  @Log.NT private final ElevatorFeedforward ff = new ElevatorFeedforward(kS, kG, kV, kA);

  // Visualizer
  @Log.NT
  private final ElevatorVisualizer positionVisualizer =
      new ElevatorVisualizer(new Color8Bit("#ff8000"));

  @Log.NT
  private final ElevatorVisualizer setpointVisualizer =
      new ElevatorVisualizer(new Color8Bit("#ff00ff"));

  public static Elevator create() {
    return Robot.isReal() ? new Elevator(new RealElevator()) : new Elevator(new SimElevator());
  }

  public static Elevator none() {
    return new Elevator(new NoElevator());
  }

  public Elevator(ElevatorIO elevator) {
    this.hardware = elevator;
    sysIdRoutine = new SysIdRoutine(new SysIdRoutine.Config(Volts.per(Seconds).of(1)), new SysIdRoutine.Mechanism(null, null, null))
  }

  public Command setGoal(double goal) {
    return runOnce(() -> pid.setGoal(goal));
  }

  public Command goToMin() {
    return run(() -> update(MIN_HEIGHT.in(Meters))).withName("goToMin");
  }

  public Command goToMax() {
    return run(() -> update(MAX_HEIGHT.in(Meters))).withName("goToMax");
  }

  public Command goToPercent(DoubleSupplier percent) {
    return run(() ->
            update(
                MathUtil.clamp(
                    MIN_HEIGHT.in(Meters)
                        + percent.getAsDouble() * (MAX_HEIGHT.in(Meters) - MIN_HEIGHT.in(Meters)),
                    MIN_HEIGHT.in(Meters),
                    MAX_HEIGHT.in(Meters))))
        .withName("goToPercent");
  }

  public Command changeGoal(InputStream change) {
    return run(
        () -> {
          pid.setGoal(
              MathUtil.clamp(
                  pid.getGoal().position + change.get(),
                  MIN_HEIGHT.in(Meters),
                  MAX_HEIGHT.in(Meters)));
          update(pid.getGoal().position);
        });
  }

  @Log.NT
  public double getPosition() {
    return hardware.getPosition();
  }

  public boolean atGoal() {
    return pid.atGoal();
  }

  /**
   * Smoothly move the elevator car to a given position/height.
   *
   * @param theGoal The target height
   */
  public void update(double theGoal) {
    double goal = MathUtil.clamp(theGoal, MIN_HEIGHT.in(Meters), MAX_HEIGHT.in(Meters));
    var previousSetpoint = pid.getSetpoint();
    double feedback = pid.calculate(getPosition(), goal);
    double acceleration =
        (pid.getSetpoint().velocity - previousSetpoint.velocity) / PERIOD.in(Seconds);
    double feedforward = ff.calculate(pid.getSetpoint().velocity, acceleration);
    log("feedback", feedback);
    log("feedforward", feedforward);
    hardware.setVoltage(feedback + feedforward);
  }



  @Override
  public void periodic() {
    positionVisualizer.setState(hardware.getPosition());
    setpointVisualizer.setState(pid.getGoal().position);
    log("command", Optional.ofNullable(getCurrentCommand()).map(Command::getName).orElse("none"));
  }

  @Override
  public void close() throws Exception {
    hardware.close();
    positionVisualizer.close();
    setpointVisualizer.close();
  }
}
