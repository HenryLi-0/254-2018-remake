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

  /**
   * Sets the gear shift to a given value
   * 
   * @param high Given truth value of whether the desired gear shift is high. As in high gear. Not the other meaning.
   */
  public void setGearShift(boolean high);
  
}
