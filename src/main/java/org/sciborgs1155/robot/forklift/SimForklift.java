package org.sciborgs1155.robot.forklift;

public class SimForklift implements ForkliftIO {
  private boolean piston = false;

  @Override
  public void stow() {
    piston = false;
  }

  @Override
  public void deploy() {
    piston = true;
  }

  @Override
  public boolean deployed() {
    return piston;
  }

  @Override
  public void close() throws Exception {}
}
