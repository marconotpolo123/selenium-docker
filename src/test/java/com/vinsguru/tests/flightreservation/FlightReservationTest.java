package com.vinsguru.tests.flightreservation;

import com.vinsguru.pages.AbstractPage;
import com.vinsguru.pages.flightreservation.*;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.flightreservation.model.FlightReservationTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {



    private RegPage regPage;
    private RegConfirmPage regConfirmPage;
    private FlightSearchPage flightSearchPage;
    private FlightSelPage flightSelPage;
    private FlightConfirmPage flightConfirmPage;

    /*private String noOfPassengers;
    private String expectedPrice;
    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setPageObj(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }*/

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setPageObj(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegTest() {
        this.regPage = new RegPage(driver);
        this.regPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        this.regPage.maxBrowser();
        Assert.assertTrue(regPage.isAt());
        this.regPage.enterUserDetails(this.testData.getFirstName(), this.testData.getLastName());
        this.regPage.enterUserCredentials(this.testData.getEmail(), this.testData.getPassword());
        this.regPage.enterAddr(this.testData.getStreet(), this.testData.getCity(), this.testData.getZip());
        this.regPage.register();
    }

    @Test(dependsOnMethods = "userRegTest")
    public void regConfirmTest() {
        this.regConfirmPage = new RegConfirmPage(driver);
        Assert.assertTrue(this.regConfirmPage.isAt());
        this.regConfirmPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "regConfirmTest")
    public void flightsSearchTest() {
        this.flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(this.flightSearchPage.isAt());
        this.flightSearchPage.selectPassengers(this.testData.getPassengersCount());
        this.flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelTest() {
        this.flightSelPage = new FlightSelPage(driver);
        Assert.assertTrue(this.flightSelPage.isAt());
        this.flightSelPage.selFlights();
        this.flightSelPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelTest")
    public void flightsConfTest() {
        this.flightConfirmPage = new FlightConfirmPage(driver);
        Assert.assertTrue(this.flightConfirmPage.isAt());
        Assert.assertEquals(this.flightConfirmPage.getPrice(), this.testData.getExpectedPrice());
    }

}
