/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;
import java.util.Random;
/**
 *
 * @author alanalin
 */
public class Pirate extends Encounterer {
    
    private int fighterPoints;
    public Pirate() {
        super();
        super.fillCargo();
        allocateSkillPoints();
    }
    
    private void allocateSkillPoints() {
        Random rand = new Random();
        fighterPoints = rand.nextInt(11);
    }

    /**
     * @return the fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }
    

}
