package org.sciborgs1155.robot.intake;

public class NoIntake implements IntakeIO {

  public void setPower(double percent) {}

  public boolean hasCube() {
    return true;
  }

  @Override
  public void close() throws Exception {}
}
