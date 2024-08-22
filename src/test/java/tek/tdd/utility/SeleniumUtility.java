package tek.tdd.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.tdd.base.BaseSetup;

import java.time.Duration;

public class SeleniumUtility extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtility.class);

    private WebDriverWait getWait()  {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_IN_SECOND));

    }
    public String getElementText(By locator){
        LOGGER.debug("returning element {}",locator);
       return waitForVisibility(locator).getText();
    }
    public boolean isElementEnable(By locator){
       return  waitForVisibility(locator)
                .isEnabled();
    }
    public WebElement waitForVisibility(By locator){
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void clickOnElement(By locator){
        LOGGER.debug("Clicking on {}",locator);
        waitForVisibility(locator).click();
    }
    public void clickOnElement(WebElement element) {
        LOGGER.debug("Clicking on {}",element);
        getWait().until(ExpectedConditions.visibilityOf(element)).click();
    }
    public String getElementText(WebElement element){
        LOGGER.debug("returning element {}",element);
        return getWait().until(ExpectedConditions.visibilityOf(element)).getText();
    }
    public boolean isElementEnable(WebElement element){
        return  getWait().until(ExpectedConditions.visibilityOf(element))
                .isEnabled();
    }
    public void sendText(By locator,String text){
        waitForVisibility(locator).clear();
        waitForVisibility(locator).sendKeys(text);
    }
    public void sendText(WebElement element,String text){
        getWait().until(ExpectedConditions.visibilityOf(element)).clear();
        getWait ().until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

}
