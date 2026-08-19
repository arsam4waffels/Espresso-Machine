package EspressoMachine.device;

import EspressoMachine.accessories.Portafilter;
import EspressoMachine.device.configs.WaterTank;
import EspressoMachine.breakdowns.machine.MachineIsBrewingException;
import EspressoMachine.breakdowns.machine.MachineIsOffException;
import EspressoMachine.breakdowns.machine.AlreadyOnException;
import EspressoMachine.breakdowns.machine.AlreadyOffException;
import EspressoMachine.breakdowns.machine.PortafilterNotInPlaceException;

public class EspressoMachine {

    private Portafilter portafilter;
    private MachineState machineState;
    private final WaterTank waterTank;
    private final MachineConfiguration configuration;

    private EspressoMachine(MachineConfiguration configuration) {
        this.configuration = configuration;
        this.waterTank = new WaterTank(configuration.getWaterTankCapacity());

        System.out.println("You have an Espresso Machine ready to go!");
        System.out.println("Espresso machine mode : [OFF]");
        System.out.println(
                "Group heads : [" +
                        configuration.getGroupHeadCount() +
                        "]"
        );
        System.out.println(
                "Water tank capacity : [" +
                        configuration.getWaterTankCapacity() +
                        "ml]"
        );
        this.machineState = MachineState.OFF;
    }

    public static class Builder {
        private int groupHeadCount = 1;
        private double waterTankCapacity = 1.5;

        public Builder groupHeadCount(int groupHeadCount) {
            validateGroupHeadAmount(groupHeadCount);
            this.groupHeadCount = groupHeadCount;
            return this;
        }

        public Builder waterTankCapacity(double waterTankCapacity) {
            validateWaterCapacity(waterTankCapacity);
            this.waterTankCapacity = waterTankCapacity;
            return this;
        }

        public EspressoMachine build() {
            return new EspressoMachine(
                    new MachineConfiguration(
                            groupHeadCount,
                            waterTankCapacity
                    )
            );
        }
    }

    public boolean isOn() {
        return machineState != MachineState.OFF;
    }

    public void turnOn() {
        checkCanTurnOn();
        machineState = MachineState.ON;
        System.out.println("Espresso machine mode : [ON]");
    }

    public void turnOff() {
        checkCanTurnOff();
        machineState = MachineState.OFF;
        System.out.println("Espresso machine mode : [OFF]");
    }

    private void checkCanTurnOn() {
        if (machineState == MachineState.ON) {
            throw new AlreadyOnException(
                    "The espresso machine is already ON."
            );
        }
        if (machineState == MachineState.BREWING) {
            throw new MachineIsBrewingException(
                    "The espresso machine is currently brewing."
            );
        }
    }

    private void checkCanTurnOff() {
        if (machineState == MachineState.OFF) {
            throw new AlreadyOffException(
                    "The espresso machine is already OFF."
            );
        }
        if (machineState == MachineState.BREWING) {
            throw new MachineIsBrewingException(
                    "The espresso machine is currently brewing."
            );
        }
    }

    private void checkMachineReady() {
        if (machineState == MachineState.OFF) {
            throw new MachineIsOffException(
                    "Espresso machine needs to be ON to work."
            );
        }
        if (machineState == MachineState.BREWING) {
            throw new MachineIsBrewingException(
                    "The espresso machine is already brewing."
            );
        }
    }

    private static void validateGroupHeadAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }
    }
    private static void validateWaterCapacity(double liters) {
        if (!Double.isFinite(liters) || liters <= 0) {
            throw new IllegalArgumentException(
                    "Water tank capacity must be a positive finite number."
            );
        }
    }

    private void checkPortafilter() {
        if (portafilter == null) throw new PortafilterNotInPlaceException(
                "Portafilter is not attached to the Espresso machine."
        );
    }
    public void attachPortafilter(Portafilter portafilter) {
        if (portafilter == null) {
            throw new IllegalArgumentException(
                    "Portafilter cannot be null."
            );
        }
        this.portafilter = portafilter;
        System.out.println("Attached : [Portafilter] -> [Espresso Machine]");
    }
    public void detachPortafilter() {
        if (portafilter == null) {
            throw new PortafilterNotInPlaceException(
                    "There is no portafilter attached."
            );
        }
        portafilter = null;
        System.out.println(
                "Detached : <- [Portafilter] [Espresso Machine]"
        );
    }
    private void checkCanDetachPortafilter() {
        if (machineState == MachineState.BREWING) {
            throw new MachineIsBrewingException(
                    "Cannot detach the portafilter while brewing."
            );
        }

        if (portafilter == null) {
            throw new PortafilterNotInPlaceException(
                    "There is no portafilter attached."
            );
        }
    }

    public void brewCoffee() {
        checkMachineReady();
        checkPortafilter();

        machineState = MachineState.BREWING;

        try {
            for (int i = 1; i <= configuration.getGroupHeadCount(); i++) {
                waterTank.consume(portafilter.coffeeType().calculateRatio(portafilter.coffeeWeight()));
                System.out.println(
                        "[" + i + "] A hot cup of "
                                + portafilter.coffeeType()
                                + " (" + portafilter.coffeeWeight()
                                + "g)");
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            machineState = MachineState.ON;
        }
    }

    public void displayStatus() {
        switch (machineState) {
            case ON -> System.out.println("Espresso machine mode : [ON]");
            case OFF -> System.out.println("Espresso machine mode : [OFF]");
            case BREWING -> System.out.println("Espresso machine mode : [BREWING]");
        }
    }
}
