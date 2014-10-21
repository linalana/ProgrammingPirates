/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.model;

import java.io.Serializable;

/**
 * This class represents a Ship Yard, where a Player can go to buy and upgrade
 * ships.
 * @author Danny
 */
public class ShipYard implements Serializable {
    
    private Ship[] ships = new Ship[10];
    
    /**
     * The constructor for ShipYard. 
     */
    public ShipYard(int techLevel) {
        for (int i = 0; i < (techLevel + 3); i++) {
            ships[i] = new Ship(i);
        }
    }
    
    public Ship[] getShips() {
        return ships;
    }
}
