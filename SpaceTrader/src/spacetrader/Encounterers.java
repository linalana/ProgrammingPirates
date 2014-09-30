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
public class Encounterers {
    private int experience;
    private Ship ship;
    
    public Encounterers() {
    Random rand = new Random();
    int deviance = rand.nextInt(2 * 10);
    experience = Game.getPlayer().getExperience() + (deviance - 10);
    ship = new Ship();
    ship.getHold()
    }
    
    private void fillCargo() {
        
    }
}
