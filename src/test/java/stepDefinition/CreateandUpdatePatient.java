package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Pojo;
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
			.formParam("patientInfo", Pojo.patientInfo)
			.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"));
					 
          }
	
	
@When("User performs POST http requests to create a patient")
public void user_performs_post_http_requests_to_create_a_patient() throws IOException {
   
	    res = req1.when()			
		       .post(getValue("Patient")).then()
		       .statusCode(201)	      
		       .extract().response();
	     id= res.jsonPath().getInt("patientId");
	           
          }

@Then("User receives status code {int}")
public void user_receives_status_code(Integer int2) {
	
	assertEquals(res.getStatusCode(),200);	
    }

@Given("User tries to update patient details for allergy and phone number")
public void user_tries_to_update_patient_details_for_allergy_and_phone_number() throws IOException {
    
	reques = given().spec(requestSpec())
			.header("Authorization", "Bearer "+UserLogin.token)
			.header("Content-Type","multipart/form-data")
			.pathParam("patientId",id)
			.formParam("patientInfo", Pojo.updateInfo)
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
