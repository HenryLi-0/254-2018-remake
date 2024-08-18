package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.Ports.Intake.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

public class RealIntake implements IntakeIO {
  private final TalonFX lead = new TalonFX(LEFT_INTAKE);
  private final TalonFX follower = new TalonFX(RIGHT_INTAKE);

  public RealIntake() {
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    lead.getConfigurator().apply(configuration);
    follower.getConfigurator().apply(configuration);

    follower.setControl(new Follower(LEFT_INTAKE, true));
  }

  public void setPower(double power) {
    // idk, do this one day
  }

  public boolean hasCube() {
    return true; // please finish this
  }

  @Override
  public void close() throws Exception {}
}
