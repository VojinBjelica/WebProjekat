package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import beans.Customer;
import beans.GenderEnum;
import beans.ObjectTypeEnum;
import beans.SportObject;

public class SportObjectFileStorage {
	public static ArrayList<SportObject> sportObjectList = new ArrayList<SportObject>();
	
	// objectName, objectType, objectOffer, workHour, averageMark
	
	public ArrayList<SportObject> readSportObjects() {
		
		System.out.println("Usao u readsportobject");
        ArrayList<SportObject> sportObjects = new ArrayList<SportObject>();
        BufferedReader in = null;
        try {
            File file = new File("./sportObjects.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            String line, objectName = "", objectType = "", objectOffer = "",workHour = "",avarageMark = "";
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
                    }
                    ObjectTypeEnum obType = ObjectTypeEnum.Gym;
                    if (objectType.equals("Gym")) obType = ObjectTypeEnum.Gym;
                    else if (objectType.equals("Pool")) obType = ObjectTypeEnum.Pool;
                    else if (objectType.equals("SportCenter")) obType = ObjectTypeEnum.SportCenter;
                    double avgMark = Double.parseDouble(avarageMark);
                    
                    
                    SportObject sportObject = new SportObject(objectName, obType, objectOffer, workHour, avgMark);
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
	
}
