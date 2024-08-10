package org.sciborgs1155.robot.forklift;

import monologue.Logged;

public interface ForkliftIO extends AutoCloseable, Logged{

    /***
     * Stows the forklift.
     */
    public void stow();

    /***
     * Deploys the forklift.
     */
    public void deploy();

    /***
     * Returns if the forklift is properly deployed
     * @return Whether the forklift is successfully deployed.
     */
    public boolean deployed();
}
