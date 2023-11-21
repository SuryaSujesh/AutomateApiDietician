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

public class Logout extends Utils{

	RequestSpecification req1;
	Response res;

@Given("User is logged in with username and password")
public void user_is_logged_in_with_username_and_password() throws IOException {
   
	req1= given().spec(requestSpec())
			 .header("Authorization", "Bearer "+UserLogin.token);		 
	          }


@When("User performs GET method to logout")
public void user_performs_get_method_to_logout() throws IOException {
	          res= req1.when()
		       .get(getValue("Logout")).then()
		       .statusCode(200).extract().response();
	            }
	


@Then("User successfully logout with status code {int}")
public void user_successfully_logout_with_status_code(Integer int1) {
    
	assertEquals(res.getStatusCode(),200);
	
}
	
} 
