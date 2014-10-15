package spacetrader.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

//import spacetrader.model.Game;
//import spacetrader.model.Player;
import java.util.Random;


/**
 *
 * @author Danny
 */
public class RandomEvent implements Serializable {
    private Player player;
    private Event[] events;
    private Event event;

    public RandomEvent() {
        player = Game.getPlayer();
        initialize();
    }
    
    
    public void initialize() {
        events = new Event[]{new Event("Kraken Attackin'!",  
        "Your ship was attacked by a Kraken! Your fuel and shields are severly depleted",
        0, -100, -5),
        new Event("A Storm!","You encountered a wicked storm! You lost a little of everything in the struggle",
        -100, -10, -5),
        new Event("Sirens","Beautiful sirens lured you off course! They stole your money and badly damaged your ship! Typical...",
        -200, -10, -10), 
        new Event("Burried Treasure!","You found burried treasure! Have some money!", 1000, 0,0)};
        Random rand = new Random();
        event = events[rand.nextInt(events.length)];
        Game.getPlayer().setMoney(Game.getPlayer().getMoney() + event.getMoney());
        Game.getPlayer().getShip().setFuel(event.getFuel());
    }
        public int getMoney() {
            return event.getMoney();
        }
        public int getFuel() {
            return event.getFuel();
        }
        public int getShield() {
            return event.getShield();
        }
        public String getTitle() {
            return event.getTitle();
        }
        public String getDescription() {
            return event.getDescription();
        }
    private class Event{
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
        public int getMoney() {
            return moneyChange;
        }
        public int getFuel() {
            return fuelChange;
        }
        public int getShield() {
            return shieldChange;
        }
        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }
    }
}
    
