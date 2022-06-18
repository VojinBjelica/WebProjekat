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
	
	
}
