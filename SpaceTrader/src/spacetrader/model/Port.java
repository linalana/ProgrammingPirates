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
     * @param continent in which the port is located
     */
    public Port(final String newName, final int newTechLevel,
            final String newResources, final Continent continent) {
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
     * @param event the event to set
     */
    public final void setEvent(final int event) {
        this.event = event;
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
     * @param policeForce the policeForce to set
     */
    public final void setPoliceForce(final PoliceForce policeForce) {
        this.policeForce = policeForce;
    }

    /**
     * @param techLevel the techLevel to set
     */
    public final void setTechLevel(final int techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * @param shipYard the shipYard to set
     */
    public final void setShipYard(ShipYard shipYard) {
        this.shipYard = shipYard;
    }
}
