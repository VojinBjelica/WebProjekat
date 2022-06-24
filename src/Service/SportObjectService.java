package Service;

import java.util.ArrayList;

import FileStorage.SportObjectFileStorage;
import beans.SportObject;

public class SportObjectService {
	public SportObjectFileStorage sofs;
	
	public SportObjectService() {
		sofs = new SportObjectFileStorage();
	}
	
	public ArrayList<SportObject> readSportObjects() {
		return sofs.readSportObjects();
	}
	
	public ArrayList<SportObject> searchObjectsByName(String searchInput) {
		return sofs.searchObjectsByName(searchInput);
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
	
	public SportObject addSportObject(SportObject sportObject) {
		return sofs.addSportObject(sportObject);
	}
	
}
