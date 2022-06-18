package Controller;

import com.google.gson.Gson;

import Service.SportObjectService;
import beans.Customer;
import beans.SportObject;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

public class SportObjectController {

	public static SportObjectService sos;
	public static Gson g;
	
	public static ArrayList<SportObject> objList;
	
	public SportObjectController() {
		sos = new SportObjectService();
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
	
}
