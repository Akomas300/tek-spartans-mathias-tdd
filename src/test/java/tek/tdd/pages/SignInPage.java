package tek.tdd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

public class SignInPage extends SeleniumUtility {
    public SignInPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(name = "email")
    public static WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(id = "loginBtn")
    public WebElement loginButton;


    @FindBy(id ="newAccountBtn" )
    public WebElement createNewAccount;
}
