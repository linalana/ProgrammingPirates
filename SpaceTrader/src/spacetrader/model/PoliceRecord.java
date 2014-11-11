package spacetrader.model;

import java.io.Serializable;

/**
 *
 * @author alanalin
 */
public class PoliceRecord implements Serializable {

    private boolean isPirate;
    private int arrestCount;

    //goes up with positive inspections and down with failed inspections
    //controls the likelyhood of an inspection
    private int inspectionHistory;
    /**
     * sets up a police record.
     */
    public PoliceRecord() {
        isPirate = false;
        arrestCount = 0;
        inspectionHistory = 0;
    }

    /**
     * @return the isPirate
     */
    public boolean isIsPirate() {
        return isPirate;
    }

    /**
     * @param isPirate the isPirate to set
     */
    public void setIsPirate(final boolean isPirate) {
        this.isPirate = isPirate;
    }

    /**
     * @return the arrestCount
     */
    public int getArrestCount() {
        return arrestCount;
    }

    /**
     * @return the inspectionHistory
     */
    public int getInspectionHistory() {
        return inspectionHistory;
    }

    /**
     * Increment InspectionHistory (good job!).
     */
    public void incrementInspectionHistory() {
        inspectionHistory++;
    }

    /**
     * Decrement InspectionHistory (good job!).
     */
    public void decrementInspectionHistory() {
        if (inspectionHistory > 0) {
            inspectionHistory--;
        }
    }

}
