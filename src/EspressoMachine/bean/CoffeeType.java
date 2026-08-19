package EspressoMachine.bean;

import EspressoMachine.breakdowns.extraction.CoffeeWeightExceededException;
import EspressoMachine.breakdowns.extraction.NotEnoughGrindCoffee;

public enum CoffeeType {
    RISTRETTO (10.0,15.0, 1.0),
    ESPRESSO (12.0,19.0, 2.0),
    LUNGO(15.0,20.0, 3.0);

    private final double minGram;
    private final double maxGram;
    private final double brewRatio;

    CoffeeType(double min, double max, double brewRatio) {
        this.minGram = min;
        this.maxGram = max;
        this.brewRatio = brewRatio;
    }

    public void isValidWeight(double gram) throws
            CoffeeWeightExceededException,
            NotEnoughGrindCoffee
    {
        if (gram <= 0)
            throw new IllegalArgumentException("Coffee weight must be positive.");
        if (gram > maxGram)
            throw new CoffeeWeightExceededException("Too much coffee in portafilter basket.");
        if (gram < minGram)
            throw new NotEnoughGrindCoffee("Need more coffee for a good extraction.");
    }

    public double getBrewRatio() {
        return brewRatio;
    }

    public double calculateRatio(double coffeeWeight) {
        return coffeeWeight * brewRatio;
    }
}
