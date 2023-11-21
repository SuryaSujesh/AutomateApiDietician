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

public class Getpatients extends Utils {

    RequestSpecification req;
    Response res;
    public static String patid;

@Given("User is logged in and tries to get all patient details")
public void user_is_logged_in_and_tries_to_get_all_patient_details() throws IOException {
    
	  req =given().spec(requestSpec())
	 .header("Authorization", "Bearer "+ UserLogin.token);		 
     }
	
	
@When("User performs GET http requests for getting patient details")
public void user_performs_get_http_requests_for_getting_patient_details() throws IOException {
   
	res= req.when()
		    .get(getValue("Patient")).then()
		    .statusCode(200).extract().response();     
           patid= res.jsonPath().getString("patientId");
          
          }

@Then("User will receive all the patient details with status code {int}")
public void user_will_receive_all_the_patient_details_with_status_code(Integer int1) {
	assertEquals(res.getStatusCode(),200);
}	
}
