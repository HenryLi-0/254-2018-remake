package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Radians;
import static org.sciborgs1155.robot.wrist.WristConstants.*;
import static org.sciborgs1155.robot.wrist.WristConstants.MOTOR_GEARING;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class SimWrist implements WristIO {
  private final SingleJointedArmSim sim =
      new SingleJointedArmSim(
          DCMotor.getVex775Pro(2),
          MOTOR_GEARING,
          0,
          WRIST_LENGTH.in(Meters),
          MIN_ANGLE.in(Radians),
          MAX_ANGLE.in(Radians),
          false,
          MIN_ANGLE.in(Radians)
          );

  public void setVoltage(double voltage) {
    sim.setInputVoltage(voltage);
  }

  public double getPosition() {
    return sim.getAngleRads();
  }

  @Override
  public void close() throws Exception {}
}
