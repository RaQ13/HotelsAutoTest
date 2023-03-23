package patterns.pl.travelers.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import patterns.pl.travelers.utils.DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    //deklaracja pól dla możliwości skorzytstania z metody flush
    //statyczne bo nei są zależne od obiektu klasy tylko powiązane z daną klasą
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuite() {
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }
    @AfterSuite
    public void afterSuite() {
        htmlReporter.flush();
        extentReports.flush();
    }
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
