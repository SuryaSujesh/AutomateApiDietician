package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import resources.Utils;
import static org.junit.Assert.assertEquals;
import java.io.IOException;


public class UserLogin extends Utils {
    
	 Response response; 
	 public static String token;
	 
	@When("User tried to login with post http method")
	public void user_tried_to_login_with_post_http_method() throws IOException {
		response=NegativeLogin.reqspec.when()
				.post(getValue("Login"))
				.then().statusCode(200)
				.extract().response();	 
	   }

	@Then("Access token is generated")
	public void access_token_is_generated() {
		  
	token = response.jsonPath().getString("token");
  
	}

	@Then("Receives status code {int}")
	public void receives_status_code(Integer int1) {
		
		String type = response.jsonPath().getString("type");
		assertEquals("Bearer ", type);
		assertEquals(response.getStatusCode(),200);
		
	    }
	
}
