package telran.range.exceptions;

public class OutOfRangeMaxValueExceptions extends Exception {
   private static final String ERROR_OUT_OF_RANGE_MAX = "Out of Range max: %d, number: %d";

   public OutOfRangeMaxValueExceptions(int max, int value) {
      super(String.format(ERROR_OUT_OF_RANGE_MAX, max, value));
   }
}
