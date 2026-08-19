package EspressoMachine.breakdowns.machine;

public class MachineIsOffException extends RuntimeException {
    public MachineIsOffException(String message) {
        super(message);
    }
}
