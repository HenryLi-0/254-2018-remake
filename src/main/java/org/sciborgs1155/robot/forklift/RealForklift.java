package org.sciborgs1155.robot.forklift;

import static org.sciborgs1155.robot.Ports.Forklift.*;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class RealForklift implements ForkliftIO {
  private final Solenoid piston_l = new Solenoid(PneumaticsModuleType.CTREPCM, SOLENOID_L);
  private final Solenoid piston_r = new Solenoid(PneumaticsModuleType.CTREPCM, SOLENOID_R);

  @Override
  public void stow() {
    piston_l.set(false);
    piston_r.set(false);
  }

  @Override
  public void deploy() {
    piston_l.set(true);
    piston_r.set(true);
  }

  @Override
  public boolean deployed() {
    return piston_l.get() && piston_r.get();
  }

  @Override
  public void close() throws Exception {
    piston_l.close();
    piston_r.close();
  }
}
