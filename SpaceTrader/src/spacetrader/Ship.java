/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;
import java.util.Random;

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
    private int fuel;
    public String[] names = new String[] {"Guppy","Mino","Snapping Turtle",
        "Pufferfish","StingRay","S.S. Electric Eel","Dolphin Tales","SharkFin",
        "Hammerhead","S.S. Bob Waters"};;
    
    public Ship() {
        this(new Random().nextInt(10));
    }
    /**
     * Creates a new ship
     * @param type the type of ship
     */
    public Ship(int typeind){
        
        this.type = names[typeind];
        if(type.equals(names[0])){
            this.cargoBays = 5;
            this.hullStrength = 1;
            this.weapons = 0;
            this.shields = 0;
            this.gadgets = 0;
            this.quarters = 0;
            this.maxRange = 20;
            
        }
        else if(type.equals(names[1])){
            this.cargoBays = 15;
            this.hullStrength = 1;
            this.weapons = 1;
            this.shields = 0;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 14;
        }
        else if(type.equals(names[2])){
            this.cargoBays = 20;
            this.hullStrength = 2;
            this.weapons = 1;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 17;
        }
        else if(type.equals(names[3])){
            this.cargoBays = 15;
            this.hullStrength = 3;
            this.weapons = 2;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 0;
            this.maxRange = 13;
        }
        else if(type.equals(names[4])){
            this.cargoBays = 20;
            this.hullStrength = 2;
            this.weapons = 1;
            this.shields = 2;
            this.gadgets = 2;
            this.quarters = 1;
            this.maxRange = 15;
        }
        else if(type.equals(names[5])){
            this.cargoBays = 50;
            this.hullStrength = 2;
            this.weapons = 0;
            this.shields = 1;
            this.gadgets = 1;
            this.quarters = 3;
            this.maxRange = 14;
        }
        else if(type.equals(names[6])){
            this.cargoBays = 20;
            this.hullStrength = 3;
            this.weapons = 3;
            this.shields = 2;
            this.gadgets = 1;
            this.quarters = 2;
            this.maxRange = 16;
        }
        else if(type.equals(names[7])){
            this.cargoBays = 30;
            this.hullStrength = 4;
            this.weapons = 2;
            this.shields = 2;
            this.gadgets = 3;
            this.quarters = 3;
            this.maxRange = 15;
        }
        else if(type.equals(names[8])){
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
        this.fuel = maxRange;
        this.hold = new CargoHold(getCargoBays());
    }
    /**
     * Changes the ship type, for when you want to upgrade or downgrade
     * @param type the new ship type
     */
    public void updateShip(int typeInd){
        this.type = names[typeInd];
        if(type.equals(names[0])){
            setCargoBays(5);
            setHullStrength(1);
            setWeapons(0);
            setShields(0);
            setGadgets(0);
            setQuarters(0);
            setMaxRange(20);
        }
        else if(type.equals(names[1])){
            setCargoBays(15);
            setHullStrength(1);
            setWeapons(1);
            setShields(0);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(14);
        }
        else if(type.equals(names[2])){
            setCargoBays(20);
            setHullStrength(2);
            setWeapons(1);
            setShields(1);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(17);
        }
        else if(type.equals(names[3])){
            setCargoBays(15);
            setHullStrength(3);
            setWeapons(2);
            setShields(1);
            setGadgets(1);
            setQuarters(0);
            setMaxRange(13);
        }
        else if(type.equals(names[4])){
            setCargoBays(20);
            setHullStrength(2);
            setWeapons(1);
            setShields(2);
            setGadgets(2);
            setQuarters(1);
            setMaxRange(15);
        }
        else if(type.equals(names[5])){
            setCargoBays(50);
            setHullStrength(2);
            setWeapons(0);
            setShields(1);
            setGadgets(1);
            setQuarters(3);
            setMaxRange(14);
        }
        else if(type.equals(names[6])){
            setCargoBays(20);
            setHullStrength(3);
            setWeapons(3);
            setShields(2);
            setGadgets(1);
            setQuarters(2);
            setMaxRange(16);
        }
        else if(type.equals(names[7])){
            setCargoBays(30);
            setHullStrength(4);
            setWeapons(2);
            setShields(2);
            setGadgets(3);
            setQuarters(3);
            setMaxRange(15);
        }
        else if(type.equals(names[8])){
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
    

    public void fillTank() {
        fuel = maxRange;
    }
    public void setFuel(int change) {
        fuel = fuel + change;
        if (fuel > maxRange) {
            fuel = maxRange;
        }
        if (fuel < 0) {
            fuel = 0;
        }
    }
    public int getFuel() {
        return fuel;
    }
    
}
