/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A weapon hold to hold weapons
 * @author Murph
 */
public class WeaponHold implements Serializable {
    private int amount;
    private int totalWeapons;
    private HashMap<Weapon, Integer> weapons;
    
    /**
     * This is the constructor for WeaponHold.
     * @param amount the amount of weapon slots available
     */
    public WeaponHold(int amount){
        this.amount = amount;
        totalWeapons = 0;
        weapons = new HashMap<>();
        setWeapons();
    }
    
    /**
     * @return weapons
     */
    public HashMap<Weapon, Integer> getWeapons() {
        return weapons;
    }
    
    /**
     * Set amount of weapons in weapon hold
     */
    public void setWeapons() {
        for (Weapon w: Weapon.values()) {
            getWeapons().put(w, 0);
        }   
    }
    
    /**
     * Add weapon to your ship
     * @param w the weapon type to be added
     * @param q the amount of weapon to be added
     */
    public boolean addWeapon(Weapon w, int q) {
        int oldVal = weapons.get(w);
        if (totalWeapons + q <= (amount)){
            weapons.put(w, oldVal + q);
            totalWeapons += q;
            return true;
        }
        return false;    
    }
    
    /**
     * Subtract weapon from your ship
     * @param w the weapon type to be subtracted
     * @param q the amount of weapon to be subtracted
     */
    public boolean subtractWeapon(Weapon w, int q) {
        int oldVal = weapons.get(w);
        if (oldVal - q >= 0){
            weapons.put(w, oldVal - q);
            totalWeapons -= q;
            return true;
        }
        return false;    
    }
}
