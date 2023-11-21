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

public class UpdatePatientNegative extends Utils{

	RequestSpecification reqs;
	Response res;
	String message;
	
	@Given("User tries to update patient with same phone number and DateOfBirth")
	public void user_tries_to_update_patient_with_same_phone_number_and_date_of_birth() throws IOException {
		reqs = given().spec(requestSpec())
				.header("Authorization", "Bearer "+UserLogin.token)
				.header("Content-Type","multipart/form-data")
				.pathParam("patientId",CreateandUpdatePatient.id)
				.formParam("patientInfo", "{\n"
						+ "\n"
						+ "\"FirstName\": \"Uma\",\n"
						+ "\"LastName\": \"Nandigam\",\n"
						+ "\"ContactNumber\": \"9138549968\",\n"
						+ "\"Email\": \"umanandigam@gmail.com\",\n"
						+ "\"Allergy\": \"Peanuts\",\n"
						+ "\"FoodCategory\": \"Vegan\",\n"
						+ "\"DateOfBirth\": \"1971-06-08\"\n"
						+ "\n"
						+ "}")
				.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"))
				.log().all();			 
	          }
	

	@When("User tries to update an existing patient with PUT requests")
	public void user_tries_to_update_an_existing_patient_with_put_requests() throws IOException {
		res = reqs.when()			
			       .put(getValue("UpdatePatient")).then()
			       .statusCode(400).log().all()	      
			       .extract().response();	 
	}

	@Then("User will receive an error message with {int}")
	public void user_will_receive_an_error_message_with(Integer int1) {
		
		message = res.jsonPath().getString("errorMessage");
		assertEquals("Patient already exists with given DateOfBirth and ContactNumber", message);
		assertEquals(res.getStatusCode(),400);
	}
	
} 
