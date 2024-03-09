
public class Order 
{
	private String product_name;
	private int count;
	private int total_price;
	private int status;
	private int customer_ID;
	
	
	
	// constructor for Order
    public Order(String product_name, int count, int total_price, int status, int customer_ID) 
    {
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        this.customer_ID = customer_ID;
    }
    
    // print order method
    public void print_order()
    {
    	String statusString;
    	if(0 == getStatus())
    		statusString = "Initialized.";
    	else if(1 == getStatus())
    		statusString = "Processing.";
    	else if(2 == getStatus())
    		statusString = "Completed.";
    	else if(3 == getStatus())
    		statusString = "Cancelled.";
    	else
    		statusString = "Unknown Status Type!!!";
    	
    	
    	System.out.println("Product name: " + getProduct_name() + 
    						" - Count: " + getCount() + 
    						" - Total price: " + getTotal_price() + 
    						" - Status: " + statusString);
    	
    }
	

	
	// getter and setter for product_name
	public String getProduct_name()
	{
		return product_name;
	}
	
	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
	}
	
	// getter and setter for count
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	// getter and setter for total_price
	public int getTotal_price()
	{
		return total_price;
	}
	
	public void setTotal_price(int total_price)
	{
		this.total_price = total_price;
	}
	
	// getter and setter for status
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	// getter and setter for customer_ID
	public int getCustomer_ID()
	{
		return customer_ID;
	}
	
	public void setCustomer_ID(int customer_ID)
	{
		this.customer_ID = customer_ID;
	}	
	
}
