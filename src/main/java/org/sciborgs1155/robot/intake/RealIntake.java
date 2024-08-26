package org.sciborgs1155.robot.intake;

import static org.sciborgs1155.robot.Ports.Intake.*;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;

public class RealIntake implements IntakeIO {
  private final TalonFX lead = new TalonFX(LEFT_INTAKE);
  private final TalonFX follower = new TalonFX(RIGHT_INTAKE);
  private final DigitalInput leftBeambreak = new DigitalInput(LEFT_BEAMBREAK);
  private final DigitalInput rightBeambreak = new DigitalInput(RIGHT_BEAMBREAK);

  public RealIntake() {
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    lead.getConfigurator().apply(configuration);
    follower.getConfigurator().apply(configuration);

    follower.setControl(new Follower(LEFT_INTAKE, true));
  }

  public void setPower(double percent) {
    lead.set(percent);
  }

  public boolean hasCube() {
    return leftBeambreak.get() || rightBeambreak.get();
  }

  @Override
  public void close() throws Exception {}
}
