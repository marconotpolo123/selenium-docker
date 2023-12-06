package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FlightConfirmPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmPage.class);

    @FindBy(css= "#flights-confirmation-section > div > div > div > form > div > div > div:nth-child(1) > div:nth-child(2) > p")
    private WebElement flightConfirmLbl;

    @FindBy(css= "#flights-confirmation-section > div > div > div > form > div > div > div:nth-child(3) > div:nth-child(2) > p")
    private WebElement totalPriceLbl;

    public FlightConfirmPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmLbl));
        return this.flightConfirmLbl.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.flightConfirmLbl.getText();
        String price = this.totalPriceLbl.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", this.totalPriceLbl.getText());
        return this.totalPriceLbl.getText();
    }
}
