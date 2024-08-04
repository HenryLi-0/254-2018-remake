package org.sciborgs1155.robot.elevator;

import static edu.wpi.first.units.Units.Meters;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.MIN_HEIGHT;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class ElevatorVisualizer implements Sendable, AutoCloseable{
    private final Mechanism2d mech;
    private final MechanismRoot2d root;
    private final MechanismLigament2d car;
    
    public ElevatorVisualizer(Color8Bit color){
        mech = new Mechanism2d(2, 3);
        root = mech.getRoot("Elevator", 0,0);
        car = root.append(new MechanismLigament2d("Car", MIN_HEIGHT.in(Meters), 90, 5, color));
    }

    public void setState(double length){
        car.setLength(length);
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
