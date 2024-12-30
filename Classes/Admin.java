import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final List<Vehicle> vehicleFleet;
    private final String password;

    public Admin() {
        this.vehicleFleet = new ArrayList<>();
        this.password = "Ausss123";
        prepopulateFleet();
    }

    private void prepopulateFleet() {
        List<Vehicle> prepopulatedVehicles = List.of(
                new Car("CAR001", "Tesla Model 3", 75.0),
                new Car("CAR002", "BMW 3 Series", 80.0),
                new Car("CAR003", "Mercedes-Benz C-Class", 85.0),
                new Car("CAR004", "Audi A4", 78.0),
                new Car("CAR005", "Lexus ES", 70.0),

                new Motorcycle("MOTO001", "Harley-Davidson Street 750", 50.0),
                new Motorcycle("MOTO002", "Triumph Street Triple", 55.0),
                new Motorcycle("MOTO003", "KTM Duke 390", 40.0),
                new Motorcycle("MOTO004", "BMW G310R", 38.0),
                new Motorcycle("MOTO005", "Ducati Scrambler", 60.0),

                new Truck("TRUCK001", "Rivian R1T", 100.0),
                new Truck("TRUCK002", "GMC Sierra EV", 110.0),
                new Truck("TRUCK003", "Ford Maverick", 95.0),
                new Truck("TRUCK004", "Chevrolet Colorado", 90.0),
                new Truck("TRUCK005", "Toyota Tacoma", 85.0)
        );
        vehicleFleet.addAll(prepopulatedVehicles);
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleFleet.add(vehicle);
        System.out.println(vehicle.getClass().getSimpleName() + " added to the fleet.");
    }

    public void displayFleet() {
        if (vehicleFleet.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
        } else {
            System.out.println("Vehicle Fleet:");
            vehicleFleet.forEach(vehicle -> System.out.println(
                vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() + 
                ", Model: " + vehicle.getModel())
            );
        }
    }

    public void displayAvailableVehicles() {
        List<Vehicle> availableVehicles = vehicleFleet.stream()
                .filter(Vehicle::isAvailable)
                .toList();

        if (availableVehicles.isEmpty()) {
            System.out.println("No available vehicles.");
        } else {
            availableVehicles.forEach(vehicle -> System.out.println(
                vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() + 
                ", Model: " + vehicle.getModel() + 
                ", Rate: $" + vehicle.getBaseRentalRate() + " per day")
            );
        }
    }

    public Vehicle getVehicleById(String vehicleId) {
        return vehicleFleet.stream()
                .filter(vehicle -> vehicle.getVehicleId().equals(vehicleId))
                .findFirst()
                .orElse(null);
    }
}
