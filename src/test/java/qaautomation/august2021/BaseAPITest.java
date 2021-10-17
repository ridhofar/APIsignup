package qaautomation.august2021;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import qaautomation.august2021.apis.APIEndpoints;
import qaautomation.august2021.apis.JsonPath;
import qaautomation.august2021.utils.APIUtility;
import qaautomation.august2021.utils.DataUtility;

public abstract class BaseAPITest {
	RequestSpecification commonJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
			.setContentType(ContentType.JSON).build().log().all();
	RequestSpecification loginJsonSpec;

	@BeforeSuite
	public void testLoginAPI() {
		String payload = DataUtility.getDataFromExcel("Payloads", "LoginPayload");
		Response response = given().spec(commonJsonSpec).body(payload).when().post(APIEndpoints.login);
		assertEquals(response.statusCode(), 200);

		String token = APIUtility.getBodyDataUsingJsonPath(response, JsonPath.authToken);
		loginJsonSpec = new RequestSpecBuilder().setBaseUri("https://api-staging-builder.engineer.ai")
				.setContentType(ContentType.JSON).addHeader("authtoken", token).build().log().all();
	}
}
