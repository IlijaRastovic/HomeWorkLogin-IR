package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginPage;
import Pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class LoginTest extends BaseTest {


  /*  Domaci
    Na sajtu: https://the-internet.herokuapp.com/login testirati login pomocu podataka iz excel tabele.
    Testirati:
    1. valid user sa valid password\\\
    2. kombinacije valid username sa svim nevalidnim password-ima
    3. valid password sa svim nevalidnim user-ima.\\\\
    */

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

    }
    @Test(priority = 1)
    public void loginWithValidCredentials(){
        String validUsername = excelReader.getStringData("Sheet1",1,0);
        String validPass = excelReader.getStringData("Sheet1",1,1);
        loginPage.enterValidUsername(validUsername);
        loginPage.enterValidPass(validPass);
        loginPage.clickLoginButton();
        Assert.assertTrue(profilePage.getLoggedInMsg().isDisplayed());
        Assert.assertEquals(profilePage.expectedURL, driver.getCurrentUrl());
        Assert.assertTrue(profilePage.getLogOutBtn().isDisplayed());
        profilePage.getLogOutBtn().click();

    }

    @Test(priority = 2)
    public void loginWithInvalidUsername() throws InterruptedException {

        ArrayList<String> invalidUsernameList = new ArrayList<>();
        int rowCount = excelReader.getLastRow("Sheet1");
        String validPass = excelReader.getStringData("Sheet1",1,1);

        for(int i = 1; i <= rowCount; i++){
            String username = excelReader.getStringData("Sheet1",i,2);
            invalidUsernameList.add(username);
        }
        for(String username: invalidUsernameList){
            loginPage.enterInvalidUsername(username);
            loginPage.enterValidPass(validPass);
            loginPage.clickLoginButton();
            Thread.sleep(200);
            //wait.until(ExpectedConditions.visibilityOf(loginPage.getInvalidUsernameMsg()));
            Assert.assertTrue(loginPage.getInvalidUsernameMsg().isDisplayed());
        }
    }

    @Test(priority = 2)
    public void loginWithInvalidPassword() throws InterruptedException {

        ArrayList<String> invalidPasswordList = new ArrayList<>();
        int rowCount = excelReader.getLastRow("Sheet1");
        String validUsername = excelReader.getStringData("Sheet1",1,0);

        for(int i = 1; i <= rowCount; i++){
            String password = excelReader.getStringData("Sheet1",i,3);
            invalidPasswordList.add(password);
        }
        System.out.println(invalidPasswordList);
        for(String password: invalidPasswordList ){
            loginPage.enterValidUsername(validUsername);
            loginPage.enterInvalidPassword(password);
            loginPage.clickLoginButton();
            Thread.sleep(200);
            wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField()));
            Assert.assertTrue(loginPage.getInvalidUsernameMsg().isDisplayed());
        }

    }




    @AfterMethod
    public void tearDown(){
       driver.quit();
    }

}
