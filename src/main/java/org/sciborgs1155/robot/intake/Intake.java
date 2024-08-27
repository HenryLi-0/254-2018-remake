package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.intake.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Intake extends SubsystemBase implements AutoCloseable, Logged {
  private final IntakeIO hardware;

  public static Intake create() {
    return Robot.isReal() ? new Intake(new RealIntake()) : none();
  }

  public static Intake none() {
    return new Intake(new NoIntake());
  }

  public Intake(IntakeIO hardware) {
    this.hardware = hardware;
  }

  public Command intake() {
    return run(() -> hardware.setPower(INTAKE_POWER)).withName("intake");
  }

  public Command outtake() {
    return run(() -> hardware.setPower(-INTAKE_POWER)).withName("outtake");
  }

  @Override
  public void periodic() {
    log("command", Optional.ofNullable(getCurrentCommand()).map(Command::getName).orElse("none"));
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
