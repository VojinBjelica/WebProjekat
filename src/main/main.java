package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;

import Controller.CustomerController;
import Controller.SportObjectController;

public class main {

	public static void main(String[] args) throws Exception {
		port(8080);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
		get("/test", (req, res) -> {
			return "Works";
		});
		new CustomerController();
		CustomerController.writeCustomers();
		CustomerController.addCustomer();
		
		new SportObjectController();
		SportObjectController.readSportObjects();
		// TODO Auto-generated method stub

	}

}
