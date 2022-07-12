package Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Service.CustomerService;
import Service.SportObjectService;
import beans.Coach;
import beans.Comment;
import beans.Customer;
import beans.Manager;
import beans.RoleEnum;
import beans.SportObject;
import beans.Training;
import beans.TrainingsSearchDTO;
import beans.User;
import beans.searchTrainersTrainingsDTO;
import beans.searchTrainingsOfObjectDTO;
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
	public static Training editableTraining;
	
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
		editableTraining = new Training();
	}
	
	public static void readSportObjects() {
		get("sportObjects/read", (req, res) -> {
			return g.toJson(sos.readSportObjects());
		});
	}
	
	public static void getAvailableManagers() {
		get("sportObjects/availableManagers", (req, res) -> {
			return g.toJson(cs.findAvailableManagers());
		});
	}
	
	public static void searchObjectsByName() {
		post("sportObjects/search", (req, res) -> {
			
			String payload = req.body();
			SportObject pd = g.fromJson(payload, SportObject.class);
			ArrayList<SportObject> searchedList = sos.searchObjectsByName(pd.getObjectName());
			
			objList = searchedList;
			g.toJson(searchedList);
			return "OK";
		});
	}
	
	public static void filteredList() {
		get("sportObjects/show", (req, res) -> {
			
			//System.out.println(sos.readSportObjects()); 
			//System.out.println("Velicina:" + sos.filteredList(objList).size());
			return g.toJson(sos.filteredList(objList));
		});
	}
	public static void addComment() {
		post("sportObjects/addComment", (req, res) -> {
			String payload = req.body();
			Comment pd = g.fromJson(payload, Comment.class);
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Customer cust = cs.findCustomerByUsername(user.getUsername());
			int id = cs.findNextID();
			Comment c = new Comment(cust,tempObject,pd.getText(),0,0,id);
			cs.addComment(c);
			return "Komentar uspesno dodat";
		});
	}
	public static void fillList() {
		get("sportObjects/fillList", (req, res) -> {
			String payload = req.body();
			ArrayList<Comment> retList = cs.approvedComments(tempObject);
			

			
			return g.toJson(retList);
		});
	}
	public static void approveComment() {
		post("sportObjects/approveComment", (req, res) -> {
			String payload = req.body();
			Comment pd = g.fromJson(payload, Comment.class);
			cs.approveComment(pd.getId());
			

			
			return "Odobren";
		});
	}
	public static void fillfullList() {
		get("sportObjects/fillfullList", (req, res) -> {
			String payload = req.body();
			ArrayList<Comment> retList = cs.allComments(tempObject);
			

			
			return g.toJson(retList);
		});
	}
	public static void getTemp(String tmp)
	{
		temp = tmp;
		hide();
	}
	public static void hide() {
		post("sportObjects/hide", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			User u = null;
			String retString = "";
			if(user != null)
			{
				u = cs.findUserByUsername(user.getUsername());
				retString = "logged " + u.getRole();
				
			}
			
			String payload = req.body();
			String  pd = g.fromJson(payload, String.class);
			
			pd = retString;
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
	public static void findTrainingsForObject()
	{
		post("sportObject/showtable", (req, res) -> {
			ArrayList<Training> retList = sos.getTrainingForObject(tempObject);
			
			return g.toJson(retList);
		});
	}
	
	public static void searchTrainingsOfObject() {
		post("sportObject/searchTrainingsOfObject", (req, res) -> {
			String payload = req.body();
			searchTrainingsOfObjectDTO tr = g.fromJson(payload, searchTrainingsOfObjectDTO.class);
			ArrayList<Training> retList = sos.searchTrainingsOfObject(tempObject, tr.getPriceFrom(), tr.getPriceTo(), tr.getType());
			return g.toJson(retList);
		
		});
	}
	
	public static void getSelectedObject() {
		post("sportObject/getOne", (req, res) -> {
			String payload = req.body();
			SportObject so = g.fromJson(payload, SportObject.class);
			tempObject = so;
			return "OK";
		});
	}
	
	public static void showSelectedObject() {
		post("sportObject/showOne", (req, res) -> {
			return g.toJson(tempObject);
		});
	}
	public static void deleteSportObject() {
		post("sportObject/deleteObject", (req, res) -> {
			sos.deleteSportObject(tempObject);
			return "Obrisan";
		});
	}
	
	public static void addViewInFile() {
		get("sportObject/addView", (req, res) -> {	
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user != null)
			{
			User custo = cs.findCustomerByUsernameAndPassword(user.getUsername(), user.getPassword());
			
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
			}
			return null;
		});
	}
	
	
	public static void addSportObject() {
		post("sportObjects/add", (req, res) -> {
			String payload = req.body();
			SportObject so = g.fromJson(payload, SportObject.class);
			boolean valid = sos.addSportObject(so);
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
	
	//Nalazi treninge za sportski objekat (za menadzera)
	public static void findTrainingsBySportObject() {
		get("sportObject/getTrainingsForSO", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			SportObject so = sos.getSportObjectByManager(mana);
			
			ArrayList<Training> trList = new ArrayList<Training>();
			trList = cs.findTrainingsBySportObject(so);
			
			return g.toJson(trList);
			
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
	
	
	public static void showEditTraining() {
		post("sportObject/editShow", (req, res) -> {
			String payload = req.body();
			Training tr = g.fromJson(payload, Training.class);
			editableTraining = tr;
			return "OK";
		});
	}
	
	public static void showEditableTraining() {
		post("sportObject/showEditableTraining", (req, res) -> {
			return g.toJson(editableTraining);
		});
	}
	
	public static void editTraining() {
		post("sportObject/editTraining", (req, res) -> {
			String payload = req.body();
			Training train = g.fromJson(payload, Training.class);
			cs.editTraining(editableTraining, train);
			return "OK";
		});
	}
	
	public static void deleteTraining() {
		post("sportObject/deleteTraining", (req, res) -> {
			String payload = req.body();
			Training train = g.fromJson(payload, Training.class);
			cs.deleteTraining(train);
			System.out.println("Deleted training: " + train.getName());
			return "OK";
		});
	}
	public static void catchUser() {
		post("sportObject/catchrole", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String retValue = "";
			User u = null;
			if(user != null)
			{
				 u = cs.findUserByUsername(user.getUsername());
				 retValue = "" + u.getRole();
			}
			return retValue;
		});
	}
	
	
	
	
	public static void searchTrainingsNameTypePrice() {
		post("sportObject/searchManagersTrainings", (req, res) -> {
			Session ss = req.session(true);
			User user = ss.attribute("user");
			Manager mana = cs.findManagerByUsername(user.getUsername());
			SportObject so = sos.getSportObjectByManager(mana);
			String payload = req.body();
			TrainingsSearchDTO train = g.fromJson(payload, TrainingsSearchDTO.class);
			
			
			ArrayList<Training> sortedList = new ArrayList<Training>();
			sortedList = cs.searchTrainingsNameTypePrice(train.getName(), train.getType(), train.getPriceFrom(), train.getPriceTo(), so);
			return g.toJson(sortedList);
		});
	}
	
	public static void searchTrainersGymTrainings()
	{
		post("sportObject/searchTrainersGymTrainings", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String payload = req.body();
			searchTrainersTrainingsDTO train = g.fromJson(payload, searchTrainersTrainingsDTO.class);
			myTrainingList = cs.searchTrainersGymTrainings(cs.getCoachByUsername(user.getUsername()), train.getSoName(), train.getSoType(), train.getPriceFrom(), train.getPriceTo(), train.getType());
			return g.toJson(myTrainingList);
		});
	}
	
	public static void searchTrainersPersonalTrainings()
	{
		post("sportObject/searchTrainersPersonalTrainings", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String payload = req.body();
			searchTrainersTrainingsDTO train = g.fromJson(payload, searchTrainersTrainingsDTO.class);
			myTrainingList = cs.searchTrainersPersonalTrainings(cs.getCoachByUsername(user.getUsername()), train.getSoName(), train.getSoType(), train.getPriceFrom(), train.getPriceTo(), train.getType());
			return g.toJson(myTrainingList);
		});
	}
	
	public static void searchTrainersGroupTrainings()
	{
		post("sportObject/searchTrainersGroupTrainings", (req, res) -> {
			ArrayList<Training> myTrainingList = new ArrayList<Training>();
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String payload = req.body();
			searchTrainersTrainingsDTO train = g.fromJson(payload, searchTrainersTrainingsDTO.class);
			myTrainingList = cs.searchTrainersGroupTrainings(cs.getCoachByUsername(user.getUsername()), train.getSoName(), train.getSoType(), train.getPriceFrom(), train.getPriceTo(), train.getType());
			return g.toJson(myTrainingList);
		});
	}
	
	
}
