/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanalin
 */
public class Encounter implements Serializable {
    Player p;
    private Encounterer e;
    public Encounter(Player p, Encounterer e) {
        this.p = p;
        this.e = e;
    }

    /**
     * Plays out the choice of the player attacking it's encounterer
     */
    public int PlayerAttack() {
        int totalDamage = p.calcDamage();
        //algorithm to decide where to do that damage on the encounterer's ship
        boolean result = getE().distributeDamage(totalDamage);
        if (result == false) {
            //distributeBooty();
            //you won
        }
        return totalDamage;
    }

    /**
     * Plays out the choice of the encoutnerer attacking player
     * @return whether or not encounter attacks
     */
    public boolean EncountererAttack() {
        if (e.willFight(p.getReputation())) {
            int totalDamage = getE().calcDamage();
            //algorithm to decide where to do that damage on the players's ship
            int result = p.distributeDamage(totalDamage);
            if (result == 0) { //D.E.D dead
                //GAME OVER
            } else if (result == 1) { //life boat escape
                //transfer to nearest port with no ship but a lifeboat
            } else if (result == 2) {
                //continue
            }
            return true;
        }
        return false;
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
    public boolean Inspection() {
        boolean illegalGoods = p.checkCargo();
        if (illegalGoods){
            p.failInspection();
        }
        return false;
    }

    /**
     * @return the e
     */
    public Encounterer getE() {
        return e;
    }

    public double[][] calculateHealth() {
        double[][] healths = new double[2][2];
        healths[0] = p.checkShipHealth();
        healths[1] = e.checkShipHealth();
        return healths;
    }

    private void EncountererFlee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
