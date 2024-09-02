package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;

public class ElevatorConstants {

  // idk about these  measurements
  public static final Measure<Distance> MIN_HEIGHT = Meters.of(0.1);
  public static final Measure<Distance> MAX_HEIGHT = Meters.of(2);

  public static final double MOTOR_GEARING = 1 / 42.44;

  // from tech binder, high gearing? velocity should be correct, idk about acceleration
  public static final Measure<Velocity<Distance>> MAX_VELOCITY = Units.MetersPerSecond.of(3.6);
  public static final Measure<Velocity<Velocity<Distance>>> MAX_ACCELERATION =
      Units.MetersPerSecondPerSecond.of(3);

  public static final double kP = 512.0; // hmm...
  public static final double kI = 0.0;
  public static final double kD = 64.0; // hmm...

  public static final double kS = 0.0;
  public static final double kG = 0.0;
  public static final double kV = 0.0;
  public static final double kA = 0.0;
}
