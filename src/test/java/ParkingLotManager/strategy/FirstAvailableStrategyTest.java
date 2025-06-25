package ParkingLotManager.strategy;

import ParkingLotManager.ParkingSlot;
import ParkingLotManager.Vehicle;
import ParkingLotManager.enums.SlotSize;
import ParkingLotManager.enums.VehicleSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirstAvailableStrategyTest {
    private FirstAvailableStrategy strategy;
    private List<ParkingSlot> slots;
    private Vehicle smallVehicle;
    private Vehicle largeVehicle;
    private Vehicle oversizeVehicle;

    @BeforeEach
    void setUp() {
        strategy = new FirstAvailableStrategy();

        // Create test slots
        ParkingSlot smallSlot = new ParkingSlot(1, SlotSize.SMALL);
        ParkingSlot largeSlot = new ParkingSlot(2, SlotSize.LARGE);
        ParkingSlot oversizeSlot = new ParkingSlot(3, SlotSize.OVERSIZE);
        slots = Arrays.asList(smallSlot, largeSlot, oversizeSlot);

        // Create test vehicles
        smallVehicle = new Vehicle("SMALL1", VehicleSize.SMALL);
        largeVehicle = new Vehicle("LARGE1", VehicleSize.LARGE);
        oversizeVehicle = new Vehicle("TRUCK1", VehicleSize.OVERSIZE);
    }

    @Test
    void testSmallVehicleAllocation() {
        ParkingSlot allocatedSlot = strategy.findSlot(slots, smallVehicle);
        assertNotNull(allocatedSlot);
        assertEquals(1, allocatedSlot.getId()); // Should get the first small slot
    }

    @Test
    void testLargeVehicleAllocation() {
        ParkingSlot allocatedSlot = strategy.findSlot(slots, largeVehicle);
        assertNotNull(allocatedSlot);
        assertEquals(2, allocatedSlot.getId()); // Should get the large slot
    }

    @Test
    void testOversizeVehicleAllocation() {
        ParkingSlot allocatedSlot = strategy.findSlot(slots, oversizeVehicle);
        assertNotNull(allocatedSlot);
        assertEquals(3, allocatedSlot.getId()); // Should get the oversize slot
    }

    @Test
    void testNoAvailableSlot() {
        // Occupy all slots
        slots.get(0).parkVehicle(smallVehicle);
        slots.get(1).parkVehicle(largeVehicle);
        slots.get(2).parkVehicle(oversizeVehicle);

        ParkingSlot allocatedSlot = strategy.findSlot(slots, smallVehicle);
        assertNull(allocatedSlot);
    }

    @Test
    void testLargeVehicleInOversizeSlot() {
        // Occupy small and large slots
        slots.get(0).parkVehicle(smallVehicle);
        slots.get(1).parkVehicle(largeVehicle);

        ParkingSlot allocatedSlot = strategy.findSlot(slots, largeVehicle);
        assertNotNull(allocatedSlot);
        assertEquals(3, allocatedSlot.getId()); // Should get oversize slot
    }

}