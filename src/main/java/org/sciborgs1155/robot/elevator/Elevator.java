package org.sciborgs1155.robot.elevator;

import org.sciborgs1155.robot.Robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class Elevator extends SubsystemBase implements AutoCloseable, Logged{
    public static Elevator create() {
        return Robot.isReal() ? new Elevator(new RealElevator()) : new Elevator(new SimElevator());
    }

    public static Elevator none() {
        return new Elevator(new NoElevator());
    }

    public Elevator(ElevatorIO elevator) {
        

    }

    @Override
    public void close() throws Exception {
        
    }
}
