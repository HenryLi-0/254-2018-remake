package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Radians;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;

public class WristConstants {
  public static final double MOTOR_GEARING = 5.0 / 1.0;
  public static final Measure<Distance> WRIST_LENGTH = Meters.of(0.1);
  public static final Measure<Angle> MIN_ANGLE = Radians.of(0);
  public static final Measure<Angle> MAX_ANGLE = Radians.of(Math.PI);
}
