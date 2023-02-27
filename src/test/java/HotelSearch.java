import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

        //wpisanie w input daty
        driver.findElement(By.name("checkin")).sendKeys("17/04/2021");
//        driver.findElement(By.name("checkout")).sendKeys("20/04/2021");

        //przez klik w kalendarzu
        driver.findElement(By.name("checkout")).click();
        //find elements dla znalezienia wielu elementów i możliwości filtrowania wyników
        //jest wiele elementów td o wartosci 28, trzeba przefiltrować do elementu, który się wyświetla
        driver.findElements(By.xpath("//td[@class='day ' and text()='28']"))
                .stream() //strumień z elementami
                .filter(el -> el.isDisplayed())//filtrowanie między elementami zwaracoące element wyświetlony
                .findFirst()//znajduje pierwszy
                .ifPresent(el -> el.click());//jeżeli dostępny click()

        /** Część trzecia zmiana lokatorów */

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        /** Część czwarta wyniki */

        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getText())
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
    }
}
