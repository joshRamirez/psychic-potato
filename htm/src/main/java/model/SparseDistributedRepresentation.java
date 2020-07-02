package model;

import java.util.LinkedList;

/**
 * SDR (Sparse Distributed Representation) is a data model which represents a large number of potential inputs with a
 * few activated. Those activated inputs are spread out through the data model. A specific collection of the activated
 * inputs represents a unique value.
 */
public class SparseDistributedRepresentation {
    /**
     * This is the full set of inputs which contains on and off inputs. The combination of active inputs is the unique
     * representation.
     */
    public boolean[] setOfInputs;
    /**
     * Since we have a sparse amount of active inputs, it would be easier to cache these in an array rather than
     * retrieve them from the setOfInputs at runtime.
     */
    private LinkedList<Integer> activatedInputs = new LinkedList<Integer>();

    public SparseDistributedRepresentation() {
    }

    public SparseDistributedRepresentation(Integer numberOfInputs) {
        setOfInputs = new boolean[numberOfInputs];
    }

    public boolean[] getSetOfInputs() {
        return setOfInputs;
    }

    public void setSetOfInputs(boolean[] setOfInputs) {
        this.setOfInputs = setOfInputs;
        for (int i = 0; i < setOfInputs.length; i++) {
            if (setOfInputs[i]) {
                activatedInputs.add(i);
            }
        }
    }

    public boolean isOnOff(Integer position) {
        return setOfInputs[position];
    }

    public void setOnOff(Integer position, boolean isOn) {
        setOfInputs[position] = isOn;
    }

    public LinkedList<Integer> getActivatedInputs() {
        return activatedInputs;
    }

    public void setActivatedInputs(LinkedList activatedInputs) {
        this.activatedInputs = activatedInputs;
    }

}