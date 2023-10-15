class ParkingSlot {
    private String slotID;
    private Car parkedCar;

    public ParkingSlot(String slotID) {
        this.slotID = slotID;
    }

    public String getSlotID() {
        return slotID;
    }

    public boolean isOccupied() {
        return parkedCar != null;
    }

    public void parkCar(Car car) {
        parkedCar = car;
    }

    public Car removeCar() {
        Car car = parkedCar;
        parkedCar = null;
        return car;
    }

    public Car getCar() {
        Car car = parkedCar;
        return car;
    }

    @Override
    public String toString() {
        if (isOccupied()) {
            return "Slot ID: " + slotID + " (Occupied)";
        } else {
            return "Slot ID: " + slotID + " (Empty)";
        }
    }
}