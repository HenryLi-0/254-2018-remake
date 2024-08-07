package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.*;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import org.sciborgs1155.robot.Constants;

public class SimElevator implements ElevatorIO {

  private final ElevatorSim sim =
      new ElevatorSim(
          MAX_VELOCITY.magnitude(),
          MAX_ACCELERATION.magnitude(),
          DCMotor.getVex775Pro(4),
          MIN_HEIGHT.in(Meters),
          MAX_HEIGHT.in(Meters),
          false,
          MIN_HEIGHT.in(Meters));

  @Override
  public double getPosition() {
    return sim.getPositionMeters();
  }

  @Override
  public void setVoltage(double voltage) {
    sim.setInputVoltage(voltage);
    sim.update(Constants.PERIOD.in(Seconds));
  }

  @Override
  public void setGearShift(boolean high) {
    // idk
  }

  @Override
  public void close() throws Exception {}
}
