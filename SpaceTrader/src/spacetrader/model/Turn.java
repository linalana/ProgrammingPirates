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
    private int distance;
    //used to determine frequency of different types of random encounters
    private String politicalSystem;
    private int techlevel;
    private int policeChance;
    private int traderChance;
    private int pirateChance;
    private static Encounter encounter;
    private static RandomEvent randomEvent;

    public Turn(Port newPort) {
        this.newPort = newPort;
        newContinent = newPort.getContinent();
        politicalSystem = newContinent.getPoliticalSystem();
        techlevel = newPort.getTechLevel();
        setChanceEncounters();
        randomPortEvents();
    }

    /**
     * travels to a new point and checks if anything exciting occurred
     *
     * @param fuelUsed to travel
     * @return the result (what happened)
     */
    public String travel(int fuelUsed) {
        String event = null;
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        if (randInt < policeChance) {
            encounter = new Encounter(Game.getPlayer(), new PoliceForce(newContinent.getPoliticalSystem()));
            event = "PoliceForce";
        } else if (randInt > policeChance
                && randInt < (policeChance + traderChance)) {
            encounter = new Encounter(Game.getPlayer(), new Trader());
            event = "Trader";
        } else if (randInt > (policeChance + traderChance)
                && randInt < (policeChance + traderChance + pirateChance)) {
            encounter = new Encounter(Game.getPlayer(), new Pirate());
            event = "Pirate";
        }
        randomEvent = new RandomEvent();
        return event;
    }

    /**
     * set chance of encountering various types of people
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
            case "theocracy":
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
     * decides an event to occur at the port
     */
    private void randomPortEvents() {
        Random rand = new Random();
        if (rand.nextDouble() > 0.5) {
            newPort.setEvent(rand.nextInt(7));
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
