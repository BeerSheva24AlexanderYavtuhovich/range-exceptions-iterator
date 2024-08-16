package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import telran.range.exceptions.OutOfRangeMaxValueExceptions;
import telran.range.exceptions.OutOfRangeMinValueExceptions;

public class Range implements Iterable<Integer> {
    private static final String ERROR_MAX_LESS_OR_EQUAL_MIN = "max less or equal min";
    private int min;
    private int max;
    private Predicate<Integer> predicate;

    private Range(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERROR_MAX_LESS_OR_EQUAL_MIN);
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int current; 
        
        private RangeIterator() {
            this.current = findFirst();
        }

        private int findFirst() {
            int temp = min;
            while (temp <= max) {
                if (predicate.test(temp)) {
                    return temp;
                }
                temp++;
            }
            return max + 1;
        }


        private int findNext(int start) {
            int temp = start;
            while (temp <= max) {
                if (predicate.test(temp)) {
                    return temp;
                }
                temp++;
            }
            return max + 1; 
        }

        @Override
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer result = current;
            current = findNext(current + 1); 
            return result;
        }
    }

    public void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public static Range getRange(int min, int max) {
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
