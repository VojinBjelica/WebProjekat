package Controller;

import static spark.Spark.post;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Service.CustomerService;
import beans.Customer;
import beans.CustomerAdapter;
import beans.LocalDateAdapter;

public class CustomerController {
	
	public static CustomerService cs;
	public static Gson g;
	
	public CustomerController()
	{
		cs = new CustomerService();
		g = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                //.registerTypeAdapter(Customer.class, new CustomerAdapter())
                //.create();
				//.setPrettyPrinting()
		        //.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
		        .create();

	}
	
	
	
	public static void writeCustomers()
	{
		post("customer/write", (req, res) -> {
			cs.writeCustomers();
			return "OK";
		});
	}
	public static void addCustomerInFile()
	{
		post("customer/addInFile", (req, res) -> {
			cs.addCustomerInFile();
			
			return "OK";
		});
	}
	public static void addCustomer()
	{
		post("customer/add", (req, res) -> {
			String payload = req.body();
			System.out.println(payload);
			Customer pd = g.fromJson(payload, Customer.class);
			cs.addCustomer(pd);
			return "OK";
		});
	}
	

}
