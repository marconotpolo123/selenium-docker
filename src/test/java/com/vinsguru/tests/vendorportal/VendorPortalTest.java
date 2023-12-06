package com.vinsguru.tests.vendorportal;

import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.vendorportal.model.VendorPortalTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.systeminfo.model.VideoEncodeAcceleratorCapability;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObj(String testDataPath) {
        this.dashboardPage = new DashboardPage(driver);
        this.loginPage = new LoginPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        this.loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        this.loginPage.maxBrowser();
        Assert.assertTrue(this.loginPage.isAt());
        this.loginPage.login(this.testData.getUsername(), this.testData.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(this.dashboardPage.isAt());
        Assert.assertEquals(this.dashboardPage.getMonthlyEarning(), this.testData.getMonthlyEarning());
        Assert.assertEquals(this.dashboardPage.getAnnualEarning(), this.testData.getAnnualEarning());
        Assert.assertEquals(this.dashboardPage.getProfitMargin(), this.testData.getProfitMargin());
        Assert.assertEquals(this.dashboardPage.getAvailableInventory(), this.testData.getAvailInventory());

        this.dashboardPage.searchOrderHistoryBy(this.testData.getSearchKeyword());
        Assert.assertEquals(this.dashboardPage.getSearchResultsCount(), this.testData.getSearchResCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        this.dashboardPage.logout();
        Assert.assertTrue(this.loginPage.isAt());
    }

}
