package spacetrader.model;

import java.io.Serializable;

/**
 *
 * @author Murph
 */
public class Game implements Serializable {

    private static Player player;
    private static World world;
    private static Port currentPort;

    /**
     *
     * Constructs a Game.
     *
     * @param p the player
     */
    public Game(final Player p) {
        player = p;
        world = new World();
        currentPort = world.getContinents()[0].getMainPort();
    }

    /**
     * @return the currentPort
     */
    public static Port getCurrentPort() {
        return currentPort;
    }

    /**
     * @param aCurrentPort the currentPort to set
     */
    public static void setCurrentPort(final Port aCurrentPort) {
        currentPort = aCurrentPort;
    }

    /**
     *
     * Getter for player.
     *
     * @return player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     *
     * @return the world
     */
    public static World getWorld() {
        return world;
    }

    /**
     *
     * @param aWorld the world
     */
    public static void setWorld(final World aWorld) {
        world = aWorld;
    }

    /**
     *
     * @return the player's ship
     */
    public static Ship getShip() {
        return getPlayer().getShip();
    }

    /**
     *
     * @return the ship's shieldHold
     */
    public static ShieldHold getShieldHold() {
        return getPlayer().getShieldHold();
    }
}
