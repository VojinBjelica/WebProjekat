package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Coach;
import beans.Customer;
import beans.Manager;
import beans.SportObject;
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
	public ArrayList<Customer> readCustomersView() {
		return cfs.readCustomersView();
	}
	public ArrayList<Manager> readManagers()
	{
		return cfs.readManagers("managers");
	}
	public ArrayList<Coach> readCoaches()
	{
		return cfs.readCoaches();
	}
	public User editProfile(User user,User usertwo)
	{
		return cfs.editProfile(user,usertwo);
	}
	public void writeCustomers()
	{
		cfs.writeCustomers();
	}
	public ArrayList<Coach> findCoachesByObject(String sobject)
	{
		return cfs.findCoachesByObject(sobject);
	}
	public ArrayList<Customer> findViewers(String sobject)
	{
		return cfs.findViewers(sobject);
	}
	public boolean addCustomerViewInFile() 
    {
		return cfs.addCustomerViewInFile();
    }
	public boolean addViewSecret(Customer cust, String objectName)
	{
		return cfs.addViewSecret(cust, objectName);
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
	public Coach addCoach(Coach customer)
	{
		return cfs.addCoach(customer);
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
	
	public Manager findManagerByUsername(String username) {
		return cfs.findManagerByUsername(username);
	}
	
	public ArrayList<Manager> findAvailableManagers(){
		return cfs.findAvailableManagers();
	}
	
	public Manager setManagerSportObject(String username, String sportObject) {
		return cfs.setManagerSportObject(username, sportObject);
	}

}
