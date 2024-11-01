package cucumber_project;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static WebDriver driver = null;

    public void waitAndClick(String xpath, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void waitAndType(String xpathForStatedTextBox, String stringToType, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForStatedTextBox)));
        WebElement statedTextBox = findElementByXPath(xpathForStatedTextBox);
        Assert.assertNotNull(statedTextBox);
        statedTextBox.sendKeys(stringToType);
    }

    public boolean waitUntilElementIsDisplayed(String xpath, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public WebElement findElementByXPath(String xPathToSearchFor) {
        try {
            return driver.findElement(By.xpath(xPathToSearchFor));
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}