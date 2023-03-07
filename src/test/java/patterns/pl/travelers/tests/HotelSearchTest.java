package patterns.pl.travelers.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import patterns.pl.travelers.pages.HotelSearchPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage();
        hotelSearchPage.setCityName("Dubai");
        hotelSearchPage.setDates("20/10/2023", "26,10,2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();


        driver.findElement(By.name("checkin")).sendKeys("17/04/2021");
        driver.findElement(By.name("checkout")).click();

        driver.findElements(By.xpath("//td[@class='day ' and text()='28']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();


        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

    @Test
    public void searchHotelsTest() {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateFlightOut = simpleDateFormat.format(new Date());

        Date dateBack = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateBack);
        c.add(Calendar.DATE, 4);
        dateBack = c.getTime();
        String dateFlightBack = simpleDateFormat.format(dateBack);

        driver.findElement(By.name("checkin")).sendKeys(dateFlightOut);
        driver.findElement(By.name("checkout")).sendKeys(dateFlightBack);
        driver.findElement(By.name("travellers")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();
        WebElement heading = driver.findElement(By.xpath("//h2[@class='text-center' and text()='No Results Found']"));
        Assert.assertEquals(heading.getText(), "No Results Found");
    }
}
