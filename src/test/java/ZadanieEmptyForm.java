import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ZadanieEmptyForm {

    WebDriver driver = new ChromeDriver();
    @Test
    public void tryEmptyForm() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/register");

        WebElement submit = driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']"));
        WebElement fname = driver.findElement(By.name("firstname"));
        WebElement lname = driver.findElement(By.name("lastname"));
        WebElement phone = driver.findElement(By.name("phone"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement confPass = driver.findElement(By.name("confirmpassword"));

        submit.click();

        /** Pierwszy sposób */

//        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
//
//        if (errorMessage.isDisplayed()) {
//           List<WebElement> errorParams = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
//                   .stream()
//                   .collect(Collectors.toList());
//           errorParams.forEach(eparam -> System.out.println(eparam.getText()));
//        }
//
//        fname.sendKeys("Gniewomir");
//        lname.sendKeys("Wsiok");
//        phone.sendKeys("123659874");
//        email.sendKeys("pastaemail.com");
//        pass.sendKeys("test");
//        confPass.sendKeys("test");
//
//        submit.click();
//
//        if (errorMessage.isDisplayed()) {
//            List<WebElement> errorParams = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().collect(Collectors.toList());
//            errorParams.forEach(eparam -> System.out.println(eparam.getText()));
//        }

        /** drugi sposób */

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }
    @Test
    public void tryInvalidEmail() throws InterruptedException {
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']"));
        WebElement fname = driver.findElement(By.name("firstname"));
        WebElement lname = driver.findElement(By.name("lastname"));
        WebElement phone = driver.findElement(By.name("phone"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement confPass = driver.findElement(By.name("confirmpassword"));
        SoftAssert softAssert = new SoftAssert();


        fname.sendKeys("Gniewomir");
        lname.sendKeys("Wsiok");
        phone.sendKeys("123659874");
        email.sendKeys("pastaemail.com");
        pass.sendKeys("test");
        confPass.sendKeys("test");

        submit.click();

        Thread.sleep(1000);

        List<String> errors2 = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        errors2.forEach(el -> System.out.println(el));

        softAssert.assertTrue(errors2.contains("The Email field must contain a valid email address."));
        softAssert.assertTrue(errors2.contains("The Password field must be at least 6 characters in length."));
        softAssert.assertAll();
        driver.quit();
    }
}
