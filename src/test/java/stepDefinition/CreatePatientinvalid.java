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
import resources.Utils;

public class CreatePatientinvalid extends Utils {
	 RequestSpecification req1;
	 Response res;

@Given("User performs post without form data")
public void user_performs_post_without_form_data() throws IOException {
	
	req1=  given().spec(requestSpec())
			.header("Authorization", "Bearer "+UserLogin.token)
			.header("Content-Type","multipart/form-data");			
          }

@When("User tries Post requests without form data")
public void user_tries_post_requests_without_form_data() throws IOException {
	res = req1.when()			
		       .post(getValue("Patient")).then()
		       .statusCode(500).log().all()	      
		       .extract().response();	    
         }

@Then("Will receive an error code {int}")
public void will_receive_an_error_code(Integer int1) {
	
	      assertEquals(res.getStatusCode(),500);
         }

}
