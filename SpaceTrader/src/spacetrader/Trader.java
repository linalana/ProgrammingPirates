/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author alanalin
 */
public class Trader extends Encounterer {
    
    public Trader() {
        super();
    }
    
    @Override
    public boolean willFight(int playerXP, int fightpoints) {
        return false;
    }
    
    
    
}
