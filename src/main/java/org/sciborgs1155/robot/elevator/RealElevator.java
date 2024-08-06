package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.Ports.Elevator.*;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class RealElevator implements ElevatorIO {
  private final TalonFX lead;
  private final TalonFX leftBottom;
  private final TalonFX rightTop;
  private final TalonFX rightBottom;

  public RealElevator() {
    lead = new TalonFX(MOTOR_LEFT_TOP);
    leftBottom = new TalonFX(MOTOR_LEFT_BOTTOM);
    rightTop = new TalonFX(MOTOR_RIGHT_TOP);
    rightBottom = new TalonFX(MOTOR_RIGHT_BOTTOM);

    
  }

  @Override
  public double getPosition() {
    return 0;
  }

  @Override
  public void setVoltage(double voltage) {

  }

  @Override
  public void close() throws Exception {}
}
