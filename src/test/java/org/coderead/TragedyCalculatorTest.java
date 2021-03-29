package org.coderead;

import org.coderead.model.Performance;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class TragedyCalculatorTest {

    @Test
    public void should_get_credits() {
        TragedyCalculator tragedyCalculator = new TragedyCalculator();
        Performance performance = new Performance();
        performance.setAudience(40);
        double volumeCredits = tragedyCalculator.getVolumeCredits(performance);
        assertThat(volumeCredits, equalTo(10.0));
    }
}