package org.sciborgs1155.robot.wrist;

public class NoWrist implements WristIO {

  public void setVoltage(double voltage) {}

  public double getPosition() {return 0;}

  public boolean limitSwitch() {return false;}

  public void zeroPosition() {};

  @Override
  public void close() throws Exception {}
}
