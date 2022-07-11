package Service;

import java.util.ArrayList;
import java.util.Date;

import FileStorage.CustomerFileStorage;
import beans.Coach;
import beans.Comment;
import beans.Customer;
import beans.CustomerType;
import beans.Dues;
import beans.Manager;
import beans.ObjectTypeEnum;
import beans.PromoCode;
import beans.SportObject;
import beans.Training;
import beans.TrainingHistory;
import beans.TrainingTypeEnum;
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
	public ArrayList<Comment> readComments() {
		return cfs.readComments();
	}
	public int findNextID()
	{
		return cfs.findNextID();
	}
	public ArrayList<Comment> approvedComments(SportObject so)
	{
		return cfs.approvedComments(so);
	}
	public ArrayList<Comment> allComments(SportObject so)
	{
		return cfs.allComments(so);
	}
	public Comment approveComment(int  com)
	{
		return cfs.approveComment(com);
	}
	public boolean writePointsInFile()
	{
		return cfs.writePointsInFile();
	}
	public Comment addComment(Comment com)
	{
		return cfs.addComment(com);
	}
	public boolean expirationChecker(String username)
	{
		return cfs.expirationChecker(username);
	}
	public CustomerType checkForTypeName(User u)
	{
		return cfs.checkForTypeName(u);
	}
	public int calculateDiscountByType(Customer cu)
	{
		return cfs.calculateDiscountByType(cu);
	}
	public ArrayList<User> deleted(String username)
	{
		return cfs.deleted(username);
	}
	public boolean addTrainingsInFile() 
    {
		return cfs.addTrainingsInFile();
    }
	public boolean scheduleCheck(String name)
	{
		return cfs.scheduleCheck(name);
	}
	public boolean pickTraining(String u,Training tr)
	{
		return cfs.pickTraining(u, tr);
	}
	public boolean dueActive(String username)
	{
		return cfs.dueActive(username);
	}
	public TrainingHistory addTrainingHistory(TrainingHistory th)
	{
		return cfs.addTrainingHistory(th);
	}
	public Customer findCustomerByUsername(String username)
	{
		return cfs.findCustomerByUsername(username);
	}
	public Coach findCoachByTraining(Training t)
	{
		return cfs.findCoachByTraining(t);
	}
	public Training findTrainingByName(String name)
	{
		return cfs.findTrainingByName(name);
	}
	public ArrayList<PromoCode> readPromoCodes() {
		return cfs.readPromoCodes();
	}
	public boolean calculatePoints()
	{
		return cfs.calculatePoints();
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
	public void cancelTr(Training t)
	{
		cfs.cancelTr(t);
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
	
	public ArrayList<Training> searchTrainersGymTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type) {
		return cfs.searchTrainersGymTrainings(c, soName, soType, priceFrom, priceTo, type);
	}
	
	
	public ArrayList<Training> searchTrainingsNameTypePrice(String name, TrainingTypeEnum type, String priceFrom, String priceTo, SportObject so) {
		return cfs.searchTrainingsNameTypePrice(name, type, priceFrom, priceTo, so);
	}
	
	public ArrayList<Training> searchTrainersPersonalTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type) {
		return cfs.searchTrainersPersonalTrainings(c, soName, soType, priceFrom, priceTo, type);
	}
	
	public ArrayList<Training> searchTrainersGroupTrainings(Coach c, String soName, ObjectTypeEnum soType, String priceFrom, String priceTo, TrainingTypeEnum type){
		return cfs.searchTrainersGroupTrainings(c, soName, soType, priceFrom, priceTo, type);
	}
	
	public ArrayList<Training> searchTrainingsForCustomer(User u, String soName, String priceFrom, String priceTo, Date dateFrom, Date dateTo, ObjectTypeEnum soType, TrainingTypeEnum type) {
		return cfs.searchTrainingsForCustomer(u, soName, priceFrom, priceTo, dateFrom, dateTo, soType, type);
	}
	
}
