/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.util.Random;

/**
 *
 * @author alanalin
 */
public class Trader extends Encounterer {
    private int traderPoints;
    
    public Trader() {
        super();
        super.fillCargo();
        allocateSkillPoints();
    }
    public boolean willEncounter(boolean playerIsPirate) {
        return !playerIsPirate;
    }
    
    private void allocateSkillPoints() {
        Random rand = new Random();
        traderPoints = rand.nextInt(11);
    }

    /**
     * @return the traderPoints
     */
    public int getTraderPoints() {
        return traderPoints;
    }
    
    
   
}