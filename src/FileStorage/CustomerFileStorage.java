package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.swing.JOptionPane;

import Service.SportObjectService;
import beans.Coach;
import beans.Customer;
import beans.Dues;
import beans.DuesTypeEnum;
import beans.GenderEnum;
import beans.Manager;
import beans.Points;
import beans.PromoCode;
import beans.RoleEnum;
import beans.SportObject;
import beans.Training;
import beans.TrainingTypeEnum;
import beans.User;

public class CustomerFileStorage {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Customer> customerTraining = new ArrayList<Customer>();
	public static ArrayList<Customer> customerViewList = new ArrayList<Customer>();
	public static ArrayList<Manager> managerList = new ArrayList<Manager>();
	public static ArrayList<Coach> coachList = new ArrayList<Coach>();
	public static ArrayList<Coach> coachObjectList = new ArrayList<Coach>();
	public static ArrayList<Dues> duesList = new ArrayList<Dues>();
	public static ArrayList<User> userList = new ArrayList<User>();
	public static ArrayList<Training> trainingList = new ArrayList<Training>();
	public static ArrayList<PromoCode> promoCodeList = new ArrayList<PromoCode>();
	public static ArrayList<Points> pointsList = new ArrayList<Points>();
	public ArrayList<Customer> readCustomers(String way) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./"+ way + ".txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();             
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Customer customer = new Customer(username,password,name,surname, dt,gen);
                    customers.add(customer);
                    customerList = customers;
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
        return customers;
    }
	public ArrayList<Customer> readCustomerTrainings() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./customerTraining.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "",trainingID = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        trainingID = st.nextToken().trim();              
                        }
                    User user = findUserByUsername(username);
                    
                    Customer customer = new Customer(username,user.getPassword(),user.getName(),user.getSurname(),user.getDateOfBirth(),user.getGender(),Integer.parseInt(trainingID));
                    customers.add(customer);
                    customerTraining = customers;
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
        return customers;
    }
	public ArrayList<Points> readPoints() {
        ArrayList<Points> customers = new ArrayList<Points>();
        BufferedReader in = null;
        try {
            File file = new File("./customerPoints.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "",points = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        points = st.nextToken().trim();              
                        }
                    Points point = new Points(username,Float.parseFloat(points));
                     customers.add(point);
                    pointsList = customers;
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
        return customers;
    }
	public ArrayList<PromoCode> readPromoCodes() {
        ArrayList<PromoCode> customers = new ArrayList<PromoCode>();
        BufferedReader in = null;
        try {
            File file = new File("./promoCodes.txt");
            in = new BufferedReader(new FileReader(file));
            String line,name="", fromDate = "",toDate = "",using= "",discount = "",used="";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	name = st.nextToken().trim();
                    	fromDate = st.nextToken().trim();
                    	toDate = st.nextToken().trim(); 
                    	using = st.nextToken().trim(); 
                    	discount = st.nextToken().trim(); 
                    	used = st.nextToken().trim(); 
                    	
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(fromDate);
                    Date dtt = formatter.parse(toDate);
                    PromoCode customer = new PromoCode(name,dt,dtt,Integer.parseInt(using),Integer.parseInt(discount),Integer.parseInt(used));
                    customers.add(customer);
                    promoCodeList = customers;
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
        return customers;
    }
	public ArrayList<Training> readTraining() {
        ArrayList<Training> customers = new ArrayList<Training>();
        BufferedReader in = null;
        try {
            File file = new File("./trainings.txt");
            in = new BufferedReader(new FileReader(file));
            String line, name = "", type = "",deleted = "", sportObject = "",date = "",id = "",duration = "",coach = "",description = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	name = st.nextToken().trim();
                    	type = st.nextToken().trim();
                    	sportObject = st.nextToken().trim();
                    	duration = st.nextToken().trim();
                    	coach = st.nextToken().trim();
                    	description = st.nextToken().trim();
                    	date = st.nextToken().trim();
                    	id = st.nextToken().trim();
                    	deleted = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    TrainingTypeEnum typeEnum = TrainingTypeEnum.Group;
                    if(type.equals("Group")) typeEnum = TrainingTypeEnum.Group;
                    else if(type.equals("Gym")) typeEnum = TrainingTypeEnum.Gym;
                    else typeEnum = TrainingTypeEnum.Personal;
                    SportObjectService sos = new SportObjectService();
                    SportObject so = sos.getSportObjectByName(sportObject);
                    Coach c = getCoachByUsername(coach);
                    Training train = new Training(name,typeEnum,so,Integer.parseInt(duration),c,description,dt,Integer.parseInt(id),Integer.parseInt(deleted));
                    customers.add(train);
                    trainingList = customers;
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
        return customers;
    }
	public ArrayList<Dues> readDues() {
        ArrayList<Dues> customers = new ArrayList<Dues>();
        BufferedReader in = null;
        try {
            File file = new File("./dues.txt");
            in = new BufferedReader(new FileReader(file));
            String line, ID="" , type = "" , paydate = "" , expdate = "",price = "",customer = "",status = "", number = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	ID = st.nextToken().trim();
                    	type = st.nextToken().trim();
                    	paydate = st.nextToken().trim();
                    	expdate = st.nextToken().trim();
                    	price = st.nextToken().trim();
                    	customer = st.nextToken().trim();
                    	status = st.nextToken().trim();
                    	number = st.nextToken().trim();
                    	
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(paydate);
                    Date dtex = formatter.parse(expdate);
                    DuesTypeEnum typeEnum = DuesTypeEnum.Month;
                    if(type.equals("Month")) typeEnum = DuesTypeEnum.Month;
                    else typeEnum = DuesTypeEnum.Year;
                    Customer cust = findCustByUsername(customer);
                    Dues retDue = new Dues(ID,typeEnum,dt,dtex,Integer.parseInt(price),cust,Boolean.parseBoolean(status),Integer.parseInt(number));
                    
                    
                    customers.add(retDue);
                    duesList = customers;
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
        return customers;
    }
	
	public boolean addTraining(Training training) {
		if (training.getName() != null && training.getType() != null && training.getPicture() != null
				&& training.getTrainer() != null && training.getTrainingDate() != null) {
			trainingList = readTraining();
			
			boolean nameDuplicate = true;
			for (Training tr : trainingList) {
				if (tr.getName().equals(training.getName())) {
					nameDuplicate = false;
				}
			}
			
			boolean nameReg = false;
			boolean durationReg = false;
			
			if (training.getName().matches("[a-zA-Z0-9 ]+")) {
				nameReg = true;
			}
			if (Integer.toString(training.getDuration()).matches("[0-9]+")) {
				durationReg = true;
			}
			
			if (nameReg == true && durationReg == true && nameDuplicate == true) {
				trainingList.add(training);
				System.out.println("Dodajem trening: " + training.getName());
				addTrainingsInFile();
				return true;
			}
			else {
				System.out.println("Invalid training data.");
			}
		}
		return false;
	}
	
	
	public Training findTrainingById(int id)
	{
		for(Training t : readTraining())
		{
			if(t.getId() == id)
			{
				return t;
			}
		}
		return null;
	}
	public ArrayList<Customer> readCustomersView() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./sportObjectInfo.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "",so = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();      
                        so = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Customer customer = new Customer(username,password,name,surname, dt,gen,so);
                    customers.add(customer);
                    customerViewList = customers;
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
        return customers;
    }
	
	public ArrayList<Manager> readManagers(String way) {
        ArrayList<Manager> customers = new ArrayList<Manager>();
        BufferedReader in = null;
        try {
            File file = new File("./"+ way + ".txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "",sportObjectName="";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();
                        sportObjectName = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Manager customer = new Manager(username,password,name,surname, dt,gen,sportObjectName);
                    customers.add(customer);
                    managerList = customers;
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
        return customers;
    }
	public ArrayList<Coach> readCoaches() {
        ArrayList<Coach> customers = new ArrayList<Coach>();
        BufferedReader in = null;
        try {
            File file = new File("./coaches.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();       
                       
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Coach customer = new Coach(username,password,name,surname, dt,gen);
                    customers.add(customer);
                    coachList = customers;
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
        return customers;
    }
	public ArrayList<Coach> readObjectCoaches() {
        ArrayList<Coach> customers = new ArrayList<Coach>();
        BufferedReader in = null;
        try {
            File file = new File("./coachObjects.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "",sportObjectName = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();       
                        sportObjectName = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Coach customer = new Coach(username,password,name,surname, dt,gen,sportObjectName);
                    customers.add(customer);
                    coachObjectList = customers;
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
        return customers;
    }
	public ArrayList<Coach> readCoachesID() {
        ArrayList<Coach> customers = new ArrayList<Coach>();
        BufferedReader in = null;
        try {
            File file = new File("./coachObjects.txt");
            in = new BufferedReader(new FileReader(file));
            String line,id = "", username = "", password = "", name = "",surname = "",gender = "",date = "",sportObjectName = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();       
                        sportObjectName = st.nextToken().trim();
                        id = st.nextToken().trim();
                        
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Coach customer = new Coach(username,password,name,surname,dt,gen,Integer.parseInt(id));
                    customers.add(customer);
                    coachObjectList = customers;
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
        return customers;
    }
	public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader in = null;
        try {
            File file = new File("./users.txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "",role="";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        username = st.nextToken().trim();
                        password = st.nextToken().trim();
                        name = st.nextToken().trim();
                        surname = st.nextToken().trim();
                        gender = st.nextToken().trim();
                        date = st.nextToken().trim();
                        role = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    RoleEnum rol = RoleEnum.Administrator;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    if(role.equals("Customer")) rol = RoleEnum.Customer;
                    else if(role.equals("Administrator")) rol = RoleEnum.Administrator;
                    else if(role.equals("Coach")) rol = RoleEnum.Coach;
                    else rol = RoleEnum.Manager;
                    User user = new User(username,password,name,surname, dt,gen,rol);
                    users.add(user);
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
        return users;
    }
	public void writeCustomers()
	{
		for(Customer c : readCustomers("customers"))
		{
			System.out.println(c.getName() + " " + c.getSurname());
		}
	}
	
	
	public boolean addCustomerInFile(String who) 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./"+who+".txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Customer customer : customerList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addPointsInFile(ArrayList<Points> thisList) 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./customerPoints.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Points customer : thisList)
        {
            String outputString = "";
            outputString += customer.getUserUsername() + ";";
            outputString += customer.getPoints() + ";";
            
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addViewSecret(Customer cust, String objectName)
	{
		customerViewList = readCustomersView();
		customerViewList.add(new Customer(cust.getUsername(),cust.getPassword(),cust.getName(),cust.getSurname(),cust.getDateOfBirth(),cust.getGender(),objectName));
		addCustomerViewInFile();
		return true;
		
	}
	public boolean calculatePoints()
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		float bodovi = 0;
		ArrayList<Points> retList = new ArrayList<Points>();
		ArrayList<Points> pointsList = readPoints();
		ArrayList<Dues> duesList = readDues();
		for(Dues d : duesList)
		{
			if(d.getExpirationDateAndTime().before(date))
			{
				System.out.println("Pre datuma");
				for(Points p : pointsList)
				{
					if(d.getCustomer().getUsername().equals(p.getUserUsername()))
					{
						bodovi = p.getPoints();
						retList.remove(new Points(p.getUserUsername(),p.getPoints()));
					}
				}
				if(d.getExpirationDateAndTime().getDay() - date.getDay() == -1)
				{
					System.out.println("razlika 1 dan");
					bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
					Points p = new Points(d.getCustomer().getUsername(),bodovi);
					retList.add(p);
				}
				else
				{
					System.out.println("razlika vise dan");
					if(d.getExpirationDateAndTime().getMonth() - date.getMonth() != 0)
					{
					if(d.getExpirationDateAndTime().getMonth() %2 == 0 && d.getExpirationDateAndTime().getMonth() != 2)
					{
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 29  )
						{
							System.out.println("Parni mesec");
							bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
							Points p = new Points(d.getCustomer().getUsername(),bodovi);
							retList.add(p);
						}
					}
					else if (d.getExpirationDateAndTime().getMonth() %2 == 0 &&d.getExpirationDateAndTime().getMonth() == 2)
					{
						System.out.println("februar");
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 27  )
						{
							bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
							Points p = new Points(d.getCustomer().getUsername(),bodovi);
							retList.add(p);
						}
					}
					else
					{
						System.out.println("neparni mesec");
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 30  )
						{
							bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
							Points p = new Points(d.getCustomer().getUsername(),bodovi);
							retList.add(p);
						}
					}
					}
					else
					{
						if(d.getExpirationDateAndTime().getMonth() %2 == 0 && d.getExpirationDateAndTime().getMonth() != 2)
						{
							if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 29  )
							{
								System.out.println("Parni mesec");
								bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						else if (d.getExpirationDateAndTime().getMonth() %2 == 0 &&d.getExpirationDateAndTime().getMonth() == 2)
						{
							System.out.println("februar");
							if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 27  )
							{
								bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						else
						{
							System.out.println("neparni mesec");
							if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 30  )
							{
								bodovi += d.getPrice()/1000 * d.getNumberOfAppointments();
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						
						
					}
					
				}
			}
		}
		System.out.println(retList.size());
		addPointsInFile(retList);
		return true;
	}
	public boolean addCustomerViewInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./sportObjectInfo.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Customer customer : customerViewList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            outputString += ";" + customer.getSportObjectNick();
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addCodesInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./promoCodes.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(PromoCode customer : promoCodeList)
        {
            String outputString = "";
            outputString += customer.getPromoCodeName() + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getFromDate()) + ";";
            outputString += formatter.format(customer.getToDate()) + ";";
            outputString += customer.getNumberOfUsing() + ";";
            outputString += customer.getDiscount() + ";";
            outputString += customer.getUsed() + ";";
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean usedCode(String codeName)
	{
		for(PromoCode code : promoCodeList)
		{
			if(code.getPromoCodeName().equals(codeName))
			{
				code.setUsed(code.getUsed() + 1);
			}
		}
		addCodesInFile();
		return true;
		
	}
	public boolean addDuesInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./dues.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Dues customer : duesList)
        {
            String outputString = "";
            outputString += customer.getID() + ";";
            if(customer.getDuesType() == DuesTypeEnum.Month)
                outputString += "Month" + ";";
                else 
                outputString += "Year" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getPayDate()) + ";";
            outputString += formatter.format(customer.getExpirationDateAndTime()) + ";";
            outputString += customer.getPrice() + ";";
            outputString += customer.getCustomer().getUsername() + ";";
            outputString += customer.isStatus() + ";";
            outputString += customer.getNumberOfAppointments();
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public void addDues(Dues due)
	{
		ArrayList<Dues> duesTempList = readDues();
		for(Dues d : duesTempList)
		{
			if(d.getCustomer().getUsername().equals(due.getCustomer().getUsername()))
			{
				d.setStatus(false);
			}
		}
		duesList.add(due);
		addDuesInFile();
	}
	public String generateID()
	{
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		
		   StringBuilder sb = new StringBuilder(10);
		   for(int i = 0; i < 10; i++)
		      sb.append(AB.charAt(rnd.nextInt(AB.length())));
		   System.out.println(sb.toString());
		   return sb.toString();
		
	}
	public boolean addManagersInFile(String who) 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./"+who+".txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Manager customer : managerList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth()) + ";"; //dodao ;
            outputString += customer.getSportObject(); //dodao i iscitavanje sportskog objekta
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addTrainingsInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./trainings.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Training customer : trainingList)
        {
            String outputString = "";
            outputString += customer.getName() + ";";
            if(customer.getType() == TrainingTypeEnum.Personal)
                outputString += "Personal" + ";";
                else if(customer.getType() == TrainingTypeEnum.Group)
                outputString += "Group" + ";";
                else outputString += "Gym" + ";";
                	
            outputString += customer.getSportObject().getObjectName() + ";";
            outputString += customer.getDuration() + ";";
            outputString += customer.getTrainer().getUsername() + ";";
            outputString += customer.getDescription() + ";";
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getTrainingDate()) + ";"; //dodao ;
            outputString += customer.getId() + ";"; //dodao i iscitavanje sportskog objekta
            outputString += customer.getDeleted(); //dodao i iscitavanje sportskog objekta
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addCoachesInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./coaches.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Coach customer : coachList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            outputString += customer.getCoachID();
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addPromoCode(PromoCode code)
	{
		ArrayList<PromoCode> codeList = readPromoCodes();
//		for(PromoCode code : codeList)
//		{
//			
//		}
		promoCodeList.add(code);
		addCodesInFile();
		return true;
	}
	public boolean addCoachesObjectInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./coachObjects.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Coach customer : coachObjectList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            outputString += customer.getSportObject(); //dodao i iscitavanje sportskog objekta

            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addUsersInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./users.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(User customer : userList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            outputString += ";" + customer.getRole();
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean addCustomersToUsers()
	{
		FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./users.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Customer customer : customerList)
        {
            String outputString = "";
            outputString += customer.getUsername() + ";";
            outputString += customer.getPassword() + ";";
            outputString += customer.getName() + ";";
            outputString += customer.getSurname() + ";";
            if(customer.getGender() == GenderEnum.Male)
            outputString += "Male" + ";";
            else if(customer.getGender() == GenderEnum.Female)
            outputString += "Female" + ";";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getDateOfBirth());
            outputString += ";Customer";
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
	}
	public Customer addCustomer(Customer customer)
	{
		if(customer.getName() != null && customer.getSurname()!= null  && customer.getUsername() != null)
		{
		customerList = readCustomers("customers");
		System.out.println("Duzina liste customera:" + customerList.size());
		userList = readUsers();
		boolean usernameDuplicate = true;
		for(User u : userList)
		{
			if(u.getUsername().equals(customer.getUsername()))
			{
				usernameDuplicate = false;
			}
		}
		User newUser = new User(customer.getUsername(),customer.getPassword(),customer.getName(),customer.getSurname(),customer.getDateOfBirth(),customer.getGender(),customer.getRole());
		userList.add(newUser);
		addUsersInFile();
		boolean nameReg = false;
		boolean surnameReg = false;
		boolean usernameReg = false;
		
		if(customer.getName().matches("[A-Z][a-z]+"))nameReg = true;
		if(customer.getSurname().matches("[A-Z][a-z]+"))surnameReg = true;
		if(customer.getUsername().matches("[A-Z]*[a-z]*[1-9]*"))usernameReg = true;
		if(nameReg == true && surnameReg == true && usernameReg == true && usernameDuplicate == true)
		{
			customerList.add(customer);
			System.out.println("Dodajem:" + customer.getName() + " " + customer.getSurname());
		}
		else
		{
			System.out.println("Invalid data");
		}
		addCustomerInFile("customers");
		}
		return customer;
	}
	public User editProfile(User user,User usertwo)
	{
		User returnUser = null;
		userList = readUsers();
		for(User u : userList)
		{
			if(u.getUsername().equals(user.getUsername()))
			{
				u.setName(usertwo.getName());
				u.setSurname(usertwo.getSurname());
				u.setPassword(usertwo.getPassword());
				u.setGender(usertwo.getGender());
				u.setDateOfBirth(usertwo.getDateOfBirth());
				returnUser = u;
			}
		}
		addUsersInFile();
		return returnUser;
      
	}
	
	public Training editTraining(Training tr1, Training tr2) {
		System.out.println("Editujem trening u file storage");
		Training retTraining = null;
		ArrayList<Training> trList = new ArrayList<Training>();
		trList = readTraining();
		for(Training t : trList) {
			if (t.getName().equals(tr1.getName())) {
				t.setName(tr2.getName());
				t.setType(tr2.getType());
				t.setDuration(tr2.getDuration());
				t.setDescription(tr2.getDescription());
				t.setPicture(tr2.getPicture());
				t.setTrainingDate(tr2.getTrainingDate());
				t.setTrainer(tr2.getTrainer());
				retTraining = t;
			}
		}
		addTrainingsInFile();
		return retTraining;
	}
	
	public boolean deleteTraining(Training t) {
		ArrayList<Training> trList = new ArrayList<Training>();
		trList = readTraining();
		for (Training tr : trList) {
			if (tr.getName().equals(t.getName())) {
				tr.setDeleted(1);
				addTrainingsInFile();
				return true;
			}
		}
		return false;
		
	}
	
	public Manager addManager(Manager customer)
	{
		if(customer.getName() != null && customer.getSurname()!= null  && customer.getUsername() != null)
		{
		managerList = readManagers("managers");
		userList = readUsers();
		boolean usernameDuplicate = true;
		for(User u : userList)
		{
			if(u.getUsername().equals(customer.getUsername()))
			{
				usernameDuplicate = false;
			}
		}
		User newUser = new User(customer.getUsername(),customer.getPassword(),customer.getName(),customer.getSurname(),customer.getDateOfBirth(),customer.getGender(),customer.getRole());
		userList.add(newUser);
		addUsersInFile();
		boolean nameReg = false;
		boolean surnameReg = false;
		boolean usernameReg = false;
		if(customer.getName().matches("[A-Z][a-z]+"))nameReg = true;
		if(customer.getSurname().matches("[A-Z][a-z]+"))surnameReg = true;
		if(customer.getUsername().matches("[A-Z]*[a-z]*[1-9]*"))usernameReg = true;
		if(nameReg == true && surnameReg == true && usernameReg == true && usernameDuplicate == true)
		{
			managerList.add(customer);
		}
		else
		{
			System.out.println("Invalid data");
		}
		addManagersInFile("managers");
		}
		return customer;
	}
	
	
	public Coach addCoach(Coach customer)
	{
		if(customer.getName() != null && customer.getSurname()!= null  && customer.getUsername() != null)
		{
		coachList = readCoaches();
		userList = readUsers();
		boolean usernameDuplicate = true;
		for(User u : userList)
		{
			if(u.getUsername().equals(customer.getUsername()))
			{
				usernameDuplicate = false;
			}
		}
		User newUser = new User(customer.getUsername(),customer.getPassword(),customer.getName(),customer.getSurname(),customer.getDateOfBirth(),customer.getGender(),customer.getRole());
		userList.add(newUser);
		addUsersInFile();
		boolean nameReg = false;
		boolean surnameReg = false;
		boolean usernameReg = false;
		if(customer.getName().matches("[A-Z][a-z]+"))nameReg = true;
		if(customer.getSurname().matches("[A-Z][a-z]+"))surnameReg = true;
		if(customer.getUsername().matches("[A-Z]*[a-z]*[1-9]*"))usernameReg = true;
		if(nameReg == true && surnameReg == true && usernameReg == true && usernameDuplicate == true)
		{
			coachList.add(customer);
		}
		else
		{
			System.out.println("Invalid data");
		}
		addCoachesInFile();
		}
		return customer;
	}
	
	public User loginUser(User customer)
	{
		ArrayList<User> customerList = readUsers();
	    System.out.println(customerList.size());
		User cust = null;
		for(User c : customerList)
		{
			if(c.getUsername().equals(customer.getUsername()) && c.getPassword().equals(customer.getPassword()))
			{
				cust = c;
			}
		}
		return cust;
	}
	
	public User findCustomerByUsernameAndPassword(String username,String password)
	{
		ArrayList<User> customerList = readUsers();
		System.out.println(customerList.size());
		User cust = null;
		for(User c : customerList)
		{
			if(c.getUsername().equals(username) && c.getPassword().equals(password))
			{
				cust = c;
			}
		}
		return cust;
	}
//	public User findCustomerByUsername(String username)
//	{
//		ArrayList<User> customerList = readUsers();
//		System.out.println(customerList.size());
//		User cust = null;
//		for(User c : customerList)
//		{
//			if(c.getUsername().equals(username))
//			{
//				cust = c;
//			}
//		}
//		return cust;
//	}
	public User findUserByUsername(String username)
	{
		ArrayList<User> customerList = readUsers();
		System.out.println(customerList.size());
		User cust = null;
		for(User c : customerList)
		{
			if(c.getUsername().equals(username))
			{
				cust = c;
			}
		}
		return cust;
	}
	public Customer findCustByUsername(String username)
	{
		ArrayList<Customer> customerList = readCustomers("customers");
		System.out.println(customerList.size());
		Customer cust = null;
		for(Customer c : customerList)
		{
			if(c.getUsername().equals(username))
			{
				cust = c;
			}
		}
		return cust;
	}
	
	//Koristim da dobijem ulogovanog menadzera
	public Manager findManagerByUsername(String username) {
		Manager tempManager = new Manager();
		managerList = readManagers("managers");
		for (Manager man : managerList) {
			if (man.getUsername().equals(username)) {
				tempManager = man;
			}
		}
		return tempManager;
	}
	
	
	public Manager setManagerSportObject(String username, String sportObject) {
		managerList = readManagers("managers");
		Manager man = new Manager();
		for (Manager m : managerList) {
			if (m.getUsername().equals(username)) {
				m.setSportObject(sportObject);
				man = m;
			}
		}
		addManagersInFile("managers");
		return man;
	}
	public ArrayList<Coach> findCoachesByObject(String sobject)
	{
		ArrayList<Coach> iterList = readObjectCoaches();
		ArrayList<Coach> returnList = new ArrayList<Coach>();
		
		for(Coach coach :iterList)
		{
			if(coach.getSportObjectNamee().equals(sobject))
			{
				returnList.add(coach);
			}
		}
		
		
		
		return returnList;
		
	}
	public ArrayList<Customer> findViewers(String sobject)
	{
		ArrayList<Customer> iterList = readCustomersView();
		ArrayList<Customer> returnList = new ArrayList<Customer>();
		
		for(Customer coach :iterList)
		{
			if(coach.getSportObjectNick().equals(sobject))
			{
				returnList.add(coach);
			}
		}
		
		
		
		return returnList;
	}
	public Training cancelTraining(Training t)
	{
		//t.setDeleted(1);
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		readTraining();
		for(Training tt : trainingList)
		{
			if(tt.getId() == t.getId())
			{
				if(tt.getTrainingDate().after(date))
				{
					if(tt.getTrainingDate().getDay() - date.getDay() >= 2)
					{
						tt.setDeleted(1);
					}
					else
					{
						if(tt.getTrainingDate().getMonth() - date.getMonth() != 0)
						{
						if(tt.getTrainingDate().getMonth() %2 == 0 && tt.getTrainingDate().getMonth() != 2)
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 28  )
							{
								tt.setDeleted(1);
							}
						}
						else if (tt.getTrainingDate().getMonth() %2 == 0 && tt.getTrainingDate().getMonth() == 2)
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 26  )
							{
								tt.setDeleted(1);
							}
						}
						else
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 29  )
							{
								tt.setDeleted(1);
							}
						}
						}
						
					}
				}
			}
		}
		addTrainingsInFile();
		return null;
	}
	
	//Nalazi menadzere koji nisu dodjeljeni sportskom objektu
	public ArrayList<Manager> findAvailableManagers() {
		managerList = readManagers("managers");
		ArrayList<Manager> availableManagers = new ArrayList<Manager>();
		for (Manager man : managerList) {
			if (man.getSportObject().equals("None")) {
				availableManagers.add(man);
			}
		}
		return availableManagers;
	}
	public Coach getCoachByUsername(String username)
	{
		Coach retCoach = null;
		for(Coach c : readCoaches())
		{
			if(c.getUsername().equals(username))
			{
				retCoach = c;
			}
		}
		return retCoach;
	}
	public ArrayList<Training> findTrainingsForCoach(Coach c)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				System.out.println(t.getType() + " == " + TrainingTypeEnum.Gym);
				if(t.getType() == TrainingTypeEnum.Gym)
				retList.add(t);
			}
		}
		return retList;
	}
	public ArrayList<Training> findPersonalForCoach(Coach c)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				if(t.getType() == TrainingTypeEnum.Personal)
				{
					if(t.getDeleted() == 0)
						retList.add(t);
				}
			}
		}
		return retList;
	}
	public ArrayList<Training> findGroupForCoach(Coach c)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				if(t.getType() == TrainingTypeEnum.Group)
				retList.add(t);
			}
		}
		return retList;
	}
	public ArrayList<Training> findTrainingsForCustomer(User u)
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		ArrayList<Training> retList = new ArrayList<Training>();
		readCustomerTrainings();
		for(Customer c : customerTraining)
		{
			if(c.getUsername().equals(u.getUsername()))
			{
				Training t = findTrainingById(c.getTrainingID());
				if(t.getTrainingDate().getMonth() == date.getMonth())
					retList.add(t);
			}
		}
		return retList;
	}
	
	//nalazi treninge za neki sportski objekat (za menadzera)
	public ArrayList<Training> findTrainingsBySportObject(SportObject so) {
		ArrayList<Training> trainingsSO = new ArrayList<Training>();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		trainingsSO = readTraining();
		
		
		for (Training t : trainingsSO) {
			if (t.getSportObject().getObjectName().equals(so.getObjectName()) && t.getDeleted() == 0) {
				retList.add(t);
			}
		}
		return retList;
	}
	
	//za sortiranje liste po datumu u prikazu menadzerovog sportskog objekta
	public ArrayList<Training> sortTrainingsDateAscending(SportObject so) {
		ArrayList<Training> trainingsSO = new ArrayList<Training>();
		ArrayList<Training> retList = new ArrayList<>();
		trainingsSO = readTraining();
		for (Training t : trainingsSO) {
			if (t.getSportObject().getObjectName().equals(so.getObjectName()) && t.getDeleted() == 0) {
				retList.add(t);
			}
		}
		retList.sort((d1, d2) -> d1.getTrainingDate().compareTo(d2.getTrainingDate()));
		return retList;
	}
	
	public ArrayList<Training> sortTrainingsDateDescending(SportObject so) {
		ArrayList<Training> trainingsSO = new ArrayList<Training>();
		ArrayList<Training> retList = new ArrayList<>();
		trainingsSO = readTraining();
		for (Training t : trainingsSO) {
			if (t.getSportObject().getObjectName().equals(so.getObjectName()) && t.getDeleted() == 0) {
				retList.add(t);
			}
		}
		retList.sort((d1, d2) -> d2.getTrainingDate().compareTo(d1.getTrainingDate()));
		return retList;
	}
	

}
