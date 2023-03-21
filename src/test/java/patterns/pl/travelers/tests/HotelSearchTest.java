package patterns.pl.travelers.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import patterns.pl.travelers.pages.HotelSearchPage;
import patterns.pl.travelers.pages.ResultsPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest2() {

        /** Sposób z podejsciem fluent
         * celem jest uniknięcie wielolinijkowego
         * odwoływania się do 1 tego samego obiektu
         *
         * Aby to osiągnąć, metody w klasie muszą muszą tę klase zwracać
         * W tym wypadku patrz HotelSearchPage linijka 49
         * */

        /**
         *         Zamiast tak:
         *
         *         hotelSearchPage.setCityName("Dubai");
         *         hotelSearchPage.setDates("20/10/2023", "26,10,2023");
         * /       hotelSearchPage.setTravellers(1, 1);
         *         hotelSearchPage.performSearch();
         *
         *         W ten sposób:
         *
         *         hotelSearchPage.setCityName("Dubai")
         *                 .setsetDates("20/10/2023", "26,10,2023")
         *                 .hotelSearchPage.setTravellers(1, 1)
         *                 .performSearch(); */

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver); //przekazany driver do PageFactory

        List<String> hotelNames = hotelSearchPage.setCityName("Dubai")
                .setDates("20/10/2023", "26,10,2023")
                .setTravellers(1,1)
                .performSearch().getHotelNames();

//        ResultsPage resultsPage = new ResultsPage(driver); //nie trzeba juz Tworzyć nowego obiektu
//        List<String> hotelNames = resultsPage.getHotelNames(); // zastąpione wyżej wraz z wywołaniem całego łańcucha

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

    /** Domyślny bez Fluent */
    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver); //przekazany driver do PageFactory
        hotelSearchPage.setCityName("Dubai");
        hotelSearchPage.setDates("20/10/2023", "26,10,2023");
        hotelSearchPage.setTravellers(1, 1);
        hotelSearchPage.performSearch();

        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

    @Test
    public void searchHotelsTest2() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
//        ResultsPage resultsPage = new ResultsPage(driver);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateFlightOut = simpleDateFormat.format(new Date());

        Date dateBack = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateBack);
        c.add(Calendar.DATE, 4);
        dateBack = c.getTime();
        String dateFlightBack = simpleDateFormat.format(dateBack);

        ResultsPage resultsPage = hotelSearchPage.setDates(dateFlightOut, dateFlightBack)
                .setTravellers(0, 1)
                .performSearch();

//        WebElement heading = driver.findElement(By.xpath("//h2[@class='text-center' and text()='No Results Found']"));
        Assert.assertTrue(resultsPage.resultHeading().isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }
}
