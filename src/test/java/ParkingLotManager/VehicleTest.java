package ParkingLotManager;

import ParkingLotManager.enums.VehicleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testVehicleCreation() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.SMALL);
        assertEquals("ABC123", vehicle.getLicensePlate());
        assertEquals(VehicleSize.SMALL, vehicle.getSize());
    }

    @Test
    void testVehicleProperties() {
        Vehicle smallVehicle = new Vehicle("SMALL1", VehicleSize.SMALL);
        Vehicle largeVehicle = new Vehicle("LARGE1", VehicleSize.LARGE);
        Vehicle oversizeVehicle = new Vehicle("TRUCK1", VehicleSize.OVERSIZE);

        assertEquals("SMALL1", smallVehicle.getLicensePlate());
        assertEquals(VehicleSize.SMALL, smallVehicle.getSize());

        assertEquals("LARGE1", largeVehicle.getLicensePlate());
        assertEquals(VehicleSize.LARGE, largeVehicle.getSize());

        assertEquals("TRUCK1", oversizeVehicle.getLicensePlate());
        assertEquals(VehicleSize.OVERSIZE, oversizeVehicle.getSize());
    }
}