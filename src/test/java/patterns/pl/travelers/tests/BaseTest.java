package patterns.pl.travelers.tests;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import patterns.pl.travelers.utils.DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //robote przejmuje SeleniumHelper
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
