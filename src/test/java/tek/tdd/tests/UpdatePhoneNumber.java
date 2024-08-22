package tek.tdd.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tek.tdd.base.UIBaseClass;
import tek.tdd.pages.SignInPage;

public class UpdatePhoneNumber extends UIBaseClass {
    SoftAssert softAssert=new SoftAssert();
    @Test
    public void updatePhoneNumber(){
        clickOnElement(By.id("signinLink"));
        sendText(signInPage.emailInput,"Nasiba22@gmail.com");
        sendText(signInPage.passwordInput,"Nasiba2345!!!");
        clickOnElement(signInPage.loginButton);
        clickOnElement(By.id("accountLink"));
        waitForVisibility(By.id("nameInput")).clear();
        sendText(By.id("nameInput"),"Matiulla");
        waitForVisibility(By.id("personalPhoneInput")).clear();
        sendText(By.id("personalPhoneInput"),"5714738612");
        clickOnElement(By.id("personalUpdateBtn"));
        softAssert.assertTrue(waitForVisibility(By.xpath("//div[text()='Personal Information Updated Successfully']")).isDisplayed());

        waitForVisibility(By.id("nameInput")).clear();
        sendText(By.id("nameInput"),"Matiulla");
        waitForVisibility(By.id("personalPhoneInput")).clear();
        sendText(By.id("personalPhoneInput"),"5714738611");
        clickOnElement(By.id("personalUpdateBtn"));
        softAssert.assertAll();


//Solft//
    }
}
