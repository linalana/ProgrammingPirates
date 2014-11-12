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
    public final boolean isIsPirate() {
        return isPirate;
    }

    /**
     * @param newIsPirate the isPirate to set
     */
    public final void setIsPirate(final boolean newIsPirate) {
        this.isPirate = newIsPirate;
    }

    /**
     * @return the arrestCount
     */
    public final int getArrestCount() {
        return arrestCount;
    }

    /**
     * @return the inspectionHistory
     */
    public final int getInspectionHistory() {
        return inspectionHistory;
    }

    /**
     * Increment InspectionHistory (good job!).
     */
    public final void incrementInspectionHistory() {
        inspectionHistory++;
    }

    /**
     * Decrement InspectionHistory (good job!).
     */
    public final void decrementInspectionHistory() {
        if (inspectionHistory > 0) {
            inspectionHistory--;
        }
    }

}
