package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);

    }
    @Test(priority = 1)
    public void loginWithValidCredentials(){
        String validUsername = excelReader.getStringData("Sheet1",1,0);
        String validPass = excelReader.getStringData("Sheet1",1,1);
        loginPage.enterValidUsername(validUsername);
        loginPage.enterValidPass(validPass);
        loginPage.clickLoginButton();
    }

    @Test(priority = 2)
    public void loginWithInvalidPassword(){

    }




    @AfterMethod
    public void tearDown(){
       //driver.quit();
    }

}
