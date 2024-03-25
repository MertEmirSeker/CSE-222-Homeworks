/**
 * Defines the Headphone device with specific attributes like name, price, and quantity.
 */

public class Headphone implements Device
{
    private final String category = "Headphones";
    private String name;
    private double price;
    private int quantity;

    /**
     * Represents a Headphone device with name, price, and quantity attributes, initializes the Headphone with the given details, Time complexity: O(1).
     *
     *
     * @param name The headphone's name.
     * @param price The headphone's price.
     * @param quantity Stock quantity.
     */
    public Headphone(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the device's category with a time complexity of O(1).
     *
     * @return The category as a String.
     */

    public String getCategory()
    {
        return category;
    }

    /**
     * Returns the device's name with a time complexity of O(1).
     *
     * @return The name of the device as a String.
     */

    public String getName()
    {
        return name;
    }

    /**
     * Sets the device's name with a time complexity of O(1).
     *
     * @param name The new name of the device.
     */

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the device's price with a time complexity of O(1).
     *
     * @return The price of the device as a double.
     */

    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the device's price with a time complexity of O(1).
     *
     * @param price The new price of the device.
     */

    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Returns the device's quantity with a time complexity of O(1).
     *
     * @return The quantity of the device as an int.
     */

    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets the device's quantity with a time complexity of O(1).
     *
     * @param quantity The new quantity of the device.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
