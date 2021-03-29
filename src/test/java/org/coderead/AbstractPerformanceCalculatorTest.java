package org.coderead;

import org.coderead.calculator.AbstractPerformanceCalculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AbstractPerformanceCalculatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_throw_exception_when_play_type_not_exist() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid type: not-exist-type.");
        AbstractPerformanceCalculator.of("not-exist-type");
    }
}