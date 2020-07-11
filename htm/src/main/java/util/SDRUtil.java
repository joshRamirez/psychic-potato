package util;

import model.SparseDistributedRepresentation;

import java.math.BigInteger;

public class SDRUtil {
    // TODO(JOSH): Need to get rid of theta member variable and pass it in. No state in this utility
    private static Integer theta;

    public static boolean isMatch(SparseDistributedRepresentation SDR1, SparseDistributedRepresentation SDR2) {
        if (null == theta || theta > SDR1.getActivatedInputs().size()) {
            theta = SDR1.getActivatedInputs().size();
        }

        if (SDR1.getSetOfInputs().length != SDR2.getSetOfInputs().length || SDR1.getActivatedInputs().size() != SDR2.getActivatedInputs().size()) {
            return false;
        }

        Integer overlap = 0;
        Integer pointer1 = 0;
        Integer pointer2 = 0;

        while (pointer1 < SDR1.getActivatedInputs().size() && pointer2 < SDR1.getActivatedInputs().size()) {
            if (SDR1.getActivatedInputs().get(pointer1).equals(SDR2.getActivatedInputs().get(pointer2))) {
                overlap++;
                pointer1++;
                pointer2++;
            } else if (SDR1.getActivatedInputs().get(pointer1) > SDR2.getActivatedInputs().get(pointer2)) {
                pointer2++;
            } else if (SDR1.getActivatedInputs().get(pointer1) < SDR2.getActivatedInputs().get(pointer2)) {
                pointer1++;
            }
        }

        return overlap >= theta;
    }

    public static Double calculateSparsity(SparseDistributedRepresentation sdr) {
        return Double.valueOf(sdr.getActivatedInputs().size()) / sdr.getSetOfInputs().length;
    }

    /**
     * Follows the formula factorial of total possible positions (of on and off bits) divided by the factorial of on
     * bits times the factorial of off bits.
     *
     * @param sdr sparse distributed representation we want to calculate the total representations for.
     * @return total possible representations the SDR can hold.
     */
    public static BigInteger calculateTotalRepresentations(SparseDistributedRepresentation sdr) {
        BigInteger possiblePositions = factorial(sdr.getSetOfInputs().length);
        BigInteger populatedPositions = factorial(sdr.getActivatedInputs().size()).multiply(factorial(sdr.getSetOfInputs().length - sdr.getActivatedInputs().size()));

        return possiblePositions.divide(populatedPositions);
    }

    /**
     * Calculates total representations using the length and bits passed in as values instead of using an SDR.
     *
     * @param inputLength SDR length (total bits which can be either on or off)
     * @param onBits number of bits that can be on for the SDR
     * @return
     */
    public static BigInteger calculateTotalRepresentations(Integer inputLength, Integer onBits) {
        BigInteger possiblePositions = factorial(inputLength);
        BigInteger populatedPositions = factorial(onBits).multiply(factorial(inputLength - onBits));

        return possiblePositions.divide(populatedPositions);
    }

    private static BigInteger factorial(int number) {
        if (number == 1) {
            return BigInteger.valueOf(1);
        } else {
            return BigInteger.valueOf(number).multiply(factorial(number - 1));
        }
    }

    public static Integer getTheta() {
        return theta;
    }

    public static void setTheta(Integer theta) {
        SDRUtil.theta = theta;
    }
}
