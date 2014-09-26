/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 * A ship class that creates ships
 * @author Murph
 */
public class Ship {
    private CargoHold hold;
    private String type;
    private int cargoBays;
    private int hullStrength; //from 1(weak) to 5(strong)
    private int weapons;
    private int shields;
    private int gadgets;
    private int quarters;
    private int maxRange;
    
    /**
     * Creates a new ship
     * @param type the type of ship
     */
    public Ship(String type){
        this.type = type;
        if(type.equals("flea")){
            this.cargoBays = 5;
            this.hullStrength = 1;
            this.weapons = 0;
            this.shields = 0;
            this.gadgets = 0;
            this.quarters = 0;
            this.maxRange = 20;
        }
        else if(type.equals("gnat")){
            this.cargoBays = 15;
            this.hullStrength = 1;
            this.weapons = 1;
            this.shields = 0;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 14;
        }
        else if(type.equals("firefly")){
            this.cargoBays = 20;
            this.hullStrength = 2;
            this.weapons = 1;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 17;
        }
        else if(type.equals("mosquito")){
            this.cargoBays = 15;
            this.hullStrength = 3;
            this.weapons = 2;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 13;
        }
        else if(type.equals("bumblebee")){
            this.cargoBays = 20;
            this.hullStrength = 2;
            this.weapons = 1;
            this.shields = 2;
            this.gadgets = 2;
            this.quarters = 1;
            this.maxRange = 15;
        }
        else if(type.equals("beetle")){
            this.cargoBays = 50;
            this.hullStrength = 2;
            this.weapons = 0;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 3;
            this.maxRange = 14;
        }
        else if(type.equals("hornet")){
            this.cargoBays = 20;
            this.hullStrength = 3;
            this.weapons = 3;
            this.shields = 2;
            this.gadgets = 1;
            this.quarters = 2;
            this.maxRange = 16;
        }
        else if(type.equals("grasshopper")){
            this.cargoBays = 30;
            this.hullStrength = 4;
            this.weapons = 2;
            this.shields = 2;
            this.gadgets = 3;
            this.quarters = 3;
            this.maxRange = 15;
        }
        else if(type.equals("termite")){
            this.cargoBays = 60;
            this.hullStrength = 5;
            this.weapons = 1;
            this.shields = 3;
            this.gadgets = 2;
            this.quarters = 3;
            this.maxRange = 13;
        }
        else{
            this.cargoBays = 35;
            this.hullStrength = 5;
            this.weapons = 3;
            this.shields = 2;
            this.gadgets = 2;
            this.quarters = 3;
            this.maxRange = 14;
        }
        
        this.hold = new CargoHold(getCargoBays());
    }
    
    /**
     * Changes the ship type, for when you want to upgrade or downgrade
     * @param type the new ship type
     */
    public void updateShip(String type){
        if(type.equals("flea")){
            setCargoBays(5);
            setHullStrength(1);
            setWeapons(0);
            setShields(0);
            setGadgets(0);
            setQuarters(0);
            setMaxRange(20);
        }
        else if(type.equals("gnat")){
            setCargoBays(15);
            setHullStrength(1);
            setWeapons(1);
            setShields(0);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(14);
        }
        else if(type.equals("firefly")){
            setCargoBays(20);
            setHullStrength(2);
            setWeapons(1);
            setShields(1);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(17);
        }
        else if(type.equals("mosquito")){
            setCargoBays(15);
            setHullStrength(3);
            setWeapons(2);
            setShields(1);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(13);
        }
        else if(type.equals("bumblebee")){
            setCargoBays(20);
            setHullStrength(2);
            setWeapons(1);
            setShields(2);
            setGadgets(2);
            setQuarters(1);
            setMaxRange(15);
        }
        else if(type.equals("beetle")){
            setCargoBays(50);
            setHullStrength(2);
            setWeapons(0);
            setShields(1);
            setGadgets(1);
            setQuarters(3);
            setMaxRange(14);
        }
        else if(type.equals("hornet")){
            setCargoBays(20);
            setHullStrength(3);
            setWeapons(3);
            setShields(2);
            setGadgets(1);
            setQuarters(2);
            setMaxRange(16);
        }
        else if(type.equals("grasshopper")){
            setCargoBays(30);
            setHullStrength(4);
            setWeapons(2);
            setShields(2);
            setGadgets(3);
            setQuarters(3);
            setMaxRange(15);
        }
        else if(type.equals("termite")){
            setCargoBays(60);
            setHullStrength(5);
            setWeapons(1);
            setShields(3);
            setGadgets(2);
            setQuarters(3);
            setMaxRange(13);
        }
        else{
            setCargoBays(35);
            setHullStrength(5);
            setWeapons(3);
            setShields(2);
            setGadgets(2);
            setQuarters(3);
            setMaxRange(14);
        }
        
        hold = new CargoHold(getCargoBays());
    }

    /**
     * @return the hold
     */
    public CargoHold getHold() {
        return hold;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the cargoBays
     */
    public int getCargoBays() {
        return cargoBays;
    }

    /**
     * @param cargoBays the cargoBays to set
     */
    public void setCargoBays(int cargoBays) {
        this.cargoBays = cargoBays;
        hold.setAmount(cargoBays);
    }

    /**
     * @return the hullStrength
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * @param hullStrength the hullStrength to set
     */
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    /**
     * @return the weapons
     */
    public int getWeapons() {
        return weapons;
    }

    /**
     * @param weapons the weapons to set
     */
    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }

    /**
     * @return the shields
     */
    public int getShields() {
        return shields;
    }

    /**
     * @param shields the shields to set
     */
    public void setShields(int shields) {
        this.shields = shields;
    }

    /**
     * @return the gadgets
     */
    public int getGadgets() {
        return gadgets;
    }

    /**
     * @param gadgets the gadgets to set
     */
    public void setGadgets(int gadgets) {
        this.gadgets = gadgets;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @param quarters the quarters to set
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * @return the maxRange
     */
    public int getMaxRange() {
        return maxRange;
    }

    /**
     * @param maxRange the maxRange to set
     */
    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }
    
    
}
