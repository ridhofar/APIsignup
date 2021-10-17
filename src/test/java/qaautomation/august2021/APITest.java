package qaautomation.august2021;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import qaautomation.august2021.apis.APIEndpoints;
import qaautomation.august2021.utils.APIUtility;
import qaautomation.august2021.utils.DataUtility;

/**
 * Unit test for simple App.
 */
public class APITest extends BaseAPITest {
	/**
	 * Rigorous Test :-)
	 */

	@Test(priority = 2)
	public void userAPI() {
		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.user);
		APIUtility.verifyStatusCode(response);
		assertEquals(APIUtility.verifyStatusCode(response), true);
	}

	@Test(priority = 3)
	public void dashboardAPI() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
	}

	@Test
	public void configAPI() {
		Response response = given().spec(commonJsonSpec).when().get(APIEndpoints.config);
		APIUtility.verifyStatusCode(response);
	}

	
	
	@Test
	public void configAPI2() {
		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
		Response response = RestAssured.given().contentType("application/json")
				.header("authtoken", "Z6HGAvgxDRnmXS_ZCevH" + "").when().get(APIEndpoints.config);
		assertEquals(response.statusCode(), 200);
	}
	
	

	@Test
	public void incorrectLoginAPI() {
		String loginFailedPayload = DataUtility.getDataFromExcel("Payloads", "IncorrectLoginPayload")
				.replace("$.username", "a@gmail.com").replace("$.password", "12344");
		Response response = given().spec(commonJsonSpec).body(loginFailedPayload).when().post(APIEndpoints.login);
		assertNotEquals(response.getStatusCode(), 200);
		assertEquals(response.getStatusCode(), 422);
	}
	@Test
	public void fakeEmail() {
		Faker faker = new Faker();
		String adlan = faker.name().username();
		System.out.println(adlan + "@gmail.com");
	}

	@Test
	public void signupAPI() {
		Faker faker = new Faker();
		String adlan = faker.name().username();
		String loginFailedPayload = DataUtility.getDataFromExcel("Payloads", "signupPayload")
				.replace("$.first_name", "adlan").replace("$.last_name", "").replace("$.email", adlan +  "a@gmail.com")
				.replace("$.password", "1234567890").replace("$.phone_number","+62-343434334")
				.replace("$.user_type", "user").replace("$.currency_id", "3");
		Response response = given().spec(commonJsonSpec).body(loginFailedPayload).when().post(APIEndpoints.signup);
		assertNotEquals(response.getStatusCode(), 200);
		assertEquals(response.getStatusCode(), 422);
	}

	@Test
	public void dashboardAPIWithSchema() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));
	}

}
