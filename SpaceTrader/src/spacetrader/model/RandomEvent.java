package spacetrader.model;

//import spacetrader.model.Game;
//import spacetrader.model.Player;
import java.util.Random;

/**
 * Random Event class.
 *
 * @author Danny
 */
public class RandomEvent {

    private Event[] events;
    private Event event;
    
    private static final int MIN_MONEY_CHANGE = -100;
    private static final int MIN_FUEL_CHANGE = -200;
    private static final int MIN_SHIELD_CHANGE = -10;
    private static final int MAX_SHIELD_CHANGE = -5;
    private static final int MAX_FUEL_CHANGE = 1000;
    private static final int MED_FUEL_CHANGE = -100;
    private static final int MED_MONEY_CHANGE = -10;
    /**
     * Creates event.
     */
    public RandomEvent() {
        initialize();
    }

    /**
     * Decides which event occurs.
     */
    public final void initialize() {
        events = new Event[]{new Event("Kraken Attackin'!",
            "Your ship was attacked by a Kraken! Your fuel and shields are "
                + "severly depleted", 0, MIN_MONEY_CHANGE, MAX_SHIELD_CHANGE),
            new Event("A Storm!", "You encountered a wicked storm! You lost a "
                    + "little of everything in the struggle", MED_FUEL_CHANGE,
                    MED_MONEY_CHANGE, MAX_SHIELD_CHANGE),
            new Event("Sirens", "Beautiful sirens lured you off course! They "
                    + "stole your money and badly damaged your ship!",
                    MIN_FUEL_CHANGE , MED_MONEY_CHANGE, MIN_SHIELD_CHANGE),
            new Event("Buried Treasure!", "You found burried treasure! Have "
                    + "some money!", MAX_FUEL_CHANGE, 0, 0)};
        Random rand = new Random();
        event = events[rand.nextInt(events.length)];
        Game.getPlayer().setMoney(Game.getPlayer().getMoney()
                + event.getMoney());
        Game.getPlayer().getShip().addFuel(event.getFuel());
    }

    /**
     * @return amount of money the event affects
     */
    public final int getMoney() {
        return event.getMoney();
    }

    /**
     * @return the amount of fuel the event affects
     */
    public final int getFuel() {
        return event.getFuel();
    }

    /**
     *
     * @return the amount of damage on the shield the event causes
     */
    public final int getShield() {
        return event.getShield();
    }

    /**
     *
     * @return the title of the event
     */
    public final String getTitle() {
        return event.getTitle();
    }

    /**
     *
     * @return the description of the event
     */
    public final String getDescription() {
        return event.getDescription();
    }

    /**
     * the event class, holds the details for the random event.
     */
    private static class Event {

        private String title;
        private String description;
        private int moneyChange;
        private int fuelChange;
        private int shieldChange;
        /**
         * Event constructor.
         * @param aTitle title of the event
         * @param aDesc description of the even
         * @param mon money change
         * @param fuel fuel change
         * @param shield shield change
         */
        public Event(final String aTitle, final String aDesc,
                     final int mon, final int fuel, final int shield) {
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
