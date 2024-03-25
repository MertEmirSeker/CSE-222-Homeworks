/**
 * Represents a generic device interface detailing essential properties and actions that all devices must implement.
 *
 */

public interface Device
{
    /**
     * Retrieves the category of the device, Time complexity: O(1).
     *
     * @return The category of the device as a String.
     */
    public String getCategory();

    /**
     * Retrieves the name of the device, Time complexity: O(1).
     *
     * @return The name of the device as a String.
     */
    public String getName();

    /**
     * Sets the name of the device, Time complexity: O(1).
     *
     * @param name The new name for the device.
     */
    public void setName(String name);

    /**
     * Retrieves the price of the device, Time complexity: O(1).
     *
     * @return The price of the device as a double.
     */
    public double getPrice();

    /**
     * Sets the price of the device, Time complexity: O(1).
     *
     * @param price The new price for the device.
     */
    public void setPrice(double price);

    /**
     * Retrieves the quantity of the device in stock, Time complexity: O(1).
     *
     * @return The stock quantity of the device as an int.
     */
    public int getQuantity();

    /**
     * Sets the stock quantity of the device, Time complexity: O(1).
     *
     * @param quantity The new stock quantity for the device.
     */
    public void setQuantity(int quantity);
}
