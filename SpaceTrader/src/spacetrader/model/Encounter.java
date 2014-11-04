package spacetrader.model;

import java.io.Serializable;

/**
 *
 * @author alanalin
 */
public class Encounter implements Serializable {

    final private Player player;
    final private Encounterer encounterer;

    public Encounter(final Player player, final Encounterer encounterer) {
        this.player = player;
        this.encounterer = encounterer;
    }

    /**
     * Runs a "fight" offering choices to the user
     */
    public void engageFight() {

    }

    /**
     * Plays out the choice of the player attacking it's encounterer
     */
    private void playerAttack() {
        final int totalDamage = player.calcDamage();
        if (totalDamage == 0) {
            //tell player they missed
        }
        //algorithm to decide where to do that damage on the encounterer's ship
        final boolean result = encounterer.distributeDamage(totalDamage);
        if (!result) {
            //distributeBooty();
            //you won
        }
    }

    /**
     * Plays out the choice of the encoutnerer attacking player
     */
    private void encountererAttack() {
        final int totalDamage = encounterer.calcDamage();
        //algorithm to decide where to do that damage on the players's ship
        final int result = player.distributeDamage(totalDamage);
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
     *
     * @return the string type of the encounterer
     */
    public String getType() {
        String type;
        if (encounterer.getClass().equals(Pirate.class)) {
            type = "Pirate";
        } else if (encounterer.getClass().equals(Trader.class)) {
            type = "Trader";
        } else {
            type = "Police Force";
        }
        return type;
    }

    /**
     * Gets the info from the player and encounterer
     *
     * @return the int array containing all the stats required for fight screen
     */
    public int[] getInfo() {
        int[] info = new int[12];
        final int[] pInfo = player.getPlayerInfo();
        final int[] otherInfo = encounterer.getEncountererInfo();
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
     * Performs inspection by police on player
     *
     * @return true if passes
     */
    public boolean inspection() {
        final boolean illegalGoods = player.checkCargo();
        if (illegalGoods) {
            player.failInspection();
        }
        return false;
    }

}
