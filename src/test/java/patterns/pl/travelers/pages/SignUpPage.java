package patterns.pl.travelers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.pl.travelers.model.User;
import patterns.pl.travelers.tests.BaseTest;
import patterns.pl.travelers.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage extends BaseTest {

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountButton;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpButton;

    @FindBy(name = "firstname")
    private WebElement fnameInput;

    @FindBy(name = "lastname")
    private WebElement lnameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit' and text()=' Sign Up']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> onSubmitErrors;

    private WebDriver driver;


    public SignUpPage myAccountClick() {
        myAccountButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        return this;
    }

    public SignUpPage signUpButtonOpenClick() {
        signUpButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        return this;
    }

    public LogedUserPage signUpButtonSumbitClick() {
        signUpButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        return new LogedUserPage(driver);
    }

    public void submitButtonClick() {
        submitButton.click();
    }

    public SignUpPage fillForm(String fname, String lname, String phone, String email, String pass, String confPass) {
        fnameInput.sendKeys(fname);
        lnameInput.sendKeys(lname);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(pass);
        confirmPasswordInput.sendKeys(confPass);
        submitButton.click();
        return this;
    }

    public SignUpPage fillForm(User user) { //przeciążona metoda fillForm obslugująca obiekt
        fnameInput.sendKeys(user.getFirstName());
        lnameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPasswordInput.sendKeys(user.getPassword());
        submitButton.click();
        return this;
    }

    public List<String> getErrors() {
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//div[@class='alert alert-danger']//p"));
        return onSubmitErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this); this.driver = driver;
    }
}
