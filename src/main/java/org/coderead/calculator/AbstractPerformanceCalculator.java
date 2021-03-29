package org.coderead.calculator;

import com.sun.xml.internal.ws.util.StringUtils;
import org.coderead.model.Performance;

public abstract class AbstractPerformanceCalculator {
    public static AbstractPerformanceCalculator of(String type) {
        try {
            return (AbstractPerformanceCalculator) Class.forName(getPackage() +
                    "." +
                    toClassName(type)).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type: " + type + ".");
        }
    }

    private static String getPackage() {
        return AbstractPerformanceCalculator.class.getPackage().getName();
    }

    private static String toClassName(String type) {
        return StringUtils.capitalize(type) + "Calculator";
    }

    public abstract double getVolumeCredits(Performance performance);

    public abstract double getAmount(Performance performance);
}
