package org.sciborgs1155.robot.elevator;

import monologue.Annotations.Log;
import monologue.Logged;

public interface ElevatorIO extends AutoCloseable, Logged {

  /***
   * Gets the elevator's position.
   */
  @Log.NT
  public double getPosition();

  /**
   * Sets the voltage to a given voltage.
   *
   * @param voltage The voltage
   */
  public void setVoltage(double voltage);
}
