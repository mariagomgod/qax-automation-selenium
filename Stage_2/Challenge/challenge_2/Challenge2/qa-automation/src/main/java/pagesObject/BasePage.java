package pagesObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // espera global
        this.actions = new Actions(driver);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ------------------------------
    //  Acciones Helper
    // ------------------------------
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    public void jsClick(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void jsScroll(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void jsClickWebElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void jsScrollWebElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    // ------------------------------
    //  Scroll
    // ------------------------------
    protected void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void scrollDown(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
    }

    // ------------------------------
    //  Alerts
    // ------------------------------
    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }


    protected void acceptAlert() {
        waitForAlert().accept();
    }

    protected void dismissAlert() {
        waitForAlert().dismiss();
    }

    protected void sendKeysToAlert(String text) {
        waitForAlert().sendKeys(text);
    }

    // ------------------------------
    //  Ventanas Múltiples
    // ------------------------------
    protected void switchToWindow(int index) {
        var tabs = driver.getWindowHandles().toArray();
        driver.switchTo().window(tabs[index].toString());
    }

    // ------------------------------
    //  iFrames
    // ------------------------------
    protected void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    protected void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    // ------------------------------
    //  Hover
    // ------------------------------
    protected void hover(By locator) {
        WebElement element = waitForVisibility(locator);
        actions.moveToElement(element).perform();
    }

    // ------------------------------
    //  Drag & Drop
    // ------------------------------
    protected void dragAndDrop(By source, By target) {
        WebElement from = waitForVisibility(source);
        WebElement to = waitForVisibility(target);
        actions.dragAndDrop(from, to).perform();
    }

    // ------------------------------
    //  Upload File
    // ------------------------------
    protected void uploadFile(By locator, String absolutePath) {
        waitForVisibility(locator).sendKeys(absolutePath);
    }

    // ------------------------------
    //  Click & Hold
    // ------------------------------
    /**
     * Arrastra un elemento desde su posición actual a un offset relativo.
     * xOffset > 0 → derecha, xOffset < 0 → izquierda
     * yOffset > 0 → abajo, yOffset < 0 → arriba
     */
    protected void dragAndDropByOffset(By source, int xOffset, int yOffset) {
        WebElement from = waitForVisibility(source);
        actions.clickAndHold(from)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }
}
