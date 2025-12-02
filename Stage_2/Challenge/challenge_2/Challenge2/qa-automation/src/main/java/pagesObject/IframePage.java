package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IframePage extends BasePage {

    public IframePage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By singleIframeDemo = By.cssSelector("iframe[id='singleframe']");
    private By txtSingleIframeInput  = By.cssSelector("input[type=\"text\"]");
    private By nestedIframeTab = By.cssSelector("a.analystic[href='#Multiple']");
    private By iframeMultiple = By.cssSelector("iframe[src='MultipleFrames.html']"); // Iframe múltiple (padre)
    private By iframeChild = By.cssSelector("iframe[src='SingleFrame.html']"); // Iframe hijo dentro del múltiple
    private By headerOutsideFrames = By.cssSelector("h1"); // Algún elemento fuera de los iframes (título de la página)

    // ==============================
    //  Acciones
    // ==============================

    public void writeInIframeInput(String text) {
        switchToFrame(singleIframeDemo);         // método de BasePage
        type(txtSingleIframeInput, text);        // método de BasePage
        switchToDefault();                 // volvemos al contexto normal
    }

    public String getSingleIframeInputValue() {
        switchToFrame(singleIframeDemo);
        WebElement input = waitForVisibility(txtSingleIframeInput);
        String value = input.getAttribute("value");
        switchToDefault();
        return value;
    }

    public void openNestedIframeTab() {
        jsClick(nestedIframeTab);
        switchToNestedChildIframe();
    }

    private void switchToNestedParentIframe() {
        switchToDefault();
        switchToFrame(iframeMultiple);
    }

    private void switchToNestedChildIframe() {
        switchToNestedParentIframe();
        switchToFrame(iframeChild);
    }
    public void writeInChildIframeInput(String text) {
        WebElement input = waitForVisibility(txtSingleIframeInput);
        input.clear();
        input.sendKeys(text);
    }

    public String getChildIframeInputValue() {
        WebElement input = waitForVisibility(txtSingleIframeInput);
        String value = input.getAttribute("value");
        switchToDefault();
        return value;
    }

    public void writeInParentIframeInput(String text) {
        driver.switchTo().parentFrame();
        WebElement singleIframeTab = driver.findElement(By.cssSelector("a.analystic[href='#Single'][data-toggle='tab']"));
        singleIframeTab.click();
        switchToFrame(iframeChild);
        type(txtSingleIframeInput, text);
    }

    public boolean isHeaderOutsideFramesVisible() {
        switchToDefault();
        WebElement header = waitForVisibility(headerOutsideFrames);
        return header.isDisplayed();
    }
}
