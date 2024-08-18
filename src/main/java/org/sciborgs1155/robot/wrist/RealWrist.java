package org.sciborgs1155.robot.wrist;

import static org.sciborgs1155.robot.Ports.Wrist.*;

import edu.wpi.first.wpilibj.DigitalInput;

public class RealWrist implements WristIO {
    
    DigitalInput limitSwitch = new DigitalInput(LIMIT_SWITCH);
    @Override
    public void close() throws Exception {}
}
