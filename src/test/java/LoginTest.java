import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    @Test
    public void login() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.id("li_myaccount"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()=' Login']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        /** Logowanie */

        driver.findElement(By.name("username")).sendKeys("ptrknorder@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()='Login']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertEquals(heading.getText(), "Hi, Genowef Barbarzyńca");
        driver.quit();

    }
}
