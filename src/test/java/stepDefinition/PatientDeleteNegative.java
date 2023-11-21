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

public class PatientDeleteNegative extends Utils {
	
	RequestSpecification req;
	Response res;
	String message;
	
	@Given("User tried to delete a patient account that does not exists")
	public void user_tried_to_delete_a_patient_account_that_does_not_exists() throws IOException {
		  req = given().spec(requestSpec())
				.header("Authorization", "Bearer "+UserLogin.token);
			
	}

	@When("User tried Delete http requests")
	public void user_tried_delete_http_requests() throws IOException {
		res = req.when()
			       .pathParam("patientId", CreateandUpdatePatient.id)	
			       .delete(getValue("PatientDelete")).then()
			       .statusCode(404)	      
			       .extract().response();		  
		  }

	@Then("User will get a error code with status {int}")
	public void user_will_get_a_error_code_with_status(Integer int1) {
		message = res.jsonPath().getString("errorCode");
		assertEquals("NOT_FOUND", message);
		assertEquals(res.getStatusCode(),404);	
	}

} 
