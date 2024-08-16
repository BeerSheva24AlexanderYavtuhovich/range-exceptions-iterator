package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

    @Test
    void iteratorTest() {
        // Range
        final int MAX = 36;
        final int MIN = 1;
        Range rangeMath = Range.getRange(MIN, MAX);

        // Arrays
        Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        Integer[] evens = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36 };
        Integer[] odds = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35 };
        Integer[] fibonacci = { 1, 2, 3, 5, 8, 13, 21, 34 };
        Integer[] squares = { 1, 4, 9, 16, 25, 36 };
        Integer[] multiplesOfSeven = { 7, 14, 21, 28, 35 };
        Integer[] powersOfTwo = { 1, 2, 4, 8, 16, 32 };

        // Predicates
        Predicate<Integer> primePredicate = n -> {
            boolean res = true;
            if (n <= 1)
                res = false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0)
                    res = false;
            }
            return res;
        };
        Predicate<Integer> evenPredicate = n -> n % 2 == 0;
        Predicate<Integer> oddsPredicate = n -> n % 2 != 0;
        Predicate<Integer> fibonacciPredicate = n -> isFibonacciNumber(n, MAX);
        Predicate<Integer> squaresPredicate = n -> n >= 0 && Math.sqrt(n) % 1 == 0;
        Predicate<Integer> multiplesOfSevenPredicate = n -> n % 7 == 0;
        Predicate<Integer> powersOfTwoPredicate = n -> n > 0 && (n & (n - 1)) == 0;

        // primesTesting
        rangeMath.setPredicate(primePredicate);
        Iterator<Integer> primeIterator = rangeMath.iterator();
        Integer[] actualPrimes = arrayCollector(primes.length, primeIterator);
        assertArrayEquals(primes, actualPrimes);
        assertThrowsExactly(NoSuchElementException.class, primeIterator::next);

        // evensTesting
        rangeMath.setPredicate(evenPredicate);
        Iterator<Integer> evenIterator = rangeMath.iterator();
        Integer[] actualEvens = arrayCollector(evens.length, evenIterator);
        assertArrayEquals(evens, actualEvens);
        assertThrowsExactly(NoSuchElementException.class, evenIterator::next);

        // odsTesting
        rangeMath.setPredicate(oddsPredicate);
        Iterator<Integer> oddsIterator = rangeMath.iterator();
        Integer[] actualOdds = arrayCollector(odds.length, oddsIterator);
        assertArrayEquals(odds, actualOdds);
        assertThrowsExactly(NoSuchElementException.class, oddsIterator::next);

        // fibonacciTesting
        Iterator<Integer> fibonacciIterator = rangeMath.iterator();
        rangeMath.setPredicate(fibonacciPredicate);
        Integer[] actualFibonacci = arrayCollector(fibonacci.length, fibonacciIterator);
        assertArrayEquals(fibonacci, actualFibonacci);
        assertThrowsExactly(NoSuchElementException.class, fibonacciIterator::next);

        // squaresTesting
        Iterator<Integer> squaresIterator = rangeMath.iterator();
        rangeMath.setPredicate(squaresPredicate);
        Integer[] actualSquares = arrayCollector(squares.length, squaresIterator);
        assertArrayEquals(squares, actualSquares);
        assertThrowsExactly(NoSuchElementException.class, squaresIterator::next);

        // multiplesOfSevenTesting
        rangeMath.setPredicate(multiplesOfSevenPredicate);
        Iterator<Integer> multiplesOfSevenIterator = rangeMath.iterator();
        Integer[] actualMultiplesOfSeven = arrayCollector(multiplesOfSeven.length, multiplesOfSevenIterator);
        assertArrayEquals(multiplesOfSeven, actualMultiplesOfSeven);
        assertThrowsExactly(NoSuchElementException.class, multiplesOfSevenIterator::next);

        // powersOfTwoTesting
        rangeMath.setPredicate(powersOfTwoPredicate);
        Iterator<Integer> powersOfTwoIterator = rangeMath.iterator();
        Integer[] actualPowersOfTwo = arrayCollector(powersOfTwo.length, powersOfTwoIterator);
        assertArrayEquals(powersOfTwo, actualPowersOfTwo);
        assertThrowsExactly(NoSuchElementException.class, powersOfTwoIterator::next);

    }

    private Integer[] arrayCollector(int expectedArrayLength, Iterator<Integer> iterator) {
        Integer[] actual = new Integer[expectedArrayLength];
        int index = 0;
        while (iterator.hasNext()) {
            actual[index++] = iterator.next();
        }
        return actual;
    }

    private static boolean isFibonacciNumber(int number, int limit) {
        boolean isFibonacci = false;
        if (number < 0)
            isFibonacci = false;
        if (number == 0)
            isFibonacci = true;
        int a = 0;
        int b = 1;

        while (b <= number && b <= limit && !isFibonacci) {
            if (b == number) {
                isFibonacci = true;
            }
            int next = a + b;
            a = b;
            b = next;
        }
        return isFibonacci;
    }
}
