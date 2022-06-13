package FileStorage;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import beans.Customer;
import beans.GenderEnum;

public class CustomerFileStorage {
	
	public ArrayList<Customer> readCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader in = null;
        try {
            File file = new File("./customers.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            String line, username = "", password = "", name = "",surname = "",gender = "", locDate = "";
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
                        locDate = st.nextToken().trim();
                    }
                    GenderEnum gen = GenderEnum.Male;
                    if(gender.equals("Male")) gen = GenderEnum.Male;
                    else if(gender.equals("Female")) gen = GenderEnum.Female;
                    Customer customer = new Customer(username,password,name,surname, LocalDate.of(2020,5,5),gen);
                    customers.add(customer);
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
	public void writeCustomers() {
		ArrayList<Customer> customerList = readCustomers();
		for(Customer c : customerList)
		{
			System.out.println(c.getName() + "," + c.getSurname());
		}
	
	}

}
