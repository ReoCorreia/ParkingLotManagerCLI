package ParkingLotManager;

import ParkingLotManager.enums.SlotSize;
import ParkingLotManager.enums.VehicleSize;
import ParkingLotManager.strategy.FirstAvailableStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    private ParkingLot parkingLot;
    private List<ParkingSlot> slots;
    private Vehicle smallVehicle;
    private Vehicle largeVehicle;
    private Vehicle oversizeVehicle;

    @BeforeEach
    void setUp() {
        // Create test slots
        ParkingSlot smallSlot = new ParkingSlot(1, SlotSize.SMALL);
        ParkingSlot largeSlot = new ParkingSlot(2, SlotSize.LARGE);
        ParkingSlot oversizeSlot = new ParkingSlot(3, SlotSize.OVERSIZE);
        slots = Arrays.asList(smallSlot, largeSlot, oversizeSlot);

        parkingLot = new ParkingLot(slots, new FirstAvailableStrategy());

        // Create test vehicles
        smallVehicle = new Vehicle("SMALL1", VehicleSize.SMALL);
        largeVehicle = new Vehicle("LARGE1", VehicleSize.LARGE);
        oversizeVehicle = new Vehicle("TRUCK1", VehicleSize.OVERSIZE);
    }

    @Test
    void testParkVehicle() {
        assertTrue(parkingLot.parkVehicle(smallVehicle));
        assertFalse(slots.get(0).isEmpty());
        assertEquals(smallVehicle, slots.get(0).getParkedVehicle());
    }

    @Test
    void testParkMultipleVehicles() {
        assertTrue(parkingLot.parkVehicle(smallVehicle));
        assertTrue(parkingLot.parkVehicle(largeVehicle));
        assertTrue(parkingLot.parkVehicle(oversizeVehicle));

        assertFalse(slots.get(0).isEmpty());
        assertFalse(slots.get(1).isEmpty());
        assertFalse(slots.get(2).isEmpty());
    }

    @Test
    void testParkDuplicateVehicle() {
        parkingLot.parkVehicle(smallVehicle);
        assertFalse(parkingLot.parkVehicle(smallVehicle)); // Should fail
    }

    @Test
    void testRemoveVehicle() {
        parkingLot.parkVehicle(smallVehicle);
        assertTrue(parkingLot.removeVehicle("SMALL1"));
        assertTrue(slots.get(0).isEmpty());
    }

    @Test
    void testRemoveNonExistentVehicle() {
        assertFalse(parkingLot.removeVehicle("NONEXISTENT"));
    }

    @Test
    void testVehicleSlotMapping() {
        parkingLot.parkVehicle(smallVehicle);
        parkingLot.parkVehicle(largeVehicle);

        assertEquals(slots.get(0), parkingLot.getVehicleSlotMap().get("SMALL1"));
        assertEquals(slots.get(1), parkingLot.getVehicleSlotMap().get("LARGE1"));
    }

    @Test
    void testParkingLotFull() {
        // Fill all slots
        parkingLot.parkVehicle(smallVehicle);
        parkingLot.parkVehicle(largeVehicle);
        parkingLot.parkVehicle(oversizeVehicle);

        // Try to park another vehicle
        Vehicle extraVehicle = new Vehicle("EXTRA1", VehicleSize.SMALL);
        assertFalse(parkingLot.parkVehicle(extraVehicle));
    }

    @Test
    void testSlotAccess() {
        assertEquals(3, parkingLot.getSlots().size());
        assertEquals(slots, parkingLot.getSlots());
    }
}