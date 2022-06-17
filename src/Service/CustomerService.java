package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Customer;

public class CustomerService {
	
	public CustomerFileStorage cfs;
	public CustomerService()
	{
		cfs = new CustomerFileStorage();
	}
	public ArrayList<Customer> readCustomers()
	{
		return cfs.readCustomers();
	}
	public void writeCustomers()
	{
		cfs.writeCustomers();
	}
	public boolean addCustomerInFile()
	{
		return cfs.addCustomerInFile();
	}
	public Customer addCustomer(Customer customer)
	{
		return cfs.addCustomer(customer);
	}
	public Customer loginUser(Customer customer)
	{
		return cfs.loginUser(customer);
	}
	public Customer findCustomerByUsernameAndPassword(String username,String password)
	{
		return cfs.findCustomerByUsernameAndPassword(username, password);
	}
	

}