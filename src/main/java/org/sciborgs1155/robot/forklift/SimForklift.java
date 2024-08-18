package org.sciborgs1155.robot.forklift;

public class SimForklift implements ForkliftIO {

  @Override
  public void stow() {}

  @Override
  public void deploy() {}

  @Override
  public boolean deployed() {
    return true;
  }

  @Override
  public void close() throws Exception {}
}
