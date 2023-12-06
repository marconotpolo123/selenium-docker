package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstNameTb;

    @FindBy(id = "lastName")
    private WebElement lastNameTb;

    @FindBy(id = "email")
    private WebElement emailTb;

    @FindBy(id = "password")
    private WebElement pwdTb;

    @FindBy(name = "street")
    private WebElement streetTb;

    @FindBy(name = "city")
    private WebElement cityTb;

    @FindBy(name = "zip")
    private WebElement zipTb;

    @FindBy(id = "register-btn")
    private WebElement regBtn;

    public RegPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameTb));
        return this.firstNameTb.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void maxBrowser() {
        this.driver.manage().window().maximize();
    }

    public void enterUserDetails(String firstName, String lastName) {
        this.firstNameTb.sendKeys(firstName);
        this.lastNameTb.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String pwd) {
        this.emailTb.sendKeys(email);
        this.pwdTb.sendKeys(pwd);
    }

    public void enterAddr(String street, String city, String zip) {
        this.streetTb.sendKeys(street);
        this.cityTb.sendKeys(city);
        this.zipTb.sendKeys(zip);
    }

    public void register() {
        this.regBtn.click();
    }

}
