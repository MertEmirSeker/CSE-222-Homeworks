
public class Person 
{
	private String name;
	private String surname;
	private String address;
	private String phone;
	private int ID;
	
	
	
	// constructor for Person
	public Person(String name, String surname, String address, String phone, int ID)
	{
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.ID = ID;
	}
	
	
	
	
	// getter and setter for name
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		
		// getter and setter for surname
		public String getSurname()
		{
			return surname;
		}
		
		public void setSurname(String surname)
		{
			this.surname = surname;
		}
		
		
		// getter and setter for address
		public String getAddress()
		{
			return address;
		}
		
		public void setAddress(String address)
		{
			this.address = address;
		}
		
		
		// getter and setter for phone
		public String getPhone()
		{
			return phone;
		}
		
		public void setPhone(String phone)
		{
			this.phone = phone;
		}
		
		
		// getter and setter for ID
		public int getID()
		{
			return ID;
		}
		
		public void setID(int ID)
		{
			this.ID = ID;
		}	
	
	
	
}
