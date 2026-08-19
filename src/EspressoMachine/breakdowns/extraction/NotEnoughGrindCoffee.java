package EspressoMachine.breakdowns.extraction;

public class NotEnoughGrindCoffee extends RuntimeException {
    public NotEnoughGrindCoffee(String message) {
        super(message);
    }
}
