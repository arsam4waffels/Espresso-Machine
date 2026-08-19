package EspressoMachine.breakdowns.machine;

public class AlreadyOnException extends RuntimeException {
    public AlreadyOnException(String message) {
        super(message);
    }
}
