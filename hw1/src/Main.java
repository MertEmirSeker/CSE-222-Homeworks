import java.io.File;
import java.util.Scanner;

public class Main
{
public static void main(String[] args)
    {
        System.out.println("Welcome to the customer tracking program!!!");
        Scanner inputScanner = new Scanner(System.in);

        // First we read the id as a string from terminal, then we convert it to int and check it.
        System.out.println("Please enter your ID...");
        String idString = inputScanner.nextLine();
        int id = 0;

        // Checking id contains only number.
        if (false == idString.matches("^-?\\d+$"))
        {
            System.out.println("ID must contain numbers only!!!");
            System.exit(-1);
        }

        try
        {

            // Converting the String to int.
            id = Integer.parseInt(idString);

            if (id <= 0)
            { // Checking whether it is 0 or negative.
                System.out.println("ID must be positive integer!!!");
                System.exit(-1);
            }
            // Throwing exeption for Integer too big situation.
        }
        catch (NumberFormatException e)
        {
            System.out.println("Integer value is too large for ID or not a valid integer!!!");
            System.exit(-1);
        }

        Operator[] operators = new Operator[100];
        Customer[] customers = new Customer[100];
        Order[] orders = new Order[100];
        int indexOp = 0, indexCu = 0, indexOr = 0;
        // Reading from file and checking some errors.
        try
        {
            File f = new File("content.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine())
            {
                // Reading every line.
                String readedLine = s.nextLine();

                // Checks if there are any semicolons in line.
                if (false == readedLine.contains(";"))
                {
                    System.out.println("There is no semicolon in the line!!!");
                    continue;
                }

                // Gives an error if there is an extra semicolon at the end of the line.
                if (true == readedLine.endsWith(";"))
                {
                    System.out.println("Missing column!!!");
                    continue;
                }

                // Spliting readed lines with semicolon.
                String[] parts = readedLine.split(";");

                // Checks if the space between semicolons is empty.
                for (int i = 0; i < parts.length; i++)
                {
                    if (parts[i].trim().isEmpty())
                    {
                        System.out.println("Empty value found in semicolon seperation!!!");
                        continue;
                    }
                }

                // Specific controls for the operator.
                if ("operator".equals(parts[0]) && parts.length == 7)
                {

                    String name = parts[1];
                    String surname = parts[2];
                    String address = parts[3];
                    String phone = parts[4];
                    String operator_IDString = parts[5];
                    String wageString = parts[6];
                    int operator_ID, wage;

                    // Specific String controls
                    // Checks that the name should only consist of letters.
                    if (false == name.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Name must contains letters only for operator!!!");
                        continue;
                    }

                    // Checks that the surname should only consist of letters.
                    else if (false == surname.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Surname must contains letters only for operator!!!");
                        continue;
                    }

                    // Checks that the address contains only letters, numbers and spaces.
                    else if (false == address.matches("[a-zA-Z0-9 ]+"))
                    {
                        System.out.println("Address must contain letters, numbers, and spaces only for operator!!!");
                        continue;
                    }

                    // Checks that the phone number must start with '+' and contain only numbers.
                    else if (false == phone.matches("^\\+[0-9]+"))
                    {
                        System.out.println("Phone number must start with '+' and contain numbers only for operator!!!");
                        continue;
                    }
                    // Checks that the operator ID must contain only numbers.
                    else if (false == operator_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Operator ID must contain numbers only for operator!!!");
                        continue;
                    }
                    // Checks that the wage must contain only numbers.
                    else if (false == wageString.matches("^-?\\d+$"))
                    {
                        System.out.println("Wage must contain numbers only for operator!!!");
                        continue;
                    }

                    // Spesific int controls
                    try
                    {
                        // Since we know it's only numbers, we can safely parse it.
                        operator_ID = Integer.parseInt(operator_IDString);
                        wage = Integer.parseInt(wageString);
                        // Checking if the numbers are negative.
                        if (operator_ID <= 0 || wage <= 0)
                        {
                            System.out.println("Integer values must be positive for operator!!!");
                            continue;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Integer value is too large for operator!!!");
                        continue;
                    }

                    // Checks if there is more than one operator with the same id.
                    int existID = 0;
                    for (int i = 0; i < indexOp; i++)
                    {
                        if (operators[i].getID() == operator_ID)
                        {
                            existID = 1;
                            break;
                        }
                    }

                    if (1 == existID)
                    {
                        System.out.println("Duplicate ID found for the operator: " + operator_ID + " !!!");
                        //System.exit(-1);
                        continue;
                    }

                    // If it passes all of these, it creates a new operator object.
                    else
                    {

                        operators[indexOp] = new Operator(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                        indexOp++;
                    }
                }

                // Specific controls for the retail customer.
                else if ("retail_customer".equals(parts[0]) && parts.length == 7)
                {
                    String name = parts[1];
                    String surname = parts[2];
                    String address = parts[3];
                    String phone = parts[4];
                    String customer_IDString = parts[5];
                    String operator_IDString = parts[6];
                    int operator_ID;
                    int customer_ID;

                    // Specific String controls
                    // Checks that the name should only consist of letters.
                    if (false == name.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Name must contains letters only for retail customer!!!");
                        continue;
                    }

                    // Checks that the surname should only consist of letters.
                    else if (false == surname.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Surname must contains letters only for retail customer!!!");
                        continue;
                    }

                    // Checks that the address contains only letters, numbers and spaces.
                    else if (false == address.matches("[a-zA-Z0-9 ]+"))
                    {
                        System.out.println(
                            "Address must contain letters, numbers, and spaces only for retail customer!!!");
                        continue;
                    }

                    // Checks that the phone number must start with '+' and contain only numbers.
                    else if (false == phone.matches("^\\+[0-9]+"))
                    {
                        System.out.println("Phone number must start with '+' and contain numbers only for retail customer!!!");
                        continue;
                    }
                    // Checks that the operator ID must contain only numbers.
                    else if (false == operator_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Operator ID must contain numbers only retail customer!!!");
                        continue;
                    }
                    // Checks that the Customer ID must contain only numbers.
                    else if (false == customer_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Customer ID must contain numbers only for retail customer!!!");
                        continue;
                    }

                    // Spesific int controls
                    try
                    {
                        // Since we know it's only numbers, we can safely parse it.
                        operator_ID = Integer.parseInt(operator_IDString);
                        customer_ID = Integer.parseInt(customer_IDString);

                        // Checking if the numbers are negative.
                        if (customer_ID <= 0 || operator_ID <= 0)
                        {
                            System.out.println("Integer values must be positive for retail customer!!!");
                            continue;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Integer value is too large for retail customer!!!");
                        continue;
                    }

                    // Checks if there is more than one customer with the same id.
                    int existID = 0;
                    for (int i = 0; i < indexCu; i++)
                    {
                        if (customers[i].getID() == customer_ID)
                        {
                            existID = 1;
                            break;
                        }
                    }

                    if (1 == existID)
                    {
                        System.out.println("Duplicate ID found for the customer: " + customer_ID + " !!!");
                        //System.exit(-1);
                        continue;
                    }

                    // If it passes all of these, it creates a new retail customer object.
                    else
                    {
                        customers[indexCu] = new RetailCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                        indexCu++;
                    }
                }

                // Specific controls for the corporate customer.
                else if ("corporate_customer".equals(parts[0]) && parts.length == 8)
                {

                    String name = parts[1];
                    String surname = parts[2];
                    String address = parts[3];
                    String phone = parts[4];
                    String customer_IDString = parts[5];
                    String operator_IDString = parts[6];
                    String company_name = parts[7];
                    int operator_ID;
                    int customer_ID;

                    // Specific String controls
                    // Checks that the name should only consist of letters.
                    if (false == name.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Name must contains letters only for corporate customer!!!");
                        continue;
                    }

                    // Checks that the surname should only consist of letters.
                    else if (false == surname.matches("[a-zA-Z ]+"))
                    {
                        System.out.println("Surname must contains letters only for corporate customer!!!");
                        continue;
                    }

                    // Checks that the address contains only letters, numbers and spaces.
                    else if (false == address.matches("[a-zA-Z0-9 ]+"))
                    {
                        System.out.println(
                            "Address must contain letters, numbers, and spaces only for corporate customer!!!");
                        continue;
                    }

                    // Checks that the company name contains only letters, numbers and spaces.
                    else if (false == company_name.matches("[a-zA-Z0-9 ]+"))
                    {
                        System.out.println(
                            "Company name must contain letters, numbers, and spaces only for corporate customer!!!");
                        continue;
                    }

                    // Checks that the phone number must start with '+' and contain only numbers.
                    else if (false == phone.matches("^\\+[0-9]+"))
                    {
                        System.out.println("Phone number must start with '+' and contain numbers only for corporate customer!!!");
                        continue;
                    }
                    // Checks that the operator ID must contain only numbers.
                    else if (false == operator_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Operator ID must contain numbers only corporate customer!!!");
                        continue;
                    }
                    // Checks that the Customer ID must contain only numbers.
                    else if (false == customer_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Customer ID must contain numbers only for corporate customer!!!");
                        continue;
                    }

                    // Spesific int controls
                    try
                    {
                        // Since we know it's only numbers, we can safely parse it.
                        operator_ID = Integer.parseInt(operator_IDString);
                        customer_ID = Integer.parseInt(customer_IDString);

                        // Checking if the numbers are negative.
                        if (customer_ID <= 0 || operator_ID <= 0)
                        {
                            System.out.println("Integer values must be positive for retail customer!!!");
                            continue;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Integer value is too large for corporate customer!!!");
                        continue;
                    }

                    // Checks if there is more than one customer with the same id.
                    int existID = 0;
                    for (int i = 0; i < indexCu; i++)
                    {
                        if (customers[i].getID() == customer_ID)
                        {
                            existID = 1;
                            break;
                        }
                    }

                    if (1 == existID)
                    {
                        System.out.println("Duplicate ID found for the customer: " + customer_ID + "!!!");
                        //System.exit(-1);
                        continue;
                    }

                    // If it passes all of these, it creates a new corporate customer object.
                    else
                    {
                        customers[indexCu] = new CorporateCustomer(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
                        indexCu++;
                    }
                }

                // Specific controls for the order.
                else if ("order".equals(parts[0]) && parts.length == 6)
                {

                    String product_name = parts[1];
                    String countString = parts[2];
                    String total_PriceString = parts[3];
                    String statusString = parts[4];
                    String customer_IDString = parts[5];
                    int count, total_price, status, customer_ID;

                    // Specific String control
                    // Checks that the name should only consist of letters.
                    if (false == product_name.matches("[a-zA-Z]+"))
                    {
                        System.out.println("Product name must contain letters only for order!!!");
                        continue;
                    }
                    // Checks that the count is only numbers.
                    else if (false == countString.matches("^-?\\d+$"))
                    {
                        System.out.println("Count must contain numbers only for order!!!");
                        continue;
                    }
                    // Checks that the total price is only numbers.
                    else if (false == total_PriceString.matches("^-?\\d+$"))
                    {
                        System.out.println("Total price must contain numbers only for order!!!");
                        continue;
                    }
                    // Checks that the status is only numbers.
                    else if (false == statusString.matches("^-?\\d+$"))
                    {
                        System.out.println("Status must contain numbers only for order!!!");
                        continue;
                    }
                    // Checks that the customer ID is only numbers.
                    else if (false == customer_IDString.matches("^-?\\d+$"))
                    {
                        System.out.println("Customer ID must contain numbers only for order!!!");
                        continue;
                    }

                    // Spesific int controls
                    try
                    {
                        count = Integer.parseInt(countString);
                        total_price = Integer.parseInt(total_PriceString);
                        status = Integer.parseInt(statusString);
                        customer_ID = Integer.parseInt(customer_IDString);

                        // Checking if the numbers are negative.
                        if (count < 0 || total_price <= 0 || status < 0 || customer_ID < 0)
                        {
                            System.out.println("Integer values must be positive for order!!!");
                            continue;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Integer value is too large for order!!!");
                        continue;
                    }

                    // If it passes all of these, it creates a new order object.
                    orders[indexOr] = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    indexOr++;
                }
                // Checks if you have the right number of columns.
                else if (("operator".equals(parts[0]) && parts.length != 7) || ("retail_customer".equals(parts[0]) && parts.length != 7) || ("corporate_customer".equals(parts[0]) && parts.length != 8) || ("order".equals(parts[0]) && parts.length != 6))
                {
                    System.out.println("Incorrect number of columns for " + parts[0] + "!!!");
                    continue;
                }
                // Checks for identifiers. (order, retail customer, corporate customer, operator)
                else
                {
                    System.out.println("Unknown Identifier!!!");
                    continue;
                }
            }

            s.close();
        }
        catch (Exception e)
        {
            System.out.println("File not found!!!");
            System.exit(0);
        }

        // Defining customers for operators.
        for (int i = 0; i < operators.length; i++)
            if (operators[i] != null)
                operators[i].define_customers(customers);

        // Defining orders for customers.
        for (int j = 0; j < customers.length; j++)
            if (customers[j] != null)
                customers[j].define_orders(orders);

        while (true)
        {

            int found = 0;
            // Printing operators with customers and orders.
            for (int i = 0; i < operators.length; i++)
            {
                if (operators[i] != null && id == operators[i].getID())
                {
                    System.out.println("*** Operator Screen ***");
                    System.out.println("----------------------------");
                    operators[i].print_operator();
                    System.out.println("----------------------------");
                    operators[i].print_customers();
                    found = 1;
                    break;
                }
            }

            // Printing customers with orders.
            if (0 == found)
            {
                for (int j = 0; j < customers.length; j++)
                {
                    if (customers[j] != null && id == customers[j].getID())
                    {
                        System.out.println("*** Customer Screen ***");
                        customers[j].print_customer();
                        customers[j].print_orders();
                        found = 1;
                        break;
                    }
                }
            }

            if (0 == found)
                System.out.println("No operator/customer was found with ID " + id + ". Please try again.");

            inputScanner.close();
            System.exit(0);
        }
    }
}
