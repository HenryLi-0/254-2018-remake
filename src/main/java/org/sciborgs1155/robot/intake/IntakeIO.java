package org.sciborgs1155.robot.intake;

import monologue.Logged;

public interface IntakeIO extends AutoCloseable, Logged {

  /***
   * Sets the intake voltage.
   */
  public void setPower(double power);

  /***
   * Gets if the robot has a cube.
   *
   * @return Whether or not the robot has a cube.
   */
  public boolean hasCube();
}
