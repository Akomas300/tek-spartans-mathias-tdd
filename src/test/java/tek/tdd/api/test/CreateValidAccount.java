package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.RandomEmail;
import tek.tdd.base.ApiTestsBase;

import java.util.HashMap;
import java.util.Map;

public class CreateValidAccount extends ApiTestsBase {
    @Test
    public void createAValidAccount() {
        RequestSpecification requestSpecification = getDefaultRequest();
        requestSpecification.body(new HashMap<>() {{
            put("email", RandomEmail.randomEmail());
            put("firstName", "sommi");
            put("lastName", "Dadji");
            put("title", "Mr.");
            put("gender", "Male");
            put("maritalStatus", "Single");
            put("employmentStatus", "Tester");
            put("dateOfBirth", "1823-11-23");
        }});

        Response response = requestSpecification.when().post(EndPoints.CREATE_PRIMARY_ACCOUNT.getValue());
        response.then().statusCode(201);
    }
    @Test
    public void createValidAccount(){
        Response response = getDefaultRequest()
                .body(new HashMap<>() {{
                    put("email", RandomEmail.randomEmail());
                    put("firstName", "sommi");
                    put("lastName", "Dadji");
                    put("title", "Mr.");
                    put("gender", "Male");
                    put("maritalStatus", "Single");
                    put("employmentStatus", "Tester");
                    put("dateOfBirth", "1823-11-23");
                }})
                .when()
                .post(EndPoints.CREATE_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(201)
                .extract()
                .response();
        ExtentTestManager.getTest().info(response.asPrettyString());
    }
}
