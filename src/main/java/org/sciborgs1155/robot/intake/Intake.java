package org.sciborgs1155.robot.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
