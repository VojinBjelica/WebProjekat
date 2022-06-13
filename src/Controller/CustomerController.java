package Controller;

import java.util.ArrayList;
import static spark.Spark.post;
import static spark.Spark.get;

import Service.CustomerService;
import beans.Customer;

public class CustomerController {
	
	public static CustomerService cs;
	public CustomerController()
	{
		cs = new CustomerService();
	}
	public static ArrayList<Customer> readCustomers()
	{
		return cs.readCustomers();
	}
	
	public static void writeCustomers()
	{
		post("customer/write", (req, res) -> {
			System.out.println("Usao u post");
			cs.writeCustomers();
			
			return "OK";
		});
	}

}
