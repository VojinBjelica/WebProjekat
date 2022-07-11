package Service;

import java.util.ArrayList;

import FileStorage.SportObjectFileStorage;
import beans.Manager;
import beans.SportObject;
import beans.Training;
import beans.TrainingTypeEnum;

public class SportObjectService {
	public SportObjectFileStorage sofs;
	
	public SportObjectService() {
		sofs = new SportObjectFileStorage();
	}
	
	public ArrayList<SportObject> readSportObjects() {
		return sofs.readSportObjects();
	}
	public ArrayList<Training> getTrainingForObject(SportObject so)
	{
		return sofs.getTrainingForObject(so);
	}
	public ArrayList<SportObject> searchObjectsByName(String searchInput) {
		return sofs.searchObjectsByName(searchInput);
	}
	public SportObject deleteSportObject(SportObject so)
	{
		return sofs.deleteSportObject(so);
	}
	
	public ArrayList<SportObject> filteredList(ArrayList<SportObject> list) {
		return sofs.filteredList(list);
	}
	
	public String hide(String pass) {
		return sofs.hide(pass);
	}
	
	public SportObject getSportObjectByName(String name) {
		return sofs.getSportObjectByName(name);
	}
	
	public boolean addSportObject(SportObject sportObject) {
		return sofs.addSportObject(sportObject);
	}
	
	public SportObject getSportObjectByManager(Manager man) {
		return sofs.getSportObjectByManager(man);
	}
	
	public ArrayList<Training> searchTrainingsOfObject(SportObject so, String priceFrom, String priceTo, TrainingTypeEnum type){
		return sofs.searchTrainingsOfObject(so, priceFrom, priceTo, type);
	}
	
}
