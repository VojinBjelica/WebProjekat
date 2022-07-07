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
import beans.Dues;
import beans.DuesTypeEnum;
import beans.LocalDateAdapter;
import beans.Manager;
import beans.PromoCode;
import beans.RoleEnum;
import beans.SportObject;
import beans.Training;
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
	public static void calculatePoints()
	{
		post("customer/calculatePoints", (req, res) -> {
			cs.calculatePoints();
			return "OK";
		});
	}
	public static void addDue()
	{
		post("customer/due", (req, res) -> {
			String payload = req.body();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			User u = cs.findUserByUsername(user.getUsername());
			boolean checker = cs.dueActive(user.getUsername());
			Customer c = new Customer(u.getUsername(),u.getPassword(),u.getName(),u.getSurname(),u.getDateOfBirth(),u.getGender(),"Customer");
			Dues pd = g.fromJson(payload, Dues.class);
			String duestype = "";
			String id = cs.generateID();
			if(checker == true) return "Your due is still active";
			else
			{
				Dues due = null;
				if(pd.getDuesType() == DuesTypeEnum.Month)
					due = new Dues(id,pd.getDuesType(),pd.getPayDate(),pd.getExpirationDateAndTime(),pd.getPrice(),c,true,30,0);
				else
					due = new Dues(id,pd.getDuesType(),pd.getPayDate(),pd.getExpirationDateAndTime(),pd.getPrice(),c,true,365,0);
				
				cs.addDues(due);
			}
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
	public static void showCustomerTrainings() {
		get("customer/showcustomertrainings", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			ArrayList<Training> retList = new ArrayList<Training>();
			User u = cs.findUserByUsername(user.getUsername());
			
			if(u.getRole() == RoleEnum.Customer)
			{
				
				retList = cs.findTrainingsForCustomer(u);
				System.out.println(retList.size() + " duzina liste treninga za " + u.getName());
				
			}
			System.out.println(retList.size() + " duzina liste treninga 2 za " + u.getName());
			
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
	public static void findDiscount()
	{
		post("customer/promoDisc", (req, res) -> {
			String payload = req.body();
			System.out.println("Usao u discount");
			Dues pd = g.fromJson(payload, Dues.class);
			int newPrice = 0;
			int helpPrice = 0;
			Session ss = req.session(true);
			User user = ss.attribute("user");
			boolean checker = cs.dueActive(user.getUsername());
			if(pd.getDuesType() == DuesTypeEnum.Month)helpPrice = 2500;
			else helpPrice = 25000;
			if(checker == true) System.out.println("Your due is still active");
			else
			{
			if(pd.getPrice() == 2500 || pd.getPrice() == 25000)
			{
				for(PromoCode pc : cs.readPromoCodes())
				{
					if(pc.getPromoCodeName().equals(pd.getPromoCode()))
					{
						if(pc.getUsed() <= pc.getNumberOfUsing())
						{
						newPrice = pd.getPrice() - (pd.getPrice() * pc.getDiscount() / 100);
						cs.usedCode(pc.getPromoCodeName());
						
						}
						else System.out.println("Vec je iskoriscen kod"); 
					}
				}

				System.out.println("Nasao cenu:" + newPrice);
				Dues retDue = new Dues(pd.getID(),pd.getDuesType(),pd.getPayDate(),pd.getExpirationDateAndTime(),newPrice,pd.getCustomer(),pd.isStatus(),pd.getNumberOfAppointments(),pd.getPromoCode());
				return g.toJson(retDue);
			}
			else System.out.println("Nemoj biti bezobrazan vec si iskoristio popust");   
			}
			return null;
			
		});
	}
	public static void cancelTraining()
	{
		post("customer/cancelTraining", (req, res) -> {
			String payload = req.body();
			Training pd = g.fromJson(payload, Training.class);
			System.out.println(pd.getId());
			Training t = cs.findTrainingById(pd.getId());
			System.out.println(t.getName());
			cs.cancelTraining(t);
			
			return "OK";
		});
	}
	public static void addPromoCode()
	{
		post("customer/addPromoCode", (req, res) -> {
			String payload = req.body();
			PromoCode pd = g.fromJson(payload, PromoCode.class);
			PromoCode pc = new PromoCode(pd.getPromoCodeName(),pd.getFromDate(),pd.getToDate(),pd.getNumberOfUsing(),pd.getDiscount(),0);
			cs.addPromoCode(pc);
			return "OK";
		});
	}
	public static void getMyTrainings()
	{
		get("customer/mytrainings", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			myTrainingList = cs.findTrainingsForCoach(cs.getCoachByUsername(user.getUsername()));
			System.out.println(myTrainingList.size());
			return g.toJson(myTrainingList);
		});
	}
	public static void getMyPersonal()
	{
		get("customer/mypersonal", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			myTrainingList = cs.findPersonalForCoach(cs.getCoachByUsername(user.getUsername()));
			System.out.println(myTrainingList.size());
			return g.toJson(myTrainingList);
		});
	}
	public static void getMyGroup()
	{
		get("customer/mygroup", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			myTrainingList = cs.findGroupForCoach(cs.getCoachByUsername(user.getUsername()));
			System.out.println(myTrainingList.size());
			return g.toJson(myTrainingList);
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
			if(user != null)
			{
				for(User u : cs.readUsers())
				{
					if(user.getUsername().equals(u.getUsername()))
					{
						role = u.getRole() + "";
					}
				}
			}
			else
			{
				role = "Customer";
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
	
	
	
	public static void getAllTrainers() {
		get("customers/getAllTrainers", (req, res) -> {
			return g.toJson(cs.readCoaches());
		});
	}
	
	public static void addTraining() {
		post("customers/addTraining", (req, res) -> {
			String payload = req.body();
			Training tr = g.fromJson(payload, Training.class);
			cs.addTraining(tr);
			return("OK");
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
