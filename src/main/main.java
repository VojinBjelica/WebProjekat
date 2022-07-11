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
		CustomerController.getMyTrainings();
		CustomerController.getMyPersonal();
		CustomerController.getMyGroup();
		CustomerController.getAllTrainers();
		CustomerController.cancelTraining();
		CustomerController.showCustomerTrainings();
		CustomerController.addTraining();
		CustomerController.addDue();
		CustomerController.addPromoCode();
		CustomerController.findDiscount();
		CustomerController.calculatePoints();
		CustomerController.getTrainings();
		CustomerController.scheduleTraining();
		CustomerController.calculateType();
		CustomerController.calculateDis();
		CustomerController.userIs();
		CustomerController.deleted();
		CustomerController.searchTrainingsForCustomer();
		CustomerController.getLoggedRole();
		
		
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
		SportObjectController.addNewManager();
		SportObjectController.hide();
		SportObjectController.findTrainingsBySportObject();
		SportObjectController.showEditTraining();
		SportObjectController.editTraining();
		SportObjectController.showEditableTraining();
		SportObjectController.deleteTraining();
		SportObjectController.searchTrainingsNameTypePrice();
		SportObjectController.findTrainingsForObject();
		SportObjectController.searchTrainersGymTrainings();
		SportObjectController.searchTrainersPersonalTrainings();
		SportObjectController.searchTrainersGroupTrainings();
		SportObjectController.searchTrainingsOfObject();
		SportObjectController.deleteSportObject();
		SportObjectController.catchUser();
		SportObjectController.addComment();
		SportObjectController.fillList();
		SportObjectController.fillfullList();
		SportObjectController.approveComment();
		// TODO Auto-generated method stub

	}

}
