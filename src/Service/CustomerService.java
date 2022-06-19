package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Customer;
import beans.User;

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
	public ArrayList<User> readUsers() {
		return cfs.readUsers();
	}
	public boolean addCustomerInFile()
	{
		return cfs.addCustomerInFile();
	}
	public Customer addCustomer(Customer customer)
	{
		return cfs.addCustomer(customer);
	}
	public User loginUser(User customer)
	{
		return cfs.loginUser(customer);
	}
	public Customer findCustomerByUsernameAndPassword(String username,String password)
	{
		return cfs.findCustomerByUsernameAndPassword(username, password);
	}
	

}
