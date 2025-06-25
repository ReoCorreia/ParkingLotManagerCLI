package ParkingLotManager.cli;

import ParkingLotManager.ParkingLot;
import ParkingLotManager.ParkingSlot;
import ParkingLotManager.Vehicle;
import ParkingLotManager.enums.SlotSize;
import ParkingLotManager.enums.VehicleSize;
import ParkingLotManager.strategy.FirstAvailableStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotCLI {
    private final Scanner scanner = new Scanner(System.in);
    private ParkingLot parkingLot;

    public void start() {
        System.out.println("Welcome to the Parking Lot Management System");
        initializeParkingLot();
        while (true) {
            System.out.println("\n1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    parkVehicle();
                break;
                case 2:
                    removeVehicle();
                break;
                case 3:
                    parkingLot.displayStatus();
                break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    return;
               default:
                   System.out.println("Invalid choice");



            }
        }
    }

    private void removeVehicle() {
        System.out.print("Enter vehicle license plate to remove: ");
        String license = scanner.nextLine();
        parkingLot.removeVehicle(license);
    }

    private void parkVehicle() {
        System.out.print("Enter vehicle license plate: ");
        String license = scanner.nextLine();
        System.out.print("Enter vehicle size (SMALL, LARGE, OVERSIZE): ");
        String sizeStr = scanner.nextLine().toUpperCase();
        VehicleSize size;
        try {
            size = VehicleSize.valueOf(sizeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid vehicle size");
            return;
        }
        Vehicle vehicle = new Vehicle(license, size);
        parkingLot.parkVehicle(vehicle);
    }

    private void initializeParkingLot() {
        System.out.println("Enter total number of slots");
        int n = scanner.nextInt();
        scanner.nextLine();


        List<ParkingSlot> slots = new ArrayList<>();
        int id = 1;
        /*
        // User input based slot distributio
        for (SlotSize size : SlotSize.values()) {
            System.out.printf("Enter number of %s slots: ", size);
            int count = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < count; i++) {
                slots.add(new ParkingSlot(id++, size));
            }
        }
        if (slots.size() != n) {
            System.out.println("Warning: Total slots do not match specified count. Using entered slot distribution.");
        }
        parkingLot = new ParkingLot(slots, new FirstAvailableStrategy());
         */

        // Percentage based slot distribution
        int smallSlots = (int) Math.round(n * 0.4);
        int largeSlots = (int) Math.round(n * 0.4);
        int oversizeSlots = n - largeSlots - smallSlots;

        for (int i = 0; i < smallSlots; i++) {
            slots.add(new ParkingSlot(id++, SlotSize.SMALL));
        }

        for (int i = 0; i < largeSlots; i++) {
            slots.add(new ParkingSlot(id++, SlotSize.LARGE));
        }

        for (int i = 0; i < oversizeSlots; i++) {
            slots.add(new ParkingSlot(id++, SlotSize.OVERSIZE));
        }

        System.out.println("Parking lot initialized with:");
        System.out.println("- Small slots: " + smallSlots + " (40%)");
        System.out.println("- Large slots: " + largeSlots + " (40%)");
        System.out.println("- Oversize slots: " + oversizeSlots + " (20%)");
        System.out.println("Total slots: " + slots.size());

        parkingLot = new ParkingLot(slots, new FirstAvailableStrategy());
    }
}
