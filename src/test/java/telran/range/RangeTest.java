package telran.range;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueExceptions;
import telran.range.exceptions.OutOfRangeMinValueExceptions;

public class RangeTest {
    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);

    @Test
    void wrongRangeCreatingTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(MAX, MIN));
    }

    @Test
    void rightNumberTest() throws Exception {
        range.checkNumber(55);
    }

    @Test
    void wrongNumberTest() throws Exception {
        assertThrowsExactly(OutOfRangeMaxValueExceptions.class, () -> range.checkNumber(MAX + 1));
        assertThrowsExactly(OutOfRangeMinValueExceptions.class, () -> range.checkNumber(MIN - 1));
    }
}
