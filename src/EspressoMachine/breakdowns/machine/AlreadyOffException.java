package EspressoMachine.breakdowns.machine;

public class AlreadyOffException extends RuntimeException {
    public AlreadyOffException(String message) {
        super(message);
    }
}
