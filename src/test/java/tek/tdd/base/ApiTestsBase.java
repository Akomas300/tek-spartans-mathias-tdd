package tek.tdd.base;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;

@Listeners({ExtentITestListenerClassAdapter.class})
public class ApiTestsBase extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(ApiTestsBase.class);

    public RequestSpecification getDefaultRequest() {
        LOGGER.info("Sending API call to {}", RestAssured.baseURI);
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public String tokenGenerator(String username, String password) {
        TokenRequest tokenRequest = new TokenRequest(username, password);
        return getDefaultRequest()
                .body(tokenRequest)
                .when()
                .post(EndPoints.TOKEN.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath().getString("token");
    }
}
