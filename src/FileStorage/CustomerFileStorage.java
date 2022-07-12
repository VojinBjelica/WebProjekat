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
import beans.Comment;
import beans.Customer;
import beans.CustomerType;
import beans.Dues;
import beans.DuesTypeEnum;
import beans.GenderEnum;
import beans.Manager;
import beans.ObjectTypeEnum;
import beans.Points;
import beans.PromoCode;
import beans.RoleEnum;
import beans.SportObject;
import beans.Training;
import beans.TrainingHistory;
import beans.TrainingTypeEnum;
import beans.TypeName;
import beans.User;

public class CustomerFileStorage {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	public static ArrayList<Customer> customerTraining = new ArrayList<Customer>();
	public static ArrayList<Customer> customerViewList = new ArrayList<Customer>();
	public static ArrayList<Manager> managerList = new ArrayList<Manager>();
	public static ArrayList<Coach> coachList = new ArrayList<Coach>();
	public static ArrayList<Coach> coachObjectList = new ArrayList<Coach>();
	public static ArrayList<Dues> duesList = new ArrayList<Dues>();
	public static ArrayList<User> userList = new ArrayList<User>();
	public static ArrayList<Training> trainingList = new ArrayList<Training>();
	public static ArrayList<PromoCode> promoCodeList = new ArrayList<PromoCode>();
	public static ArrayList<TrainingHistory> trainingHistory = new ArrayList<TrainingHistory>();
	public static ArrayList<Points> pointsList = new ArrayList<Points>();
	public static ArrayList<CustomerType> customerTypeList = new ArrayList<CustomerType>();
	
