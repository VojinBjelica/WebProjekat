package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;
import beans.WsHandler;

import java.io.File;

import Controller.CustomerController;
import Controller.SportObjectController;

public class main {

	public static void main(String[] args) throws Exception {
		port(8080);
		
		webSocket("/ws", WsHandler.class);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
		get("/test", (req, res) -> {
			return "Works";
		});
		new CustomerController();
		CustomerController.writeCustomers();
		CustomerController.addCustomer();
<<<<<<< HEAD
		CustomerController.loginCustomer();
		CustomerController.logoutCustomer();
=======
		
		new SportObjectController();
		SportObjectController.readSportObjects();
>>>>>>> 3ff221d6b50c168d53bccc0e7ce991247f471a13
		// TODO Auto-generated method stub

	}

}
