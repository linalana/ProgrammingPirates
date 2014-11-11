package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * A shield hold to hold shields.
 *
 * @author Murph
 */
public class ShieldHold implements Serializable {

    private int amount;
    private int totalEnergyShields;
    private int energyStrength;
    private int totalReflectiveShields;
    private int reflectiveStrength;
    private HashMap<Shield, Integer> shields;

    /**
     * This is the constructor for ShieldHold.
     *
     * @param amount the amount of shield slots available
     */
    public ShieldHold(int amount) {
        this.amount = amount;
        this.totalEnergyShields = 0;
        this.totalReflectiveShields = 0;
        this.energyStrength = 0;
        this.reflectiveStrength = 0;
        shields = new HashMap<>();
        setShields();
    }

    /**
     * @return shields
     */
    public HashMap<Shield, Integer> getShields() {
        return shields;
    }

    /**
     * Set amount of shields in shield hold
     */
    public void setShields() {
        for (Shield s : Shield.values()) {
            getShields().put(s, 0);
        }
    }

    /**
     * Add shield to your ship
     *
     * @param s the shield type to be added
     * @param q the amount of shield to be added
     */
    public boolean addShield(Shield s, int q) {
        int oldVal = shields.get(s);
        if (totalEnergyShields + totalReflectiveShields + q <= (amount)) {
            shields.put(s, oldVal + q);
            switch (s) {
                case ENERGY:
                    totalEnergyShields += q;
                    energyStrength += Shield.ENERGY.strength;
                    break;
                case REFLECTIVE:
                    totalReflectiveShields += q;
                    reflectiveStrength += Shield.REFLECTIVE.strength;
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * Subtract shield from your ship
     *
     * @param s the shield type to be subtracted
     * @param q the amount of shield to be subtracted
     */
    public boolean subtractShield(Shield s, int q) {
        int oldVal = shields.get(s);
        if (oldVal - q >= 0) {
            shields.put(s, oldVal - q);
            switch (s) {
                case ENERGY:
                    totalEnergyShields -= q;
                    energyStrength -= Shield.ENERGY.strength;
                    break;
                case REFLECTIVE:
                    totalReflectiveShields -= q;
                    reflectiveStrength -= Shield.REFLECTIVE.strength;
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * Damage the shields
     *
     * @param damage the amount of damage
     * @return remaining damage
     */
    public int decreaseStrength(int damage) {
        
        if (totalEnergyShields > 0) {
            int strength = getEnergyStrength();
            if (strength <= damage) {
                energyStrength = 0;
                totalEnergyShields = 0;
                damage -= strength;
            } else {
                energyStrength -= damage;
                damage = 0;
                totalEnergyShields = getEnergyStrength() % 25 + 1;
            }
        } else if (totalReflectiveShields > 0) {
            int strength = getReflectiveStrength();
            if (getReflectiveStrength() <= damage) {
                reflectiveStrength = 0;
                totalReflectiveShields = 0;
                damage -= strength;
            } else {
                reflectiveStrength -= damage;
                damage = 0;
                totalReflectiveShields = getReflectiveStrength() % 50 + 1;
            }
        }
        return damage;
    }

    /**
     * Recharge the shields to full power
     */
    public void recharge() {
        energyStrength = totalEnergyShields * 25;
        reflectiveStrength = totalReflectiveShields * 50;
    }

    /**
     * @return the energyStrength
     */
    public int getEnergyStrength() {
        return energyStrength;
    }

    /**
     * @return the reflectiveStrength
     */
    public int getReflectiveStrength() {
        return reflectiveStrength;
    }

    /**
     *
     * @return amount of reflective shields
     */
    public int getTotalReflectiveShields() {
        return totalReflectiveShields;
    }

    /**
     *
     * @return amount of energy shields
     */
    public int getTotalEnergyShields() {
        return totalEnergyShields;
    }

    /**
     * @return the total number of shields the shieldhold can hold
     */
    public int getMaxCapacity() {
        return amount;
    }

    /**
     * @return the total number of shields the shieldhold is holding
     */
    public int getEmptySlots() {
        return amount - (totalReflectiveShields + totalEnergyShields);
    }
}
