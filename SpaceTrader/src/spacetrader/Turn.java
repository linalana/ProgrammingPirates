/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author James
 */
public class Turn {
    
    private Continent newContinent;
    private Port newPort;
    private int distance;
    
    public Turn(Port newPort) {
        this.newPort = newPort;
        newContinent = newPort.getContinent();
    }
    
    
}
