package spacetrader.model;

//import spacetrader.model.Game;
//import spacetrader.model.Player;
import java.util.Random;

/**
 * Random Event class
 *
 * @author Danny
 */
public class RandomEvent {

    private Event[] events;
    private Event event;

    /**
     * Creates event
     */
    public RandomEvent() {
        initialize();
    }

    /**
     * Decides which event occurs
     */
    public void initialize() {
        events = new Event[]{new Event("Kraken Attackin'!",
            "Your ship was attacked by a Kraken! Your fuel and shields are severly depleted",
            0, -5, -5),
            new Event("A Storm!", "You encountered a wicked storm! You lost a little of everything in the struggle",
            -100, -10, -5),
            new Event("Sirens", "Beautiful sirens lured you off course! They stole your money and badly damaged your ship! Typical...",
            -200, -10, -10),
            new Event("Burried Treasure!", "You found burried treasure! Have some money!", 1000, 0, 0)};
        Random rand = new Random();
        event = events[rand.nextInt(events.length)];
        Game.getPlayer().setMoney(Game.getPlayer().getMoney() + event.getMoney());
        Game.getPlayer().getShip().addFuel(event.getFuel());
    }

    /**
     * @return amount of money the event affects
     */
    public int getMoney() {
        return event.getMoney();
    }

    /**
     * @return the amount of fuel the event affects
     */
    public int getFuel() {
        return event.getFuel();
    }

    /**
     *
     * @return the amount of damage on the shield the event causes
     */
    public int getShield() {
        return event.getShield();
    }

    /**
     *
     * @return the title of the event
     */
    public String getTitle() {
        return event.getTitle();
    }

    /**
     *
     * @return the description of the event
     */
    public String getDescription() {
        return event.getDescription();
    }

    /**
     * the event class, holds the details for the random event
     */
    private class Event {

        private String title;
        private String description;
        private int moneyChange;
        private int fuelChange;
        private int shieldChange;

        public Event(String aTitle, String aDesc, int mon, int fuel, int shield) {
            title = aTitle;
            description = aDesc;
            moneyChange = mon;
            fuelChange = fuel;
            shieldChange = shield;
        }

        /**
         *
         * @return money change
         */
        public int getMoney() {
            return moneyChange;
        }

        /**
         *
         * @return fuel change
         */
        public int getFuel() {
            return fuelChange;
        }

        /**
         *
         * @return shield strength change
         */
        public int getShield() {
            return shieldChange;
        }

        /**
         * @return title of event
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @return description of event
         */
        public String getDescription() {
            return description;
        }
    }
}
