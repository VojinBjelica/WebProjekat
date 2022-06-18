package beans;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CustomerAdapter implements JsonSerializer<Customer> {

	@Override
	public JsonElement serialize(Customer arg0, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		 JsonObject obj = new JsonObject();
	        obj.addProperty("name", arg0.getName());
	        obj.addProperty("surname", arg0.getSurname());
	        obj.addProperty("username", arg0.getUsername());
	        obj.addProperty("password", arg0.getPassword());
	        obj.addProperty("gender", arg0.getGender().toString());
	        obj.addProperty("date", arg0.getDateOfBirth().toString());
	        

	        
		return obj;
	}
	

}
