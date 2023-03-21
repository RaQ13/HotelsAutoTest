package patterns.pl.travelers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.pl.travelers.utils.SeleniumHelper;

public class LogedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;

    private WebDriver driver;

    public LogedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeadingText() {
        SeleniumHelper.waitForElementToBeVisible(driver, heading);
        return heading.getText();
    }
}
