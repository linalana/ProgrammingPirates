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
    
    public Turn(Port newPort) {
        this.newPort = newPort;
        newContinent = newPort.getContinent();
        politicalSystem = newContinent.getPoliticalSystem();
        techlevel = newPort.getTechLevel();
        doRandomEncounters();
    }
    
    private void doRandomEncounters() {
        
    }
    
    
    
}
