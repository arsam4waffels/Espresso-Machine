package EspressoMachine.breakdowns.machine;

public class MachineIsBrewingException extends RuntimeException {
    public MachineIsBrewingException(String message) {
        super(message);
    }
}
