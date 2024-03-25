/**
 * Defines the TV device with specific attributes like name, price, and quantity.
 */
public class TV implements Device
{
    private final String category = "TV";
    private String name;
    private double price;
    private int quantity;

    /**
     * Represents a TV device with name, price, and quantity attributes, initializes the TV with the given details, Time complexity: O(1).
     *
     *
     * @param name The TV's name.
     * @param price The TV's price.
     * @param quantity Stock quantity.
     */
    public TV(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the TV's category, Time complexity: O(1).
     *
     * @return The category of the TV.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Gets the TV's name, Time complexity: O(1).
     *
     * @return The name of the TV.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Updates the TV's name, Time complexity: O(1).
     *
     * @param name The new name for the TV.
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Returns the TV's price, Time complexity: O(1).
     *
     * @return The price of the TV.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets a new price for the TV, Time complexity: O(1).
     *
     * @param price The new price of the TV.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Retrieves the quantity of the TV in stock, Time complexity: O(1).
     *
     * @return The stock quantity of the TV.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Adjusts the quantity of the TV in stock, Time complexity: O(1).
     *
     * @param quantity The new stock quantity of the TV.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
