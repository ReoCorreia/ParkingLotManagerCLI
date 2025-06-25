package ParkingLotManager.strategy;

import ParkingLotManager.ParkingSlot;
import ParkingLotManager.Vehicle;
import ParkingLotManager.enums.SlotSize;
import ParkingLotManager.enums.VehicleSize;

import java.util.List;

public class FirstAvailableStrategy implements ParkingStrategy {
    @Override
    public ParkingSlot findSlot(List<ParkingSlot> slots, Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        for (ParkingSlot slot : slots) {
            if (slot.isEmpty() && canFit(slot.getSlotSize(), vehicleSize)) {
                return slot;
            }
        }
        return null;
    }

    private boolean canFit(SlotSize slotSize, VehicleSize vehicleSize) {
        /*
        * Is desgined with overflow in mind
        * Small slot: only small vehicles
        * Large slot: small or large vehicles
        * Oversize slot: any vehicle
         */
        switch (slotSize) {
            case SMALL:
                return vehicleSize == VehicleSize.SMALL;
            case LARGE:
                return vehicleSize == VehicleSize.SMALL || vehicleSize == VehicleSize.LARGE;
            case OVERSIZE:
                return true;
            default:
                return false;

        }
    }

}
