package org.coderead;

import org.coderead.model.Performance;

public abstract class AbstractPerformanceCalculator {
    public static AbstractPerformanceCalculator of(String type) {
        if ("tragedy".equals(type)) {
            return new TragedyCalculator();
        }

        if ("comedy".equals(type)) {
            return new ComedyCalculator();
        }

        if ("action".equals(type)) {
            return new ActionCalculator();
        }

        throw new IllegalArgumentException("Invalid type: " + type + ".");
    }

    public abstract double getVolumeCredits(Performance performance);

    public abstract double getAmount(Performance performance);
}
