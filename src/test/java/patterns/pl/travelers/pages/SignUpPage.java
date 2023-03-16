package patterns.pl.travelers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.pl.travelers.tests.BaseTest;

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


    public void myAccountClick() {
        myAccountButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
    }

    public void signUpButtonClick() {
        signUpButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
    }

    public void submitButtonClick() {
        submitButton.click();
    }

    public void fillForm(String fname, String lname, String phone, String email, String pass, String confPass) {
        fnameInput.sendKeys(fname);
        lnameInput.sendKeys(lname);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(pass);
        confirmPasswordInput.sendKeys(confPass);
        submitButton.click();
    }

    public List<String> getErrors() {
       return onSubmitErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
