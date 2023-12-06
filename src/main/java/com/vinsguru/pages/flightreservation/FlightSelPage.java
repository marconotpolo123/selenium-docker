package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelPage extends AbstractPage {

    @FindBy(name="departure-flight")
    private List<WebElement> depFlightsOptions;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrFlightsOptions;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsBtn;

    public FlightSelPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
        return this.confirmFlightsBtn.isDisplayed();
    }

    public void selFlights() {
        int random = ThreadLocalRandom.current().nextInt(0, depFlightsOptions.size());
        this.depFlightsOptions.get(random).click();
        this.arrFlightsOptions.get(random).click();
    }

    public void confirmFlights() {
        this.confirmFlightsBtn.click();
    }
}
