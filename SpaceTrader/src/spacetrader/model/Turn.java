package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author James
 */
public class Turn implements Serializable {

    private Continent newContinent;
    private Port newPort;
    //used to determine frequency of different types of random encounters
    private String politicalSystem;
    private int policeChance;
    private int traderChance;
    private int pirateChance;
    private static Encounter encounter;
    private static RandomEvent randomEvent;
    private static final int NUM_PORT_EVENTS = 7;
    /**
     * Constructor for Turn.
     * @param aNewPort the current port.
     */
    public Turn(final Port aNewPort) {
        this.newPort = aNewPort;
        newContinent = aNewPort.getContinent();
        politicalSystem = newContinent.getPoliticalSystem();
        setChanceEncounters();
        randomPortEvents();
    }

    /**
     * travels to a new point and checks if anything exciting occurred.
     *
     * @param fuelUsed to travel
     * @return the result (what happened)
     */
    public final String travel(final int fuelUsed) {
        Random rand = new Random();
        String encounterType = null;
        int randInt = rand.nextInt(100);
        //100% chances filled for what encounter happens
        if (randInt < policeChance) {
            encounter = new Encounter(Game.getPlayer(),
                        new PoliceForce(newContinent.getPoliticalSystem()));
            encounterType = "PoliceForce";
        } else if (randInt > policeChance
                && randInt < (policeChance + traderChance)) {
            encounter = new Encounter(Game.getPlayer(), new Trader());
            encounterType = "Trader";
        } else if (randInt > (policeChance + traderChance)
                && randInt < (policeChance + traderChance + pirateChance)) {
            encounter = new Encounter(Game.getPlayer(), new Pirate());
            encounterType = "Pirate";
        } else {
            randomEvent = new RandomEvent();
        }
        return encounterType;
    }

    /**
     * set chance of encountering various types of people.
     * chances for each political system differ
     */
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
            default:
                policeChance = 40;
                traderChance = 15;
                pirateChance = 5;
                break;
        }
    }

    /**
     * @return the encounter
     */
    public static Encounter getEncounter() {
        return encounter;
    }

    /**
     * decides an event to occur at the port.
     */
    private void randomPortEvents() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            newPort.setEvent(rand.nextInt(NUM_PORT_EVENTS));
        }
        newPort.getBazaar().setGoodsForSale();
    }

    /**
     * @return the title of the random event
     */
    public static String getEventTitle() {
        return randomEvent.getTitle();
    }

    /**
     * @return description of event
     */
    public static String getEventDescription() {
        return randomEvent.getDescription();
    }

}
