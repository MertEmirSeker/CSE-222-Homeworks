
public class Operator extends Person
{
	private int wage;
	private Customer[] customers;
	
	// constructor for Operator
	public Operator(String name, String surname, String address, String phone, int ID, int wage)
	{
		super(name, surname, address, phone, ID);
		this.wage = wage;
		this.setCustomers(new Customer[100]);
	}
	
	
	
	// print operator method
	public void print_operator()
	{
		System.out.println("Name & Surname: " + getName() + " " + getSurname());
		System.out.println("Address: " + getAddress());
		System.out.println("Phone: " + getPhone());
		System.out.println("ID: " + getID());
		System.out.println("Wage: " + getWage());
	}
	
	// print customer method
	public void print_customers()
	{
		int operatorHasCustomer = 0;
		
		for(int i = 0 ; i < getCustomers().length ; i++)
		{
			if(getCustomers()[i] != null)
			{
				operatorHasCustomer = 1;
				
				if(getCustomers()[i] instanceof CorporateCustomer)
					System.out.println("Customer #" + (i + 1) + " (a corporate customer):");
					
				else
					System.out.println("Customer #" + (i + 1) + " (a retail customer):");
				
				getCustomers()[i].print_customer();
				getCustomers()[i].print_orders();
			}
		}
		
		if(0 == operatorHasCustomer)
		{
			System.out.println("This operator doesn't have any customer.");
		    System.out.println("----------------------------");	}
		}
			
	
	// define customers method
	public void define_customers(Customer[] allCustomers)
	{
		int count = 0;
		
		for(int i = 0 ; i < allCustomers.length ; i++)
		{
			if(allCustomers[i] != null && allCustomers[i].getOperator_ID() == this.getID())
			{
				if(count < allCustomers.length)
				{
					getCustomers()[count] = allCustomers[i];
					count++;				
				}
				else
				{
					System.out.println("Customer limit reached!!!");
					break;				
				}
			}
		}
	}
	
	
	
	// getter and setter for wage
	public int getWage() 
	{
		return wage;
	}

	public void setWage(int wage)
	{
		this.wage = wage;
	}

	
	// getter and setter for customers array
	public Customer[] getCustomers() 
	{
		return customers;
	}

	public void setCustomers(Customer[] customers)
	{
		this.customers = customers;
	}
}
