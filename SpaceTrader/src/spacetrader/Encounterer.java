/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.util.Random;

/**
 *
 * @author alanalin
 */
public abstract class Encounterer {
    protected int reputation;
    protected Ship ship;
    
    public Encounterer() {
        Random rand = new Random();
        int deviance = rand.nextInt(2 * 10);
        reputation = Game.getPlayer().getReputation() + (deviance - 10);
        ship = new Ship();
    }
    
    protected void fillCargo() {
        Random rand = new Random();
        for (TradeGood t: TradeGood.values()) {
            if (rand.nextBoolean()) {
                int count = rand.nextInt(5);
                getShip().getHold().addCargo(t, count);
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
    
    
}
