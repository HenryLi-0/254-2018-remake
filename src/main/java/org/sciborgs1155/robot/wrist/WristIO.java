package org.sciborgs1155.robot.wrist;

import monologue.Logged;

public interface WristIO extends AutoCloseable, Logged {
  /**
   * Sets the wrist motor voltage.
   */
  public void setVoltage(double voltage);

  /**
   * Returns the position.
   * @return
   */
  public double getPosition();
}
