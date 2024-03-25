import java.util.Scanner;

/**
 * Entry point for the GTU Electronics Inventory Management System, this class handles user interaction, allowing users to perform operations such as adding, removing, updating, listing, and exporting devices in an inventory.
 */
public class Main
{
    /**
     * Creates and returns a Device instance based on the specified category, name, price, and quantity, supports creating various types of devices, including smartphones, laptops, headphones, smartwatches, and TVs, returns null if the specified category does not match any known device type, this method has a constant time complexity O(1) since it uses a switch statement to determine the device type, and the creation of each device object has a constant time complexity.
     *
     * @param category The category of the device to create.
     * @param name The name of the device.
     * @param price The price of the device.
     * @param quantity The stock quantity of the device.
     * @return A new Device instance or null if the category is unrecognized.
     */

    public static Device create_device(String category, String name, double price, int quantity)
    {
        switch (category.toLowerCase())
        {
            case "smart phone":
                return new Smartphone(name, price, quantity);

            case "laptop":
                return new Laptop(name, price, quantity);

            case "headphones":
                return new Headphone(name, price, quantity);

            case "smart watch":
                return new Smartwatch(name, price, quantity);

            case "tv":
                return new TV(name, price, quantity);

            default:
                return null;
        }
    }


    /**
     * Implements the main functionality of the GTU Electronics Inventory Management System, provides a menu-driven interface for managing the inventory, including options to add, remove, update, list, find cheapest device, sort devices, calculate total inventory value, restock devices, and export inventory report. The program runs until the user chooses to exit.
     */

    public static void main(String[] args)
    {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        inventory.add_device(create_device("smart phone", "Samsung Galaxy S21", 799.99, 50));
        inventory.add_device(create_device("laptop", "Dell XPS 15", 1500.00, 30));
        inventory.add_device(create_device("headphones", "Sony WH-1000XM4", 348.00, 100));
        inventory.add_device(create_device("smart watch", "Apple Watch Series 6", 399.99, 70));
        inventory.add_device(create_device("tv", "LG OLED55", 1200.00, 20));

        System.out.println("\nWelcome to the GTU Electronics Inventory Management System!");
        do
        {
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");
            System.out.print("Enter a choice: ");

            while (!scanner.hasNextInt())
            {
                System.out.print("Invalid option. Please try again!!!: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 0:
                    System.out.println("Exiting program. Goodbye!!!");
                    continue;

                case 1:
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter device name: ");
                    String name = scanner.nextLine();

                    double price = 0;
                    System.out.print("Enter price: ");
                    while (true) {
                        if (scanner.hasNextDouble())
                        {
                            price = scanner.nextDouble();
                            if (price > 0)
                                break;
                            else
                                System.out.println("Invalid input. Please enter a positive value for price:");

                        }
                        else
                        {
                            System.out.println("Invalid input. Please enter a valid price:");
                            scanner.next();
                        }
                    }

                    int quantity = 0;
                    System.out.print("Enter quantity: ");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            quantity = scanner.nextInt();
                            if (quantity > 0) {
                                break;
                            } else {
                                System.out.println("Invalid input. Please enter a positive value for quantity:");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid quantity:");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();

                    Device nd = create_device(category, name, price, quantity);

                    if (nd != null) {
                        inventory.add_device(nd);
                        System.out.println(name + " added successfully to the inventory.");
                    } else {
                        System.out.println("Invalid category. Device not added.");
                    }

                    break;

                case 2:
                    inventory.list_all();
                    System.out.print("Enter the number of the device to remove: ");

                    while (!scanner.hasNextInt())
                    {
                        System.out.println("Invalid input. Please enter a valid device number:");
                        scanner.next();
                    }
                    int device_number = scanner.nextInt();
                    scanner.nextLine();

                    boolean removed = inventory.remove(device_number);
                    if (removed)
                        System.out.println("Device removed successfully.");
                    else
                        System.out.println("Invalid number. No device removed.");

                    break;

                case 3:
                    inventory.list_all();

                    System.out.print("Enter the number of the device to update: ");
                    int update_number = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new name (leave blank to keep current name): ");
                    String new_name = scanner.nextLine();

                    System.out.print("Enter new price (leave blank to keep current price): ");
                    String new_price_input = scanner.nextLine();
                    Double new_price = null;
                    if (!new_price_input.isEmpty())
                    {
                        try
                        {
                            new_price = Double.parseDouble(new_price_input.replace("$", ""));
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid price format. Keeping the current price.");
                        }
                    }

                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                    String new_quantity_input = scanner.nextLine();
                    Integer new_quantity = null;
                    if (!new_quantity_input.isEmpty())
                    {
                        try
                        {
                            new_quantity = Integer.parseInt(new_quantity_input);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid quantity format. Keeping the current quantity.");
                        }
                    }

                    boolean updated = inventory.update(update_number, new_name, new_price, new_quantity);
                    if (updated)
                        System.out.println("Device details updated successfully.");
                    else
                        System.out.println("Invalid number. No device updated.");

                    break;

                case 4:
                    inventory.list_all();
                    break;

                case 5:
                    Device chepest_device = inventory.find_cheapest();
                    break;

                case 6:
                    inventory.sort();
                    break;

                case 7:
                    System.out.println("Total inventory value is: $" + inventory.total_value());
                    break;

                case 8:
                    inventory.list_all();
                    System.out.print("Enter the number of the device to restock: ");
                    while (!scanner.hasNextInt())
                    {
                        System.out.println("Invalid input. Please enter a valid device number:");
                        scanner.next(); // Clear the invalid input
                    }
                    int number = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                    String action = scanner.nextLine();
                    boolean add_stock = action.equalsIgnoreCase("add");

                    String message;
                    if (add_stock)
                    {
                        message = "Enter the quantity to add: ";
                    }
                    else
                    {
                        message = "Enter the quantity to remove: ";
                    }
                    System.out.print(message);

                    while (!scanner.hasNextInt())
                    {
                        System.out.println("Invalid input. Please enter a valid quantity:");
                        scanner.next();
                    }
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    boolean success = inventory.restock(number, amount, add_stock);
                    if (success)
                    {
                        if (add_stock)

                            System.out.println("Stock added to device successfully.");
                        else
                            System.out.println("Stock removed from device successfully.");
                    }
                    else
                        System.out.println("Failed to restock device. Ensure the device number is correct and the quantity is valid.");

                    break;

                case 9:
                    inventory.export();
                    break;

                default:
                    System.out.println("Invalid option, please try again!!!");
                    break;
            }

        } while (choice != 0);

        scanner.close();
    }
}