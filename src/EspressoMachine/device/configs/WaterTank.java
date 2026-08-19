package EspressoMachine.device.configs;

import EspressoMachine.breakdowns.extraction.NotEnoughWaterException;

public class WaterTank {
    private final double capacity;
    private double currentLevel;

    public WaterTank(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Water tank capacity must be greater than zero."
            );
        }
        this.capacity = capacity;
        this.currentLevel = capacity;
    }
    public double getCapacity() {
        return capacity;
    }
    public double getCurrentLevel() {
        return currentLevel;
    }
    public void consume(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Water consumption must be greater than zero."
            );
        }
        if (currentLevel < amount) {
            throw new NotEnoughWaterException(
                    "Not enough water in reservoir."
            );
        }
        currentLevel -= amount;
    }
}
