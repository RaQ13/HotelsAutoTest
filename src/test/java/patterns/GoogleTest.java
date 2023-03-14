package patterns;

import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void googleSearchTest() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.setSearchInGoogle("Selenium");
    }
}