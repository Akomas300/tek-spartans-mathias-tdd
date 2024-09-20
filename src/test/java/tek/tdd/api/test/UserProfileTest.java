package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;
public class UserProfileTest extends ApiTestsBase {
    @Test(dataProvider = "profileData")
    public void userProfileTest(String string1,String string2){
       String token1 = tokenGenerator(string1,string2);
        Response response1 = getDefaultRequest()
                .header("Authorization", "Bearer " + token1)
                .when()
                .get(EndPoints.USER_PROFILE.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();
        ExtentTestManager.getTest().info(response1.asPrettyString());
        String username=response1.jsonPath().getString("username");
        Assert.assertEquals(username,string1);
    }
    @DataProvider(name = "profileData")
    private String[][] profileData(){
        return new String[][]{
                {"SUPERVISOR","tek_supervisor"},
                {"operator_readonly","Tek4u2024"}
        };
    }
}
