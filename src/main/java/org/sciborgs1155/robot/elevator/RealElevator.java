package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.Ports.Elevator.*;

import com.ctre.phoenix6.hardware.CANcoder;

public class RealElevator implements ElevatorIO {
  private final CANcoder lead;
  private final CANcoder leftBottom;
  private final CANcoder rightTop;
  private final CANcoder rightBottom;

  public RealElevator() {
    lead = new CANcoder(MOTOR_LEFT_TOP);
    leftBottom = new CANcoder(MOTOR_LEFT_BOTTOM);
    rightTop = new CANcoder(MOTOR_RIGHT_TOP);
    rightBottom = new CANcoder(MOTOR_RIGHT_BOTTOM);
  }

  @Override
  public double getPosition() {
    return 0;
  }

  @Override
  public void setVoltage(double voltage) {}

  @Override
  public void close() throws Exception {}
}
