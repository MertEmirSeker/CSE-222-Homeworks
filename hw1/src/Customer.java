public class Customer extends Person
{
	private Order[] orders;
	private int operator_ID;
	
	// constructor for Customer
    public Customer(String name, String surname, String address, String phone, int ID, int operator_ID)
    {
    	super(name,surname,address,phone,ID);
    	this.operator_ID = operator_ID;
    	this.orders = new Order[100];
    }
	
    // print customer method
	public void print_customer()
	{
		System.out.println("Name & Surname: " + getName() + " " + getSurname());
		System.out.println("Address: " + getAddress());
		System.out.println("Phone: " + getPhone());
		System.out.println("ID: " + getID());
		System.out.println("Operator ID: " + getOperator_ID());
		
	}
	
	// print orders method
	public void print_orders()
	{
		int customerHasOrder = 0;

		if( null == getOrders() || 0 == getOrders().length)
		{
			System.err.println("This customer has no orders.");
			return;
		}
		
		
		for(int i = 0 ; i < getOrders().length ; i++)
		{
			if(getOrders()[i] != null && getOrders()[i].getCount() > 0)
			{
				customerHasOrder = 1;
				System.out.print("Order #" + (i+1) + " => ");
				getOrders()[i].print_order();
			}

		}
		
		if(0 == customerHasOrder)
			System.out.println("No valid orders found for this customer.");

		
		System.out.println("----------------------------");
	}
	
	// define orders method
	public void define_orders(Order[] allOrders)
	{
		int count = 0;
		
		for(int i = 0 ; i < allOrders.length ; i++)
		{
			if(allOrders[i] != null && allOrders[i].getCustomer_ID() == this.getID())
			{
				if(count < getOrders().length)
				{
					getOrders()[count] = allOrders[i];
					count++;
				}
				else
					break;
				
			}
		}


		
		if(0 == count)
            System.out.println("No orders found for customer ID: " + getID());
	
		else if(count == this.getOrders().length)
			System.out.println("Maximum order limit reached for customer: " + this.getID());
		
	
	}
	
	
	
	// getter and setter for orders
	public Order[] getOrders()
	{
		return orders;
	}
	
	public void setOrders(Order[] orders)
	{
		this.orders = orders;
	}
	
	// getter and setter for operator_ID
	public int getOperator_ID()
	{
		return operator_ID;
	}
	
	public void setOperator_ID(int operator_ID)
	{
		this.operator_ID = operator_ID;
	}	
}
