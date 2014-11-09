package spacetrader.model;

import java.io.Serializable;
import java.util.Random;

/**
 * A ship class that creates ships
 *
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
    private int price;
    private boolean lifeBoat;
    public String[] names = new String[]{"Guppy", "Minnow", "Snapping Turtle",
        "Pufferfish", "StingRay", "S.S. Electric Eel", "Dolphin Tales", "SharkFin",
        "Hammerhead", "S.S. Bob Waters"};

    public Ship() {
        this(new Random().nextInt(10));
        lifeBoat = false;
    }

    /**
     * Creates a new ship
     *
     * @param type the type of ship
     */
    public Ship(int typeind) {
        this.type = names[typeind];
        if (type.equals(names[0])) {
            this.cargoBays = 5;
            this.hullStrength = 5;
            this.weaponSlots = 0;
            this.shieldSlots = 0;
            this.gadgetSlots = 0;
            this.quarters = 0;
            this.maxRange = 20;
            this.price = 5000;

        } else if (type.equals(names[1])) {
            this.cargoBays = 15;
            this.hullStrength = 5;
            this.weaponSlots = 1;
            this.shieldSlots = 0;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 14;
            this.price = 10000;
        } else if (type.equals(names[2])) {
            this.cargoBays = 20;
            this.hullStrength = 10;
            this.weaponSlots = 1;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 17;
            this.price = 15000;
        } else if (type.equals(names[3])) {
            this.cargoBays = 15;
            this.hullStrength = 15;
            this.weaponSlots = 2;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 0;
            this.maxRange = 13;
            this.price = 20000;
        } else if (type.equals(names[4])) {
            this.cargoBays = 20;
            this.hullStrength = 10;
            this.weaponSlots = 1;
            this.shieldSlots = 2;
            this.gadgetSlots = 2;
            this.quarters = 1;
            this.maxRange = 15;
            this.price = 25000;
        } else if (type.equals(names[5])) {
            this.cargoBays = 50;
            this.hullStrength = 10;
            this.weaponSlots = 0;
            this.shieldSlots = 1;
            this.gadgetSlots = 1;
            this.quarters = 3;
            this.maxRange = 14;
            this.price = 30000;
        } else if (type.equals(names[6])) {
            this.cargoBays = 20;
            this.hullStrength = 15;
            this.weaponSlots = 3;
            this.shieldSlots = 2;
            this.gadgetSlots = 1;
            this.quarters = 2;
            this.maxRange = 16;
            this.price = 35000;
        } else if (type.equals(names[7])) {
            this.cargoBays = 30;
            this.hullStrength = 20;
            this.weaponSlots = 2;
            this.shieldSlots = 2;
            this.gadgetSlots = 3;
            this.quarters = 3;
            this.maxRange = 15;
            this.price = 40000;
        } else if (type.equals(names[8])) {
            this.cargoBays = 60;
            this.hullStrength = 25;
            this.weaponSlots = 1;
            this.shieldSlots = 3;
            this.gadgetSlots = 2;
            this.quarters = 3;
            this.maxRange = 13;
            this.price = 45000;
        } else {
            this.cargoBays = 35;
            this.hullStrength = 25;
            this.weaponSlots = 3;
            this.shieldSlots = 2;
            this.gadgetSlots = 2;
            this.quarters = 3;
            this.maxRange = 14;
            this.price = 50000;
        }
        this.fuel = maxRange;
        this.cargoHold = new CargoHold(getCargoBays());
        this.weaponHold = new WeaponHold(getWeaponSlots());
        this.shieldHold = new ShieldHold(getShieldSlots());
        this.gadgetHold = new GadgetHold(getGadgetSlots());
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
     * @return the weapon hold
     */
    public int getPrice() {
        return price;
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

    /**
     * fill fuel to max
     */
    public void fillTank() {
        fuel = maxRange;
    }

    /**
     * adds the change in fuel to the total fuel
     *
     * @param change in fuel
     */
    public void addFuel(int change) {
        fuel = fuel + change;
        if (fuel > maxRange) {
            fuel = maxRange;
        }
        if (fuel < 0) {
            fuel = 0;
        }
    }

    /**
     * @return amount of fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Gets the damage capable
     *
     * @return int damage
     */
    public int getDamage() {
        return getWeaponHold().calcTotalDamage();
    }

    /**
     * distributes damage
     *
     * @param totalDamage, damage to be allocated to parts of ship
     * @return true if ship lives
     */
    boolean distributeDamage(int totalDamage) {
        int remainingDamage = getShieldHold().decreaseStrength(totalDamage);
        if (remainingDamage != 0) {
            hullStrength -= remainingDamage;
            if (hullStrength > 0) {
                return true;
            }
            return false;
        }
        return true;
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

    /**
     * @return total strength of shields
     */
    public int getShieldStrength() {
        return getShieldHold().getEnergyStrength() + getShieldHold().getReflectiveStrength();
    }

    /**
     * @return total possible damage of weapons
     */
    public int getWeaponStrength() {
        return getWeaponHold().calcTotalDamage();
    }

    /**
     * checks for narcotics and fire arms
     *
     * @return true if cargohold contains those items
     */
    public boolean checkHoldForIllegal() {
        return getCargoHold().findIllegal();
    }

    /**
     * removes illegal goods from cargohold
     */
    void removeIllegalGoods() {
        getCargoHold().removeIllegal();
    }

    /**
     * calculates the sell price of the ship and contained goods
     *
     * @return total value of ship and contents
     */
    public int calculateValue() {
        int v = price;
        for (TradeGood g : getCargoHold().getGoods().keySet()) {
            int goodQuantity = getCargoHold().getGoods().get(g);
            if (Game.getCurrentPort().getTechLevel() > g.getMTLU()) {
                double goodPrice = 0.8 * goodQuantity * g.calculatePrice(Game.getCurrentPort());
                v = v + (int) goodPrice;
            }
        }
        for (Gadget ga : getGadgetHold().getGadgets().keySet()) {
            int gadgetQuantity = getGadgetHold().getGadgets().get(ga);
            double gadgetPrice = 0.8 * gadgetQuantity * ga.calculatePrice(Game.getCurrentPort().getTechLevel());
            v = v + (int) gadgetPrice;
        }
        for (Shield s : getShieldHold().getShields().keySet()) {
            int shieldQuantity = getShieldHold().getShields().get(s);
            double shieldPrice = 0.8 * shieldQuantity * s.calculatePrice(Game.getCurrentPort());
            v = v + (int) shieldPrice;
        }
        for (Weapon w : getWeaponHold().getWeapons().keySet()) {
            int weaponQuantity = getWeaponHold().getWeapons().get(w);
            double weaponPrice = 0.8 * weaponQuantity * w.calculatePrice(Game.getCurrentPort().getTechLevel());
            v = v + (int) weaponPrice;
        }

        return v;
    }

    /**
     * @param cargoHold the cargoHold to set
     */
    public void setCargoHold(CargoHold cargoHold) {
        this.cargoHold = cargoHold;
    }

    /**
     * @param weaponHold the weaponHold to set
     */
    public void setWeaponHold(WeaponHold weaponHold) {
        this.weaponHold = weaponHold;
    }

    /**
     * @param shieldHold the shieldHold to set
     */
    public void setShieldHold(ShieldHold shieldHold) {
        this.shieldHold = shieldHold;
    }

    /**
     * @param gadgetHold the gadgetHold to set
     */
    public void setGadgetHold(GadgetHold gadgetHold) {
        this.gadgetHold = gadgetHold;
    }

    /**
     * @param maxRange the maxRange to set
     */
    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

}
