package org.sciborgs1155.robot.wrist;

import monologue.Logged;

public interface WristIO extends AutoCloseable, Logged {
  /** Sets the wrist motor voltage. */
  public void setVoltage(double voltage);

  /**
   * Returns the position.
   *
   * @return
   */
  public double getPosition();

  /**
   * Returns if the limit switch has been pressed.
   *
   * @return
   */
  public boolean limitSwitch();

  /** Sets the wrist position to zero. (Not IRL) */
  public void zeroPosition();
}
