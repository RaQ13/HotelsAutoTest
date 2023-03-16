package patterns.pl.travelers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

//    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
//    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusButton;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusButton;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    private WebDriver driver; //2. pole webdriver

    /** Page factory inicjujący pola z @FindBy
     * PageFactory (driver, strona)
     * */

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver,  this);
        this.driver = driver; //3. przypisanie do pola przekazanego drivera
    }

    public void setCityName(String cityName) {
        //1. dla wyszukania match z podanego miasta
        String xpathMatch = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        driver.findElement(By.xpath(xpathMatch)).click();
//        hotelMatch.click();
    }

    public void setDates(String checkin, String checkout) {
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        addTraveller(adultPlusButton, adultsToAdd);
        addTraveller(childPlusButton, childToAdd);
    }

    public void addTraveller(WebElement travellerBtn, int numberOfTravellers) {
        for (int i = 0; i < numberOfTravellers; i++) {
            travellerBtn.click();
        }
    }

    public void performSearch() {
        searchButton.click();
    }
}
