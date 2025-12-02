package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProgressBarPage extends BasePage {

    public ProgressBarPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By downloadBtn = By.id("cricle-btn");
    private By progressBarValue = By.cssSelector("div.progressbar-text");

    // ==============================
    //  Acciones
    // ==============================
    public WebElement getDownloadBtn() {
        return waitForClickable(downloadBtn);
    }

    public void clickOnDownloadBtn() {
        jsScroll(downloadBtn);
        jsClick(downloadBtn);
    }

    // Método que devuelve el valor numérico de la barra (0-100)
    public int getProgressValue() {
        WebElement element = waitForVisibility(progressBarValue);
        String value = element.getText().trim();
        return Integer.parseInt(value);
    }

    public boolean isProgressNotStarted() {
        List<WebElement> elements = driver.findElements(progressBarValue);
        if (elements.isEmpty()) {
            return true;
        }
        String value = elements.get(0).getText().trim();
        return value.isEmpty();
    }

    public void waitForProgressToReachHundred() {
        wait.until(ExpectedConditions.textToBe(progressBarValue, "100"));
    }

    public boolean isFinalState() {
        return getProgressValue() == 100;
    }
}
