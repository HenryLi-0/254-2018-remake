package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import java.util.List;

public class TalonSwerveModule implements ModuleIO {

  @Override
  public List<Fault> getFaults() {}

  @Override
  public void close() throws Exception {}

  @Override
  public SwerveModuleState getState() {}

  @Override
  public SwerveModulePosition getPosition() {}

  @Override
  public void setDesiredState(SwerveModuleState desiredState) {}

  @Override
  public SwerveModuleState getDesiredState() {}

  @Override
  public void resetEncoders() {}
}
