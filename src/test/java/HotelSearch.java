import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
                //el.isDisplayed() podświetla sie na szaro i moze zostać zamienione an method reference
//                .filter(el -> el.isDisplayed())//filtrowanie między elementami zwaracoące element wyświetlony
                .filter(WebElement::isDisplayed)//method reference zamiast wyrażenia lambda
                .findFirst()//znajduje pierwszy
//                .ifPresent(el -> el.click());//jeżeli dostępny click()
                .ifPresent(WebElement::click);
        /** Część trzecia zmiana lokatorów */

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        /** Część czwarta wyniki */

        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
//                .map(el -> el.getText())
                .map(el -> el.getAttribute("textContent")) //zmiana sposobu pobierania tekstu bo nie wyświetlaja się wszystkie
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());

//        hotelNames.forEach(el -> System.out.println(el));
        //method reference, dla każdego elementu który znajduje sie w hotelnames ma byc wywołany println
        //automatycznie każdy element jest przekazywany jako argument do println
        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }
}
