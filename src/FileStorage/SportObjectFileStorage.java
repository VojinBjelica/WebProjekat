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
import beans.Training;
import beans.TrainingTypeEnum;

public class SportObjectFileStorage {
	public static ArrayList<SportObject> sportObjectList = new ArrayList<SportObject>();
	
	// objectName, objectType, objectOffer, workHour, averageMark, longtitude, latitude, streetAndNumber, city, zipCode, status
	
	public ArrayList<SportObject> readSportObjects() {
		
        ArrayList<SportObject> sportObjects = new ArrayList<SportObject>();
        BufferedReader in = null;
        try {
            File file = new File("./sportObjects.txt");
            in = new BufferedReader(new FileReader(file));
            String line, objectName = "", objectType = "", objectOffer = "",workHour = "",avarageMark = "",
            		logo="", longitude="", latitude="", streetAndNumber="", city="", zipCode="", status="", deleted="";
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
                    	deleted = st.nextToken().trim();
                    	
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
                    
                    int del = Integer.parseInt(deleted);
                    
                    SportObject sportObject = new SportObject(objectName, obType, objectOffer, statusBool, loc, logo, avgMark, workHour, del);
                    if (sportObject.isStatus() && sportObject.getDeleted() == 0) {
                    	sportObjects.add(0, sportObject);
                    } else if (!sportObject.isStatus() && sportObject.getDeleted() == 0){
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
	
	public SportObject deleteSportObject(SportObject so)
	{
		sportObjectList = readSportObjects();
		SportObject spo = null;
		for(SportObject sp : sportObjectList)
		{
			if(sp.getObjectName().equals(so.getObjectName()))
			{
				sp.setDeleted(1);
				spo = sp;

			}
				
		}
		addSportObjectInFile("sportObjects");
		return spo;
	}
	
	public ArrayList<SportObject> filteredList(ArrayList<SportObject> list) {
		ArrayList<SportObject> filtered = new ArrayList<SportObject>();
		for (SportObject s : list) {
			if(s.getDeleted() == 0)
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
	
	public boolean addSportObject(SportObject sportObject) {
		if (sportObject.getObjectName() != null && sportObject.getObjectType() != null) {
			sportObjectList = readSportObjects();
			
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
			}
			if (sportObject.getLocation().getAddress().getStreetAndNumber().matches("[a-zA-Z ]+[0-9 ]+[a-zA-Z ]?")) {
				streetReg = true;
			}
			if (sportObject.getLocation().getAddress().getCity().matches("[a-zA-Z ]+")) {
				cityReg = true;
			}
			if (sportObject.getWorkHour().matches("([01]?[0-9]|2[0-3]):?[0-5]?[0-9]?-([01]?[0-9]|2[0-3]):?[0-5]?[0-9]?")) {
				workHourReg = true;
			}
			
			if (nameReg == true && nameDuplicate == true && streetReg == true && cityReg == true && workHourReg == true ) {
				sportObjectList.add(sportObject);
				addSportObjectInFile("sportObjects");
				return true;
			}
			else {
				System.out.println("Invalid sport object data");
			}
			//addSportObjectInFile("sportObjects");
		}
		return false;
	}
	public ArrayList<Training> getTrainingForObject(SportObject so)
	{
		CustomerFileStorage cfs = new CustomerFileStorage();
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training t : cfs.readTraining())
		{
			if(t.getSportObject().getObjectName().equals(so.getObjectName()))
			{
				retList.add(t);
			}
		}
		return retList;
	}
	
	public ArrayList<Training> searchTrainingsOfObject(SportObject so, String priceFrom, String priceTo, TrainingTypeEnum type)
	{
		CustomerFileStorage cfs = new CustomerFileStorage();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		double searchPriceFrom;
		double searchPriceTo;
		
		if (priceFrom.equals("None")) {
			searchPriceFrom = 0;
		} else {
			searchPriceFrom = Double.parseDouble(priceFrom);
		}
		
		if (priceTo.equals("None")) {
			searchPriceTo = 1000000;
		} else {
			searchPriceTo = Double.parseDouble(priceTo);
		}
		
		for(Training t : cfs.readTraining())
		{
			if(t.getSportObject().getObjectName().equals(so.getObjectName()))
			{
				if(type != TrainingTypeEnum.None) {
					if (t.getPrice() >= searchPriceFrom && t.getPrice() <= searchPriceTo && t.getType() == type && t.getDeleted() == 0) {
						retList.add(t);
					}
				} else if (priceFrom.equals("None") && priceTo.equals("None") && t.getDeleted() == 0) {
					retList.add(t);
				} else {
					if (t.getPrice() >= searchPriceFrom && t.getPrice() <= searchPriceTo && t.getDeleted() == 0) {
						retList.add(t);
					}
				}	
			}
		}
		return retList;
	}

	
	public boolean addSportObjectInFile(String who) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("./"+who+".txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
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
					outputString+="true;";
				} else if (so.isStatus() == false) {
					outputString+="false;";
				}
				outputString += so.getDeleted();
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
