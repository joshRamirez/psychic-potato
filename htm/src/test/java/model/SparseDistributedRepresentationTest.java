package model;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class SparseDistributedRepresentationTest {

    @Test
    public void testSDRCreation() {
        boolean[] inputs = new boolean[2048];
        int expectedResult = 40;
        LinkedList<Integer> previouslyUsed = new LinkedList<Integer>();

        for (int i = 0; i < expectedResult; i++) {
            Integer bit;
            do {
                bit = (int) (Math.random() * 2048);
            } while (previouslyUsed.contains(bit));
            previouslyUsed.add(bit);

            inputs[previouslyUsed.getLast()] = true;
        }

        SparseDistributedRepresentation sdr = new SparseDistributedRepresentation();
        sdr.setSetOfInputs(inputs);

        assertEquals("Active input count is incorrect", expectedResult, sdr.getActivatedInputs().size());
    }
}
