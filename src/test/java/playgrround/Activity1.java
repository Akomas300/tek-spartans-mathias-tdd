package playgrround;

import dev.failsafe.internal.util.Durations;
import net.bytebuddy.implementation.bytecode.Duplication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity1 {
    private WebDriver driver;
    @BeforeMethod
    public void navigateToFacebook(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");

    }
    @Test
    public void testFacebookTitle(){
       String title = driver.getTitle();
        Assert.assertEquals(title,"Facebook - log in or sign up");
       // System.out.println(title);
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
    @Test(dataProvider ="fullName")
    public void testFullName(String firstName, String lastName,String expectResult){
        String result=getFullName(firstName,lastName);
        expectResult=firstName+", "+lastName;
    }
    public String getFullName(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new RuntimeException("FirstName or Last Name can NOT be null");

        if (firstName.isEmpty() || lastName.isEmpty())
            throw new RuntimeException("First Name or Last Name can NOT be Empty");

        lastName = lastName.trim();
        firstName = firstName.trim();

        return lastName.toUpperCase() + ", " +
                firstName.substring(0, 1).toUpperCase() +
                firstName.substring(1).toLowerCase();
    }
    @DataProvider(name = "fullName")
    private String[][] testFullName(){
        String[][] data={{"nu","mathias","NU, Mathias"},{"mahdi","ade","MAHDI, Ade"}};
        return data;
    }
}
