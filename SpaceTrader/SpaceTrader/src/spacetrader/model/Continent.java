/*
 * The continent class
 * Holds political, tech, and resource information, shares with Port.
 */

package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alanalin
 * @version 1.0
 */
public class Continent implements Serializable {
    
    private static String[] techLevels = new String[] {"PRE-AGRICULTURE", 
        "AGRICULTURE", "MEDIEVAL", "RENAISSANCE", "EARLY INDUSTRIAL", 
        "INDUSTRIAL", "POST-INDUSTRIAL", "HI-TECH"};
    private static String[] resources = new String[] {"NO SPECIAL RESOURCES", 
        "MINERAL RICH", "MINERAL POOR", "DESERT", "LOTS OF WATER", "RICH SOIL", 
        "POOR SOIL", "RICH FAUNA", "LIFELESS", "WEIRD MUSHROOMS", 
        "LOTS OF HERBS", "ARTISTIC", "WARLIKE"};

    private final String name;
    private final int x;
    private final int y;
    
    private final String politicalSystem;
    private int techLevel;
    private final int resource;
    
    private final Port[] ports;
    private final Port mainPort;
    private final int maxPorts = 10;
    
    /**
     *
     * The constructor for a continent, randomly generates details
     * 
     * @param name the name of the continent
     * @param politicalSystem the political system
     * @param x position
     * @param y position
     */
    public Continent(String name, String politicalSystem, int x, int y) {
        ports = new Port[maxPorts];
        
        this.x = x;
        this.y = y;
        this.name = name;
        this.politicalSystem = politicalSystem;
        setTechLevel();
        //randomly generate a primary resource
        Random rand = new Random();
        resource = rand.nextInt(13);
        
        //create port(s)
        mainPort = new Port(name, getTechLevel(), getResource(), this);
        ports[0] = mainPort;
        
    }
    
    /**
     * Sets the tech level based on the current political system
     */
    private void setTechLevel() {
        Random rand = new Random();
        switch (getPoliticalSystem()) {
            case "anarchy": techLevel = rand.nextInt(5);
                break;
            case "capitalist": techLevel = rand.nextInt(3) + 5;
                break;
            case "communist": techLevel = rand.nextInt(5);
                break;
            case "confederacy": techLevel = rand.nextInt(8);
                break;
            case "corporate": techLevel = rand.nextInt(3) + 5;
                break;
            case "cybernetic": techLevel = 7;
                break;
            case "democracy": techLevel = rand.nextInt(8);
                break;
            case "dictatorship": techLevel = rand.nextInt(8);
                break;
            case "fascist": techLevel = rand.nextInt(4) + 4;
                break;
            case "feudal": techLevel = rand.nextInt(3);
                break;
            case "military": techLevel = rand.nextInt(4) + 4;
                break;
            case "monarchy": techLevel = rand.nextInt(7);
                break;
            case "pacifist": techLevel = rand.nextInt(4);
                break;
            case "socialist": techLevel = rand.nextInt(6);
                break;
            case "satori": techLevel = 1;
                break;
            case "technocracy": techLevel = rand.nextInt(3) + 5;
                break;
            case "theocracy": techLevel = rand.nextInt(4);
                break;
        }
    }
    
    /**
     * Only to be used to ensure home port has tech level 7
     * @param techLevel 
     */
    protected void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
    
    /**
     * @return the resource
     */
    public String getResource() {
        return resources[resource];
    }
    
    
    @Override
    public String toString() {
        String portString = "";
        int i = 0;
        while ( i < maxPorts && ports[i] != null) {
            portString += ports[i].toString();
            if (i != maxPorts - 1 && ports[i + 1] != null) {
                portString += ", ";
            }
            i++;
        }
        return name + " @ (" + getX() + ", " + getY() + ") : " + getPoliticalSystem() + ", Tech: " + techLevels[getTechLevel()] + ", Primary Resource: " + getResource() + ", Ports: " + portString;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the techLevel
     */
    public int getTechLevel() {
        return techLevel;
    }
    
    /**
     * 
     * @return techLevel as a string
     */
    public String getTechLevelString() {
        return techLevels[techLevel].toLowerCase();
    }

    /**
     * @return the mainPort
     */
    public Port getMainPort() {
        return mainPort;
    }

    /**
     * @return the politicalSystem
     */
    public String getPoliticalSystem() {
        return politicalSystem;
    }

    public boolean isClicked(double eventX, double eventY) {
        double dx = eventX - x - 7.5;
        double dy = eventY - y - 7.5;
        return (Math.sqrt(dx*dx + dy*dy) < 7.5);
    }

    public String getName() {
        return name;
    }
}