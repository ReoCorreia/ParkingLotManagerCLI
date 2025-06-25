package ParkingLotManager;

import ParkingLotManager.enums.SlotSize;

public class ParkingSlot {
    private final int id;
    private final SlotSize slotSize;
    private Vehicle parkedVehicle;

    public ParkingSlot(int id, SlotSize slotSize) {
        this.id = id;
        this.slotSize = slotSize;
        this.parkedVehicle = null;
    }

    public int getId() {
        return id;
    }

    public SlotSize getSlotSize() {
        return slotSize;
    }

    public boolean isEmpty() {
        return parkedVehicle == null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    public boolean isAvailable() {
        return false;
    }
}