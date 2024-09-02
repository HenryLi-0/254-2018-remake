package org.sciborgs1155.robot;

import static edu.wpi.first.units.Units.Radians;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sciborgs1155.lib.TestingUtil.fastForward;
import static org.sciborgs1155.lib.TestingUtil.run;
import static org.sciborgs1155.robot.wrist.WristConstants.MAX_ANGLE;
import static org.sciborgs1155.robot.wrist.WristConstants.MIN_ANGLE;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.wrist.Wrist;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class WristTest {
    Wrist wrist;

    @BeforeEach
    public void setup() {
        wrist = Wrist.create();
    }

    @Test
    public void testStowExtend(){
        run(wrist.extend());
        fastForward();
        assertEquals(MAX_ANGLE.in(Radians), wrist.getPosition(), 0);
        run(wrist.stow());
        fastForward();
        assertEquals(MIN_ANGLE.in(Radians), wrist.getPosition(), 0);
    }

    @AfterEach
    public void afterEach() throws Exception {
        CommandScheduler.getInstance().unregisterAllSubsystems();
        CommandScheduler.getInstance().cancelAll();
        wrist.close();
    }
}
