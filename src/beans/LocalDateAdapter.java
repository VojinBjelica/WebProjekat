package beans;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {


	@Override
	public LocalDate read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		 if (arg0.peek() == JsonToken.NULL) {
			 	arg0.nextNull();
	            return null;
	        } else {
	            return LocalDate.parse(arg0.nextString());
	        }
	}

	@Override
	public void write(JsonWriter arg0, LocalDate arg1) throws IOException {
		// TODO Auto-generated method stub
		if (arg1 == null) {
			arg0.nullValue();
        } else {
        	arg0.value(arg1.toString());
        }
		
	}

}
