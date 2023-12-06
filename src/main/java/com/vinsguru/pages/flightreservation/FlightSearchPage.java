package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengerSel;

    @FindBy(id = "search-flights")
    private WebElement searchLightsBtn;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSel));
        return this.passengerSel.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers) {
        Select passengers = new Select(this.passengerSel);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights() {
        this.searchLightsBtn.click();
    }


}
