package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Customer;
import beans.Manager;
import beans.User;

public class CustomerService {
	
	public CustomerFileStorage cfs;
	public CustomerService()
	{
		cfs = new CustomerFileStorage();
	}
	public ArrayList<Customer> readCustomers()
	{
		return cfs.readCustomers("customers");
	}
	public ArrayList<Manager> readManagers()
	{
		return cfs.readManagers("managers");
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
		return cfs.addCustomerInFile("customers");
	}
	public boolean addManagersInFile()
	{
		return cfs.addManagersInFile("managers");
	}
	public Customer addCustomer(Customer customer)
	{
		return cfs.addCustomer(customer);
	}
	public Manager addManagers(Manager customer)
	{
		return cfs.addManager(customer);
	}
	public User loginUser(User customer)
	{
		return cfs.loginUser(customer);
	}
	public User findCustomerByUsernameAndPassword(String username,String password)
	{
		return cfs.findCustomerByUsernameAndPassword(username, password);
	}
	

}
