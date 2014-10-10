/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;
import java.io.Serializable;
import java.util.Random;

/**
 * A ship class that creates ships
 * @author Murph
 */
public class Ship implements Serializable {
    private CargoHold cargoHold;
    private WeaponHold weaponHold;
    private ShieldHold shieldHold;
    private GadgetHold gadgetHold;
    private String type;
    private int cargoBays;
    private int hullStrength; //from 5(weak) to 25(strong)
    private int weaponSlots;
    private int shieldSlots;
    private int gadgetSlots;
    private int quarters;
    private int maxRange;
    private int fuel;
    private boolean lifeBoat;
    public String[] names = new String[] {"Guppy","Minnow","Snapping Turtle",
        "Pufferfish","StingRay","S.S. Electric Eel","Dolphin Tales","SharkFin",
        "Hammerhead","S.S. Bob Waters"};
    
    public Ship() {
        this(new Random().nextInt(10));
        lifeBoat = false;
    }
    /**
     * Creates a new ship
     * @param type the type of ship
     */
    public Ship(int typeind){
        this.type = names[typeind];
        if(type.equals(names[0])){
            this.cargoBays = 5;
            this.hullStrength = 5;
            this.weaponSlots = 0;
            this.shieldSlots = 0;
            this.gadgetSlots = 0;
            this.quarters = 0;
            this.maxRange = 20;
            
        }
        else if(type.equals(names[1])){
            this.cargoBays = 15;
            this.hullStrength = 5;
            this.weaponSlots = 1;
            this.shieldSlots = 0;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 14;
        }
        else if(type.equals(names[2])){
            this.cargoBays = 20;
            this.hullStrength = 10;
            this.weaponSlots = 1;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 17;
        }
        else if(type.equals(names[3])){
            this.cargoBays = 15;
            this.hullStrength = 15;
            this.weaponSlots = 2;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 13;
        }
        else if(type.equals(names[4])){
            this.cargoBays = 20;
            this.hullStrength = 10;
            this.weaponSlots = 1;
            this.shieldSlots = 2;
            this.gadgetSlots = 2;
            this.quarters = 1;
            this.maxRange = 15;
        }
        else if(type.equals(names[5])){
            this.cargoBays = 50;
            this.hullStrength = 10;
            this.weaponSlots = 0;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 3;
            this.maxRange = 14;
        }
        else if(type.equals(names[6])){
            this.cargoBays = 20;
            this.hullStrength = 15;
            this.weaponSlots = 3;
            this.shieldSlots = 2;
            this.gadgetSlots = 1;
            this.quarters = 2;
            this.maxRange = 16;
        }
        else if(type.equals(names[7])){
            this.cargoBays = 30;
            this.hullStrength = 20;
            this.weaponSlots = 2;
            this.shieldSlots = 2;
            this.gadgetSlots = 3;
            this.quarters = 3;
            this.maxRange = 15;
        }
        else if(type.equals(names[8])){
            this.cargoBays = 60;
            this.hullStrength = 25;
            this.weaponSlots = 1;
            this.shieldSlots = 3;
            this.gadgetSlots = 2;
            this.quarters = 3;
            this.maxRange = 13;
        }
        else{
            this.cargoBays = 35;
            this.hullStrength = 25;
            this.weaponSlots = 3;
            this.shieldSlots = 2;
            this.gadgetSlots = 2;
            this.quarters = 3;
            this.maxRange = 14;
        }
        this.fuel = maxRange;
        this.cargoHold = new CargoHold(getCargoBays());
        this.weaponHold = new WeaponHold(getWeaponSlots());
        this.shieldHold = new ShieldHold(getShieldSlots());
        this.gadgetHold = new GadgetHold(getGadgetSlots());
    }
    /**
     * Changes the ship type, for when you want to upgrade or downgrade
     * @param type the new ship type
     */
    public void updateShip(int typeInd){
        this.type = names[typeInd];
        if(type.equals(names[0])){
            cargoBays = 5;
            hullStrength = 1;
            setWeaponSlots(0);
            setShieldSlots(0);
            setGadgetSlots(0);
            quarters = 0;
            maxRange = 20;
        }
        else if(type.equals(names[1])){
            cargoBays = 15;
            hullStrength = 1;
            setWeaponSlots(1);
            setShieldSlots(0);
            setGadgetSlots(1);
            quarters = 0;
            maxRange = 14;
        }
        else if(type.equals(names[2])){
            cargoBays = 20;
            hullStrength = 2;
            setWeaponSlots(1);
            setShieldSlots(1);
            setGadgetSlots(1);
            quarters = (0);
            maxRange = (17);
        }
        else if(type.equals(names[3])){
            cargoBays = 15;
            hullStrength = 3;
            setWeaponSlots(2);
            setShieldSlots(1);
            setGadgetSlots(1);
            quarters = (0);
            maxRange = (13);
        }
        else if(type.equals(names[4])){
            cargoBays = 20;
            hullStrength = 2;
            setWeaponSlots(1);
            setShieldSlots(2);
            setGadgetSlots(2);
            quarters = (1);
            maxRange = (15);
        }
        else if(type.equals(names[5])){
            cargoBays = 50;
            hullStrength = 2;
            setWeaponSlots(0);
            setShieldSlots(1);
            setGadgetSlots(1);
            quarters = (3);
            maxRange = (14);
        }
        else if(type.equals(names[6])){
            cargoBays = 20;
            hullStrength = 3;
            setWeaponSlots(3);
            setShieldSlots(2);
            setGadgetSlots(1);
            quarters = (2);
            maxRange = (16);
        }
        else if(type.equals(names[7])){
            cargoBays = 30;
            hullStrength = 4;
            setWeaponSlots(2);
            setShieldSlots(2);
            setGadgetSlots(3);
            quarters = (3);
            maxRange = (15);
        }
        else if(type.equals(names[8])){
            cargoBays = 60;
            hullStrength = 5;
            setWeaponSlots(1);
            setShieldSlots(3);
            setGadgetSlots(2);
            quarters = (3);
            maxRange = (13);
        }
        else{
            cargoBays = 35;
            hullStrength = 5;
            setWeaponSlots(3);
            setShieldSlots(2);
            setGadgetSlots(2);
            quarters = (3);
            maxRange = (14);
        }
        
        cargoHold = new CargoHold(getCargoBays());
        weaponHold = new WeaponHold(getWeaponSlots());
        shieldHold = new ShieldHold(getShieldSlots());
        gadgetHold = new GadgetHold(getGadgetSlots());
    }

