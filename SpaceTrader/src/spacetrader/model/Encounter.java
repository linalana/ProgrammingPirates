package spacetrader.model;

import java.io.Serializable;

/**
 *
 * @author alanalin
 */
public class Encounter implements Serializable {

    private Player p;
    private Encounterer e;
    /**
     * Encounter constructor.
     * @param newP a player
     * @param newE an encounterer
     */
    public Encounter(final Player newP, final Encounterer newE) {
        this.p = newP;
        this.e = newE;
    }

    /**
     * Runs a "fight" offering choices to the user.
     */
    public void engageFight() {

    }

    /**
     * Plays out the choice of the player attacking it's encounterer.
     */
    private void playerAttack() {
        int totalDamage = p.calcDamage();
        if (totalDamage == 0) {
            //this is a filler statement
            int filler = 0;
            //tell player they missed
        }
        //algorithm to decide where to do that damage on the encounterer's ship
        boolean result = e.distributeDamage(totalDamage);
        if (!result) {
            //this is a filler statement
            int filler = 0;
            //distributeBooty();
            //you won
        }
    }

    /**
     * Plays out the choice of the encounterer attacking player.
     */
    private void encountererAttack() {
        int totalDamage = e.calcDamage();
        //algorithm to decide where to do that damage on the players's ship
        int result = p.distributeDamage(totalDamage);
        if (result == 0) { //D.E.D dead
            //GAME OVER
            //this is a filler statement
            int filler = 0;
        } else if (result == 1) { //life boat escape
            //transfer to nearest port with no ship but a lifeboat
            //this is a filler statement
            int filler = 0;
        } else if (result == 2) {
            //this is a filler statement
            int filler = 0;
            //continue
        }
    }

    /**
     * Finds the what type the encounterer is.
     *
     * @return the string type of the encounterer
     */
    public final String getType() {
        String type;
        if (e.getClass().equals(Pirate.class)) {
            type = "Pirate";
        } else if (e.getClass().equals(Trader.class)) {
            type = "Trader";
        } else {
            type = "Police Force";
        }
        return type;
    }

    /**
     * Gets the info from the player and encounterer.
     *
     * @return the int array containing all the stats required for fight screen
     */
    public final int[] getInfo() {
        //12 is the number of stats needed - only used once
        int[] info = new int[12];
        int[] pInfo = p.getPlayerInfo();
        int[] otherInfo = e.getEncountererInfo();
        for (int i = 0; i < 12; i++) {
            if (i < 6) {
                info[i] = pInfo[i];
            } else {
                info[i] = otherInfo[i - 6];
            }
        }
        return info;
    }

    /**
     * Performs inspection by police on player.
     *
     * @return true if passes
     */
    public final boolean inspection() {
        boolean illegalGoods = p.checkCargo();
        if (illegalGoods) {
            p.failInspection();
        }
        return false;
    }

}
