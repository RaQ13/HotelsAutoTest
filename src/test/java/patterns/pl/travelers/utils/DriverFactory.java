package patterns.pl.travelers.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/** Klasa definiujaca driver */
public class DriverFactory {

    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");

        if (name.equals("firefox")) {
            return new FirefoxDriver();
        } else {
            //org.openqa.selenium.remote.http.ConnectionFailedException: Unable to establish websocket connection to
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*"); //dodanie tej opcji rozwiązuje
            return new ChromeDriver(options);
        }
    }
}
