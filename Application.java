import java.util.Scanner;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarPark carPark = new CarPark();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add a parking slot");
            System.out.println("2. Delete a parking slot by slot ID (only if not occupied)");
            System.out.println("3. List all slots");
            System.out.println("4. Park a car into a slot");
            System.out.println("5. Find a car by registration number");
            System.out.println("6. Remove a car by registration number");
            System.out.println("7. Find cars by make");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter slot ID: ");
                    String slotID = scanner.nextLine();
                    if (!slotID.matches("[A-Z]\\d{3}")) {
                        System.out.println("Enter a valid Slot Id i.e D003.");
                        break;
                    }
                    if (carPark.addParkingSlot(slotID)) {
                        System.out.println("Parking slot added successfully.");
                    } else {
                        System.out.println("Slot with the same ID already exists.");
                    }
                    break;
                case "2":
                    System.out.print("Enter slot ID to delete: ");
                    String deleteSlotID = scanner.nextLine();
                    if (carPark.deleteParkingSlot(deleteSlotID)) {
                        System.out.println("Parking slot deleted successfully.");
                    } else {
                        System.out.println("Slot does not exist or is occupied.");
                    }
                    break;
                case "3":
                    List<ParkingSlot> allSlots = carPark.getAllSlots();
                    for (ParkingSlot slot : allSlots) {
                        System.out.println(slot);
                    }
                    break;
                case "4":
                    System.out.print("Enter slot ID to park the car: ");
                    String parkSlotID = scanner.nextLine();
                    // after this line
                    ParkingSlot parkingSlot = carPark.getParkingSlot(parkSlotID);
                    if (parkingSlot == null || parkingSlot.isOccupied()) {
                        System.out.println("Parking Slot is Occupied or Not Exists.");
                        break;
                    }
                    //
                    System.out.print("Enter car registration number: ");
                    String regNumber = scanner.nextLine();
                    System.out.print("Enter car make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter car model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter car year: ");
                    String year = scanner.nextLine();

                    if (!year.matches("\\d{4}")) {
                        System.out.println("Enter a Valid year i.e 1998 ");
                        break;
                    }

                    if (!regNumber.matches("[A-Z]\\d{4}")) {
                        System.out.println("Enter a valid Registration No i.e D0036.");
                        break;
                    }

                    Car car = new Car(regNumber, make, model, year);
                    Car parkedCar = carPark.parkCar(parkSlotID, car);
                    if (parkedCar != null) {
                        System.out.println("Car parked successfully in slot " + parkSlotID);
                        System.out.println("Parking time: " + parkedCar.getParkingTime());
                    } else {
                        System.out.println("Slot does not exist, is occupied, or car cannot be parked.");
                    }
                    break;
                case "5":
                    System.out.print("Enter car registration number to find: ");
                    String findRegNumber = scanner.nextLine();
                    CarSlotPair carSlot = carPark.findCarByRegistration(findRegNumber);
                    if (carSlot!= null) {
                        Car foundCar = carSlot.getCar();
                        System.out.println("Car found in a slot.");
                        System.out.println("Slot ID: " + carSlot.getSlotID());
                        System.out.println("Registration Number: " + foundCar.getRegistrationNumber());
                        System.out.println("Make: " + foundCar.getMake());
                        System.out.println("Parking Time: " + foundCar.getParkingTime());
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case "6":
                    System.out.print("Enter car registration number to remove: ");
                    String removeRegNumber = scanner.nextLine();
                    CarSlotPair removeCarSlot = carPark.removeCarByRegistration(removeRegNumber);
                    if (removeCarSlot != null) {
                        Car removedCar = removeCarSlot.getCar();
                        System.out.println("Car removed from the slot.");
                        System.out.println("Slot ID: " + removeCarSlot.getSlotID());
                        System.out.println("Registration Number: " + removedCar.getRegistrationNumber());
                        System.out.println("Make: " + removedCar.getMake());
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case "7":
                    System.out.print("Enter car make to find: ");
                    String findMake = scanner.nextLine();
                    List<CarSlotPair> carSlotPairsByMake = carPark.findCarsByMake(findMake);
                    if (!carSlotPairsByMake.isEmpty()) {
                        System.out.println("Cars found with make: " + findMake);
                        for (CarSlotPair carByMake : carSlotPairsByMake) {
                            Car foundCarByMake = carByMake.getCar();
                            System.out.println("Slot ID: " + carByMake.getSlotID());
                            System.out.println("Registration Number: " + foundCarByMake.getRegistrationNumber());
                            System.out.println("Make: " + foundCarByMake.getMake());
                            System.out.println("Model: " + foundCarByMake.getModel());
                            System.out.println("Year: " + foundCarByMake.getYear());
                            System.out.println("Parking Time: " + foundCarByMake.getParkingTime());
                        }
                    } else {
                        System.out.println("No cars found with make: " + findMake);
                    }
                    break;
                case "8":
                    System.out.println("Program end!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
