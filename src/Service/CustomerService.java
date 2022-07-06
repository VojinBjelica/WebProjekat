package Service;

import java.util.ArrayList;

import FileStorage.CustomerFileStorage;
import beans.Coach;
import beans.Customer;
import beans.Dues;
import beans.Manager;
import beans.PromoCode;
import beans.SportObject;
import beans.Training;
import beans.User;

public class CustomerService {
	
	public CustomerFileStorage cfs;
	public CustomerService()
	{
		cfs = new CustomerFileStorage();
	}
	public ArrayList<Customer> readCustomers()
	{
		return cfs.readCustomers("customers");
	}
	public ArrayList<PromoCode> readPromoCodes() {
		return cfs.readPromoCodes();
	}
	public boolean addCodesInFile() 
    {
		return cfs.addCodesInFile();
    }
	public boolean usedCode(String codeName)
	{
		return cfs.usedCode(codeName);
	}
	public void addDues(Dues due)
	{
		cfs.addDues(due);
	}
	public boolean addPromoCode(PromoCode code)
	{
		return cfs.addPromoCode(code);
	}
	public String generateID()
	{
		return cfs.generateID();
	}
	public ArrayList<Dues> readDues()
	{
		return cfs.readDues();
	}
	public boolean addDuesInFile()
	{
		return cfs.addDuesInFile();
	}
	public Training findTrainingById(int id)
	{
		return cfs.findTrainingById(id);
	}
	public User findUserByUsername(String username)
	{
		return cfs.findUserByUsername(username);
	}
	public ArrayList<Training> findTrainingsForCustomer(User u)
	{
		return cfs.findTrainingsForCustomer(u);
	}
	public Training cancelTraining(Training t)
	{
		return cfs.cancelTraining(t);
	}
	public ArrayList<Training> findGroupForCoach(Coach c)
	{
		return cfs.findGroupForCoach(c);
	}
	public ArrayList<Training> findTrainingsForCoach(Coach c)
	{
		return cfs.findTrainingsForCoach(c);
	}
	public ArrayList<Training> findPersonalForCoach(Coach c)
	{
		return cfs.findPersonalForCoach(c);
	}
	public Coach getCoachByUsername(String username)
	{
		return cfs.getCoachByUsername(username);
	}
	public ArrayList<Customer> readCustomersView() {
		return cfs.readCustomersView();
	}
	public ArrayList<Training> readTraining() {
		return cfs.readTraining();
	}
	public ArrayList<Manager> readManagers()
	{
		return cfs.readManagers("managers");
	}
	public ArrayList<Coach> readCoaches()
	{
		return cfs.readCoaches();
	}
	public User editProfile(User user,User usertwo)
	{
		return cfs.editProfile(user,usertwo);
	}
	public void writeCustomers()
	{
		cfs.writeCustomers();
	}
	public ArrayList<Coach> findCoachesByObject(String sobject)
	{
		return cfs.findCoachesByObject(sobject);
	}
	public ArrayList<Customer> findViewers(String sobject)
	{
		return cfs.findViewers(sobject);
	}
	public boolean addCustomerViewInFile() 
    {
		return cfs.addCustomerViewInFile();
    }
	public boolean addViewSecret(Customer cust, String objectName)
	{
		return cfs.addViewSecret(cust, objectName);
	}
	public ArrayList<User> readUsers() {
		return cfs.readUsers();
	}
	public boolean addCustomerInFile()
	{
		return cfs.addCustomerInFile("customers");
	}
	public boolean addManagersInFile()
	{
		return cfs.addManagersInFile("managers");
	}
	public Customer addCustomer(Customer customer)
	{
		return cfs.addCustomer(customer);
	}
	public Coach addCoach(Coach customer)
	{
		return cfs.addCoach(customer);
	}
	public Manager addManagers(Manager customer)
	{
		return cfs.addManager(customer);
	}
	public User loginUser(User customer)
	{
		return cfs.loginUser(customer);
	}
	public User findCustomerByUsernameAndPassword(String username,String password)
	{
		return cfs.findCustomerByUsernameAndPassword(username, password);
	}
	
	public Manager findManagerByUsername(String username) {
		return cfs.findManagerByUsername(username);
	}
	
	public ArrayList<Manager> findAvailableManagers(){
		return cfs.findAvailableManagers();
	}
	
	public Manager setManagerSportObject(String username, String sportObject) {
		return cfs.setManagerSportObject(username, sportObject);
	}
	
	public boolean addTraining(Training training) {
		return cfs.addTraining(training);
	}
	public ArrayList<Training> findTrainingsBySportObject(SportObject so) {
		return cfs.findTrainingsBySportObject(so);
	}
	
	public Training editTraining(Training tr1, Training tr2) {
		return cfs.editTraining(tr1, tr2);
	}
	
	public boolean deleteTraining(Training t) {
		return cfs.deleteTraining(t);
	}
	
	public ArrayList<Training> sortTrainingsDateAscending(SportObject so) {
		return cfs.sortTrainingsDateAscending(so);
	}
	
	public ArrayList<Training> sortTrainingsDateDescending(SportObject so) {
		return cfs.sortTrainingsDateDescending(so);
	}
	
	
}
