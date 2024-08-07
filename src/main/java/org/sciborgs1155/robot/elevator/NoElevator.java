package org.sciborgs1155.robot.elevator;

public class NoElevator implements ElevatorIO {

  @Override
  public double getPosition() {
    return 0;
  }

  @Override
  public void setVoltage(double voltage) {}

  @Override
  public void setGearShift(boolean high) {}

  @Override
  public void close() throws Exception {}
}
