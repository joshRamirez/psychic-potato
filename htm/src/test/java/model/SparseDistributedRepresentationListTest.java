package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SparseDistributedRepresentationListTest {

    @Test
    public void testAdd() {
        SparseDistributedRepresentationList SDRList = new SparseDistributedRepresentationList();

        Integer n = 256;
        Integer w = 4;
        Integer theta = 3;
        boolean[] inputs1 = new boolean[n];
        inputs1[54] = true;
        inputs1[41] = true;
        inputs1[123] = true;
        inputs1[204] = true;
        boolean[] inputs2 = new boolean[n];
        inputs2[12] = true;
        inputs2[41] = true;
        inputs2[204] = true;
        inputs2[241] = true;

        SparseDistributedRepresentation sdr1 = new SparseDistributedRepresentation();
        sdr1.setSetOfInputs(inputs1);

        SparseDistributedRepresentation sdr2 = new SparseDistributedRepresentation();
        sdr2.setSetOfInputs(inputs2);

        SDRList.add(sdr1);

        assertEquals("active count position 1 is incorrect", 1, SDRList.getSDRActiveCounts()[41]);
        assertEquals("active count position 2 is incorrect", 1, SDRList.getSDRActiveCounts()[54]);
        assertEquals("active count position 3 is incorrect", 1, SDRList.getSDRActiveCounts()[123]);
        assertEquals("active count position 4 is incorrect", 1, SDRList.getSDRActiveCounts()[204]);
        assertEquals("active count position 5 is incorrect", 0, SDRList.getSDRActiveCounts()[12]);
        assertEquals("active count position 6 is incorrect", 0, SDRList.getSDRActiveCounts()[241]);

        SDRList.add(sdr2);

        assertEquals("active count position 1 is incorrect", 2, SDRList.getSDRActiveCounts()[41]);
        assertEquals("active count position 2 is incorrect", 1, SDRList.getSDRActiveCounts()[54]);
        assertEquals("active count position 3 is incorrect", 1, SDRList.getSDRActiveCounts()[123]);
        assertEquals("active count position 4 is incorrect", 2, SDRList.getSDRActiveCounts()[204]);
        assertEquals("active count position 5 is incorrect", 1, SDRList.getSDRActiveCounts()[12]);
        assertEquals("active count position 6 is incorrect", 1, SDRList.getSDRActiveCounts()[241]);

        boolean[] inputs3 = new boolean[n];
        inputs3[12] = true;
        inputs3[41] = true;
        inputs3[54] = true;
        inputs3[123] = true;
        inputs3[204] = true;
        inputs3[241] = true;
        SparseDistributedRepresentation expectedSDRUnion = new SparseDistributedRepresentation();
        expectedSDRUnion.setSetOfInputs(inputs3);

        for (int i = 0; i < expectedSDRUnion.getSetOfInputs().length; i++) {
            assertEquals("input at " + i + " is incorrect", expectedSDRUnion.isOnOff(i), SDRList.getSDRUnion().isOnOff(i));
        }
    }

}
