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
		CustomerController.addUser();
		CustomerController.loginCustomer();
		CustomerController.logoutCustomer();
		CustomerController.initList();
		CustomerController.editProfile();
		CustomerController.readListUser();
		CustomerController.showCoachesInObject();
		CustomerController.findViewers();
		
		new SportObjectController();
		SportObjectController.readSportObjects();
		SportObjectController.searchObjectsByName();
		SportObjectController.filteredList();
		SportObjectController.logoutCustomer();
		SportObjectController.getSelectedObject();
		SportObjectController.showSelectedObject();
		SportObjectController.addSportObject();
		SportObjectController.showManagersSportObject();
		SportObjectController.getAvailableManagers();
		SportObjectController.setSportObjectManager();	
		SportObjectController.addViewInFile();
		// TODO Auto-generated method stub

	}

}
