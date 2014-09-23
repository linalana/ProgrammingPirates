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
    
    private static final String[] events = new String[] {"DROUGHT", "COLD", 
    "CROPFAIL", "WAR", "BOREDOM", "PLAGUE", "LACKOFWORKERS"};
        
    private String name;
    private ShipYard shipYard;
    private Bazaar bazaar;
    private int techLevel;
    private String resources;
    private int event;

    
    /**
     * This is the constructor for the Port, it sets all the necessary
     * variables.
     * @param newName
     * @param newTechLevel
     * @param newResources
     */
    public Port(String newName, int newTechLevel, String newResources) {
        name = newName;
        techLevel = newTechLevel;
        resources = newResources;
        shipYard = new ShipYard();
        bazaar = new Bazaar(getTechLevel());
        
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

    /**
     * @return the bazaar
     */
    public Bazaar getBazaar() {
        return bazaar;
    }

    /**
     * @return the techLevel
     */
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * @return the resources
     */
    public String getResources() {
        return resources;
    }

    /**
     * @return the event
     */
    public String getEvent() {
        return events[event];
    }

    /**
     * @param event the event to set
     */
    public void setEvent(int event) {
        this.event = event;
    }
}
