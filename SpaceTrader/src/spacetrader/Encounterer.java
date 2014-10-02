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
public class Encounterer {
    private int experience;
    private Ship ship;
    
    public Encounterer() {
    Random rand = new Random();
    int deviance = rand.nextInt(2 * 10);
    experience = Game.getPlayer().getExperience() + (deviance - 10);
    ship = new Ship();
    fillCargo();
    }
    
    private void fillCargo() {
        Random rand = new Random();
        for (TradeGood t: TradeGood.values()) {
            if (rand.nextBoolean()) {
                int count = rand.nextInt(5);
                ship.getHold().addCargo(t, count);
            }
        }
    }
    /**
     * Decide if Encounterer will fight or flee
     * 
     * @param playerXP the exp of the player
     * @param fightPoints the fighter skill points of the player
     * @return true if will fight
     */
    public boolean willFight(int playerXP, int fightPoints) {
        return (playerXP <= experience);
        //take into account fighter skill points
    }
    
    public int attack() {
        Random rand = new Random();
        return rand.nextInt(10) * experience / 100;
    }
    
    
}
