package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountType;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.TokenResponse;
import tek.tdd.base.ApiTestsBase;

import java.util.HashMap;
import java.util.Map;

public class TokenGenerationTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(TokenGenerationTest.class);

    //Create a test validate token generated with supervisor User
    @Test
    public void generateValidToken() {
        RequestSpecification requestSpecification = getDefaultRequest();
        Map<String, String> body = new HashMap<>();
        body.put("username", "supervisor");
        body.put("password", "tek_supervisor");

        requestSpecification.body(body);

        //Send request to /api/token
        Response response = requestSpecification.when().post("/api/token");
        response.then().statusCode(200);

        LOGGER.info("Response is {}", response.asPrettyString());
    }

    @Test(dataProvider = "testData")
    public void generateInvalidToken(String string1,String string2,String string3,int code) {
        RequestSpecification requestSpecification = getDefaultRequest();
        Map<String, String> body = new HashMap<>();
        body.put("username", string1);
        body.put("password", string2);

        requestSpecification.body(body);

        //Send request to /api/token
        Response response = requestSpecification.when().post("/api/token");
       // response.then().statusCode(Integer.parseInt(string4));
        response.then().statusCode(code);
        String error = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(error,string3);

        LOGGER.info("Response is {}", response.asPrettyString());
    }
    @DataProvider(name = "testData")
    private Object[][] invalidUsernameOrPassword(){
        return new Object[][]{
                {"supervisor","tek_supervisorl","Password not matched",400},
                {"supervisor2","tek_supervisor","User supervisor2 not found",404}
        };
    }
    @Test
    public void generateTokenUseObjectAsBody() {
        RequestSpecification request = getDefaultRequest();

        TokenRequest requestBody = new TokenRequest("supervisor", "tek_supervisor");

        request.body(requestBody);

        Response response = request.when().post(EndPoints.TOKEN.getValue());

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void convertResponseToPOJO() {
        TokenRequest tokenRequest = new TokenRequest("supervisor", "tek_supervisor");
        Response response = getDefaultRequest()
                .body(tokenRequest)
                .when().post(EndPoints.TOKEN.getValue())
                .then().statusCode(200)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());

        //Convert Response body to POJO
        TokenResponse token = response.jsonPath().getObject("", TokenResponse.class);

        Assert.assertEquals(token.getUsername(), "supervisor");
        Assert.assertNotNull(token.getToken());
        Assert.assertEquals(token.getAccountType(), AccountType.CSR);
    }
}