package org.sciborgs1155.robot.wrist;

import static org.sciborgs1155.robot.Ports.Wrist.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;

public class RealWrist implements WristIO {
  private final DigitalInput limitSwitch = new DigitalInput(LIMIT_SWITCH);
  private final TalonFX motor = new TalonFX(BACK_MOTOR);

  public RealWrist() {
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    motor.getConfigurator().apply(configuration);
  }

  public void extend() {
    // please do
  }

  public void retract() {
    // please do
  }

  @Override
  public void close() throws Exception {}
}
