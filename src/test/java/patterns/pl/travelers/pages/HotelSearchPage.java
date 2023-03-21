package patterns.pl.travelers.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.pl.travelers.utils.SeleniumHelper;

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

    //logger
    private static final Logger logger = LogManager.getLogger();



    /** Page factory inicjujący pola z @FindBy
     * PageFactory (driver, strona)
     * */

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver,  this);
        this.driver = driver; //3. przypisanie do pola przekazanego drivera
    }


    /** Podejście fluent:
     * zmiana zwracanego typu na daną Klase:
     * zwracanie tej klasy
     *
     * !! Jeżeli jesteśmy na tej samej stronie możemy zwórcić dana klase!!*/

//    public void setCityName(String cityName) {
    public HotelSearchPage setCityName(String cityName) {
        logger.info("Setting city " + cityName); //z użyciem loggera
//        logger.info("Setting city " + cityName);
        //1. dla wyszukania match z podanego miasta
        String xpathMatch = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpathMatch));
        driver.findElement(By.xpath(xpathMatch)).click();
        logger.info("Setting city " + cityName + " done");
//        logger.info("Setting city " + cityName + " done");
        return this;
    }

    public HotelSearchPage setDates(String checkin, String checkout) {
        logger.info("Setting dates");
        logger.info("Setting checkin date " + checkin);
        checkinInput.sendKeys(checkin);
        logger.info("Setting checkin date " + checkin + " done");
        logger.info("Setting checkin date " + checkout);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting checkin date " + checkout + " done");
        return this;
    }

    public HotelSearchPage setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        logger.info("Setting adults " + adultsToAdd);
        addTraveller(adultPlusButton, adultsToAdd);
        logger.info("Setting adults " + adultsToAdd + " done");
        logger.info("Setting children " + childToAdd);
        addTraveller(childPlusButton, childToAdd);
        logger.info("Setting children " + childToAdd + " done");
        return this;
    }

    public HotelSearchPage addTraveller(WebElement travellerBtn, int numberOfTravellers) {
        for (int i = 0; i < numberOfTravellers; i++) {
            SeleniumHelper.waitForElementToBeVisible(driver, travellerBtn);
            travellerBtn.click();
        }
        return this;
    }

    //Ta metoda przenosi na inną stronę, dlatego musi zwracać NOWY obiekt danej klasy, wraz z
    // przekazanym driverem do obiektu
    public ResultsPage performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Perform search done");
        return new ResultsPage(driver);
    }
}
