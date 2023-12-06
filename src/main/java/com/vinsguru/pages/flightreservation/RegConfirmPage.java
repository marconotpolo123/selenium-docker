package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegConfirmPage extends AbstractPage {

    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightsSearchBtn;

    public RegConfirmPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchBtn));
        return this.goToFlightsSearchBtn.isDisplayed();
    }

    public void goToFlightsSearch() {
        this.goToFlightsSearchBtn.click();
    }
}
