package patterns.pl.travelers.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import patterns.pl.travelers.pages.SignUpPage;

public class SignUpTest extends BaseTest{

    @Test
    public void signUpTest() {
        String lastName = "Barbarzyńca";
        int randomnumber = (int) (Math.random()*10000);
        String email = "ptrknorder" + randomnumber + "@gmail.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.myAccountClick();
        signUpPage.signUpButtonClick();
        signUpPage.fillForm("Genowef", lastName, "666666666", email, "Test123", "Test123");
        signUpPage.signUpButtonClick();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Genowef Barbarzyńca");
    }
}
