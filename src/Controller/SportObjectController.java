package Controller;

import com.google.gson.Gson;

import Service.CustomerService;
import Service.SportObjectService;
import beans.Customer;
import beans.Manager;
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
		g = new Gson();
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
	
	public static void addSportObject() {
		post("sportObjects/add", (req, res) -> {
			String payload = req.body();
			SportObject so = g.fromJson(payload, SportObject.class);
			sos.addSportObject(so);
			cs.setManagerSportObject(tempManager.getUsername(), so.getObjectName());
			return("OK");
		});
	}
	
	public static void setSportObjectManager() {
		post("sportObjects/addSOManager", (req, res) -> {
			String payload = req.body();
			Manager man = g.fromJson(payload, Manager.class);
			tempManager = man;
			return "ok";
		});
	}
	
	//dodao i ovo sad
	public static void showManagersSportObject() {
		get("sportObjects/showManagersSO", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			SportObject sObject = sos.getSportObjectByManager(mana);
			return g.toJson(sObject);
		});
	}
	
}
