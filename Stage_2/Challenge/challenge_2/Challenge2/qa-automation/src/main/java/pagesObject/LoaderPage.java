package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoaderPage extends BasePage {

    public LoaderPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By loaderModal = By.id("myModal");
    private By modalTitle = By.xpath("//h4[@class='modal-title' and normalize-space()='Modal title']");
    private By runBtn = By.id("loader");
    private By closeBtn = By.xpath("//button[@class='btn btn-default' and normalize-space()='Close']");
    private By loaderTxt = By.xpath("//h1[normalize-space()='Please wait...']");

    // ==============================
    //  Acciones
    // ==============================
    public WebElement getRunBtn() {
        return waitForClickable(runBtn);
    }

    public void clickOnRunBtn() {
        jsScroll(runBtn);
        jsClick(runBtn);
    }

    public void clickOnCloseBtn() {
        waitForClickable(closeBtn).click();
    }

    public boolean isRunBtnEnabled() {
        return waitForClickable(runBtn).isEnabled();
    }

    public void waitForLoaderToAppear() {
        waitForVisibility(loaderTxt);
    }

    public boolean isLoaderVisible() {
        List<WebElement> elements = driver.findElements(loaderTxt);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public void waitForFinalContentToBeVisible() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        longWait.until(ExpectedConditions.visibilityOfElementLocated(loaderModal));
        longWait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
    }

    public boolean isFinalContentVisible() {
        return waitForVisibility(modalTitle).isDisplayed();
    }
}
