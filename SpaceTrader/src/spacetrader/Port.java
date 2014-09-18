/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 * This class represents a Port.
 * @author Danny
 */
public class Port {
    private String name;
    private ShipYard shipYard;
    private Bazaar bazaar;
    private int techLevel;
    private int resources;
    
    /**
     * This is the constructor for the Port, it sets all the necessary
     * variables.
     * @param newName
     * @param newTechLevel
     * @param newResources
     */
    public Port(String newName, int newTechLevel, int newResources) {
        name = newName;
        techLevel = newTechLevel;
        resources = newResources;
        shipYard = new ShipYard();
        bazaar = new Bazaar();
        
    }

    /**
     * This is a getter method for the name of the Port.
     * @return name
     */
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return getName();
    }
}
