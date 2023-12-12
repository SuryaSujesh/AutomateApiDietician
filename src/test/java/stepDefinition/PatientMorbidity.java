package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

public class PatientMorbidity extends Utils{
	
	RequestSpecification req1;
	Response res1;
	

@Given("Patient is logged in with username and password")
public void patient_is_logged_in_with_username_and_password() throws IOException {
	
	req1= given().spec(requestSpec())
			 .header("Authorization", "Bearer " + PatientLogin.pattoken)           
			 .pathParam("patientId",CreateandUpdatePatient.id);	
}

@When("Patient performs GET http requests to get the morbidity details")
public void patient_performs_get_http_requests_to_get_the_morbidity_details() throws IOException {

	      res1= req1.when()
		       .get(getValue("PatientMorbidity"))
		       .then()
		       .statusCode(200).extract().response();
	            }

@Then("Patient receives morbidity details with 200OK status code")
public void patient_receives_morbidity_details_with_200ok_status_code() {
	
	  assertEquals(res1.getStatusCode(),200);
	
}
} 
