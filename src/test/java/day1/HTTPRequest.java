package day1;

import org.testng.annotations.Test;
 import static io.restassured.RestAssured.*;
 import static io.restassured.matcher.RestAssuredMatchers.*;
 import static org.hamcrest.Matchers.*;
/*
 * given()-->all prerequisite-->cookies,set cookies, content-type, add param
 * 
 * when()-->request-->get,put,post,delete
 * 
 * then()-->all validation
 * 
 * */

import java.util.HashMap;

public class HTTPRequest {
	

	int id;
	 //Getting a single user
	
	@Test()
	void GetUser()
	{
		given()
		
		.when()
		.get("https://reqres.in/api/users/2")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
	//Getting all user
	
	@Test()
	void GetAllUser()
	{
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
	}

//Create a new user

	@Test()
	void CrateUser()
	{
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


	
	/*Update user  
	 * 
	 * First get the id of the user and then update the data of the corresponding data.*/	
	
	@Test(priority=1)
	void CreateUser()
	{	
		HashMap<String,String> data= new HashMap();
		data.put("name","Sharukh"); 
		data.put("job","SDET");
		
		id=given()
		.contentType("application/json").body(data)
		
		.when()
		.post("https://reqres.in/api/users").jsonPath().getInt("id");

	}
	
	
	@Test(priority=2,dependsOnMethods= {"CreateUser"})
	void updateUser()
	{
		HashMap<String,String> data= new HashMap();
		data.put("name","Rana"); 
		data.put("job","Dev");
		
		given()
		.contentType("application/json").body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
	}


	
	// Delete User
	
	@Test
	void deleteUser()
	{		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/2")
		
		.then()
		.statusCode(204)
		.log().all();
	}
	
}
