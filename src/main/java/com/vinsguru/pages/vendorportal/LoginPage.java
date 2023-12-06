package com.vinsguru.pages.vendorportal;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement usernameTb;

    @FindBy(id = "password")
    private WebElement pwdTb;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.usernameTb));
        return this.usernameTb.isDisplayed();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void maxBrowser() {
        this.driver.manage().window().maximize();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void login(String username, String pwd) {
        this.usernameTb.sendKeys(username);
        this.pwdTb.sendKeys(pwd);
        this.loginBtn.click();
    }
}
