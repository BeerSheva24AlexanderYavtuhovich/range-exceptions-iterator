package telran.range;

import telran.range.exceptions.OutOfRangeMaxValueExceptions;
import telran.range.exceptions.OutOfRangeMinValueExceptions;

public class RangeProcessor {
    private Range range;
    private int counterOfGreatherMax;
    private int counterOfLessMin;
    private int counterOfRightNumbers;

    public RangeProcessor(Range range) {
        this.range = range;
    }

    public void processNumber(int number) {
        try {
            range.checkNumber(number);
            counterOfRightNumbers++;
        } catch (OutOfRangeMaxValueExceptions e) {
            counterOfGreatherMax++;
        } catch (OutOfRangeMinValueExceptions e) {
            counterOfLessMin++;
        }
    }

    public int getCounterOfGreatherMax() {
        return counterOfGreatherMax;
    }

    public int getCounterOfLessMin() {
        return counterOfLessMin;
    }

    public int getCounterOfRightNumbers() {
        return counterOfRightNumbers;
    }

}
