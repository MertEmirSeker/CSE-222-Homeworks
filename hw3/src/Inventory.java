import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;

/**
 * Manages an inventory of devices, supporting operations such as adding, listing, removing, updating, finding the cheapest device, calculating total value, restocking, and exporting an inventory report.
 */

public class Inventory
{
    private LinkedList<ArrayList<Device>> device_list;

    /**
     * Constructs a new Inventory instance, initializes the internal structure for storing devices and time complexity: O(1), as it simply instantiates the underlying LinkedList.
     */
    public Inventory()
    {
        this.device_list = new LinkedList<>();
    }

    /**
     * Adds a device to the first list of device lists if no device with the same name exists, checks each device in all lists for name uniqueness before adding, adds to a new list if no lists exist, confirms addition with a message, time complexity: O(n*m), where n is the max devices per list, m is the number of lists.
     *
     * @param d the device to add.
     */
    public void add_device(Device d)
    {
        // Iterate through the list of device lists
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> device_array_list = device_list.get(i);
            // Iterate through the devices in each device list
            for (int j = 0; j < device_array_list.size(); j++)
            {
                Device exist_device = device_array_list.get(j);

                // Check if a device with the same name already exists
                if (exist_device.getName().equalsIgnoreCase(d.getName()))
                {
                    System.out.println("A device with the name \"" + d.getName() + "\" already exists. Device not added.");
                    return;
                }
            }
        }

        // If the device list is empty, create a new list and add the device
        if (device_list.isEmpty())
        {
            ArrayList<Device> new_device_list = new ArrayList<>();
            new_device_list.add(d);
            device_list.add(new_device_list);
        }
        // If not empty, add the device to the first list
        else
            device_list.getFirst().add(d);

