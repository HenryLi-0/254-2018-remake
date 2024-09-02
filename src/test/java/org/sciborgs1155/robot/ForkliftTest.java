package org.sciborgs1155.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sciborgs1155.lib.TestingUtil.fastForward;
import static org.sciborgs1155.lib.TestingUtil.run;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.forklift.Forklift;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class ForkliftTest {
    Forklift forklift;
    
    @BeforeEach
    public void setup(){
        forklift = Forklift.create();
    }

    @Test
    public void test(){
        run(forklift.deploy());
        fastForward();
        assertEquals(true, forklift.getDeployed());
        run(forklift.stow());
        fastForward();
        assertEquals(false, forklift.getDeployed());
    }

    @AfterEach
    public void afterEach() throws Exception {
        CommandScheduler.getInstance().unregisterAllSubsystems();
        CommandScheduler.getInstance().cancelAll();
        forklift.close();
    }

}
