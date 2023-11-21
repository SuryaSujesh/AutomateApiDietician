package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;
import utilities.Testdata;

public class CreateandUpdatePatient extends Utils {
	
	Testdata d= new Testdata();	
    RequestSpecification req1,reques;
	Response res,response;
	public static int id; 
	
@Given("User enters patient details")
public void user_enters_patient_details() throws IOException {
	
	req1=  given().spec(requestSpec())
			.header("Authorization", "Bearer "+UserLogin.token)
			.header("Content-Type","multipart/form-data")
			.formParam("patientInfo", "{\n"
					+ "\n"
					+ "\"FirstName\": \"Fred\",\n"
					+ "\"LastName\": \"Lip\",\n"
					+ "\"ContactNumber\": \"9978654321\",\n"
					+ "\"Email\": \"fredlip@gmail.com\",\n"
					+ "\"Allergy\": \"Cashew\",\n"
					+ "\"FoodCategory\": \"Vegan\",\n"
					+ "\"DateOfBirth\": \"1971-06-08\"\n"
					+ "\n"
					+ "}")
			.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"));
					 
          }
	
	
@When("User performs POST http requests to create a patient")
public void user_performs_post_http_requests_to_create_a_patient() throws IOException {
   
	    res = req1.when()			
		       .post(getValue("Patient")).then()
		       .statusCode(201).log().all()	      
		       .extract().response();
	     id= res.jsonPath().getInt("patientId");
	           
          }

@Then("User receives status code {int}")
public void user_receives_status_code(Integer int2) {
	
	assertEquals(res.getStatusCode(),201);
	
    }

@Given("User tries to update patient details for allergy and phone number")
public void user_tries_to_update_patient_details_for_allergy_and_phone_number() throws IOException {
    
	reques = given().spec(requestSpec())
			.header("Authorization", "Bearer "+UserLogin.token)
			.header("Content-Type","multipart/form-data")
			.pathParam("patientId",id)
			.formParam("patientInfo", "{\n"
					+ "\n"
					+ "\"FirstName\": \"Nick\",\n"
					+ "\"LastName\": \"Mary\",\n"
					+ "\"ContactNumber\": \"9138239658\",\n"
					+ "\"Email\": \"fredlip@gmail.com\",\n"
					+ "\"Allergy\": \"Peanuts\",\n"
					+ "\"FoodCategory\": \"Vegan\",\n"
					+ "\"DateOfBirth\": \"1970-04-08\"\n"
					+ "\n"
					+ "}")
			.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"))
			.log().all();		 
          }


@When("User performs PUT http requests to update a patient")
public void user_performs_put_http_requests_to_update_a_patient() throws IOException {
    
	response = reques.when()			
		       .put(getValue("UpdatePatient")).then()
		       .statusCode(200).log().all()	      
		       .extract().response();    
	            }

@Then("User receives status code with updated message and status code {int}")
public void user_receives_status_code_with_updated_message_and_status_code(Integer int1) {
    
	assertEquals(response.getStatusCode(),200);
}
}
