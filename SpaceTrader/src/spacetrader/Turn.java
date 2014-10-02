/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import java.util.Random;

/**
 *
 * @author James
 */
public class Turn {
    
    private Continent newContinent;
    private Port newPort;
    private int distance;
    //used to determine frequency of different types of random encounters
    private String politicalSystem;
    private int techlevel;
    private int policeChance;
    private int traderChance;
    private int pirateChance;
    
    public Turn(Port newPort) {
        this.newPort = newPort;
        newContinent = newPort.getContinent();
        politicalSystem = newContinent.getPoliticalSystem();
        techlevel = newPort.getTechLevel();
        setChanceEncounters();
    }
    
    public void travel(int fuelUsed) {
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        if (randInt < policeChance) {
            //police encounter
        } else if (randInt > policeChance
                   && randInt < (policeChance + traderChance)) {
            //trader encounter
        } else if (randInt > (policeChance + traderChance)
                   && randInt < (policeChance + traderChance + pirateChance)) {
            //pirate encounter
        }
        //set new current port
        Game.setCurrentPort(newPort);
        //deduct fuel from ship based on distance traveled
        Game.getPlayer().getShip().setFuel(-fuelUsed);
    }
    
    private void setChanceEncounters() {
        switch (politicalSystem) {
            case "anarchy": 
                policeChance = 0;
                traderChance = 5;
                pirateChance = 50;
                break;
            case "capitalist":
                policeChance = 10;
                traderChance = 50;
                pirateChance = 10;
                break;
            case "communist":
                policeChance = 50;
                traderChance = 5;
                pirateChance = 20;
                break;
            case "confederacy":
                policeChance = 30;
                traderChance = 25;
                pirateChance = 20;
                break;
            case "corporate":
                policeChance = 40;
                traderChance = 35;
                pirateChance = 15;
                break;
            case "cybernetic":
                policeChance = 40;
                traderChance = 5;
                pirateChance = 40;
                break;
            case "democracy":
                policeChance = 20;
                traderChance = 35;
                pirateChance = 20;
                break;
            case "dictatorship":
                policeChance = 30;
                traderChance = 15;
                pirateChance = 30;
                break;
            case "fascist":
                policeChance = 60;
                traderChance = 5;
                pirateChance = 5;
                break;
            case "feudal":
                policeChance = 10;
                traderChance = 10;
                pirateChance = 50;
                break;
            case "military":
                policeChance = 50;
                traderChance = 25;
                pirateChance = 0;
                break;
            case "monarchy":
                policeChance = 25;
                traderChance = 25;
                pirateChance = 20;
                break;
            case "pacifist":
                policeChance = 10;
                traderChance = 40;
                pirateChance = 5;
                break;
            case "socialist":
                policeChance = 10;
                traderChance = 15;
                pirateChance = 40;
                break;
            case "satori":
                policeChance = 5;
                traderChance = 5;
                pirateChance = 5;
                break;
            case "technocracy":
                policeChance = 30;
                traderChance = 35;
                pirateChance = 10;
                break;
            case "theocracy":
                policeChance = 40;
                traderChance = 15;
                pirateChance = 5;
                break;
        }
    }
    
    
    
}
