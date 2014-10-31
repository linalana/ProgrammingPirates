
package spacetrader.model;

import java.io.Serializable;

/**
 *
 * @author alanalin
 */
public class Encounter implements Serializable {
    Player p;
    Encounterer e;
    public Encounter(Player p, Encounterer e) {
        this.p = p;
        this.e = e;
    }
    
    /**
     * Runs a "fight" offering choices to the user
     */
    public void engageFight() {
        
    }
    
    /**
     * Plays out the choice of the player attacking it's encounterer
     */
    private void PlayerAttack() {
        int totalDamage = p.calcDamage();
        if (totalDamage == 0) {
            //tell player they missed
        }
        //algorithm to decide where to do that damage on the encounterer's ship
        boolean result = e.distributeDamage(totalDamage);
        if (result == false) {
            //distributeBooty();
            //you won
        }
    }
    
    /**
     * Plays out the choice of the encoutnerer attacking player
     */
    private void EncountererAttack() {
        int totalDamage = e.calcDamage();
        //algorithm to decide where to do that damage on the players's ship
        int result = p.distributeDamage(totalDamage);
        if (result == 0) { //D.E.D dead
            //GAME OVER
        } else if (result == 1) { //life boat escape
            //transfer to nearest port with no ship but a lifeboat
        } else if (result == 2) {
            //continue
        }
    }
    
    /**
     * Finds the what type the encounterer is
     * @return the string type of the encounterer
     */
    public String getType() {
        if (e.getClass().equals(Pirate.class)) {
            return "Pirate";
        } else if (e.getClass().equals(Trader.class)) {
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
    
    
}
