/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.Serializable;
import spacetrader.engine.ApplicationController;

/**
 *
 * @author alanalin
 */
public class Encounter implements Serializable {
    private final Player p;
    private final Encounterer e;
    private int[] inspectionRecord;

    public Encounter(Player p, Encounterer e) {
        this.p = p;
        this.e = e;
    }

    /**
     * Plays out the choice of the player attacking it's encounterer
     * @return the damage the player does
     */
    public int PlayerAttack() {
        return p.calcDamage();
    }
    /**
     * Damages the encounterer
     * @param damage the damage to do
     * @return true if they live
     */
    public boolean DamageEncounterer(int damage) {
        boolean result = getE().distributeDamage(damage);
        if (result == false) {
            //distributeBooty();
            //you won
        }
        return result;
    }
    /**
     * Plays out the choice of the encouterer attacking player
     * @return whether or not encounter attacks
     */
    public boolean eWillAttack() {
        return e.willFight(p.getReputation());
    }
    /**
     * calculates the damage done by the encounterer
     * @return
     */
    public int EncountererAttack() {
        return getE().calcDamage();

    }
    /**
     * damages the player the given amount
     * @param damage amount of damage
     * @return 0 if player dies, 1 if escaped on lifeboat, and 2 if still living
     */
    public int DamagePlayer(int damage) {
        //algorithm to decide where to do that damage on the players's ship
        return p.distributeDamage(damage);
    }

    /**
     * Finds the what type the encounterer is
     * @return the string type of the encounterer
     */
    public String getType() {
        if (getE().getClass().equals(Pirate.class)) {
            return "Pirate";
        } else if (getE().getClass().equals(Trader.class)) {
            return "Trader";
        } else {
            return "Police Force";
        }
    }

    /**
     * Gets the info from the player and encounterer
     * @return the int array containing all the stats required for fight screen
     */
    public int[] getInfo() {
       int[] info = new int[12];
       int[] pInfo = p.getPlayerInfo();
       int[] otherInfo = e.getEncountererInfo();
       for (int i = 0; i < 12; i++) {
           if (i < 6) {
               info[i] = pInfo[i];
           } else {
               info[i] = otherInfo[i-6];
           }
       }
       return info;
    }
    /**
     * Performs inspection by police on player
     * @return true if passes
     */
    public void Inspection() {
        inspectionRecord = new int[2];
        boolean illegalGoods = p.checkCargo();
        if (illegalGoods){
            inspectionRecord[0] = 1; //true
            inspectionRecord[1] = p.failInspection();
        } else {
            p.passInspection();
        }
        ApplicationController.changeScene("Inspection.fxml");
    }

    /**
     * @return the encounterer
     */
    public Encounterer getE() {
        return e;
    }
    /**
     * collects the health info
     * @return the healths of hull and shields of the player and encounterer
     */
    public double[][] calculateHealth() {
        double[][] healths = new double[2][2];
        healths[0] = p.checkShipHealth();
        healths[1] = e.checkShipHealth();
        return healths;
    }
    /**
     * Determines outcome of encounterer's flee
     */
    private void EncountererFlee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     *
     * @return the inspection record, null if not an inspection
     */
    public int[] getInspection() {
        return inspectionRecord;
    }

}
