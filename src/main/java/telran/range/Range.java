package telran.range;

import telran.range.exceptions.OutOfRangeMaxValueExceptions;
import telran.range.exceptions.OutOfRangeMinValueExceptions;

public class Range {
    private static final String ERROR_MAX_LESS_OR_EQUAL_MIN = "max less or equal min";
    private int min;
    private int max;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static Range getRange(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERROR_MAX_LESS_OR_EQUAL_MIN);
        }
        return new Range(min, max);
    }

    public void checkNumber(int number) throws OutOfRangeMaxValueExceptions, OutOfRangeMinValueExceptions {
        if (number > max) {
            throw new OutOfRangeMaxValueExceptions(max, number);
        }
        if (number < min) {
            throw new OutOfRangeMinValueExceptions(min, number);
        }
    }
}
