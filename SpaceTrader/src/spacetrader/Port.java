/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author Michael
 */
public class Port {
    private String name;
    private ShipYard shipYard;
    private Bazaar bazaar;
    private int techLevel;
    private int resources;
    
    public Port(String newName, int newTechLevel, int newResources) {
        name = newName;
        techLevel = newTechLevel;
        resources = newResources;
        shipYard = new ShipYard();
        bazaar = new Bazaar();
        
    }
}
