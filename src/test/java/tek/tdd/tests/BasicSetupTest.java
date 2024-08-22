package tek.tdd.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.BaseSetup;
import tek.tdd.base.UIBaseClass;
import tek.tdd.pages.HomePage;
import tek.tdd.utility.SeleniumUtility;

public class BasicSetupTest extends UIBaseClass {
   // HomePage homePage=new HomePage();
    private static final Logger LOGGER = LogManager.getLogger(BasicSetupTest.class);
    @Test
    public void validateTopLeftCornerLogo(){

        String actualLogoText=getElementText(By.className("top-nav__logo"));
        Assert.assertEquals(actualLogoText,"TEKSCHOOL");
    }
    @Test
    public void validateSignInButtonIsEnable(){

        Assert.assertTrue( isElementEnable(By.id("signinLink")));
    }
}
