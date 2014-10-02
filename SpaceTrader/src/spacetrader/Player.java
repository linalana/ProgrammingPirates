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

    private int posX;
    private int posY;

    private int experience;
    private boolean pirate;
    private int policeRecord;


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
        this.ship = new Ship(0);

        posX = 0;
        posY = 0;

        experience = 0;
        pirate = false;
        policeRecord = 0;

    }
    @Override
    public String toString() {
        return "Player name: " + name + ". Fighter: " + fighter + ". Trader: " + trader + ". Engineer: " + engineer + ". Investor: " + investor;
    }
    
    public void attack(Encounterer e) {
        
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
    public void setShip(int type) {
        ship.updateShip(type);
    }

    
    /**
     * @param newPosX the new x position
     * @param mewPosY the new y position
     */
    public void setLocation(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
    }
    /**
     * 
     * @return an array of ints, the first is the x position and the second is 
     * the y position.
     */
    public int[] getLocation() {
        return new int[] {posX, posY};
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * @param exp experience to be added
     */
    public void addExperience(int exp) {
        experience += exp;
    }

    /**
     * @return the pirate
     */
    public boolean isPirate() {
        return pirate;
    }

    /**
     * @param pirate the pirate to set
     */
    public void setPirate(boolean pirate) {
        this.pirate = pirate;
    }

    /**
     * @return the policeRecord
     */
    public int getPoliceRecord() {
        return policeRecord;
    }

    /**
     * add arrest
     */
    public void incrementPoliceRecord() {
        this.policeRecord++;

    }
}
 
