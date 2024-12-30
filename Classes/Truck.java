public class Truck extends Vehicle implements Rentable {

    public Truck(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailableForRental()) {
            System.out.println("The truck is currently unavailable for rental.");
            return;
        }
        setAvailable(false);
        System.out.println("Rental confirmed: " + customer.getName() + " has rented the truck for " + days + " days.");
    }

    @Override
    public void returnVehicle() {
        if (isAvailable()) {
            System.out.println("The truck is already marked as available.");
            return;
        }
        setAvailable(true);
        System.out.println("The truck has been successfully returned and is now available for rental.");
    }
}
