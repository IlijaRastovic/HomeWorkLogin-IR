package Pages;

import Base.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class LoginPage {
    WebDriver driver;

    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;
    WebElement invalidUsernameMsg;


    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    public WebElement getUsernameField() {
        return driver.findElement(By.id("username")) ;
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("password")) ;
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.cssSelector("button[type='submit']"));
    }

    public WebElement getInvalidUsernameMsg() {
        return driver.findElement(By.id("flash"));
    }
    //------------------------------------------------------------------------

    public void enterValidUsername(String  validUsername){
        getUsernameField().clear();
        getUsernameField().sendKeys(validUsername);
    }
    public void enterValidPass(String  validPass){
        getPasswordField().clear();
        getPasswordField().sendKeys(validPass);
    }
    public void clickLoginButton(){
        getLoginButton().click();
    }
    public void enterInvalidUsername(String invalidUsername){
//        for (String invalidUsername : invalidUsernameList) {
//            getUsernameField().clear();
//            getUsernameField().sendKeys(invalidUsername);
//            getPasswordField().clear();
//            getPasswordField().sendKeys(validPassword);
//            getLoginButton().click();
//        }
        getUsernameField().clear();
        getUsernameField().sendKeys(invalidUsername);
    }
    public void enterInvalidPassword(String invalidPassword){
        getPasswordField().clear();
        getPasswordField().sendKeys(invalidPassword);
    }
}
