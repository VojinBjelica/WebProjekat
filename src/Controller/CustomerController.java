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
import beans.Coach;
import beans.Customer;
import beans.CustomerAdapter;
import beans.LocalDateAdapter;
import beans.Manager;
import beans.RoleEnum;
import beans.SportObject;
import beans.User;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class CustomerController {
	
	public static CustomerService cs;
	public static Gson g;
	public static String temp;
	public static String temptwo;
	
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
		temp = "Customer";
		temptwo = "";

	}
	
	
	
	public static void writeCustomers()
	{
		post("customer/write", (req, res) -> {
			cs.writeCustomers();
			return "OK";
		});
	}
	public static void initList() {
		get("customer/listinit", (req, res) -> {
			return g.toJson(cs.readUsers());
		});
	}
	public static void showCoachesInObject() {
		get("customer/showObjectCoaches", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			ArrayList<Coach> retList = new ArrayList<Coach>();
			retList = cs.findCoachesByObject(mana.getSportObject());
			System.out.println(retList.size());
			return g.toJson(retList);
		});
	}
	public static void findViewers() {
		get("customer/getViewers", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			ArrayList<Customer> retList = new ArrayList<Customer>();
			retList = cs.findViewers(mana.getSportObject());
			System.out.println(retList.size());
			return g.toJson(retList);
		});
	}
	public static void readListUser() {
		get("customer/showuserlist", (req, res) -> {
			ArrayList<User> userList = cs.readUsers();
			String hashPass = null;

			for(User u : userList)
			{
				
				for(char c : u.getPassword().toCharArray()) {
				    // process c
					hashPass = u.getPassword().replaceAll(u.getPassword(),"Hidden");
				}
				u.setPassword(hashPass);
				
				
			}
			return g.toJson(userList);
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
			System.out.println("Usao u add");
			String payload = req.body();
			Customer pd = g.fromJson(payload, Customer.class);
			cs.addCustomer(pd);
			return "OK";
		});
	}
	public static void addCoach()
	{
		post("customer/add", (req, res) -> {
			System.out.println("Usao u add");
			String payload = req.body();
			Customer pd = g.fromJson(payload, Customer.class);
			cs.addCustomer(pd);
			return "OK";
		});
	}
	public static void editProfile()
	{
		post("customer/editprofile", (req, res) -> {
			String payload = req.body();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			User pd = g.fromJson(payload, User.class);
			cs.editProfile(user,pd);
			return "OK";
		});
	}
	public static void addUser()
	{
		post("customer/add", (req, res) -> {
			String payload = req.body();
			System.out.println("Usao u add user");
			User pd = g.fromJson(payload, User.class);
			System.out.println("Uloga:" + pd.getRole());
			Coach coach = new Coach(pd.getUsername(),pd.getPassword(),pd.getName(),pd.getSurname(),pd.getDateOfBirth(),pd.getGender(),RoleEnum.Coach);
			Manager manager = new Manager(pd.getUsername(),pd.getPassword(),pd.getName(),pd.getSurname(),pd.getDateOfBirth(),pd.getGender(),RoleEnum.Manager);
			Customer customer = new Customer(pd.getUsername(),pd.getPassword(),pd.getName(),pd.getSurname(),pd.getDateOfBirth(),pd.getGender(),RoleEnum.Customer);
			if(pd.getRole() == RoleEnum.Manager)
				cs.addManagers(manager);
			else if(pd.getRole() == RoleEnum.Customer)
			{
				cs.addCustomer(customer);
			}
			else 
			{
				cs.addCoach(coach);
			}
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String role = "";
			for(User u : cs.readUsers())
			{
				if(user.getUsername().equals(u.getUsername()))
				{
					role = u.getRole() + "";
				}
			}
			return role;
		});
	}
	public static void setCookie(Response res,Request req,String username) {
		String cookie = hasCookie(req,username);
		if(cookie == null)
		{
        String id = UUID.randomUUID().toString();
        res.cookie(username, id, 3600, false, true);
        System.out.println("User cookie:"+  req.cookie(username));
        System.out.println("Added cookie");
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
	public static void hidee() {
		post("customer/hidecombo", (req, res) -> {
			
			String payload = req.body();
			String  pd = g.fromJson(payload, String.class);

			System.out.println(temp + "roleeeee");
			pd = temp;
			System.out.println(temp + "role");
			g.toJson(pd);
			return pd;
		});
	}
	public static void loginCustomer()
	{
		post("customer/login", (req, res) -> {
			res.type("application/json");
			String payload = req.body();
			String s = "";
			User u = g.fromJson(payload, User.class);
			User cust = cs.loginUser(u);

			if(cust != null)
			{
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if (user == null) {
				user = u;
				ss.attribute("user", user);
			}
			
			User custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			System.out.println("Login user: " + custo.getName() + " " + custo.getSurname());
			System.out.println("Role:"+ custo.getRole());
			String role = "";
			if(custo.getRole() == RoleEnum.Administrator)
				{
				role = "Administrator";
				}
			else if(custo.getRole() == RoleEnum.Coach)role = "Coach";
			else if(custo.getRole() == RoleEnum.Manager)role = "Manager";
			else role = "Customer";
			
			//System.out.println(user.getPassword() + " " + user.getUsername());
			setCookie(res,req,custo.getUsername());
			getCookie(req,custo.getUsername());
			g.toJson(user);
			temp = role;
			temptwo = role;
			s = "logged";
			System.out.println(role + " role za hide");
			CustomerController.hidee();
			SportObjectController.getTemp(s + " "+temp);
			return role;
			}
			s="login";
			temp = "Customer";
			CustomerController.hidee();
			SportObjectController.getTemp(s + " "+temp);
			return s;
			
			
				
			
		});
	}
	
	
	
	
	
	public static void logoutCustomer()
	{
		get("customer/logout", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			temp = "Customer";
			User custo = null;
			if (user != null) {
				custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
				System.out.println("Izlogovan:"  + custo.getName() + " " + custo.getSurname());

			}
			
			if (user != null) {
				ss.invalidate();
			}
			return true;
		});
	}
	
}
