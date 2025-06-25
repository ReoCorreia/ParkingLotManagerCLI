package ParkingLotManager;

import ParkingLotManager.strategy.ParkingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final Map<String, ParkingSlot> vehicleSlotMap;
    private final ParkingStrategy strategy;

    public ParkingLot(List<ParkingSlot> slots, ParkingStrategy strategy) {
        this.slots = slots;
        this.vehicleSlotMap = new HashMap<>();
        this.strategy = strategy;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicleSlotMap.containsKey(vehicle.getLicensePlate())) {
            System.out.println("Vehicle already parked!");
            return false;
        }
        ParkingSlot slot = strategy.findSlot(slots, vehicle);
        if (slot == null) {
            System.out.println("No suitable slot available.");
            return false;
        }
        slot.parkVehicle(vehicle);
        vehicleSlotMap.put(vehicle.getLicensePlate(), slot);
        System.out.println("Vehicle parked at slot " + slot.getId());
        return true;
    }

    public boolean removeVehicle(String licensePlate) {
        ParkingSlot slot = vehicleSlotMap.get(licensePlate);
        if (slot == null) {
            System.out.println("Vehicle not found.");
            return false;
        }
        slot.removeVehicle();
        vehicleSlotMap.remove(licensePlate);
        System.out.println("Vehicle removed from slot " + slot.getId());
        return true;
    }

    public void displayStatus() {
        System.out.println("\nParking Lot Status:");
        for (ParkingSlot slot : slots) {
            String status = slot.isEmpty() ? "Available"
                    : "Occupied by " + slot.getParkedVehicle().getLicensePlate();
            System.out.printf("Slot %d [%s] %s\n", slot.getId(), slot.getSlotSize(), status);
        }
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public Map<String, ParkingSlot> getVehicleSlotMap() {
        return vehicleSlotMap;
    }
}
