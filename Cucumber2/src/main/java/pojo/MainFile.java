package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainFile {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub


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
String str=obj.writeValueAsString(p);
System.out.println(str);
RestAssured.baseURI="https://rahulshettyacademy.com";
Response res=given().queryParam("key", "qaclick123")
.contentType("application/json")
.body(str)
.when()
.post("/maps/api/place/add/json")
.then().log().all().assertThat().statusCode(200).extract().response();

String responseString=res.asString();
System.out.println(responseString);
	}

}
