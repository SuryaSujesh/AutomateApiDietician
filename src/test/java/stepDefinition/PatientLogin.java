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

public class PatientLogin extends Utils{
	
	RequestSpecification req1,reqsp;
	Response response,resp;
	public static String pattoken;

@Given("Patient login endoint is given with {string} and {string}")
public void patient_login_endoint_is_given_with_and(String testcaseName, String Sheetname) throws IOException {
    
	req1 =  given().spec(requestSpec())  
            .headers("Content-Type","application/json")
            .headers("Accept","application/json")
            .body(Testdata.Invalidloginpatient(testcaseName, Sheetname));

}

@When("Patient tries to login with wrong credential")
public void patient_tries_to_login_with_wrong_credential() throws IOException {
	response=req1.when()
			.post(getValue("Login"))
			.then().statusCode(401)
			.extract().response();	
}

@Then("Status code {int} is created is created an error message")
public void status_code_is_created_is_created_an_error_message(Integer int1) {
   
	assertEquals(response.getStatusCode(),401);
}

@Given("Patient login endpoint is given with {string} and {string}")
public void patient_login_endpoint_is_given_with_and(String testcaseName, String Sheetname) throws IOException {
	reqsp = given().spec(requestSpec())  
            .headers("Content-Type","application/json")
            .headers("Accept","application/json")
            .body(Testdata.Validloginpatient(testcaseName, Sheetname));

}

@When("Patient tries to login with Post http request")
public void patient_tries_to_login_with_post_http_request() throws IOException {
   
	
	resp   = reqsp.when()
			.post(getValue("Login"))
			.then().statusCode(200)
			.extract().response();		
}

@Then("An access token is created for the patient")
public void an_access_token_is_created_for_the_patient() {

	pattoken = resp.jsonPath().getString("token");
	
}

@Then("Patient receives status code {int}")
public void patient_receives_status_code(Integer int1) {
   
	assertEquals(resp.getStatusCode(),200);	
	
}



	

}
