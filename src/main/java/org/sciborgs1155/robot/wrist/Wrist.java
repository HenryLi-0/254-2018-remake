package org.sciborgs1155.robot.wrist;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Wrist extends SubsystemBase implements AutoCloseable, Logged {
  private final WristIO hardware;

  public static Wrist create() {
    return Robot.isReal() ? new Wrist(new RealWrist()) : new Wrist(new SimWrist());
  }

  public static Wrist none() {
    return new Wrist(new NoWrist());
  }

  public Wrist(WristIO hardware) {
    this.hardware = hardware;
  }

  /***
   * Extends the wrist.
   */
  public Command extend() {
    return run(() -> {});
  }

  /***
   * Retracts the wrist.
   */
  public Command retract() {
    return run(() -> {});
  }

  @Override
  public void close() throws Exception {
    hardware.close();
  }
}
