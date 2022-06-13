package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Customer;

public class CustomerService {
	
	CustomerFileStorage cfs = new CustomerFileStorage();
	public ArrayList<Customer> readCustomers()
	{
		return cfs.readCustomers();
	}
	
	public void writeCustomers()
	{
		cfs.writeCustomers();
	}

}
