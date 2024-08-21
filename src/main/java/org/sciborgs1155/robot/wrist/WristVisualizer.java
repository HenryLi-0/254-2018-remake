package org.sciborgs1155.robot.wrist;

import static edu.wpi.first.units.Units.Degrees;
import static org.sciborgs1155.robot.wrist.WristConstants.*;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class WristVisualizer implements Sendable, AutoCloseable {
  private final Mechanism2d mech;
  private final MechanismRoot2d root;
  private final MechanismLigament2d wrist;

  public WristVisualizer(Color8Bit color) {
    mech = new Mechanism2d(2, 2);
    root = mech.getRoot("Wrist Mechanism", 1, 1);
    wrist = root.append(new MechanismLigament2d("Wrist", 1, MIN_ANGLE.in(Degrees), 5, color));
  }

  /**
   * Set the angle of the simulated wrist.
   * @param angle
   */
  public void setAngle(Measure<Angle> angle) {
    wrist.setAngle(angle.in(Degrees));
  }
  /**
   * Set the angle of the simulated wrist. (Given in degrees!)
   * @param degrees
   */
  public void setAngle(double degrees) {
    wrist.setAngle(degrees);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    mech.initSendable(builder);
  }

  @Override
  public void close() throws Exception {
    mech.close();
  }
}
