package EspressoMachine.breakdowns.extraction;

public class CoffeeWeightExceededException extends RuntimeException {
    public CoffeeWeightExceededException(String message) {
        super(message);
    }
}
