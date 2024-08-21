package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Kilograms;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;
import static edu.wpi.first.units.Units.Radians;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;

public class WristConstants {
  public static final double MOTOR_GEARING = 5.0 / 1.0;
  public static final Measure<Distance> WRIST_LENGTH = Meters.of(0.35);
  public static final Measure<Angle> MIN_ANGLE = Radians.of(0);
  public static final Measure<Angle> MAX_ANGLE = Radians.of(Math.PI);
  public static final Measure<Mass> MASS = Kilograms.of(3);

  // note to future self: find constants
  public static final Measure<Velocity<Distance>> MAX_VELOCITY = MetersPerSecond.of(999);
  public static final Measure<Velocity<Velocity<Distance>>> MAX_ACCELERATION =
      MetersPerSecondPerSecond.of(999);

  public static final double kP = 32.0;
  public static final double kI = 0.0;
  public static final double kD = 1.0;

  public static final double kS = 0.0;
  public static final double kG = 0.0;
  public static final double kV = 0.0;
  public static final double kA = 0.0;
}
