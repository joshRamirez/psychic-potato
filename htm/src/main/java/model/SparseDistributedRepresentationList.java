package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * HTM processes data over time hence the temporal (T in HTM). This classes objective is to create an object that
 * maintains the information about the state of a collection of SDRs. The collection of SDRs would represent the
 * resulting SDR over a time period. The assumption here is that an SDR output would persist in the next levels SDR
 * input for a time period and then leave when that time period is over (this can be due to time or an inhibitory
 * neuron.)
 * <p>
 * This should represent the union property of SDRs.
 */
public class SparseDistributedRepresentationList {
    HashMap<LinkedList<Integer>, SparseDistributedRepresentation> SDRs;
    private SparseDistributedRepresentation SDRUnion;
    private int[] SDRActiveCounts;

    public SparseDistributedRepresentationList() {
        SDRs = new HashMap<LinkedList<Integer>, SparseDistributedRepresentation>();
        SDRUnion = new SparseDistributedRepresentation();
//        SDRActiveCounts = new LinkedList<Integer>();
    }

    public HashMap<LinkedList<Integer>, SparseDistributedRepresentation> getSDRs() {
        return SDRs;
    }

    public void setSDRs(HashMap<LinkedList<Integer>, SparseDistributedRepresentation> SDRs) {
        this.SDRs = SDRs;
    }

    public void add(SparseDistributedRepresentation SDR) {
        if (null == SDRUnion.getSetOfInputs()) {
            SDRActiveCounts = new int[SDR.getSetOfInputs().length];

            SDRUnion = SDR;
        }

        for (int i = 0; i < SDR.getActivatedInputs().size(); i++) {
            SDRActiveCounts[SDR.getActivatedInputs().get(i)] += 1;
            SDRUnion.setOnOff(SDR.getActivatedInputs().get(i), true);
        }
    }

    public int[] getSDRActiveCounts() {
        return SDRActiveCounts;
    }

    public void setSDRActiveCounts(int[] SDRActiveCounts) {
        this.SDRActiveCounts = SDRActiveCounts;
    }

    public SparseDistributedRepresentation getSDRUnion() {
        return SDRUnion;
    }

    public void setSDRUnion(SparseDistributedRepresentation SDRUnion) {
        this.SDRUnion = SDRUnion;
    }

}
