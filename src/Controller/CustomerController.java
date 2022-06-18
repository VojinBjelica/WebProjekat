package Controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Service.CustomerService;
import beans.Customer;
import beans.CustomerAdapter;
import beans.LocalDateAdapter;
import beans.User;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

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
	public static void setCookie(Response res,Request req,String username) {
		String cookie = hasCookie(req,username);
		if(cookie == null)
		{
        String id = UUID.randomUUID().toString();
        res.cookie(username, id, 3600, false, true);
        System.out.println("User cookie:"+  req.cookie(username));
        System.out.println("Dodeljen cookie");
		}
		else
		{
			System.out.println("User cookie:"+  req.cookie(username));
		}
		
 
    }
	public static String hasCookie(Request req,String username) {
		
        String cookie = req.cookie(username);
        return cookie;
	}
	public static void getCookie(Request req,String username) {
	
	        String cookie = req.cookie(username);
    }
	public static void loginCustomer()
	{
		post("customer/login", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			Customer u = g.fromJson(payload, Customer.class);
			Customer cust = cs.loginUser(u);
			if(cust != null)
			{
			Session ss = req.session(true);
			Customer user = ss.attribute("user");
			if (user == null) {
				user = u;
				ss.attribute("user", user);
			}
			Customer custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			System.out.println("Login user: " + custo.getName() + " " + custo.getSurname());
			
			//System.out.println(user.getPassword() + " " + user.getUsername());
			setCookie(res,req,custo.getUsername());
			getCookie(req,custo.getUsername());
			g.toJson(user);
			
			return "Success login";
			}
			return "Unsuccess login";
			
			
				
			
		});
	}
	
		
	
	
	public static void logoutCustomer()
	{
		get("customer/logout", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			Customer user = ss.attribute("user");
			Customer custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			
			System.out.println("Izlogovan:"  + custo.getName() + " " + custo.getSurname());
			if (user != null) {
				ss.invalidate();
			}
			return true;
		});
	}
	
}
