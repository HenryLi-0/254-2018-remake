package org.sciborgs1155.robot.intake;

import monologue.Annotations.Log;
import monologue.Logged;

public interface IntakeIO extends AutoCloseable, Logged {

  /***
   * Sets the intake voltage.
   */
  public void setPower(double percent);

  /***
   * Gets if the robot has a cube.
   *
   * @return Whether or not the robot has a cube.
   */
  @Log.NT
  public boolean hasCube();
}
