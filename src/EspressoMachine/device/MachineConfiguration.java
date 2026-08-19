package EspressoMachine.device;

public final class MachineConfiguration {
    private final int groupHeadCount;
    private final double waterTankCapacity;

    public MachineConfiguration(int groupHeadCount, double waterTankCapacity) {
        this.groupHeadCount = groupHeadCount;
        this.waterTankCapacity = waterTankCapacity;
    }

    public int getGroupHeadCount() {
        return groupHeadCount;
    }

    public double getWaterTankCapacity() {
        return waterTankCapacity;
    }
}
