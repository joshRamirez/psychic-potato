package util;

import model.SparseDistributedRepresentation;
import org.junit.Test;

import java.math.BigInteger;

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

    @Test
    public void testCalculateTotalRepresentations() {
        Integer n = 16;
        Integer w = 2;
        Integer theta = 1;
        boolean[] inputs1 = new boolean[n];
        inputs1[1] = true;
        inputs1[12] = true;

        SparseDistributedRepresentation sdr1 = new SparseDistributedRepresentation();
        sdr1.setSetOfInputs(inputs1);

        BigInteger expected = BigInteger.valueOf(120);

        assertEquals("Total possible representations are not correct for n = 16 and w 2", expected, SDRUtil.calculateTotalRepresentations(sdr1));

        Integer n2 = 256;
        Integer w2 = 4;
        Integer theta2 = 3;
        boolean[] inputs2 = new boolean[n2];
        inputs2[12] = true;
        inputs2[41] = true;
        inputs2[123] = true;
        inputs2[204] = true;

        SparseDistributedRepresentation sdr2 = new SparseDistributedRepresentation();
        sdr2.setSetOfInputs(inputs2);

        BigInteger expected2 = BigInteger.valueOf(174792640);

        assertEquals("Total possible representations are not correct for n = 256 and w = 4", expected2, SDRUtil.calculateTotalRepresentations(sdr2));
    }

    @Test
    public void testCalculateTotalRepresentationsNoSDR() {
        Integer n = 16;
        Integer w = 2;
        Integer theta = 1;
        BigInteger expected = BigInteger.valueOf(120);

        assertEquals("Total possible representations are not correct for n = 16 and w 2", expected, SDRUtil.calculateTotalRepresentations(n, w));

        Integer n2 = 256;
        Integer w2 = 4;
        Integer theta2 = 3;
        BigInteger expected2 = BigInteger.valueOf(174792640);

        assertEquals("Total possible representations are not correct for n = 256 and w = 4", expected2, SDRUtil.calculateTotalRepresentations(n2, w2));
    }

    @Test
    public void testCalculateSparsity() {
        Integer n = 256;
        Integer w = 4;
        Integer theta = 3;
        boolean[] inputs = new boolean[n];
        inputs[12] = true;
        inputs[41] = true;
        inputs[123] = true;
        inputs[204] = true;

        SparseDistributedRepresentation sdr = new SparseDistributedRepresentation();
        sdr.setSetOfInputs(inputs);

        Double expected = 0.015625;

        assertEquals("Sparsity is not correct for n = 256 and w = 4", expected, SDRUtil.calculateSparsity(sdr), .0001);
    }
}
