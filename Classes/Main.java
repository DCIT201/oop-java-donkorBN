import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        while (true) {
            // Display main menu
            System.out.println("=== Vehicle Rental System ===");
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. Add a vehicle (Admin)");
            System.out.println("4. Display vehicle fleet (Admin)");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            int action = scanner.nextInt();
            scanner.nextLine(); 

            if (action == 5) {
                System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
                break;
            }

            if (action == 3 || action == 4) {
                
                System.out.print("Enter admin password to proceed: ");
                String inputPassword = scanner.nextLine();
                if (!admin.verifyPassword(inputPassword)) {
                    System.out.println("Invalid password. Access denied.");
                    continue;
                }
            }

            if (action == 3) {
                
                System.out.println("Select the type of vehicle to add:");
                System.out.println("1. Car");
                System.out.println("2. Motorcycle");
                System.out.println("3. Truck");
                System.out.print("Please choose an option: ");
                int vehicleChoice = scanner.nextInt();
                scanner.nextLine(); 

                System.out.print("Enter vehicle ID: ");
                String vehicleId = scanner.nextLine();
                System.out.print("Enter vehicle model: ");
                String model = scanner.nextLine();
                System.out.print("Enter base rental rate: $ or GHc");
                double baseRentalRate = scanner.nextDouble();
                scanner.nextLine(); 

                Vehicle vehicle = null;
                switch (vehicleChoice) {
                    case 1:
                        vehicle = new Car(vehicleId, model, baseRentalRate);
                        break;
                    case 2:
                        vehicle = new Motorcycle(vehicleId, model, baseRentalRate);
                        break;
                    case 3:
                        vehicle = new Truck(vehicleId, model, baseRentalRate);
                        break;
                    default:
                        System.out.println("Invalid vehicle type selected.");
                        continue;
                }

                admin.addVehicle(vehicle);
                System.out.println("The vehicle has been successfully added to the fleet.");
            } else if (action == 4) {
               
                System.out.println("Displaying the complete vehicle fleet:");
                admin.displayFleet();
            } else if (action == 1) {
                
                System.out.println("=== Available Vehicles for Rent ===");
                admin.displayAvailableVehicles();

                System.out.print("Enter the vehicle ID you wish to rent: ");
                String vehicleId = scanner.nextLine();

                Vehicle vehicle = admin.getVehicleById(vehicleId);
                if (vehicle == null || !vehicle.isAvailable()) {
                    System.out.println("The vehicle is either unavailable or does not exist.");
                    continue;
                }

                // Input customer name
                System.out.print("Enter your full name: ");
                String name = scanner.nextLine();
                Customer customer = new Customer(name);

                // Input number of rental days
                System.out.print("Enter the number of days for the rental: ");
                int days = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                ((Rentable) vehicle).rent(customer, days);
                System.out.println("Rental confirmed. Total cost: $" + vehicle.calculateRentalCost(days));
            } else if (action == 2) {
                // Return a vehicle
                System.out.print("Enter the vehicle ID to return: ");
                String vehicleId = scanner.nextLine();

                Vehicle vehicle = admin.getVehicleById(vehicleId);
                if (vehicle == null) {
                    System.out.println("Vehicle not found in the system.");
                    continue;
                }

                ((Rentable) vehicle).returnVehicle();
                System.out.println("Thank you for returning the vehicle.");
            } else {
                System.out.println("Invalid option. Please try again.");
            }

            // Ask whether user wants to continue or exit
            System.out.print("Would you like to continue or exit? (continue/exit): ");
            String continueOrExit = scanner.nextLine().toLowerCase();
            if (continueOrExit.equals("exit")) {
                System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
