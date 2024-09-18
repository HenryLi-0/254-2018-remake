package org.sciborgs1155.robot.forklift;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;
import monologue.Logged;
import org.sciborgs1155.robot.Robot;

public class Forklift extends SubsystemBase implements AutoCloseable, Logged {
  private final ForkliftIO hardware;

  public static Forklift create() {
    return Robot.isReal() ? new Forklift(new RealForklift()) : new Forklift(new SimForklift());
  }

  public static Forklift none() {
    return new Forklift(new NoForklift());
  }

  public Forklift(ForkliftIO forklift) {
    this.hardware = forklift;
  }

  /***
   * Stows the forklift.
   */
  public Command stow() {
    return run(() -> hardware.stow()).withName("stow");
  }

  /***
   * Deploys the forklift.
   */
  public Command deploy() {
    return run(() -> hardware.deploy()).withName("deploy");
  }

  /***
   * Returns whether it is deployed or not.
   */
  public boolean getDeployed() {
    return hardware.deployed();
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
