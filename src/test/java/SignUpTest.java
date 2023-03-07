import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignUpTest extends BaseTest{

    //pole private jest tworzone bo po wyjęciu kodu tworzącego drivero
    //nie mamy do niego dostępu
//    private WebDriver driver;
//
//    @BeforeMethod
//    public void setup() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("http://www.kurs-selenium.pl/demo/");
//    }
//
//    @AfterMethod
//    public void tearDown() { //metoda uruchamiana po teście
//        driver.quit();
//    }

    /** Powyższy wyseparowany do BaseTest */

    @Test
    public void signUpTest() {

        /** Otwieranie formularza rejestracji */

//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("http://www.kurs-selenium.pl/demo/");

        /** Działania na wielu elementach html */

        //pobiera listę elementów -> stream -> filtrowanie dla elementu widocznego i dostępnego
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        //po prostu pobranie drugiego elementu z listy
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        /** Wprowadzanie danych do formularza */

        String lastName = "Barbarzyńca";
        int randomnumber = (int) (Math.random()*10000);
        String email= "ptrknorder" + randomnumber + "@gmail.com";
        driver.findElement(By.name("firstname")).sendKeys("Genowef");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("666666666");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(
                heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Genowef Barbarzyńca");
//        driver.quit();
    }
}
