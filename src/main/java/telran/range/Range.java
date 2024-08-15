package telran.range;

import java.util.Iterator;
import java.util.function.Predicate;

import telran.range.exceptions.OutOfRangeMaxValueExceptions;
import telran.range.exceptions.OutOfRangeMinValueExceptions;

public class Range implements Iterable<Integer> {
    private static final String ERROR_MAX_LESS_OR_EQUAL_MIN = "max less or equal min";
    private int min;
    private int max;
    private Predicate<Integer> predicate;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        int current = min;

        @Override
        public boolean hasNext() {
            while (current <= max && !predicate.test(current)) {
                current++;
            }
            return current <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return current++;
        }

    }

    public static Range getRange(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERROR_MAX_LESS_OR_EQUAL_MIN);
        }
        return new Range(min, max);
    }

    void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
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
