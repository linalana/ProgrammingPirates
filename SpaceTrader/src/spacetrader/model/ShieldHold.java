/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.util.HashMap;

/**
 * A shield hold to hold shields
 * @author Murph
 */
public class ShieldHold {
    private int amount;
    private int totalShields;
    private HashMap<Shield, Integer> shields;
    
    /**
     * This is the constructor for ShieldHold.
     * @param amount the amount of shield slots available
     */
    public ShieldHold(int amount){
        this.amount = amount;
        totalShields = 0;
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
        if (totalShields + q <= (amount)){
            shields.put(s, oldVal + q);
            totalShields += q;
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
            totalShields -= q;
            return true;
        }
        return false;    
    }

    int distributeDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}