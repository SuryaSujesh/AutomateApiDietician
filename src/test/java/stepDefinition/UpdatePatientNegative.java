package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

public class UpdatePatientNegative extends Utils{

	RequestSpecification reqs,req1;
	Response res,res2;
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
				.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"));						 
	          }
	

	@When("User tries to update an existing patient with PUT requests")
	public void user_tries_to_update_an_existing_patient_with_put_requests() throws IOException {
		res = reqs.when()			
			       .put(getValue("UpdatePatient")).then()
			       .statusCode(400)		   
			       .extract().response();	 
	}

	@Then("User will receive an error message with {int}")
	public void user_will_receive_an_error_message_with(Integer int1) {
		
		message = res.jsonPath().getString("errorMessage");
		assertEquals("Patient already exists with given DateOfBirth and ContactNumber", message);
		assertEquals(res.getStatusCode(),400);
				
	}

	@Given("User tries to update patient without token")
	public void user_tries_to_update_patient_without_token() throws IOException {
		req1 = given().spec(requestSpec())
				.header("Content-Type","multipart/form-data")
				.pathParam("patientId",CreateandUpdatePatient.id)
				.formParam("patientInfo", "{\n"
						+ "\n"
						+ "\"FirstName\": \"Jin\",\n"
						+ "\"LastName\": \"Nigam\",\n"
						+ "\"ContactNumber\": \"9138512368\",\n"
						+ "\"Email\": \"gam@gmail.com\",\n"
						+ "\"Allergy\": \"Peanuts\",\n"
						+ "\"FoodCategory\": \"Vegan\",\n"
						+ "\"DateOfBirth\": \"1989-06-08\"\n"
						+ "\n"
						+ "}")
				.multiPart("file", new File("src/test/java/resources/HyperThyroid_Report_final.pdf"))
				;

	}

	@When("User tries to update patient without access token")
	public void user_tries_to_update_patient_without_access_token() throws IOException {
		res2 = req1.when()			
			       .put(getValue("UpdatePatient")).then()
			       .statusCode(401)		   
			       .extract().response();
	}


@Then("User should receive unauthorized meassage with {int}")
public void user_should_receive_unauthorized_meassage_with(Integer int1) {
	assertEquals(res2.getStatusCode(),401);
}

	
} 
