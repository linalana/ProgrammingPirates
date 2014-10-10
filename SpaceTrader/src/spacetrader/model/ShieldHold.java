/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.util.HashMap;
import java.util.Random;

/**
 * A shield hold to hold shields
 * @author Murph
 */
public class ShieldHold {
    private int amount;
    private int totalEnergyShields;
    private int energyStrength;
    private int totalReflectiveShields;
    private int reflectiveStrength;
    private HashMap<Shield, Integer> shields;
    
    /**
     * This is the constructor for ShieldHold.
     * @param amount the amount of shield slots available
     */
    public ShieldHold(int amount){
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
        for (Shield s: Shield.values()) {
            getShields().put(s, 0);
        }   
    }
    
    /**
     * Add shield to your ship
     * @param s the shield type to be added
     * @param q the amount of shield to be added
     */
    public boolean addShield(Shield s, int q) {
        int oldVal = shields.get(s);
        if (totalEnergyShields + totalReflectiveShields + q <= (amount)){
            shields.put(s, oldVal + q);
            switch(s){
                case ENERGY:
                    totalEnergyShields += q;
                    energyStrength += Shield.ENERGY.strength;
                case REFLECTIVE:
                    totalReflectiveShields += q;
                    reflectiveStrength += Shield.REFLECTIVE.strength;
            }
            return true;
        }
        return false;    
    }
    
    /**
     * Subtract shield from your ship
     * @param s the shield type to be subtracted
     * @param q the amount of shield to be subtracted
     */
    public boolean subtractShield(Shield s, int q) {
        int oldVal = shields.get(s);
        if (oldVal - q >= 0){
            shields.put(s, oldVal - q);
            switch(s){
                case ENERGY:
                    totalEnergyShields -= q;
                    energyStrength -= Shield.ENERGY.strength;
                case REFLECTIVE:
                    totalReflectiveShields -= q;
                    reflectiveStrength -= Shield.REFLECTIVE.strength;
            }
            return true;
        }
        return false;    
    }
    
    /**
     * Damage the shields
     * @param damage the amount of damage
     */
    public void decreaseStrength(int damage){
        Random rand = new Random();
        int num = rand.nextInt(1);
        if(num == 0){
            energyStrength -= damage;
            totalEnergyShields = energyStrength%25 + 1;
        }
        else{
            reflectiveStrength -= damage;
            totalReflectiveShields = reflectiveStrength%50 + 1;
        }
    }
    
    /**
     * Recharge the shields to full power
     */
    public void recharge(){
        energyStrength = totalEnergyShields*25;
        reflectiveStrength = totalReflectiveShields*50;
    }

    int distributeDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
