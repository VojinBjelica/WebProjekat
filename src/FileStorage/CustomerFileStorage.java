package FileStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import Service.SportObjectService;
import beans.Coach;
import beans.Customer;
import beans.GenderEnum;
import beans.Manager;
import beans.RoleEnum;
import beans.SportObject;
import beans.Training;
import beans.TrainingTypeEnum;
import beans.User;

public class CustomerFileStorage {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Customer> customerViewList = new ArrayList<Customer>();
	public static ArrayList<Manager> managerList = new ArrayList<Manager>();
	public static ArrayList<Coach> coachList = new ArrayList<Coach>();
	public static ArrayList<Coach> coachObjectList = new ArrayList<Coach>();
	public static ArrayList<User> userList = new ArrayList<User>();
	public static ArrayList<Training> trainingList = new ArrayList<Training>();
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
	public ArrayList<Training> readTraining() {
        ArrayList<Training> customers = new ArrayList<Training>();
        BufferedReader in = null;
        try {
            File file = new File("./trainings.txt");
            in = new BufferedReader(new FileReader(file));
            String line, name = "", type = "", sportObject = "",duration = "",coach = "",description = "";
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
                        }
                    
                    TrainingTypeEnum typeEnum = TrainingTypeEnum.Group;
                    if(type.equals("Group")) typeEnum = TrainingTypeEnum.Group;
                    else if(type.equals("Gym")) typeEnum = TrainingTypeEnum.Gym;
                    else typeEnum = TrainingTypeEnum.Personal;
                    SportObjectService sos = new SportObjectService();
                    SportObject so = sos.getSportObjectByName(sportObject);
                    Coach c = getCoachByUsername(coach);
                    Training train = new Training(name,typeEnum,so,Integer.parseInt(duration),c,description);
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
	public boolean addViewSecret(Customer cust, String objectName)
	{
		customerViewList = readCustomersView();
		customerViewList.add(new Customer(cust.getUsername(),cust.getPassword(),cust.getName(),cust.getSurname(),cust.getDateOfBirth(),cust.getGender(),objectName));
		addCustomerViewInFile();
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
				retList.add(t);
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
	
	

}