	public ArrayList<Customer> readCustomers(String way) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./"+ way + ".txt");
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",type= "",surname = "",gender = "",date = "";
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
                        type = st.nextToken().trim();
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    TypeName typee = TypeName.No;
                    if(type.equals("Zlatni"))typee = TypeName.Gold;
                    else if (type.equals("Srebrni")) typee = TypeName.Silver;
                    else if(type.equals("Bronzani")) typee = TypeName.Bronze;
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Customer customer = new Customer(username,password,name,surname, dt,gen,typee);
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
	public ArrayList<TrainingHistory> readTrainingHistory() {
        ArrayList<TrainingHistory> customers = new ArrayList<TrainingHistory>();
        BufferedReader in = null;
        try {
            File file = new File("./trainingHistory.txt");
            in = new BufferedReader(new FileReader(file));
            String line, date = "",training = "", customer = "", coach = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        date = st.nextToken().trim();
                        training = st.nextToken().trim();
                        customer = st.nextToken().trim();
                        coach = st.nextToken().trim();
                   
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(date);
                    GenderEnum gen = GenderEnum.Male;
                    Customer cust = findCustomerByUsername(customer);
                    Training t = findTrainingByName(training);
                    Coach c = findCoachByTraining(t);
                    TrainingHistory th = new TrainingHistory(dt,t,cust,c);
                    customers.add(th);
                    trainingHistory = customers;
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
                catch (Exception e) 
                {
                	
                }
                }
        }
        return customers;
    }
	public ArrayList<Comment> readComments() {
        ArrayList<Comment> customers = new ArrayList<Comment>();
        BufferedReader in = null;
        try {
            File file = new File("./comments.txt");
            in = new BufferedReader(new FileReader(file));
            String line, customerUsername = "",id = "",sportObjectName = "", text = "", mark = "" , approved = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	customerUsername = st.nextToken().trim();
                    	sportObjectName = st.nextToken().trim();
                    	text = st.nextToken().trim();
                    	mark = st.nextToken().trim();
                    	approved = st.nextToken().trim();
                    	id = st.nextToken().trim();
                        }
                    SportObjectService sos = new SportObjectService();
                    Customer u = findCustByUsername(customerUsername);
                    SportObject so = sos.getSportObjectByName(sportObjectName);
                    Comment com = new Comment(u,so,text,Integer.parseInt(mark),Integer.parseInt(approved),Integer.parseInt(id));
                    customers.add(com);
                    
                    commentList = customers;
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
                catch (Exception e) 
                {
                	
                }
                }
        }
        return customers;
    }
	
	public ArrayList<CustomerType> readCustomerType() {
        ArrayList<CustomerType> customers = new ArrayList<CustomerType>();
        BufferedReader in = null;
        try {
            File file = new File("./customerType.txt");
            in = new BufferedReader(new FileReader(file));
            String line, type = "",discount = "", points = "";
            StringTokenizer st;
            try {
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("") || line.indexOf('#') == 0)
                        continue;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                    	type = st.nextToken().trim();
                    	discount = st.nextToken().trim();
                    	points = st.nextToken().trim();
                      
                        }
                   
                    TypeName typee = TypeName.No;
                    if(type.equals("Zlatni"))typee = TypeName.Gold;
                    else if (type.equals("Srebrni")) typee = TypeName.Silver;
                    else if(type.equals("Bronzani")) typee = TypeName.Bronze;
                    CustomerType ct = new CustomerType(typee,Integer.parseInt(discount),Integer.parseInt(points));
                    customers.add(ct);
                    customerTypeList = customers;
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
                catch (Exception e) 
                {
                	
                }
                }
        }
        return customers;
    }
	public boolean expirationChecker(String username)
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		duesList = readDues();
		for(Dues d : duesList)
		{
			if(d.getExpirationDateAndTime().before(date))
			{
				d.setStatus(false);
			}
			boolean temp = dueActive(username);
			if(temp == true)
			{
				if(d.getCustomer().getUsername().equals(username))
					d.setStatus(false);
			}
			
		}
		addDuesInFile();
		return true;
		
	}
	
	public boolean dueActive(String username)
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		for(Dues d : readDues())
		{
			if(d.getCustomer().getUsername().equals(username) && d.getExpirationDateAndTime().after(date))
			{
				return true;
			}
		}
		return false;
		
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
            String line, name = "", type = "",check="",deleted = "",cancel="", sportObject = "",date = "",id = "",duration = "",coach = "",description = "",price="",picture="";
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
                    	price = st.nextToken().trim();
                    	picture = st.nextToken().trim();
                    	id = st.nextToken().trim();
                    	deleted = st.nextToken().trim();
                    	check = st.nextToken().trim();
                    	cancel = st.nextToken().trim();
                    	
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
                    Training train = new Training(name,typeEnum,so,Integer.parseInt(duration),c,description,picture,dt,Integer.parseInt(id),Integer.parseInt(deleted),Double.parseDouble(price),Integer.parseInt(check),Integer.parseInt(cancel));
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
            String line, ID="" , type = "",done = "" , paydate = "" , expdate = "",price = "",customer = "",status = "", number = "";
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
                    	done = st.nextToken().trim();
                    	
                        }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt = formatter.parse(paydate);
                    Date dtex = formatter.parse(expdate);
                    DuesTypeEnum typeEnum = DuesTypeEnum.Month;
                    if(type.equals("Month")) typeEnum = DuesTypeEnum.Month;
                    else typeEnum = DuesTypeEnum.Year;
                    Customer cust = findCustByUsername(customer);
                    Dues retDue = new Dues(ID,typeEnum,dt,dtex,Integer.parseInt(price),cust,Boolean.parseBoolean(status),Integer.parseInt(number),Integer.parseInt(done));
                    
                    
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
				System.out.println("Adding training: " + training.getName());
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
	public Training findTrainingByName(String name)
	{
		for(Training t : readTraining())
		{
			if(t.getName().equals(name))
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
            String line, username = "", password = "", name = "",surname = "",gender = "",date = "",role="",deleted = "",points = "";
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
                        deleted = st.nextToken().trim();
                        points = st.nextToken().trim();
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
                    User user = new User(username,password,name,surname, dt,gen,rol,Integer.parseInt(deleted),Float.parseFloat(points));
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
            outputString += formatter.format(customer.getDateOfBirth()) + ";";
            if(customer.getUserType() == TypeName.Gold)
                outputString += "Zlatni" + ";";
                else if(customer.getUserType() == TypeName.Silver)
                outputString += "Srebrni" + ";";
                else if(customer.getUserType() == TypeName.Bronze)
                    outputString += "Bronzani" + ";";
                else  
                    outputString += "None" + ";";
            output.println(outputString);
        }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public int findNextID()
	{
		int retid = 0;
		for(Comment c : readComments())
		{
			retid = c.getId();
		}
		retid = retid + 1;
		return retid;
	}
	public boolean addCommentInFile() 
    {
		
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./comments.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(Comment customer : commentList)
        {
            String outputString = "";
            outputString += customer.getCustomer().getUsername() + ";";
            outputString += customer.getSportObjectt().getObjectName() + ";";
            outputString += customer.getText() + ";";
            outputString += customer.getMark() + ";";
            outputString += customer.getApproved() + ";";
            outputString += customer.getId();
            
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
	public boolean addTrainingHistoryInFile() 
    {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./trainingHistory.txt");
        PrintWriter output = new PrintWriter(fileWriter, true);
        for(TrainingHistory customer : trainingHistory)
        {
            String outputString = "";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outputString += formatter.format(customer.getCheckInDate()) + ";";
            outputString += customer.getTraining().getName() + ";";
            outputString += customer.getCustomer().getUsername() + ";";
            outputString += customer.getTrainer().getName();
            
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
	public boolean turnOffDue()
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		for(Dues d : readDues())
		{
			if(d.getExpirationDateAndTime().before(date))
			{
				d.setStatus(false);
			}
		}
		return true;
	}
	public boolean calculatePoints()
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		float bodovi = 0;
		float broj_izgubljenih_bodova = 0;

		ArrayList<Points> retList = readPoints();
		ArrayList<Points> pointsList = readPoints();
		ArrayList<Dues> duesList = readDues();
		for(Dues d : duesList)
		{
			bodovi = 0;
			broj_izgubljenih_bodova = 0;
			if(d.getDone() == 0)
			{
			if(d.getExpirationDateAndTime().before(date) || d.isStatus() == false)
			{
				d.setDone(1);
				
				int i = 0;
				for(Points p : pointsList)
				{
					if(d.getCustomer().getUsername().equals(p.getUserUsername()))
					{
						bodovi = p.getPoints();

						retList.remove(i);
						i++;
						retList.remove(new Points(p.getUserUsername(),p.getPoints()));
					}
				}
				if(d.getExpirationDateAndTime().getDay() - date.getDay() == -1)
				{
					
					bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
					if(d.getDuesType().equals(DuesTypeEnum.Month))
					if(d.getNumberOfAppointments() >= 20 )
					{
					broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
					bodovi = bodovi - broj_izgubljenih_bodova;
					}
					if(d.getDuesType().equals(DuesTypeEnum.Year))
						if(d.getNumberOfAppointments() >= 244 )
						{
						broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
						bodovi = bodovi - broj_izgubljenih_bodova;
						}
					Points p = new Points(d.getCustomer().getUsername(),bodovi);
					retList.add(p);
				}
				else
				{
					
					if(d.getExpirationDateAndTime().getMonth() - date.getMonth() != 0)
					{
					if(d.getExpirationDateAndTime().getMonth() %2 == 0 && d.getExpirationDateAndTime().getMonth() != 2)
					{
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 29  )
						{
							bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
							if(d.getDuesType().equals(DuesTypeEnum.Month))
								if(d.getNumberOfAppointments() >= 20 )
								{
								broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
								bodovi = bodovi - broj_izgubljenih_bodova;
								}
								if(d.getDuesType().equals(DuesTypeEnum.Year))
									if(d.getNumberOfAppointments() >= 244 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
							Points p = new Points(d.getCustomer().getUsername(),bodovi);
							retList.add(p);
						}
					}
					else if (d.getExpirationDateAndTime().getMonth() %2 == 0 &&d.getExpirationDateAndTime().getMonth() == 2)
					{
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 27  )
						{
							bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
							if(d.getDuesType().equals(DuesTypeEnum.Month))
								if(d.getNumberOfAppointments() >= 20 )
								{
								broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
								bodovi = bodovi - broj_izgubljenih_bodova;
								}
								if(d.getDuesType().equals(DuesTypeEnum.Year))
									if(d.getNumberOfAppointments() >= 244 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
							Points p = new Points(d.getCustomer().getUsername(),bodovi);
							retList.add(p);
						}
					}
					else
					{
						if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 30  )
						{
							bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
							if(d.getDuesType().equals(DuesTypeEnum.Month))
								if(d.getNumberOfAppointments() >= 20 )
								{
								broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
								bodovi = bodovi - broj_izgubljenih_bodova;
								}
								if(d.getDuesType().equals(DuesTypeEnum.Year))
									if(d.getNumberOfAppointments() >= 244 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
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
								
								bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
								if(d.getDuesType().equals(DuesTypeEnum.Month))
									if(d.getNumberOfAppointments() >= 20 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
									if(d.getDuesType().equals(DuesTypeEnum.Year))
										if(d.getNumberOfAppointments() >= 244 )
										{
										broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
										bodovi = bodovi - broj_izgubljenih_bodova;
										}
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						else if (d.getExpirationDateAndTime().getMonth() %2 == 0 &&d.getExpirationDateAndTime().getMonth() == 2)
						{
							if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 27  )
							{
								bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
								if(d.getDuesType().equals(DuesTypeEnum.Month))
									if(d.getNumberOfAppointments() >= 20 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
									if(d.getDuesType().equals(DuesTypeEnum.Year))
										if(d.getNumberOfAppointments() >= 244 )
										{
										broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
										bodovi = bodovi - broj_izgubljenih_bodova;
										}
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						else
						{
							if(date.getDay() - d.getExpirationDateAndTime().getDay() <= 30  )
							{
								bodovi += (d.getPrice()/1000.0) * d.getNumberOfAppointments();
								if(d.getDuesType().equals(DuesTypeEnum.Month))
									if(d.getNumberOfAppointments() >= 20 )
									{
									broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
									bodovi = bodovi - broj_izgubljenih_bodova;
									}
									if(d.getDuesType().equals(DuesTypeEnum.Year))
										if(d.getNumberOfAppointments() >= 244 )
										{
										broj_izgubljenih_bodova = d.getPrice()/1000 * 133 * 4;
										bodovi = bodovi - broj_izgubljenih_bodova;
										}
								Points p = new Points(d.getCustomer().getUsername(),bodovi);
								retList.add(p);
							}
						}
						
						
					}
					
				}
			}
		}
		}
		addDuesInFile();
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
            outputString += customer.getNumberOfAppointments() + ";";
            outputString += customer.getDone();
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
            outputString += formatter.format(customer.getTrainingDate()) + ";";
            outputString += customer.getPrice() + ";";
            outputString += customer.getPicture() + ";";
            outputString += customer.getId() + ";"; 
            outputString += customer.getDeleted() + ";"; 
            outputString += customer.getCheck() + ";";
            outputString += customer.getCancel();
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public boolean pickTraining(String u,Training tr)
	{
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Training t = tr;
		boolean trainingCheck = false;
		Dues d = findDueByUsername(u);
		if(d == null)
		{
			trainingCheck = false;
		}
		else if(d.isStatus() == true && d.getNumberOfAppointments() != 0)
		{
			trainingCheck = true;
			decrementApp(d.getID());
		}
		
		return trainingCheck;
		
	}
	public Dues findDueByUsername(String username)
	{
		ArrayList<Dues> dues = readDues();
		for(Dues due : dues)
		{
			if(due.getCustomer().getUsername().equals(username) && due.isStatus() == true)
			{
				return due;
			}
		}
		return null;
	}
	public boolean scheduleCheck(String name)
	{
		for(Training t : readTraining())
		{
			if(t.getName().equals(name))
			{
				t.setCheck(t.getCheck()+1);
			}
		}
		System.out.println("Zakazao");
		addTrainingsInFile();
		return true;
	}
	public boolean decrementApp(String due)
	{
		for(Dues t : readDues())
		{
			if(t.getID().equals(due))
			{
				t.setNumberOfAppointments(t.getNumberOfAppointments()-1);
			}
		}
		addDuesInFile();
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
	public boolean writePointsInFile()
	{
		userList = readUsers();
		pointsList = readPoints();
		for(Points p : pointsList)
		{
			for(User u : userList)
			{
				if(p.getUserUsername().equals(u.getUsername()))
				{
					u.setCollectedPoints(p.getPoints());
				}
			}
		}
		addUsersInFile();
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
            outputString += ";" + customer.getRole() + ";";
            outputString += customer.getDeleted() + ";";
            outputString += customer.getCollectedPoints();
            output.println(outputString);
        }
        //addCustomersToUsers();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
	public Comment addComment(Comment com)
	{
		commentList = readComments();
		commentList.add(com);
		addCommentInFile();
		return com;
	}
	public ArrayList<Comment> approvedComments(SportObject so)
	{
		commentList = readComments();
		ArrayList<Comment> retList = new ArrayList<Comment>();
		for(Comment c : commentList)
		{
			if(c.getSportObjectt().getObjectName().equals(so.getObjectName()))
			if(c.getApproved() == 1)
				retList.add(c);
		}
		return retList;
	}
	public ArrayList<Comment> allComments(SportObject so)
	{
		commentList = readComments();
		ArrayList<Comment> retList = new ArrayList<Comment>();
		for(Comment c : commentList)
		{
			if(c.getSportObjectt().getObjectName().equals(so.getObjectName()))
			
				retList.add(c);
		}
		return retList;
	}
	public Comment approveComment(int id)
	{
		commentList = readComments();
		Comment co = null;
		for(Comment c : commentList)
		{
			if(c.getId() == id)
			{
						c.setApproved(1);
						co = c;
			}	
		}
		addCommentInFile();
		return co;
	}
	public ArrayList<User> deleted(String username)
	{
		userList = readUsers();
		ArrayList<User> retList = new ArrayList<User>();
		for(User u : userList)
		{
			if(u.getUsername().equals(username))
			{
				u.setDeleted(1);
				
			}
			retList.add(u);
		}
		addUsersInFile();
		return retList;
	}
	public Coach findCoachByTraining(Training t)
	{
		Coach c = null;
		for(Training tr : readTraining())
		{
			if(tr.getName().equals(t.getName()))
			{
				c = tr.getTrainer();
				return c;
			}
		}
		return null;
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
	public TrainingHistory addTrainingHistory(TrainingHistory th)
	{
		trainingHistory = readTrainingHistory();

		
		trainingHistory.add(th);
		addTrainingHistoryInFile();

		
		return th;
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
		User cust = null;
		for(User c : customerList)
		{
			if(c.getUsername().equals(customer.getUsername()) && c.getPassword().equals(customer.getPassword()))
			{
				if(c.getDeleted() == 0)
				cust = c;
			}
		}
		return cust;
	}
	
	public User findCustomerByUsernameAndPassword(String username,String password)
	{
		ArrayList<User> customerList = readUsers();
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
	public Customer findCustomerByUsername(String username)
	{
		ArrayList<Customer> customerList = readCustomers("customers");
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
//	public User findCustomerByUsername(String username)
//	){
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
		trainingList = readTraining();
		int i = 0;
		for(Training tt : trainingList)
		{
			System.out.println("Trening: " + tt.getName() + " == " + t.getName());
			if(tt.getName().equals(t.getName()))
			{
				if(tt.getTrainingDate().after(date))
				{
					if(tt.getTrainingDate().getDay() - date.getDay() >= 2)
					{
						//tt.setDeleted(1);
						tt.setCancel(1);
					}
					else
					{
						if(tt.getTrainingDate().getMonth() - date.getMonth() != 0)
						{
						if(tt.getTrainingDate().getMonth() %2 == 0 && tt.getTrainingDate().getMonth() != 2)
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 28  )
							{
								//tt.setDeleted(1);
								tt.setCancel(1);
							}
						}
						else if (tt.getTrainingDate().getMonth() %2 == 0 && tt.getTrainingDate().getMonth() == 2)
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 26  )
							{
								//tt.setDeleted(1);
								tt.setCancel(1);
							}
						}
						else
						{
							if(date.getMonth() - tt.getTrainingDate().getMonth() <= 29  )
							{
								//tt.setDeleted(1);
								tt.setCancel(1);
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
	public void cancelTr(Training t)
	{
		trainingList = readTraining();
		for(Training tt : trainingList)
		{
			if(tt.getName().equals(t.getName()))
				tt.setCancel(1);
		}
		addTrainingsInFile();
		
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
				if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0)
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
					if(t.getCancel() == 0)
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
		ArrayList<TrainingHistory> trHistory = readTrainingHistory();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		for(TrainingHistory trh : trHistory)
		{
			if(trh.getCustomer().getUsername().equals(u.getUsername()))
			{
				if(trh.getTraining().getTrainingDate().getMonth() == date.getMonth())
					retList.add(trh.getTraining());
			}
		}
		return retList;
	}
	
	public ArrayList<Training> searchTrainingsForCustomer(User u, String soName, String priceFrom, String priceTo, Date dateFrom, Date dateTo, ObjectTypeEnum soType, TrainingTypeEnum type) {
		readCustomerTrainings();
		ArrayList<Training> retList = new ArrayList<Training>();
		ArrayList<TrainingHistory> trHistory = readTrainingHistory();
		System.out.println("IMA OVOLIKO TRAINING HISTORYJA : " + trHistory.size());
		
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		String searchSoName;
		double searchPriceFrom;
		double searchPriceTo;
		Date searchDateFrom;
		Date searchDateTo;
		
		if (soName.equals("None")) {
			searchSoName = " ";
		} else {
			searchSoName = soName;
		}
		if (dateTo == null) {
			searchDateTo = new Date(195, 01, 01);
		} else {
			searchDateTo = dateTo;
		}
		
		if (dateFrom == null) {
			searchDateFrom = new Date(0, 01, 01);
		} else {
			searchDateFrom = dateFrom;
		}
		
		
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
		
		if (type == null) {
			type = TrainingTypeEnum.None;
		}
		if (soType == null) {
			soType = ObjectTypeEnum.None;
		}
		
			
				for(TrainingHistory trh : trHistory)
				{
					if(trh.getCustomer().getUsername().equals(u.getUsername()))
					{
						if(trh.getTraining().getTrainingDate().getMonth() == date.getMonth()) {
							if (type != TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
								if (trh.getTraining().getSportObject().getObjectName().toLowerCase().trim().contains(searchSoName.toLowerCase().trim()) &&
										trh.getTraining().getPrice() >= searchPriceFrom && trh.getTraining().getPrice() <= searchPriceTo &&
										trh.getCheckInDate().after(searchDateFrom) && trh.getCheckInDate().before(searchDateTo) 
										&& trh.getTraining().getType() == type && trh.getTraining().getSportObject().getObjectType() == soType) {
									retList.add(trh.getTraining());
								}
							} else if (type != TrainingTypeEnum.None && soType == ObjectTypeEnum.None) {
								if (trh.getTraining().getSportObject().getObjectName().toLowerCase().trim().contains(searchSoName.toLowerCase().trim()) &&
										trh.getTraining().getPrice() >= searchPriceFrom && trh.getTraining().getPrice() <= searchPriceTo &&
										trh.getCheckInDate().after(searchDateFrom) && trh.getCheckInDate().before(searchDateTo) &&
										trh.getTraining().getType() == type) {
									retList.add(trh.getTraining());
								}
							} else if (type == TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
								if (trh.getTraining().getSportObject().getObjectName().toLowerCase().trim().contains(searchSoName.toLowerCase().trim()) &&
										trh.getTraining().getPrice() >= searchPriceFrom && trh.getTraining().getPrice() <= searchPriceTo &&
										trh.getCheckInDate().after(searchDateFrom) && trh.getCheckInDate().before(searchDateTo) &&
										trh.getTraining().getSportObject().getObjectType() == soType) {
									retList.add(trh.getTraining());
								} 
							}else if (soName.equals("None") && priceFrom.equals("None") && priceTo.equals("None") && dateFrom == null && dateTo == null) {
								retList.add(trh.getTraining());
							} else {
								if (trh.getTraining().getSportObject().getObjectName().toLowerCase().trim().contains(searchSoName.toLowerCase().trim()) &&
										trh.getTraining().getPrice() >= searchPriceFrom && trh.getTraining().getPrice() <= searchPriceTo &&
										trh.getCheckInDate().after(searchDateFrom) && trh.getCheckInDate().before(searchDateTo)) {
									retList.add(trh.getTraining());
								}
							}
						
						}
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
	

	public ArrayList<Training> searchTrainingsNameTypePrice(String name, TrainingTypeEnum type, String priceFrom, String priceTo, SportObject so) {
		ArrayList<Training> trainingsSO = new ArrayList<Training>();
		ArrayList<Training> retList = new ArrayList<>();
		
		String searchName;
		TrainingTypeEnum searchType;
		double searchPriceFrom;
		double searchPriceTo;
		
		if (name.equals("None")) {
			searchName = " ";
		} else {
			searchName = name;
		}
		
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
		
		
		
		trainingsSO = readTraining();
		for (Training t : trainingsSO) {
			if(type != TrainingTypeEnum.None) {
				if (t.getSportObject().getObjectName().equals(so.getObjectName()) &&
						t.getDeleted() == 0 &&
						t.getName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) &&
						t.getType() == type && t.getPrice() >= searchPriceFrom && t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
			} else if (t.getSportObject().getObjectName().equals(so.getObjectName()) && t.getDeleted() == 0 && type == TrainingTypeEnum.None && name.equals("None") && priceTo.equals("None") && priceFrom.equals("None")) {
				retList.add(t);
			} else {
				if (t.getSportObject().getObjectName().equals(so.getObjectName()) &&
						t.getDeleted() == 0 &&
						t.getName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) &&
						t.getPrice() >= searchPriceFrom && t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
			}
		}
		return retList;
	}
	public CustomerType checkForTypeName(User u)
	{
		ArrayList<Points> pointList = readPoints();
		Points point = null;
		ArrayList<CustomerType> typeList = readCustomerType();
		for(Points p : pointList)
		{
			if(p.getUserUsername().equals(u.getUsername()))
			{
				point = p;
			}
		}
		TypeName custType = null;
		for(CustomerType ct : typeList)
		{
			String s = ct.getNeededPoints() +"";
			if(Float.parseFloat(s) <= point.getPoints())
			{
				custType = ct.getCustomerType();
				break;
			}
			else custType = TypeName.No;
		}
		for(Customer c : readCustomers("customers"))
		{
			if(c.getUsername().equals(u.getUsername()))
			{
				c.setUserType(custType);
			}
		}
		addCustomerInFile("customers");
		CustomerType tc = null;
		for(CustomerType ct : readCustomerType())
		{
			if(ct.getCustomerType() == custType)
				tc = ct;
		}
		return tc;
	}
	public int calculateDiscountByType(Customer cu)
	{
		TypeName tn = null;
		for(Customer c : readCustomers("customers"))
		{
			if(c.getUsername().equals(cu.getUsername()))
			{
				if(c.getUserType().equals(TypeName.Gold))tn = TypeName.Gold;
				else if (c.getUserType().equals(TypeName.Silver))tn = TypeName.Silver;
				else if(c.getUserType().equals(TypeName.Bronze))tn = TypeName.Bronze;
				else tn = TypeName.No;
			}
		}
		int discount = 0;
		for(CustomerType ct : readCustomerType())
		{
			if(ct.getCustomerType().equals(tn))
			{
				discount = ct.getDiscount();
			}
		}
		return discount;
	}
	
	public ArrayList<Training> searchTrainersGymTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		String searchName;
		double searchPriceFrom;
		double searchPriceTo;
		
		if (soName.equals("None")) {
			searchName = " ";
		} else {
			searchName = soName;
		}
		
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
		
		
		
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				if (type != TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
				} else if (type == TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
					
				} else if (type != TrainingTypeEnum.None && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
					
				} else if (type == TrainingTypeEnum.None && soType == ObjectTypeEnum.None)  {
					if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								 && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
				}
				
				else if (type == TrainingTypeEnum.None && soName.equals("None") && priceTo.equals("None") && priceFrom.equals("None") && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Gym && t.getDeleted() == 0) {
						retList.add(t);
					}					
				}
			}
		}
		return retList;
	}
	
	public ArrayList<Training> searchTrainersPersonalTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		String searchName;
		double searchPriceFrom;
		double searchPriceTo;
		
		if (soName.equals("None")) {
			searchName = " ";
		} else {
			searchName = soName;
		}
		
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
		
		
		
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				if (type != TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Personal && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
				} else if (type == TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Personal && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
					
				} else if (type != TrainingTypeEnum.None && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Personal && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
					
				} else if (type == TrainingTypeEnum.None && soType == ObjectTypeEnum.None)  {
					if(t.getType() == TrainingTypeEnum.Personal && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								 && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
				}
				
				else if (type == TrainingTypeEnum.None && soName.equals("None") && priceTo.equals("None") && priceFrom.equals("None") && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Personal && t.getDeleted() == 0) {
						retList.add(t);
					}					
				}
			}
		}
		return retList;
	}
	
	public ArrayList<Training> searchTrainersGroupTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type)
	{
		ArrayList<Training> trainingPrivList = readTraining();
		ArrayList<Training> retList = new ArrayList<Training>();
		
		String searchName;
		double searchPriceFrom;
		double searchPriceTo;
		
		if (soName.equals("None")) {
			searchName = " ";
		} else {
			searchName = soName;
		}
		
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
		
		
		
		for(Training t : trainingPrivList)
		{
			Coach coach = getCoachByUsername(t.getTrainer().getUsername());
			if(coach.getUsername().equals(c.getUsername()))
			{
				if (type != TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Group && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
				} else if (type == TrainingTypeEnum.None && soType != ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Group && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getSportObject().getObjectType() == soType && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
					
				} else if (type != TrainingTypeEnum.None && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Group && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								&& t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo && t.getType() == type) {
							retList.add(t);
						}
					}
					
				} else if (type == TrainingTypeEnum.None && soType == ObjectTypeEnum.None)  {
					if(t.getType() == TrainingTypeEnum.Group && t.getDeleted() == 0) {
						if (t.getSportObject().getObjectName().toLowerCase().trim().contains(searchName.toLowerCase().trim()) 
								 && t.getPrice() >= searchPriceFrom
								&& t.getPrice() <= searchPriceTo) {
							retList.add(t);
						}
					}
				}
				
				else if (type == TrainingTypeEnum.None && soName.equals("None") && priceTo.equals("None") && priceFrom.equals("None") && soType == ObjectTypeEnum.None) {
					if(t.getType() == TrainingTypeEnum.Group && t.getDeleted() == 0) {
						retList.add(t);
					}					
				}
			}
		}
		return retList;
	}
	
	
	
	
}
