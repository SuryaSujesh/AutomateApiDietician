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

public class GetMorbidity extends Utils {
	
    RequestSpecification req1;
	Response res1,resp;
	String morbidityName;
	

@Given("User is logged in with valid credentials")
public void user_is_logged_in_with_valid_credentials() throws IOException {
	
	req1= given().spec(requestSpec())
		 .header("Authorization", "Bearer "+UserLogin.token);		 
          }

@When("User performs GET http requests")
public void user_performs_get_http_requests() throws IOException {
	
	    res1= req1.when()
	       .get(getValue("Morbidity")).then()
	       .statusCode(200).extract().response();
            }


@Then("User get morbidity details with 200OK")
public void user_get_morbidity_details_with_200ok() {
	
	assertEquals(res1.getStatusCode(),200);	
	morbidityName= res1.jsonPath().getString("morbidityTestName");
    }
@When("User performs GET http requests with morbidityname")
public void user_performs_get_http_requests_with_morbidityname() {
	     resp= req1.when()
              .get("/morbidity/TSH")
              .then().statusCode(200).extract().response();
     }

@Then("User get morbidity condition with 200OK")
public void user_get_morbidity_condition_with_200ok() {

	assertEquals(resp.getStatusCode(),200);
     }

}


