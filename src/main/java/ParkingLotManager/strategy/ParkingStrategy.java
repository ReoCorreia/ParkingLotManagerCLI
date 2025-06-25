package ParkingLotManager.strategy;

import java.util.List;
import ParkingLotManager.ParkingSlot;
import ParkingLotManager.Vehicle;

public interface ParkingStrategy {
    ParkingSlot findSlot(List<ParkingSlot> slots, Vehicle vehicle);
}