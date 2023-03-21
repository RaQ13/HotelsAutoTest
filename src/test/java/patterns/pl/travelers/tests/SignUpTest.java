package patterns.pl.travelers.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import patterns.pl.travelers.model.User;
import patterns.pl.travelers.pages.LogedUserPage;
import patterns.pl.travelers.pages.SignUpPage;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Barbarzyńca";
        int randomnumber = (int) (Math.random() * 10000);
        String email = "ptrknorder" + randomnumber + "@gmail.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        LogedUserPage logedUserPage = signUpPage.myAccountClick()
        .signUpButtonOpenClick()
        .fillForm("Genowef", lastName, "666666666", email, "Test123", "Test123")
        .signUpButtonSumbitClick();

        Assert.assertTrue(logedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(logedUserPage.getHeadingText(), "Hi, Genowef " + lastName);
    }

    /** Korzysta z obiektu klasy User */
    @Test
    public void signUpTest2() {
        String lastName = "Barbarzyńca";
        int randomnumber = (int) (Math.random() * 10000);
        String email = "ptrknorder" + randomnumber + "@gmail.com";

        SignUpPage signUpPage = new SignUpPage(driver);
        LogedUserPage logedUserPage = new LogedUserPage(driver);
        User user = new User(); //obiekt klasy user

        user.setFirstName("Genowef")
        .setLastName(lastName)
        .setPhone("666666666")
        .setEmail(email)
        .setPassword("Test123");

        signUpPage.myAccountClick()
        .signUpButtonOpenClick()
        .fillForm(user) //przekazywany obiekt
        .signUpButtonSumbitClick();

        Assert.assertTrue(logedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(logedUserPage.getHeadingText(), "Hi, Genowef " + lastName);
    }

    @Test
    public void tryEmptyFormTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.myAccountClick()
        .signUpButtonOpenClick()
        .submitButtonClick();

        List<String> onSubmitErros = signUpPage.getErrors();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(onSubmitErros.contains("The Email field is required."));
        softAssert.assertTrue(onSubmitErros.contains("The Password field is required."));
        softAssert.assertTrue(onSubmitErros.contains("The Password field is required."));
        softAssert.assertTrue(onSubmitErros.contains("The First name field is required."));
        softAssert.assertTrue(onSubmitErros.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void tryInvalidEmailTest() {

        SoftAssert softAssert = new SoftAssert();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.myAccountClick()
        .signUpButtonOpenClick()
        .fillForm("Gniewomir", "Wsiok", "123659874", "pastaemail.com", "test", "test")
        .submitButtonClick();

        List<String> onSubmitErrors = signUpPage.getErrors();
        softAssert.assertTrue(onSubmitErrors.contains("The Email field must contain a valid email address."));
        softAssert.assertTrue(onSubmitErrors.contains("The Password field must be at least 6 characters in length."));
        softAssert.assertAll();
    }
}
