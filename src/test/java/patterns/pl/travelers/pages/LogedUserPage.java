package patterns.pl.travelers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;

    public LogedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText() {
        return heading.getText();
    }
}
