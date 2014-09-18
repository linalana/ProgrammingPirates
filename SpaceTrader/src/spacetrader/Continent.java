/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author alanalin
 */
public class Continent {
    private String name;
    private String politicalSystem;
    private int techLevel;
    private int resources;
    private Port mainPort;
    
    public Continent(String name, String politicalSystem) {
        this.name = name;
        this.politicalSystem = politicalSystem;
        switch (politicalSystem) {
            
        }
        
        //mainPort = Port(name, techLevel, resources);
    }
}