    /**
     * @return the cargo hold
     */
    public CargoHold getCargoHold() {
        return cargoHold;
    }
    
    /**
     * @return the weapon hold
     */
    public WeaponHold getWeaponHold() {
        return weaponHold;
    }
    
    /**
     * @return the shield hold
     */
    public ShieldHold getShieldHold() {
        return shieldHold;
    }
    
    /**
     * @return the gadget hold
     */
    public GadgetHold getGadgetHold() {
        return gadgetHold;
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
     * @return the hullStrength
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * @return the weaponSlots
     */
    public int getWeaponSlots() {
        return weaponSlots;
    }

    /**
     * @param weaponSlots the weaponSlots to set
     */
    public void setWeaponSlots(int weaponSlots) {
        this.weaponSlots = weaponSlots;
    }

    /**
     * @return the shieldSlots
     */
    public int getShieldSlots() {
        return shieldSlots;
    }

    /**
     * @param shieldSlots the shieldSlots to set
     */
    public void setShieldSlots(int shieldSlots) {
        this.shieldSlots = shieldSlots;
    }

    /**
     * @return the gadgetSlots
     */
    public int getGadgetSlots() {
        return gadgetSlots;
    }

    /**
     * @param gadgetSlots the gadgetSlots to set
     */
    public void setGadgetSlots(int gadgetSlots) {
        this.gadgetSlots = gadgetSlots;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the maxRange
     */
    public int getMaxRange() {
        return maxRange;
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
    /**
     * Gets the damage capable
     * @return int damage
     */
    public int getDamage() {
        return weaponHold.calcTotalDamage();
    }
    /**
     * distributes damage
     * 
     * @param totalDamage, damage to be allocated to parts of ship
     * @return true if ship lives
     */
    boolean distributeDamage(int totalDamage) {
        int remainingDamage = shieldHold.decreaseStrength(totalDamage);
        if (remainingDamage != 0) {
            hullStrength -= remainingDamage;
            if (hullStrength > 0) {
                return true;
            }
        }    
        return false;
    }

    /**
     * @return if has a lifeBoat
     */
    public boolean hasLifeBoat() {
        return lifeBoat;
    }

    /**
     * @param lifeBoat the lifeBoat to set
     */
    public void setLifeBoat(boolean lifeBoat) {
        this.lifeBoat = lifeBoat;
    }

    public int getShieldStrength() {
        return shieldHold.getEnergyStrength() + shieldHold.getReflectiveStrength();
    }

    public int getWeaponStrength() {
        return weaponHold.calcTotalDamage();
    }
    
}
