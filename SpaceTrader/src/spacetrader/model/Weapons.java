/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.model;

/**
 *
 * @author Murph
 */
public class Weapons {
    private int pulseAmount;
    private int beamAmount;
    private int militaryAmount;
    
    public Weapons(){
        this.pulseAmount = 0;
        this.beamAmount = 0;
        this.militaryAmount = 0;
    }

    /**
     * @return the pulseAmount
     */
    public int getPulseAmount() {
        return pulseAmount;
    }

    /**
     * @param pulseAmount the pulseAmount to set
     */
    public void setPulseAmount(int pulseAmount) {
        this.pulseAmount = pulseAmount;
    }

    /**
     * @return the beamAmount
     */
    public int getBeamAmount() {
        return beamAmount;
    }

    /**
     * @param beamAmount the beamAmount to set
     */
    public void setBeamAmount(int beamAmount) {
        this.beamAmount = beamAmount;
    }

    /**
     * @return the militaryAmount
     */
    public int getMilitaryAmount() {
        return militaryAmount;
    }

    /**
     * @param militaryAmount the militaryAmount to set
     */
    public void setMilitaryAmount(int militaryAmount) {
        this.militaryAmount = militaryAmount;
    }
}
