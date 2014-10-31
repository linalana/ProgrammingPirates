
package spacetrader.model;

import java.io.Serializable;

/**
 * This class represents a Port.
 * @author Danny
 */
public class Port implements Serializable {
    
    private static final String[] events = new String[] {"DROUGHT", "COLD", 
    "CROPFAIL", "WAR", "BOREDOM", "PLAGUE", "LACKOFWORKERS"};
        
    private String name;
    private ShipYard shipYard;
    private Bazaar bazaar;
    private int techLevel;
    private String resources;
    private int event = -1;
    private Continent continent;
    private PoliceForce policeForce;

    
    /**
     * This is the constructor for the Port, it sets all the necessary
     * variables.
     * @param newName
     * @param newTechLevel
     * @param newResources
     * @param continent in which the port is located
     */
    public Port(String newName, int newTechLevel, String newResources, 
                Continent continent) {
        name = newName;
        techLevel = newTechLevel;
        resources = newResources;
        shipYard = new ShipYard(newTechLevel);
        bazaar = new Bazaar(this);
        this.continent = continent;
        policeForce = new PoliceForce(continent.getPoliticalSystem());

        
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
     * @return the resources
     */
    public ShipYard getShipyard() {
        return shipYard;
    }

    /**
     * @return the event
     */
    public String getEvent() {
        if (event == -1) {
            return null;
        }
        return events[event];
    }

    /**
     * @param event the event to set
     */
    public void setEvent(int event) {
        this.event = event;
    }

    /**
     * @return the continent the port is in
     */
    public Continent getContinent() {
        return continent;
    }

    /**
     * @return the policeForce
     */
    public PoliceForce getPoliceForce() {
        return policeForce;
    }

    /**
     * @param policeForce the policeForce to set
     */
    public void setPoliceForce(PoliceForce policeForce) {
        this.policeForce = policeForce;
    }
}
