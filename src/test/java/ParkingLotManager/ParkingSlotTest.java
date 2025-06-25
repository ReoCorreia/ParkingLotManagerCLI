package ParkingLotManager;

import ParkingLotManager.enums.SlotSize;
import ParkingLotManager.enums.VehicleSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {

    private ParkingSlot smallSlot;
    private ParkingSlot largeSlot;
    private ParkingSlot oversizeSlot;
    private Vehicle smallVehicle;
    private Vehicle largeVehicle;
    private Vehicle oversizeVehicle;

    @BeforeEach
    void setUp() {
        smallSlot = new ParkingSlot(1, SlotSize.SMALL);
        largeSlot = new ParkingSlot(2, SlotSize.LARGE);
        oversizeSlot = new ParkingSlot(3, SlotSize.OVERSIZE);

        smallVehicle = new Vehicle("ABC123", VehicleSize.SMALL);
        largeVehicle = new Vehicle("XYZ789", VehicleSize.LARGE);
        oversizeVehicle = new Vehicle("TRUCK1", VehicleSize.OVERSIZE);
    }

    @Test
    void testSlotCreation() {
        assertEquals(1, smallSlot.getId());
        assertEquals(SlotSize.SMALL, smallSlot.getSlotSize());
        assertTrue(smallSlot.isEmpty());
        assertNull(smallSlot.getParkedVehicle());
    }

    @Test
    void testParkVehicle() {
        smallSlot.parkVehicle(smallVehicle);
        assertFalse(smallSlot.isEmpty());
        assertEquals(smallVehicle, smallSlot.getParkedVehicle());
    }

    @Test
    void testRemoveVehicle() {
        smallSlot.parkVehicle(smallVehicle);
        assertFalse(smallSlot.isEmpty());

        smallSlot.removeVehicle();
        assertTrue(smallSlot.isEmpty());
        assertNull(smallSlot.getParkedVehicle());
    }

    @Test
    void testSlotProperties() {
        assertEquals(2, largeSlot.getId());
        assertEquals(SlotSize.LARGE, largeSlot.getSlotSize());
        assertEquals(3, oversizeSlot.getId());
        assertEquals(SlotSize.OVERSIZE, oversizeSlot.getSlotSize());
    }
}