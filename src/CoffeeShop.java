import EspressoMachine.accessories.Portafilter;
import EspressoMachine.bean.CoffeeType;
import EspressoMachine.device.EspressoMachine;

public class CoffeeShop {
    public static void main(String[] args) {

        EspressoMachine espressoMachine = new EspressoMachine
                .Builder()
                .groupHeadCount(2)
                .waterTankCapacity(500)
                .build();

        espressoMachine.turnOn();
        CoffeeType espresso = CoffeeType.ESPRESSO;
        Portafilter portafilter = new Portafilter(espresso, 15);
        espressoMachine.attachPortafilter(portafilter);
        espressoMachine.brewCoffee();
        espressoMachine.detachPortafilter();
        espressoMachine.turnOff();

    }
}