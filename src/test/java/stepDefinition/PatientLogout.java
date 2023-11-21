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

public class PatientLogout extends Utils {
	
	RequestSpecification req1;
	Response res;
	

@Given("Patient tries to logout with endpoint")
public void patient_tries_to_logout_with_endpoint() throws IOException {
	req1= given().spec(requestSpec())
			 .header("Authorization", "Bearer "+ PatientLogin.pattoken);		 
	          }
	


@When("Patient performs GET method to logout")
	public void patient_performs_get_method_to_logout() throws IOException {
	   
	               res= req1.when()
			       .get(getValue("Logout")).then()
			       .statusCode(200).extract().response();
		            }
	

	@Then("Patient successfully logout with success meassage and status code {int}")
	public void patient_successfully_logout_with_success_meassage_and_status_code(Integer int1) {
		assertEquals(res.getStatusCode(),200);
	}


}
