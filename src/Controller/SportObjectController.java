package Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Service.CustomerService;
import Service.SportObjectService;
import beans.Coach;
import beans.Customer;
import beans.Manager;
import beans.RoleEnum;
import beans.SportObject;
import beans.User;
import spark.Session;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

public class SportObjectController {

	public static SportObjectService sos;
	public static CustomerService cs;
	public static Gson g;
	public static SportObject tempObject;
	public static String temp;
	public static Manager tempManager;
	
	public static ArrayList<SportObject> objList;
	
	public SportObjectController() {
		sos = new SportObjectService();
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
		objList = new ArrayList<SportObject>();
		tempObject = new SportObject();
		temp = "";
		tempManager = new Manager();
	}
	
	public static void readSportObjects() {
		get("sportObjects/read", (req, res) -> {
			return g.toJson(sos.readSportObjects());
		});
	}
	
	public static void getAvailableManagers() {
		get("sportObjects/availableManagers", (req, res) -> {
			System.out.println("Nasao slobodne menadzere");
			return g.toJson(cs.findAvailableManagers());
		});
	}
	
	public static void searchObjectsByName() {
		post("sportObjects/search", (req, res) -> {
			
			String payload = req.body();
			System.out.println(req.body());
			SportObject pd = g.fromJson(payload, SportObject.class);
			System.out.println(pd);
			ArrayList<SportObject> searchedList = sos.searchObjectsByName(pd.getObjectName());
			System.out.println(searchedList.size());
			for (SportObject s : searchedList) {
				
				System.out.println(s.getObjectName());
			}
			objList = searchedList;
			g.toJson(searchedList);
			return "OK";
		});
	}
	
	public static void filteredList() {
		get("sportObjects/show", (req, res) -> {
			
			System.out.println(sos.readSportObjects()); 
			System.out.println("Velicina:" + sos.filteredList(objList).size());
			return g.toJson(sos.filteredList(objList));
		});
	}
	public static void getTemp(String tmp)
	{
		temp = tmp;
		hide();
	}
	public static void hide() {
		post("sportObjects/hide", (req, res) -> {
			System.out.println("Bitan str: " + temp);
			String payload = req.body();
			String  pd = g.fromJson(payload, String.class);
			pd = temp;
			g.toJson(pd);
			return pd;
		});
	}
	
	public static void logoutCustomer()
	{
		get("sportObject/logout", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			User custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			
			System.out.println("Izlogovan:"  + custo.getName() + " " + custo.getSurname());
			if (user != null) {
				ss.invalidate();
			}
			return true;
		});
	}
	
	public static void getSelectedObject() {
		post("sportObject/getOne", (req, res) -> {
			String payload = req.body();
			SportObject so = g.fromJson(payload, SportObject.class);
			System.out.println("Selected object name: " + so.getObjectName());
			tempObject = so;
			return "OK";
		});
	}
	
	public static void showSelectedObject() {
		post("sportObject/showOne", (req, res) -> {
			System.out.println("temp obj:" + tempObject.getObjectName());
			return g.toJson(tempObject);
		});
	}
	
	public static void addViewInFile() {
		get("sportObject/addView", (req, res) -> {	
			System.out.println("Usao u dodavanje view");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			User custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			System.out.println("custov role:" + custo.getRole());
			ArrayList<Customer> tempList = cs.readCustomersView();
			int help = 0;
			for(Customer c : tempList)
			{
				if(c.getUsername().equals(custo.getUsername()))
				{
					help = 1;
				}
			}
			if(custo.getRole().equals(RoleEnum.Customer))
			{
				if(help == 0)
				{
					Customer cust = new Customer(custo.getUsername(),custo.getPassword(),custo.getName(),custo.getSurname(),custo.getDateOfBirth(),custo.getGender());
					cs.addViewSecret(cust, tempObject.getObjectName());
				}
			}
			return "View added";
		});
	}
	
	
	public static void addSportObject() {
		post("sportObjects/add", (req, res) -> {
			String payload = req.body();
			SportObject so = g.fromJson(payload, SportObject.class);
			System.out.println("USAO U DODAVANJE NOVOG SO");
			boolean valid = sos.addSportObject(so);
			System.out.println("*******************");
			System.out.println("TEMP MANAGER JE: " + tempManager.getName() + " " + tempManager.getSurname());
			System.out.println("Objekat SO je: " + so.getObjectName());
			if (valid) {
				cs.setManagerSportObject(tempManager.getUsername(), so.getObjectName());
			}
			return("OK");
		});
	}
	
	public static void setSportObjectManager() {
		post("sportObjects/addSOManager", (req, res) -> {
			String payload = req.body();
			Manager man = g.fromJson(payload, Manager.class);
			tempManager = man;
			return "OK";
		});
	}
	
	//Pokaze menadzerov sportski objekat
	public static void showManagersSportObject() {
		get("sportObjects/showManagersSO", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			SportObject sObject = sos.getSportObjectByManager(mana);
			return g.toJson(sObject);
		});
	}
	
	//Za pravljenje novog menadzera pri dodavanju sportskog objekta
	public static void addNewManager() {
		post("sportObject/addManager", (req, res) -> {
			String payload = req.body();
			User pd = g.fromJson(payload, User.class);
			Manager manager = new Manager(pd.getUsername(),pd.getPassword(),pd.getName(),pd.getSurname(),pd.getDateOfBirth(),pd.getGender(),RoleEnum.Manager);
			tempManager = manager;
			cs.addManagers(manager);
			return "OK";
		});
	}
	

}
