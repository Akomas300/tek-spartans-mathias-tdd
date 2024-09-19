package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.TokenResponse;
import tek.tdd.base.ApiTestsBase;

public class UserProfileTest extends ApiTestsBase {
    @Test(dataProvider = "profileData")
    public void userProfileTest(String string1,String string2){
        TokenRequest tokenRequest = new TokenRequest(string1, string2);
        Response response = getDefaultRequest()
                .body(tokenRequest)
                .when().post(EndPoints.TOKEN.getValue())
                .then().statusCode(200)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());

        String token = response.jsonPath().getString("token");
        Assert.assertNotNull(token);

        Response response1 = getDefaultRequest()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.USER_PROFILE.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();
        ExtentTestManager.getTest().info(response1.asPrettyString());

    }
    @DataProvider(name = "profileData")
    private String[][] profileData(){
        return new String[][]{
                {"supervisor","tek_supervisor"},
                {"operator_readonly","Tek4u2024"}
        };
    }
}
