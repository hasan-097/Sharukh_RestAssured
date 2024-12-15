package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

public class DifferentWaysToCreateRequestBody {
	
	
/*
	//Request Body using HashMap
	
	@Test
	void usingHashMap() {
		HashMap<String,String> data=new HashMap();
		data.put("name","Sharukh");
		data.put("job","SDET");
		
		given()
		.contentType("application/json").body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.log().all();
	}

*/	
	
	//Request Body using external Json File
	
		@Test
		void usingExternalJson() {
			HashMap<String,String> data=new HashMap();
			data.put("name","Sharukh");
			data.put("job","SDET");
			
			given()
			.contentType("application/json").body(data)
			
			.when()
			.post("https://reqres.in/api/users")
			
			.then()
			.statusCode(201)
			.log().all();
		}
}
