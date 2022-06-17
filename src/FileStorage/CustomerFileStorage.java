package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import beans.Customer;
import beans.GenderEnum;
import beans.User;

public class CustomerFileStorage {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public ArrayList<Customer> readCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./customers.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();             
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Customer customer = new Customer(username,password,name,surname, dt,gen);
                    customers.add(customer);
                    customerList = customers;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                }
                catch (Exception e) { }
            }
        }
        return customers;
    }
	public void writeCustomers()
	{
		for(Customer c : readCustomers())
		{
			System.out.println(c.getName() + " " + c.getSurname());
		}
	}
	public boolean addCustomerInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./customers.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        System.out.println("duzina liste: " + customerList.size());
        for(Customer customer : customerList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public Customer addCustomer(Customer customer)
	{
		customerList = readCustomers();
		customerList.add(customer);
		addCustomerInFile();
		return customer;
	}
	
	public Customer loginUser(Customer customer)
	{
		ArrayList<Customer> customerList = readCustomers();
		Customer cust = null;
		for(Customer c : customerList)
		{
			if(c.getUsername().equals(customer.getUsername()) && c.getPassword().equals(customer.getPassword()))
			{
				cust = c;
			}
		}
		return cust;
	}
	public Customer findCustomerByUsernameAndPassword(String username,String password)
	{
		ArrayList<Customer> customerList = readCustomers();
		Customer cust = null;
		for(Customer c : customerList)
		{
			if(c.getUsername().equals(username) && c.getPassword().equals(password))
			{
				cust = c;
			}
		}
		return cust;
	}
	

}
