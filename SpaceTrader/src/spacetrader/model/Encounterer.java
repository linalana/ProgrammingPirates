/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 */
public abstract class Encounterer implements Serializable {
    protected int reputation;
    protected Ship ship;
    protected int fighterPoints;
    
    public Encounterer() {
        Random rand = new Random();
        int deviance = rand.nextInt(2 * 10);
        reputation = Game.getPlayer().getReputation() + (deviance - 10);
        ship = new Ship();
        fighterPoints = rand.nextInt(11);
    }
    
    protected void fillCargo() {
        Random rand = new Random();
        for (TradeGood t: TradeGood.values()) {
            if (rand.nextBoolean()) {
                int count = rand.nextInt(5);
                getShip().getCargoHold().addCargo(t, count);
            }
        }
    }
    /**
     * Decide if Encounterer will fight or flee
     * 
     * @param playerRep the exp of the player
     * @param fightPoints the fighter skill points of the player
     * @return true if will fight
     */
    public boolean willEncounter(int playerRep, int fightPoints) {
        return (playerRep <= getReputation());
        //take into account fighter skill points
        //NOTE: we might have to move this method into the individual trader/pirate/police classes
    }
    
    public int attack() {
        Random rand = new Random();
        return rand.nextInt(10) * getReputation() / 100;
    }

    /**
     * @return the reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }
    
    /**
     * @return the fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }
    /**
     * @return the fighterPoints
     */
    public int getTraderPoints() {
        return 0;
    }
    
    
    
}
