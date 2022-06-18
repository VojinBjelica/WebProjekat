package Controller;

import com.google.gson.Gson;

import Service.SportObjectService;
import static spark.Spark.get;

public class SportObjectController {

	public static SportObjectService sos;
	public static Gson g;
	
	public SportObjectController() {
		sos = new SportObjectService();
		g = new Gson();
	}
	
	public static void readSportObjects() {
		get("sportObjects/read", (req, res) -> {
			System.out.println(sos.readSportObjects()); 
			return g.toJson(sos.readSportObjects());
		});
	}
	
}
