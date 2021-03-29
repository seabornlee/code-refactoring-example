package org.coderead;

import org.coderead.model.Performance;

public interface IPerformanceCalculator {
    double getVolumeCredits(Performance performance);

    double getAmount(Performance performance);
}
