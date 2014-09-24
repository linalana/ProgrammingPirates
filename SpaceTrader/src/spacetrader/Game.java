/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author Murph
 */
public class Game {
    
    private static Player player;
    private static World world;
    private static Port currentPort;
    
    /**
     *
     * Constructs a Game
     * 
     * @param p the player
     */
    public Game(Player p){
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
    public static void setCurrentPort(Port aCurrentPort) {
        currentPort = aCurrentPort;
    }
    
    /**
     *
     * Getter for player
     * 
     * @return player
     */
    public static Player getPlayer() {
        return player;
    }
}
