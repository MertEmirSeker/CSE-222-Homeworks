/**
 * Defines the Smartwatch device with specific attributes like name, price, and quantity.
 */
public class Smartwatch implements Device
{
    private final String category = "Smart Watch";
    private String name;
    private double price;
    private int quantity;

    /**
     * Represents a Smartwatch device with name, price, and quantity attributes, initializes the Smartwatch with the given details, Time complexity: O(1).
     *
     *
     * @param name The Smartwatch's name.
     * @param price The Smartwatch's price.
     * @param quantity Stock quantity.
     */
    public Smartwatch(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the smartwatch's category, Time complexity: O(1).
     *
     * @return The category of the smartwatch.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Gets the smartwatch's name, Time complexity: O(1).
     *
     * @return The name of the smartwatch.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Updates the smartwatch's name, Time complexity: O(1).
     *
     * @param name The new name for the smartwatch.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the smartwatch's price, Time complexity: O(1).
     *
     * @return The price of the smartwatch.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets a new price for the smartwatch, Time complexity: O(1).
     *
     * @param price The new price of the smartwatch.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Retrieves the quantity of the smartwatch in stock, Time complexity: O(1).
     *
     * @return The stock quantity of the smartwatch.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Adjusts the quantity of the smartwatch in stock, Time complexity: O(1).
     *
     * @param quantity The new stock quantity of the smartwatch.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
