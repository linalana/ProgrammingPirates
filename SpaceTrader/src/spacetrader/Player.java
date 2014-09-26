package spacetrader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Player {
    private String name;
    private Ship ship;
    private int fighter;
    private int trader;
    private int engineer;
    private int investor;
    private int money = 1000;

    /**
     *
     * @param aName
     * @param aFighter
     * @param aTrader
     * @param aEngineer
     * @param aInvestor
     */
    public Player(String aName, int aFighter, int aTrader, int aEngineer, int aInvestor) {
        name = aName;
        fighter = aFighter;
        trader = aTrader;
        engineer = aEngineer;
        investor = aInvestor;
        this.ship = new Ship("gnat");
    }
    public String toString() {
        return "Player name: " + name + ". Fighter: " + fighter + ". Trader: " + trader + ". Engineer: " + engineer + ". Investor: " + investor;
    }
    //Getters and setters

    /**
     *
     * Setter for name
     * 
     * @param newName
     */
        public void setName(String newName) {
        name = newName;
    }

    /**
     *
     * Getter for name
     * 
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Setter for Fighter skill points
     * 
     * @param newFighter
     */
    public void setFighter(int newFighter) {
        fighter = newFighter;
    }

    /**
     *
     * Getter for Fighter skill points
     * 
     * @return int fighter
     */
    public int getFighter() {
        return fighter;
    }

    /**
     *
     * Setter for Trader skill points
     * 
     * @param newTrader
     */
    public void setTrader(int newTrader) {
       trader = newTrader;
    }

    /**
     *
     * Getter for Trader skill points
     * 
     * @return int trader
     */
    public int getTrader() {
        return trader;
    }

    /**
     *
     * Setter for Engineer skill points
     * 
     * @param newEngineer
     */
    public void setEngineer(int newEngineer) {
        engineer = newEngineer;
    }

    /**
     *
     * Getter for Engineer skill points
     * 
     * @return int engineer
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     *
     * Setter for Investor skill points
     * 
     * @param newInvestor
     */
    public void setInvestor(int newInvestor) {
        investor = newInvestor;
    }

    /**
     *
     * Getter for Investor skill points
     * 
     * @return int investor
     */
    public int getInvestor() {
        return investor;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @param type the ship to set
     */
    public void setShip(String type) {
        ship.updateShip(type);
    }
}
 
