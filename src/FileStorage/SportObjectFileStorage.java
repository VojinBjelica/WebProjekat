package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import java.awt.Image;

import beans.Address;
import beans.Customer;
import beans.GenderEnum;
import beans.Location;
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
                    	// Za sad pravim skraceni oblik, pa kasnije da skontam kako da cijeli proslijedim
                    	objectName = st.nextToken().trim();
                    	objectType = st.nextToken().trim();
                    	objectOffer = st.nextToken().trim();
                    	workHour = st.nextToken().trim();
                    	avarageMark = st.nextToken().trim();
                    	//logo = st.nextToken().trim();
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
                    	statusBool = true;
                    }
                    
                    //Image logoImg;
                    
                    SportObject sportObject = new SportObject(objectName, obType, objectOffer, statusBool, loc, avgMark, workHour);
                    sportObjects.add(sportObject);
                    
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
	
}
