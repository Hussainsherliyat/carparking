import java.util.*;

class CarSlotPair {
    private String slotID;
    private Car car;

    public CarSlotPair(String slotID, Car car) {
        this.slotID = slotID;
        this.car = car;
    }

    public String getSlotID() {
        return slotID;
    }

    public Car getCar() {
        return car;
    }
}

class CarPark {
    private Map<String, ParkingSlot> parkingSlots;

    public CarPark() {
        parkingSlots = new HashMap<>();
    }

    public boolean addParkingSlot(String slotID) {
        if (parkingSlots.containsKey(slotID)) {
            return false; // Slot with the same ID already exists
        }
        parkingSlots.put(slotID, new ParkingSlot(slotID));
        return true;
    }

    public boolean deleteParkingSlot(String slotID) {
        ParkingSlot slot = parkingSlots.get(slotID);
        if (slot == null) {
            return false; // Slot does not exist
        }
        if (slot.isOccupied()) {
            return false; // Slot is occupied, cannot be deleted
        }
        parkingSlots.remove(slotID);
        return true;
    }

    public ParkingSlot getParkingSlot(String slotID) {
        ParkingSlot slot = parkingSlots.get(slotID);
        return slot;
    }

    public List<ParkingSlot> getAllSlots() {
        return new ArrayList<>(parkingSlots.values());
    }

    public Car parkCar(String slotID, Car car) {
        ParkingSlot slot = parkingSlots.get(slotID);
        slot.parkCar(car);
        return car;
    }

    public CarSlotPair findCarByRegistration(String registrationNumber) {
        for (ParkingSlot slot : parkingSlots.values()) {
            if (slot.isOccupied()) {
                Car car = slot.getCar();
                if (car.getRegistrationNumber().equals(registrationNumber)) {
                    return new CarSlotPair(slot.getSlotID(), car);
                }
            }
        }
        return null; // Car not found
    }

    public CarSlotPair removeCarByRegistration(String registrationNumber) {
        for (ParkingSlot slot : parkingSlots.values()) {
            if (slot.isOccupied()) {
                Car car = slot.getCar();
                if (car.getRegistrationNumber().equals(registrationNumber)) {
                    slot.removeCar();
                    return new CarSlotPair(slot.getSlotID(), car);
                }
            }
        }
        return null; // Car not found
    }

    public List<CarSlotPair> findCarsByMake(String make) {
        List<CarSlotPair> carsByMake = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots.values()) {
            if (slot.isOccupied() && slot.getCar().getMake().equals(make)) {
                carsByMake.add(new CarSlotPair(slot.getSlotID(),slot.getCar()));
            }
        }
        return carsByMake;
    }
}