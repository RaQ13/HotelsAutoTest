import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZadanieSearchTest extends BaseTest{

    @Test
    public void searchHotelsTest() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("http://www.kurs-selenium.pl/demo/");

        //pobiera aktualną date w formacie dd/MM/yyyy
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateFlightOut = simpleDateFormat.format(new Date());

        //zwiększa aktualną datę o 4 dni
        Date dateBack = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateBack);
        c.add(Calendar.DATE, 4);
        dateBack = c.getTime();
        String dateFlightBack = simpleDateFormat.format(dateBack);

//        System.out.println(dateFlightOut);
//        System.out.println(dateFlightBack);

        driver.findElement(By.name("checkin")).sendKeys(dateFlightOut);
        driver.findElement(By.name("checkout")).sendKeys(dateFlightBack);
        driver.findElement(By.name("travellers")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();
        WebElement heading = driver.findElement(By.xpath("//h2[@class='text-center' and text()='No Results Found']"));
        Assert.assertEquals(heading.getText(), "No Results Found");
//        driver.quit();
    }
}
