package stepDefinition;
import pojo.AddPlace;
import pojo.Location;
import pojo.MainFile;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class stepDefinition {
	String str="";
	Response res;
	@Given("Add Place Payload")
	public void add_Place_Payload() throws Throwable {
		AddPlace p =new AddPlace();
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("+91 9838933937");
		p.setAddress("29, side layout, cohen 09");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		p.setWebsite("https://rahulshettyacademy.com");
		p.setLanguage("French-IN");

		ObjectMapper obj=new ObjectMapper();
		 str=obj.writeValueAsString(p);
		System.out.println(str);
		

	    throw new PendingException();
	}

	@When("User calls AddAPI with post http request")
	public void user_calls_AddAPI_with_post_http_request() throws Throwable {

		RestAssured.baseURI="https://rahulshettyacademy.com";
		 res=given().queryParam("key", "qaclick123")
		.contentType("application/json")
		.body(str)
		.when()
		.post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		//String responseString=res.asString();
		//System.out.println(responseString);

	    throw new PendingException();
	}

	@Then("The API call get success with status code {int}")
	public void the_API_call_get_success_with_status_code(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(res.getStatusCode(),200);
	    throw new PendingException();
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String resp=res.asString();
		JsonPath js=new JsonPath(resp);
		assertEquals(js.get(arg1).toString(),arg2);
	    throw new PendingException();
	}
}
