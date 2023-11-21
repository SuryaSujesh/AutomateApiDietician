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

public class GetMorbidityNegative extends Utils {

	RequestSpecification req1;
	Response res1;

@Given("User performs get morbidity without access token")
public void user_performs_get_morbidity_without_access_token() throws IOException {
	req1= given().spec(requestSpec()); }
					 

@When("User tries GET http requests without access token")
public void user_tries_get_http_requests_without_access_token() throws IOException {
    
	 res1= req1.when()
		       .get(getValue("Morbidity")).then()
		       .statusCode(401).extract().response();
	            }


@Then("User will get {int} error code")
public void user_will_get_error_code(Integer int1) {
	assertEquals(res1.getStatusCode(),401);
}
	
}
