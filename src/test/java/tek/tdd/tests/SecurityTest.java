package tek.tdd.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.pages.SignInPage;
import tek.tdd.utility.SeleniumUtility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecurityTest extends UIBaseClass {
    @Test(dataProvider = "testData")
    public void validateUserSuccessfullySignIn(String email,String password){
        clickOnElement((homePage.signInLink));
        sendText(SignInPage.emailInput,email);
        sendText(By.id("password"),password);
        clickOnElement(By.id("loginBtn"));
        Assert.assertTrue(waitForVisibility(By.id("logoutBtn")).isDisplayed());
    }
    @Test(dataProvider = "testData")
    public void negativeTest(String email,String password){
        clickOnElement((homePage.signInLink));
        sendText(SignInPage.emailInput,email);
        sendText(By.id("password"),password);
        clickOnElement(By.id("loginBtn"));
       Assert.assertTrue(waitForVisibility(By.className("error")).isDisplayed());
    }
    @DataProvider(name = "testData")
    private String[][] signInData(Method testMethod){

        if (testMethod.getName().equals("validateUserSuccessfullySignIn")){
            return new String[][]{
                    {"akowa300@gmail.com","Mathias123!"}
            };
        }else if (testMethod.getName().equals("negativeTest")){
            return new String[][]{
                      {"akowa300@gmail.com","Mathias123"},
                     {"akowa300@gmail.com","Mathias12"}
                    };
        }
        return null;
    }
    public void validateAllErrorsMessages(){
    }


}
