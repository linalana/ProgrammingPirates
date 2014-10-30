/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a Ship Yard, where a Player can go to buy and upgrade
 * ships.
 * @author Danny
 */
public class ShipYard implements Serializable {
    
    private Ship[] ships = new Ship[10];
    private HashMap<Gadget, int[]> gadgetsForSale;
    private int techLevel;
    
    /**
     * The constructor for ShipYard. 
     * @param techLevel
     */
    public ShipYard(int techLevel) {
        this.techLevel = techLevel;
        for (int i = 0; i < (techLevel + 3); i++) {
            ships[i] = new Ship(i);
        }
        gadgetsForSale = new HashMap<>();
        setGadgetsForSale();
    }
    
    /**
     *
     * Getter for ships in shipyard
     * 
     * @return Ship[] of ships
     */
    public Ship[] getShips() {
        return ships;
    }
    
    /**
     * @return the gadgetsForSale
     */
    public HashMap<Gadget, int[]> getGadgetsForSale() {
        return gadgetsForSale;
    }

    /**
     * Calculate price and quantity
     */
    public void setGadgetsForSale() {
        for (Gadget g: Gadget.values()) {
            gadgetsForSale.put(g, new int[] {g.CalculatePrice(techLevel), g.CalculateSellQuantity(techLevel)});
        }   
    }
    
    /**
     *
     * Update the quantity (input positive quantity for adding, negative for subtracting)
     * @param g the Gadget in question
     * @param q the quantity changed (positive or negative)
     * @return
     */
    public boolean updateGadgetQuantity(Gadget g, int q) {
        
        int[] oldVals = gadgetsForSale.get(g);
        if (oldVals[1] + q > 1) {
            gadgetsForSale.put(g, new int[]{oldVals[0], oldVals[1] + q});
            return true;
        }
        return false;    
    }
}
