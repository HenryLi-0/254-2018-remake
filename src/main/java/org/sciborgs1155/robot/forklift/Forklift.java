package org.sciborgs1155.robot.forklift;

import org.sciborgs1155.robot.Robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class Forklift extends SubsystemBase implements AutoCloseable, Logged{
    private final ForkliftIO hardware;


    public static Forklift create(){
        return Robot.isReal() ? new Forklift(new RealForklift()) : new Forklift(new SimForklift()); 
    }

    public static Forklift none(){
        return new Forklift(new NoForklift());
    }

    public Forklift(ForkliftIO forklift){
        this.hardware = forklift;
    }

    /***
     * Stows the forklift.
     */
    public void stow(){
        hardware.stow();
    }
    /***
     * Deploys the forklift.
     */
    public void deploy(){
        hardware.deploy();
    }

    @Override
    public void close() throws Exception {
        hardware.close();
    }
}
