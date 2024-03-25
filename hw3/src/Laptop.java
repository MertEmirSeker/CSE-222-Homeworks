/**
 * Defines the Laptop device with specific attributes like name, price, and quantity.
 */
public class Laptop implements Device
{
    private final String category = "Laptop";
    private String name;
    private double price;
    private int quantity;

    /**
     * Represents a Laptop device with name, price, and quantity attributes, initializes the Laptop with the given details, Time complexity: O(1).
     *
     *
     * @param name The laptop's name.
     * @param price The laptop's price.
     * @param quantity The quantity in stock.
     */
    public Laptop(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the laptop's category, Time complexity: O(1).
     *
     * @return The category of the laptop.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Gets the laptop's name, Time complexity: O(1).
     *
     * @return The name of the laptop.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the laptop's name, Time complexity: O(1).
     *
     * @param name The new name of the laptop.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the laptop's price, Time complexity: O(1).
     *
     * @return The price of the laptop.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the laptop's price, Time complexity: O(1).
     *
     * @param price The new price of the laptop.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Gets the laptop's quantity in stock, Time complexity: O(1).
     *
     * @return The quantity of the laptop.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets the laptop's quantity in stock, Time complexity: O(1).
     *
     * @param quantity The new quantity of the laptop.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
