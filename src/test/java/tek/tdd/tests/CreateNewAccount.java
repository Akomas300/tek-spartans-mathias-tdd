package tek.tdd.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.utility.RandomEmail;


public class CreateNewAccount extends UIBaseClass {
    @Test
    public void createNewAccount() {
        String randomEmail = RandomEmail.randomEmail("mathias");
        clickOnElement((homePage.signInLink));
        clickOnElement(signInPage.createNewAccount);
        sendText(signUpPage.nameInput,"Nasiba");
        sendText(signUpPage.emailInput,randomEmail);
        sendText(signUpPage.passwordInput,"Nasiba2345!!!");
        sendText(signUpPage.confirmPasswordInput,"Nasiba2345!!!");
        clickOnElement(signUpPage.signUpBtn);

      String expectedResult =   getElementText(By.className("account__information-email"));
        Assert.assertEquals(randomEmail, expectedResult,"emails should match");
    }
    @Test
    public void createNewAccountWithExistingEmail() {
        clickOnElement((homePage.signInLink));
        clickOnElement(signInPage.createNewAccount);
        sendText(signUpPage.nameInput,"Nasiba");
        sendText(signUpPage.emailInput,"Nasiba22@gmail.com");
        sendText(signUpPage.passwordInput,"Nasiba2345!!!");
        sendText(signUpPage.confirmPasswordInput,"Nasiba2345!!!");
        clickOnElement(signUpPage.signUpBtn);
        String text=getElementText(By.className("error"));
        Assert.assertEquals(text,"this email is already exist, please use another email address");
    }
}
