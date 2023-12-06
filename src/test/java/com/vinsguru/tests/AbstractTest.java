package com.vinsguru.tests;

import com.vinsguru.listener.TestListener;
import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public class AbstractTest {

    protected WebDriver driver;

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setupConfig() throws Exception {
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {

        if(Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }

        ctx.setAttribute(Constants.DRIVER, this.driver);
    }
    /*public void setDriver(String browser) throws MalformedURLException {
        if(Boolean.getBoolean("selenium.grid.enabled")) {
            this.driver = getRemoteDriver(browser);
        } else {
            this.driver = getLocalDriver();
        }
    }*/




    /*private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        Capabilities capabilities;
        if(browser.equalsIgnoreCase("chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }*/

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        } else {
            capabilities = new ChromeOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        //log.info("grid url: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
