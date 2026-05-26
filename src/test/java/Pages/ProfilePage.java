package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {

    WebDriver driver;
    WebElement loggedInMsg;
    WebElement logOutBtn;
    public String expectedURL = "https://the-internet.herokuapp.com/secure";

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoggedInMsg() {
        return driver.findElement(By.id("flash")) ;
    }

    public WebElement getLogOutBtn() {
        return driver.findElement(By.linkText("Logout"));
    }
}
