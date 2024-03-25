/**
 * Defines the Smartphone device with specific attributes like name, price, and quantity.
 */
public class Smartphone implements Device
{
    private final String category = "Smart Phone";
    private String name;
    private double price;
    private int quantity;

    /**
     * Represents a Smartphone device with name, price, and quantity attributes, initializes the Smartphone with the given details, Time complexity: O(1).
     *
     *
     * @param name The Smartphone's name.
     * @param price The Smartphone's price.
     * @param quantity Stock quantity.
     */
    public Smartphone(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the smartphone's category, Time complexity: O(1).
     *
     * @return The category of the smartphone.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Returns the smartphone's name, Time complexity: O(1).
     *
     * @return The name of the smartphone.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the smartphone's name, Time complexity: O(1).
     *
     * @param name The new name of the smartphone.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the smartphone's price, Time complexity: O(1).
     *
     * @return The price of the smartphone.
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the smartphone's price, Time complexity: O(1).
     *
     * @param price The new price of the smartphone.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Returns the smartphone's stock quantity, Time complexity: O(1).
     *
     * @return The quantity of the smartphone.
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets the smartphone's stock quantity, Time complexity: O(1).
     *
     * @param quantity The new quantity of the smartphone.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
