package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SparseDistributedRepresentationTest {

    @Test
    public void testSDRCreation() {
        boolean[] inputs = new boolean[2048];
        int expectedResult = 40;

        for (int i = 0; i < expectedResult; i++) {
            inputs[(int) (Math.random() * 2048)] = true;
        }

        SparseDistributedRepresentation sdr = new SparseDistributedRepresentation();
        sdr.setSetOfInputs(inputs);

        assertEquals("Active input count is incorrect", expectedResult, sdr.getActivatedInputs().size());
    }
}
