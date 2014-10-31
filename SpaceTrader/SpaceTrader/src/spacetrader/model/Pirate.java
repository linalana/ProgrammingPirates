/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;
import java.io.Serializable;
import java.util.Random;
/**
 *
 * @author alanalin
 */
public class Pirate extends Encounterer implements Serializable {

    public Pirate() {
        super();
        super.fillCargo();
    }   

}
