package cucumber_project.steps;

import cucumber_project.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;


public class StepDefinitions extends BaseTest {

    // GIVEN Steps
    @Given("^I Open The Browser$")
    public void initialize() {
        // Setup WebDriverManager to manage ChromeDriver binaries
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setAcceptInsecureCerts(true);

        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Given("^I Close The Browser$")
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Given("I Navigate To {string} Page")
    public void navigateToPage(String url) {
        driver.get(url);
    }

    @Then("I Successfully Login With Valid Username {string} And Valid Password {string}")
    public void loginWithValidUsernameAndPassword(String username, String password) {
        waitAndType("//input[@id='user-name']", username, 5);
        waitAndType("//input[@id='password']", password, 5);
        waitAndClick("//input[@id='login-button']", 5);
        waitUntilElementIsDisplayed("//div[@class='app_logo']", 5);
    }

    @Then("I Verify That I Am Redirected To {string} Page")
    public void i_Verify_That_I_Am_Redirected_To_Page(String string) {
        String[] listUrl = string.split("_");
        String rootUrl = listUrl[0];
        String pathUrl = listUrl[1];
        String expectedURL = "";
        String redirectURL = "";

        if (pathUrl.contains("3rdPartyPages")) {
            expectedURL = pathUrl;
            redirectURL = driver.getCurrentUrl();
        } else if (rootUrl.equals("")) {
            System.out.println("Base URL is NULL");
            Assert.assertNotEquals("", rootUrl);
        } else {
            expectedURL = rootUrl + pathUrl;
            redirectURL = driver.getCurrentUrl();
        }
        Assert.assertEquals(redirectURL, expectedURL);
    }

    @When("I Click On {string} Element")
    public void i_Click_On_Element(String string) {
        waitAndClick(string, 10);
    }

    @Then("I Verify That {string} Is Displayed")
    public void i_Verify_That_Is_Displayed(String string) {
        Boolean found = waitUntilElementIsDisplayed(string, 15);
        Assert.assertEquals(found, true);
    }

    @When("I Insert Text {string} In The {string} Field")
    public void i_Insert_Text_In_The_Field(String textSearch, String textBar) {
        waitAndType(textBar, textSearch, 10);
    }

    @And("I Switch To Open Tab")
    public void and_I_Switch_To_Open_Tab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

    @Then("I Verify That I Am Logged Out")
    public void i_Verify_That_I_Am_Logged_Out() {
        Boolean found = waitUntilElementIsDisplayed("loginPage.loginBtn", 15);
        Assert.assertEquals(found, true);
    }
}