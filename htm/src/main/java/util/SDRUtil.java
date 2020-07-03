package util;

import model.SparseDistributedRepresentation;

public class SDRUtil {
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

    public static Integer getTheta() {
        return theta;
    }

    public static void setTheta(Integer theta) {
        SDRUtil.theta = theta;
    }
}
