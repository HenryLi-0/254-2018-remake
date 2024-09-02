package org.sciborgs1155.robot;

import static edu.wpi.first.units.Units.Meters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sciborgs1155.lib.TestingUtil.*;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.MAX_HEIGHT;
import static org.sciborgs1155.robot.elevator.ElevatorConstants.MIN_HEIGHT;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.elevator.Elevator;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class ElevatorTest {
    Elevator elevator;

    @BeforeEach
    public void setup(){
        elevator = Elevator.create();   
    }

    @Test
    public void testMinMax(){
        run(elevator.goToMax());
        fastForward();
        assertEquals(MAX_HEIGHT.in(Meters),elevator.getPosition(),0.1);
        run(elevator.goToMin());
        fastForward();
        assertEquals(MIN_HEIGHT.in(Meters),elevator.getPosition(),0.1);
    }

    @AfterEach
    public void afterEach() throws Exception {
        CommandScheduler.getInstance().unregisterAllSubsystems();
        CommandScheduler.getInstance().cancelAll();
        elevator.close();
    }
}
