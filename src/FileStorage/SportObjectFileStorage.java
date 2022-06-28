package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import java.awt.Image;

import beans.Address;
import beans.Customer;
import beans.GenderEnum;
import beans.Location;
import beans.Manager;
import beans.ObjectTypeEnum;
import beans.SportObject;

public class SportObjectFileStorage {
	public static ArrayList<SportObject> sportObjectList = new ArrayList<SportObject>();
	
	// objectName, objectType, objectOffer, workHour, averageMark, longtitude, latitude, streetAndNumber, city, zipCode, status
	
	public ArrayList<SportObject> readSportObjects() {
		
        ArrayList<SportObject> sportObjects = new ArrayList<SportObject>();
        BufferedReader in = null;
        try {
            File file = new File("./sportObjects.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            String line, objectName = "", objectType = "", objectOffer = "",workHour = "",avarageMark = "",
            		logo="", longitude="", latitude="", streetAndNumber="", city="", zipCode="", status="";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	objectName = st.nextToken().trim();
                    	objectType = st.nextToken().trim();
                    	objectOffer = st.nextToken().trim();
                    	workHour = st.nextToken().trim();
                    	avarageMark = st.nextToken().trim();
                    	logo = st.nextToken().trim();
                    	longitude = st.nextToken().trim();
                    	latitude = st.nextToken().trim();
                    	streetAndNumber = st.nextToken().trim();
                    	city = st.nextToken().trim();
                    	zipCode = st.nextToken().trim();
                    	status = st.nextToken().trim();
                    	
                    }
                    ObjectTypeEnum obType = ObjectTypeEnum.Gym;
                    if (objectType.equals("Gym")) obType = ObjectTypeEnum.Gym;
                    else if (objectType.equals("Pool")) obType = ObjectTypeEnum.Pool;
                    else if (objectType.equals("SportCenter")) obType = ObjectTypeEnum.SportCenter;
                    double avgMark = Double.parseDouble(avarageMark);
                    
                    Address address = new Address(streetAndNumber, city, zipCode);
                    Location loc = new Location(longitude, latitude, address);
                    
                    boolean statusBool = true;
                    if (status.equals("true")) {
                    	statusBool = true;
                    } else if (status.equals("false")) {
                    	statusBool = false;
                    }
                    
                    
                    
                    SportObject sportObject = new SportObject(objectName, obType, objectOffer, statusBool, loc, logo, avgMark, workHour);
                    if (sportObject.isStatus()) {
                    	sportObjects.add(0, sportObject);
                    } else {
                    	sportObjects.add(sportObject);
                    }
                    
                    
                    sportObjectList = sportObjects;
                    
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                }
                catch (Exception e) { }
            }
        }
        return sportObjects;
    }
	
	public ArrayList<SportObject> searchObjectsByName(String searchInput) {
		ArrayList<SportObject> searchedList = new ArrayList<SportObject>();
		
		for (SportObject so : readSportObjects()) {
			if (so.getObjectName().trim().toLowerCase().contains(searchInput.trim().toLowerCase())) {
				searchedList.add(so);
			}
		}
		filteredList(searchedList);
		return searchedList;
	}
	
	public ArrayList<SportObject> filteredList(ArrayList<SportObject> list) {
		ArrayList<SportObject> filtered = new ArrayList<SportObject>();
		for (SportObject s : list) {
			filtered.add(s);
	        
		}
		
		return filtered;
	}
	
	public String hide(String pass) {
		return pass;
	}
	
	//Klikom na tabelu se proslijedi ime objekta i preko njega nadje sportski objekat
	public SportObject getSportObjectByName(String name) {
		ArrayList<SportObject> list = readSportObjects();
		SportObject sObject = null;
		for (SportObject so : list) {
			if (so.getObjectName().equals(name)) {
				sObject = so;
			}
		}
		return sObject;
	}
	
	public SportObject addSportObject(SportObject sportObject) {
		if (sportObject.getObjectName() != null && sportObject.getObjectType() != null) {
			sportObjectList = readSportObjects();
			System.out.println("Gornja duzina: " + sportObjectList.size() );
			
			boolean nameDuplicate = true;
			for (SportObject so : sportObjectList) {
				if (so.getObjectName().equals(sportObject.getObjectName())) {
					nameDuplicate = false;
				}
			}
			boolean nameReg = false;
			boolean streetReg = false;
			boolean cityReg = false;
			boolean workHourReg = false;
			
			if (sportObject.getObjectName().matches("[a-zA-Z0-9 ]*")) {
				nameReg = true;
				System.out.println("Dobro ime");
			}
			if (sportObject.getLocation().getAddress().getStreetAndNumber().matches("[a-zA-Z ]+[0-9 ]+[a-zA-Z ]?")) {
				streetReg = true;
				System.out.println("Dobra ulica i broj");
			}
			if (sportObject.getLocation().getAddress().getCity().matches("[a-zA-Z ]+")) {
				cityReg = true;
				System.out.println("Dobar grad");
			}
			if (sportObject.getWorkHour().matches("([01]?[0-9]|2[0-3]):?[0-5]?[0-9]?-([01]?[0-9]|2[0-3]):?[0-5]?[0-9]?")) {
				workHourReg = true;
				System.out.println("Dobri work hours");
			}
			
			if (nameReg == true && nameDuplicate == true && streetReg == true && cityReg == true && workHourReg == true ) {
				sportObjectList.add(sportObject);
				System.out.println("Dodajem: " + sportObject.getObjectName());
			}
			else {
				System.out.println("Invalid sport object data");
			}
			addSportObjectInFile("sportObjects");
		}
		return sportObject;
	}
	
	public boolean addSportObjectInFile(String who) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("./"+who+".txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			System.out.println("Donja duzina: " + sportObjectList.size() );
			for (SportObject so : sportObjectList) {
				String outputString = "";
				outputString += so.getObjectName() + ";";
				if (so.getObjectType() == ObjectTypeEnum.Gym) {
					outputString+="Gym" + ";";
				}
				else if (so.getObjectType() == ObjectTypeEnum.Pool) {
					outputString+="Pool" + ";";
				}
				else if (so.getObjectType() == ObjectTypeEnum.SportCenter) {
					outputString+="SportCenter" + ";";
				}
				outputString += so.getObjectOffer() + ";";
				outputString += so.getWorkHour() + ";";
				outputString += so.getAvarageMark() + ";";
				outputString += so.getLogo() + ";";
				outputString += so.getLocation().getLatitude() + ";";
				outputString += so.getLocation().getLongitude() + ";";
				outputString += so.getLocation().getAddress().getStreetAndNumber() + ";";
				outputString += so.getLocation().getAddress().getCity() + ";";
				outputString += so.getLocation().getAddress().getZipCode() + ";";
				if (so.isStatus() == true) {
					outputString+="true";
				} else if (so.isStatus() == false) {
					outputString+="false";
				}
				output.println(outputString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public SportObject getSportObjectByManager(Manager man) {
		SportObject soTemp = new SportObject();
		for(SportObject so : sportObjectList) {
			if (so.getObjectName().equals(man.getSportObject())) {
				soTemp = so; 
			}
		}
		return soTemp;
	}
	
}
