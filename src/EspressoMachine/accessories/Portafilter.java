package EspressoMachine.accessories;

import EspressoMachine.bean.CoffeeType;
import org.jetbrains.annotations.NotNull;

public record Portafilter(CoffeeType coffeeType, double coffeeWeight) {

    public Portafilter(@NotNull CoffeeType coffeeType, double coffeeWeight) {
        coffeeType.isValidWeight(coffeeWeight);

        this.coffeeType = coffeeType;
        this.coffeeWeight = coffeeWeight;
        System.out.println("Coffee weight in portafilter : [" + coffeeWeight + "g]");
    }
}
