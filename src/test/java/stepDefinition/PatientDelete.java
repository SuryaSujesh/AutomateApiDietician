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

public class PatientDelete extends Utils {
	
	RequestSpecification req;
	Response res;
	

@Given("User tried to delete a patient account")
public void user_tried_to_delete_a_patient_account() throws IOException {
    
	       req = given().spec(requestSpec())
			.header("Authorization", "Bearer "+UserLogin.token);
	
}
@When("User performed http method to delete a patient")
public void user_performed_http_method_to_delete_a_patient() throws IOException {
   
	res = req.when()
		       .pathParam("patientId", CreateandUpdatePatient.id)	
		       .delete(getValue("PatientDelete")).then()
		       .header("Content-Type", "text/plain;charset=UTF-8")
		       .statusCode(200)	      
		       .extract().response();
	            }


@Then("User will get a success message with status code {int}")
public void user_will_get_a_success_message_with_status_code(Integer int1) {
  
	 assertEquals(res.getStatusCode(),200);
	 
                }
	} 
