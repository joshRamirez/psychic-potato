package util;

import model.SparseDistributedRepresentation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SDRUtilTest {

    @Test
    public void testIsMatch() {
        Integer n = 256;
        Integer w = 4;
        Integer theta = 3;
        boolean[] inputs1 = new boolean[n];
        inputs1[12] = true;
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

        SDRUtil.setTheta(theta);
        assertEquals("SDRs did not match", true, SDRUtil.isMatch(sdr1, sdr2));
    }

    @Test
    public void testIsNotMatch() {
        Integer n = 256;
        Integer w = 4;
        Integer theta = 3;
        boolean[] inputs1 = new boolean[n];
        inputs1[45] = true;
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

        SDRUtil.setTheta(theta);
        assertEquals("SDRs incorrectly matched", false, SDRUtil.isMatch(sdr1, sdr2));
    }
}
