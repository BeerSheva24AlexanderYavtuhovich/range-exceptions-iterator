package telran.range.exceptions;

public class OutOfRangeMinValueExceptions extends Exception {
   private static final String ERROR_OUT_OF_RANGE_MIN = "Out of Range min: %d, number: %d";

   public OutOfRangeMinValueExceptions(int min, int value) {
      super(String.format(ERROR_OUT_OF_RANGE_MIN, min, value));
   }

}
