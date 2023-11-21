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
import utilities.Testdata;
  

public class NegativeLogin extends Utils {
	
    public static RequestSpecification reqspec;
	Response response;
	
	@Given("Login API endpoint is given with {string} and {string}")
	public void login_api_endpoint_is_given_with_and(String testcaseName, String Sheetname) throws IOException {
		
		reqspec = given().spec(requestSpec())  
                 .headers("Content-Type","application/json")
                 .headers("Accept","application/json")
                 .body(Testdata.UserloginPayload(testcaseName, Sheetname));
	}
	
	
	@When("Tries to login")
	public void tries_to_login() throws IOException {
		 response=reqspec.when()
				.post(getValue("Login"))
				.then().statusCode(401)
				.extract().response();	}

	@Then("Invalid login and status code {int} is created")
	public void invalid_login_and_status_code_is_created(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

}
