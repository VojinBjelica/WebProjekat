package Controller;

import static spark.Spark.post;

import java.util.ArrayList;

import Service.CustomerService;
import beans.Customer;

public class CustomerController {
	
	public static CustomerService cs;
	
	public CustomerController()
	{
		cs = new CustomerService();
	}
	
	public static void writeCustomers()
	{
		post("customer/write", (req, res) -> {
			cs.writeCustomers();
			return "OK";
		});
	}

}
