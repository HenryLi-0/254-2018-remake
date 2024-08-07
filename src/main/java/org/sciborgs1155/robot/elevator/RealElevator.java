package org.sciborgs1155.robot.elevator;

import static org.sciborgs1155.robot.Ports.Elevator.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class RealElevator implements ElevatorIO {
  private final Solenoid gearShift = new Solenoid(PneumaticsModuleType.CTREPCM, SOLENOID);
  private final TalonFX lead = new TalonFX(MOTOR_LEFT_TOP);
  private final TalonFX leftBottom = new TalonFX(MOTOR_LEFT_BOTTOM);
  private final TalonFX rightTop = new TalonFX(MOTOR_RIGHT_TOP);
  private final TalonFX rightBottom = new TalonFX(MOTOR_RIGHT_BOTTOM);

  public RealElevator() {
    gearShift.set(false);

    TalonFXConfiguration configuration = new TalonFXConfiguration();
    lead.getConfigurator().apply(configuration);
    leftBottom.getConfigurator().apply(configuration);
    rightTop.getConfigurator().apply(configuration);
    rightBottom.getConfigurator().apply(configuration);

    leftBottom.setControl(new Follower(MOTOR_LEFT_TOP, true));
    rightTop.setControl(new Follower(MOTOR_LEFT_TOP, false));
    rightBottom.setControl(new Follower(MOTOR_LEFT_TOP, true));
  }

  @Override
  public double getPosition() {
    return lead.getPosition().getValueAsDouble();
  }

  @Override
  public void setVoltage(double voltage) {
    lead.setVoltage(voltage);
  }

  @Override
  public void setGearShift(boolean high) {
    gearShift.set(high);
  }

  @Override
  public void close() throws Exception {}
}
