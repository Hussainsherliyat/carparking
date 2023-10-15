import java.util.Date;

class Car {
    private String registrationNumber;
    private String make;
    private String model;
    private String year;
    private Date parkingTime;

    public Car(String registrationNumber, String make, String model, String year) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.parkingTime = new Date();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public Date getParkingTime() {
        return parkingTime;
    }

    @Override
    public String toString() {
        return "Registration: " + registrationNumber + ", Make: " + make + ", Model: " + model + ", Year: " + year;
    }
}
