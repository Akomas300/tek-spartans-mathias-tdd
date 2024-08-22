package playgrround;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyFirstTest {
    @Test
    public void myFirstTestMethod(){
        System.out.println("Hello Word");
    }
    @BeforeMethod
    public void myBeforeTestMethod(){
        System.out.println("This is before each method");
    }
    @AfterMethod
    public void myAfterTestMethod(){
        System.out.println("This is my after each method");
    }
    @Test
    public  void mySecondTestMethod(){
        System.out.println("This is my second test method");
    }
}
