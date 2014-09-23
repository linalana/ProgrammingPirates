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
    
    static Player player;
    
    /**
     *
     * Constructs a Game
     * 
     * @param p the player
     */
    public Game(Player p){
        player = p;
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
