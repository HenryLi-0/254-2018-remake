package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Seconds;
import static org.sciborgs1155.robot.Constants.PERIOD;
import static org.sciborgs1155.robot.wrist.WristConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;
import monologue.Annotations.Log;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Wrist extends SubsystemBase implements AutoCloseable, Logged {
  private final WristIO hardware;

  public static Wrist create() {
    return Robot.isReal() ? new Wrist(new RealWrist()) : new Wrist(new SimWrist());
  }

  @Log.NT
  private final ProfiledPIDController pid =
      new ProfiledPIDController(
          kP, kI, kD, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCELERATION));

  @Log.NT private final ElevatorFeedforward ff = new ElevatorFeedforward(kS, kG, kV, kA);

  // Visualizers
  @Log.NT
  private final WristVisualizer positionVisualizer = new WristVisualizer(new Color8Bit("#ff8000"));

  @Log.NT
  private final WristVisualizer setpointVisualizer = new WristVisualizer(new Color8Bit("#ff00ff"));

  public static Wrist none() {
    return new Wrist(new NoWrist());
  }

  public Wrist(WristIO hardware) {
    this.hardware = hardware;
  }

  @Log.NT
  public double getPosition() {
    return hardware.getPosition();
  }

  public Command stow() {
    return run(() -> update(MIN_ANGLE.in(Radians))).withName("stow");
  }

  public Command extend() {
    return run(() -> update(MAX_ANGLE.in(Radians))).withName("extend");
  }

  /**
   * Smoothly move the wrist to a given angle.
   *
   * @param theGoal The target angle
   */
  public void update(double theGoal) {
    double goal = MathUtil.clamp(theGoal, MIN_ANGLE.in(Radians), MAX_ANGLE.in(Radians));
    var previousSetpoint = pid.getSetpoint();
    double feedback = pid.calculate(hardware.getPosition(), goal);
    double acceleration =
        (pid.getSetpoint().velocity - previousSetpoint.velocity) / PERIOD.in(Seconds);
    double feedforward = ff.calculate(pid.getSetpoint().velocity, acceleration);
    log("feedback", feedback);
    log("feedforward", feedforward);
    hardware.setVoltage(feedback + feedforward);
  }

  @Override
  public void periodic() {
    if (hardware.limitSwitch()) {
      hardware.zeroPosition();
    }
    positionVisualizer.setAngle(Units.radiansToDegrees(hardware.getPosition()));
    setpointVisualizer.setAngle(Units.radiansToDegrees(pid.getGoal().position));
    log("command", Optional.ofNullable(getCurrentCommand()).map(Command::getName).orElse("none"));
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
