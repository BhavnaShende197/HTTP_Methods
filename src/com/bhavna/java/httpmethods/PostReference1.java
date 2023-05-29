package com.bhavna.java.httpmethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;

public class PostReference1 {

	public static void main(String[] args) {
		
		
		//Step 1 : Declare Base URI and request body variables
		String BaseURI = "https://reqres.in";
		
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		//Fetch request Body parameter values.
		
	    //Parse the response using jsonPath
		
		JsonPath jsprequest = new JsonPath(requestBody);   //Creating s JSON Object 
		String req_name = jsprequest.getString("name");
		String req_job = jsprequest.getString("job");
		
		//generate date in format as shown 
	    LocalDateTime Date = LocalDateTime.now();
		String expectedDate = Date.toString().substring(0,11);
		
		
		//Declare Base URI
		RestAssured.baseURI= BaseURI;
		
		// Configure Request Body
		int statusCode=given().header("Content-Type","application/json").body(requestBody)
				.when().post("/api/users").then().extract().statusCode();
		
		
		//Without Log all
		String responseBody = given().header("Content-Type","application/json").body(requestBody)
				.when().post("/api/users").then().extract().response().asString();
		
		
		
		// Parse the response body
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt"); 
		res_createdAt = res_createdAt.substring(0,11);
		
		
		// Validate the response body parameters
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt, expectedDate);
		
		System.out.println("statusCode is " +statusCode);
		System.out.println("responseBody is " +responseBody);
	    System.out.println(res_createdAt);
	    System.out.println("expectedDate is " +expectedDate);
	    System.out.println(".............Bhavna have done this code...........");
	    
	    
	  }
			
	}
 

