
public class CorporateCustomer extends Customer 
{
	private String company_name;
	
	
	// constructor for CorporateCustomer
    public CorporateCustomer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) 
    {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }
    
    // method print_customer
    @Override
 	public void print_customer()
 	{
    	super.print_customer();
    	System.out.println("Company name: " + getCompany_name());

 	}
    
    // getter and setter for company_name
    public String getCompany_name()
    {
    	return company_name;
    }
    
    public void setCompany_name(String company_name)
    {
    	this.company_name = company_name;
    }
}
