import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HotelSearch {

    @Test
    public void searchHotel() {
        /** Część pierwsza wyszukiwanie wyniuku*/

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //półśrodek dla czekania na element
//        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
//        Thread.sleep(3000); //krok 1, okazuje sie że wystarczyło poczekać na element
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        /** Część druga wybór daty */

        //wpisanei w input daty
        driver.findElement(By.name("checkin")).sendKeys("17/04/2021");
        driver.findElement(By.name("checkout")).sendKeys("20/04/2021");

    }
}
