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
    private int fighter;
    private int trader;
    private int engineer;
    private int investor;
    public Player(String aName, int aFighter, int aTrader, int aEngineer, int aInvestor) {
        name = aName;
        fighter = aFighter;
        trader = aTrader;
        engineer = aEngineer;
        investor = aInvestor;
    }

    //Getters and setters
    public void setName(String newName) {
        name = newName;
    }
    public String getName() {
        return name;
    }
    public void setFighter(int newFighter) {
        fighter = newFighter;
    }
    public int getFighter() {
        return fighter;
    }
    public void setTrader(int newTrader) {
        fighter = newTrader;
    }
    public int getTrader() {
        return trader;
    }
    public void setEngineer(int newEngineer) {
        fighter = newEngineer;
    }
    public int getEngineer() {
        return engineer;
    }
    public void setInvestor(int newInvestor) {
        fighter = newInvestor;
    }
    public int getInvestor() {
        return investor;
    }
}
 
