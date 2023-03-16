package patterns.pl.travelers.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import patterns.pl.travelers.pages.LogedUserPage;
import patterns.pl.travelers.pages.SignUpPage;

public class SignUpTest extends BaseTest{

    @Test
    public void signUpTest() {
        String lastName = "Barbarzyńca";
        int randomnumber = (int) (Math.random()*10000);
        String email = "ptrknorder" + randomnumber + "@gmail.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        LogedUserPage logedUserPage = new LogedUserPage(driver);
        signUpPage.myAccountClick();
        signUpPage.signUpButtonClick();
        signUpPage.fillForm("Genowef", lastName, "666666666", email, "Test123", "Test123");
        signUpPage.signUpButtonClick();

        Assert.assertTrue(logedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(logedUserPage.getHeadingText(), "Hi, Genowef " + lastName);
    }
}
