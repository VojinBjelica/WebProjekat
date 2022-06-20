package Controller;

import com.google.gson.Gson;

import Service.CustomerService;
import Service.SportObjectService;
import beans.Customer;
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
	
	public static ArrayList<SportObject> objList;
	
	public SportObjectController() {
		sos = new SportObjectService();
		cs = new CustomerService();
		g = new Gson();
		objList = new ArrayList<SportObject>();
	}
	
	public static void readSportObjects() {
		get("sportObjects/read", (req, res) -> {
			System.out.println(sos.readSportObjects()); 
			return g.toJson(sos.readSportObjects());
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
	
	public static void hide(String str) {
			post("sportObjects/hide", (req, res) -> {
			
			String payload = req.body();
			String  pd = g.fromJson(payload, String.class);
			pd = str;
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
	
}
