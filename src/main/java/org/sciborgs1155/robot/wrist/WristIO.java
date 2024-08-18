package org.sciborgs1155.robot.wrist;

import monologue.Logged;

public interface WristIO extends AutoCloseable, Logged {

  /***
   * Extends the wrist.
   */
  public void extend();

  /***
   * Retracts the wrist.
   */
  public void retract();
}
