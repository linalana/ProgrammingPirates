package spacetrader.model;

import java.io.Serializable;

/**
 * This class represents a Port.
 *
 * @author Danny
 */
public class Port implements Serializable {

    private static final String[] EVENTS = new String[]{"DROUGHT", "COLD",
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
     *
     * @param newName the ports new name
     * @param newTechLevel the ports new tech level
     * @param newResources the ports new resources
     * @param aContinent in which the port is located
     */
    public Port(final String newName, final int newTechLevel,
            final String newResources, final Continent aContinent) {
        name = newName;
        techLevel = newTechLevel;
        resources = newResources;
        shipYard = new ShipYard(newTechLevel);
        bazaar = new Bazaar(this);
        this.continent = aContinent;
        policeForce = new PoliceForce(continent.getPoliticalSystem());

    }

    /**
     * This is a getter method for the name of the Port.
     *
     * @return name
     */
    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return getName();
    }

    /**
     * @return the bazaar
     */
    public final Bazaar getBazaar() {
        return bazaar;
    }

    /**
     * @return the techLevel
     */
    public final int getTechLevel() {
        return techLevel;
    }

    /**
     * @return the resources
     */
    public final String getResources() {
        return resources;
    }

    /**
     * @return the resources
     */
    public final ShipYard getShipyard() {
        return shipYard;
    }

    /**
     * @return the event
     */
    public final String getEvent() {
        if (event == -1) {
            return null;
        }
        return EVENTS[event];
    }

    /**
     * @param aEvent the event to set
     */
    public final void setEvent(final int aEvent) {
        this.event = aEvent;
    }

    /**
     * @return the continent the port is in
     */
    public final Continent getContinent() {
        return continent;
    }

    /**
     * @return the policeForce
     */
    public final PoliceForce getPoliceForce() {
        return policeForce;
    }

    /**
     * @param aPoliceForce the policeForce to set
     */
    public final void setPoliceForce(final PoliceForce aPoliceForce) {
        this.policeForce = aPoliceForce;
    }

    /**
     * @param aTechLevel the techLevel to set
     */
    public final void setTechLevel(final int aTechLevel) {
        this.techLevel = aTechLevel;
    }

    /**
     * @param aShipYard the shipYard to set
     */
    public final void setShipYard(final ShipYard aShipYard) {
        this.shipYard = aShipYard;
    }
}
