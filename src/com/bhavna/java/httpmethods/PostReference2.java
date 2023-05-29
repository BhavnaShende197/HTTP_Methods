package com.bhavna.java.httpmethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class PostReference2 {

	public static void main(String[] args) {
		
		
		//Step 1 : Declare Base URI and request body variables
		String BaseURI = "https://fakerestapi.azurewebsites.net";
		
		String requestBody = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"idBook\": 0,\r\n"
				+ "  \"firstName\": \"bhavna\",\r\n"
				+ "  \"lastName\": \"shende\"\r\n"
				+ "}";
		
		//Fetch request Body parameter values.
		
	    //Parse the response using jsonPath
		
		JsonPath jsprequest = new JsonPath(requestBody);   //Creating s JSON Object 
		String req_firstName = jsprequest.getString("firstName");
		String req_lastName = jsprequest.getString("lastName");
		
		
	
		RestAssured.baseURI= BaseURI;
		
		// Configure Request Body
		int statusCode=given().header("Content-Type","application/json").body(requestBody)
				.when().post("/api/users").then().extract().statusCode();
		
		
		//Without Log all
		String responseBody = given().header("Content-Type","application/json").body(requestBody)
				.when().post("/api/users").then().extract().response().asString();
		
		
		
		// Parse the response body
		JsonPath jsp = new JsonPath(responseBody);
		String res_firstName = jsp.getString("firstName");
		String res_lastName = jsp.getString("lastName");
		
		
		
		// Validate the response body parameters
		Assert.assertEquals(res_firstName, req_firstName);
		Assert.assertEquals(res_lastName, req_lastName);
		
		
		System.out.println("statusCode is " +statusCode);
		System.out.println("responseBody is " +responseBody);
	    
	    
	    
	  }
			
	}
 