        // Print confirmation of device addition
        System.out.println(d.getCategory() + ", " + d.getName() + ", " + d.getPrice() + "$, " + d.getQuantity() + " amount added...");
    }

    /**
     * Lists all devices in a formatted table, if inventory is empty, indicates so, iterates over each list and device within, displaying number, category, name, price, and quantity, time complexity: O(n*m), where n is the average number of devices per list and m is the number of lists.
     */
    public void list_all()
    {
        // Check if the inventory is empty
        if (device_list.isEmpty())
        {
            System.out.println("The inventory is empty.");
            return;
        }

        // Print the header for the table
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-3s | %-13s | %-20s | %-8s | %-9s |%n", "No.", "Category", "Name", "Price", "Quantity");
        System.out.println("----------------------------------------------------------------------");

        int count = 1; // To keep track of the device number in the list

        // Iterate through each list of devices in the device list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                Device device = list.get(j);

                // Print the device information in a formatted manner
                System.out.printf("| %-3d | %-13s | %-20s | $%-7.2f | %-9d |%n", count++, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity());
            }
        }

        // Print the bottom line of the table
        System.out.println("----------------------------------------------------------------------");
    }

    /**
     * Removes the device at the specified number from the device lists, iterates through each device in all lists, using a count to match against the specified number, if a device list becomes empty after removal, it also removes that list from the collection, time complexity: O(n*m) in the worst case, where n is the max number of devices per list and m is the number of lists, returns true if a device is successfully removed, false otherwise.
     *
     * @param number the device number to remove
     * @return boolean indicating success or failure of the removal
     */
    public boolean remove(int number)
    {
        int count = 1; // To keep track of the device number in the list

        // Iterate through each list of devices in the device list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                // Check if the current device number matches the specified number
                if (count == number)
                {
                    list.remove(j); // Remove the device from the list

                    // If the list becomes empty after removal, remove the entire list from device_list
                    if (list.isEmpty())
                        device_list.remove(i);

                    return true; // Return true indicating successful removal
                }
                count++; // Increment count to move to the next device
            }
        }
        return false; // Return false if the specified number doesn't correspond to any device
    }

    /**
     * Updates the details of a device identified by its sequential number across all device lists, can update the device's name, price, and/or quantity if new values are provided and valid, time complexity: O(n*m), where n is the average number of devices per list and m is the number of lists, returns true if the update is successful, false if the device is not found.
     *
     * @param number The sequential number of the device to update.
     * @param new_name New name for the device; if null or empty, name is not updated.
     * @param new_price New price for the device; if null or not positive, price is not updated.
     * @param new_quantity New quantity for the device; if null or not positive, quantity is not updated.
     * @return true if the device was successfully updated, false otherwise.
     */
    public boolean update(int number, String new_name, Double new_price, Integer new_quantity)
    {
        int count = 1; // To keep track of the device number in the list

        // Iterate through each list of devices in the device list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                if (count == number)
                { // Check if the current device number matches the specified number
                    Device device = list.get(j);

                    // Update device name if new_name is provided and not empty
                    if (new_name != null && !new_name.isEmpty())
                    {
                        device.setName(new_name);
                    }

                    // Update device price if new_price is provided and positive
                    if (new_price != null && new_price > 0)
                    {
                        device.setPrice(new_price);
                    }

                    // Update device quantity if new_quantity is provided and positive
                    if (new_quantity != null && new_quantity > 0)
                    {
                        device.setQuantity(new_quantity);
                    }

                    // Print updated device details
                    System.out.println("Device details updated: Name - " + device.getName() + ", Price - $" + device.getPrice() + ", Quantity - " + device.getQuantity());
                    return true; // Indicate successful update
                }
                count++; // Move to the next device
            }
        }
        return false; // Indicate failure to find the specified device
    }

    /**
     * Finds and returns the cheapest device across all device lists in the inventory, if the inventory is empty, prints a message and returns null, time complexity: O(n*m), where n is the average number of devices per list and m is the number of lists.
     *
     * @return The cheapest device found, or null if the inventory is empty.
     */
    public Device find_cheapest()
    {
        // Check if the inventory is empty
        if (device_list.isEmpty())
        {
            System.out.println("Inventory is empty.");
            return null;
        }

        // Initialize variables to store the cheapest device and its price
        Device cheapest_device = null;
        double lowest_price = Double.MAX_VALUE;

        // Iterate through each list of devices in the device_list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                Device device = list.get(j);

                // Check if the price of the current device is lower than the lowest recorded price
                if (device.getPrice() < lowest_price)
                {
                    // If so, update the cheapest_device and lowest_price variables
                    cheapest_device = device;
                    lowest_price = device.getPrice();
                }
            }
        }

        // If a cheapest device is found, print its details
        if (cheapest_device != null)
        {
            System.out.println("The cheapest device is: ");
            System.out.println("Category: " + cheapest_device.getCategory() + ", Name: " + cheapest_device.getName() + ", Price: " + cheapest_device.getPrice() + "$, Quantity: " + cheapest_device.getQuantity());
        }

        return cheapest_device; // Return the cheapest device found
    }

    /**
     * Compiles and returns a list of all devices across every list in the inventory, time complexity: O(n*m), where n is the average number of devices per list and m is the number of lists, this method goes through each device list and adds every device to a single list, which it then returns.
     *
     * @return An ArrayList of Device objects representing all devices in the inventory.
     */
    public ArrayList<Device> get_all_devices()
    {
        // Create a new ArrayList to store all devices
        ArrayList<Device> all_devices = new ArrayList<>();

        // Iterate through each list of devices in the device_list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                // Add each device to the all_devices list
                all_devices.add(list.get(j));
            }
        }
        return all_devices; // Return the list containing all devices
    }

    /**
     * Sorts all devices in the inventory by their price in ascending order using the bubble sort algorithm, prints the sorted list of devices, including their category, name, price, and quantity, if the inventory is empty, a message indicating this is printed instead, time complexity: O(n^2), where n is the total number of devices in the inventory.
     */
    public void sort()
    {
        // Get all devices from the inventory
        ArrayList<Device> all_devices = get_all_devices();

        // Check if the inventory is empty
        if (all_devices.isEmpty())
        {
            System.out.println("The inventory is empty.");
            return;
        }

        boolean swapped = false; // Flag to indicate whether a swap has occurred

        // Perform bubble sort algorithm to sort devices by price
        for (int i = 0; i < all_devices.size() - 1; i++)
        {
            for (int j = 0; j < all_devices.size() - i - 1; j++)
            {
                // Compare adjacent devices by price and swap if necessary
                if (all_devices.get(j).getPrice() > all_devices.get(j + 1).getPrice())
                {
                    Device temp = all_devices.get(j);
                    all_devices.set(j, all_devices.get(j + 1));
                    all_devices.set(j + 1, temp);
                    swapped = true; // Set swapped to true to indicate a swap occurred
                }
            }
            // If no swap occurred in this iteration, the array is already sorted
            if (!swapped)
                break;
        }

        // Print the sorted list of devices
        System.out.println("Devices sorted by price:");
        for (int i = 0; i < all_devices.size(); i++)
        {
            Device device = all_devices.get(i);
            System.out.println((i + 1) + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: $" + String.format("%.2f", device.getPrice()) + ", Quantity: " + device.getQuantity());
        }
    }

    /**
     * Calculates and returns the total value of all devices in the inventory, the total value is the sum of the product of each device's price and quantity, time complexity: O(n*m), where n is the average number of devices per list and m is the number of lists.
     *
     * @return The total value of all devices.
     */
    public double total_value()
    {
        double total = 0; // Initialize total value

        // Iterate through each list of devices in the device_list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                Device device = list.get(j);

                // Calculate the value of each device and add it to the total
                total += device.getPrice() * device.getQuantity();
            }
        }
        return total; // Return the total value of all devices
    }

    /**
     * Restocks or reduces the stock of a specified device by its number in the inventory, the time complexity of this operation in the worst-case scenario is O(n*m), where n is the number of device lists in device_list and m is the average number of devices in each list, if reducing stock, ensures the quantity does not fall below 0.
     *
     * @param number    The number of the device to restock or reduce stock, based on listing order.
     * @param amount    The amount to add or subtract from the device's stock.
     * @param add_stock True to add stock, false to reduce stock.
     * @return true if the operation is successful, false otherwise (e.g., trying to reduce more stock than available).
     */
    public boolean restock(int number, int amount, boolean add_stock)
    {
        int count = 1; // To keep track of the device number in the list

        // Iterate through each list of devices in the device_list
        for (int i = 0; i < device_list.size(); i++)
        {
            ArrayList<Device> list = device_list.get(i);

            // Iterate through each device in the list
            for (int j = 0; j < list.size(); j++)
            {
                // Check if the current device number matches the specified number
                if (count == number)
                {
                    Device device = list.get(j);
                    int current_amount = device.getQuantity();
                    int new_amount = current_amount;

                    // Check if the stock is being added or reduced
                    if (add_stock)
                        new_amount += amount; // Add stock
                    else
                    {
                        // Check if reducing stock would result in negative quantity
                        if (amount > current_amount)
                        {
                            System.out.println("Cannot reduce stock below 0.");
                            return false; // Return false indicating unsuccessful restock
                        }
                        new_amount -= amount; // Reduce stock
                    }

                    // Update the quantity of the device
                    device.setQuantity(new_amount);

                    // Print message indicating restocking or reduction of stock
                    if (add_stock)
                        System.out.println("Device " + device.getName() + " restocked. New quantity: " + new_amount);
                    else
                        System.out.println("Device " + device.getName() + " stock reduced. New quantity: " + new_amount);

                    return true; // Return true indicating successful restock
                }
                count++; // Increment count to move to the next device
            }
        }
        return false; // Return false if the specified number doesn't correspond to any device
    }

    /**
     * Generates and exports an inventory report to a text file named "Report.txt", the report includes device details, calculates total devices and inventory value, and handles any file writing errors, worst-case time complexity is O(n*m), where n is the number of device lists and m is the average devices per list.
     */

    public void export()
    {
        // Get the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dateStr = dateFormat.format(new Date());

        // Create the header for the report
        String header = "Electronics Shop Inventory Report\nGenerated on: " + dateStr + "\n----------------------------------------------------------------------\n";

        // Define column titles
        String columnTitles = "| No. | Category     | Name               | Price   | Quantity |\n";

        // Define divider for formatting
        String divider = "----------------------------------------------------------------------\n";

        // Initialize a StringBuilder to construct the report
        StringBuilder report = new StringBuilder(header + columnTitles + divider);

        int count = 0;           // Initialize a counter for devices
        double totalValue = 0.0; // Initialize total value of inventory

        // Iterate through each list of devices in the device_list
        for (ArrayList<Device> list : device_list)
        {
            // Iterate through each device in the list
            for (Device device : list)
            {
                count++; // Increment device counter
                // Calculate total value by adding the price of each device multiplied by its quantity
                totalValue += device.getPrice() * device.getQuantity();
                // Append device details to the report using formatted string
                report.append(String.format("| %-4d| %-13s| %-19s| $%-7.2f| %-9d|%n",
                        count, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity()));
            }
        }
        // Append divider after listing devices
        report.append(divider);
        // Append summary section to the report
        report.append("Summary:\n");
        report.append("- Total Number of Devices: ").append(count).append("\n");
        report.append("- Total Inventory Value: $").append(String.format("%.2f", totalValue)).append("\n");
        report.append("End of Report\n");

        // Attempt to write the report to file
        File file = new File("Report.txt");
        try
        {
            if (!file.exists())
            {
                file.createNewFile(); // Try to create the file if it doesn't exist
            }
            FileWriter writer = new FileWriter(file);
            writer.write(report.toString()); // Write the content
            writer.flush();
            writer.close();
            System.out.println("Inventory report exported successfully to Report.txt");
        }
        catch (IOException e)
        {
            // Handle any errors that occur during file writing
            System.out.println("An error occurred while writing the inventory report to file: " + e.getMessage());
        }

        // Print the report to console
        System.out.println(report.toString());
    }
}
