package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DynamicDataPage  extends BasePage {

    public DynamicDataPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By dynamicDataBtn = By.id("save");
    private By dynamicContentContainer = By.id("loading");
    private By imgLocator = By.cssSelector("#loading img");

    // ==============================
    //  Acciones
    // ==============================

    public WebElement getGetDynamicDataBtn() {
        return waitForClickable(dynamicDataBtn);
    }

    public void clickOnGetDynamicDataBtn() {
        jsScroll(dynamicDataBtn);
        jsClick(dynamicDataBtn);
    }

    public boolean isDynamicAreaVisible() {
        return waitForVisibility(dynamicContentContainer).isDisplayed();
    }

    // Texto actual dentro del contenido dinámico (inicialmente vacío)
    public String getDynamicContentTxt() {
        WebElement container = driver.findElement(dynamicContentContainer);
        return container.getText().trim();
    }

    // Espera hasta que el contenido dinámico cambie respecto al valor inicial y no esté vacío
    public void waitForDynamicContentToChange(String initialContent) {
        wait.until(driver -> {
            String current = getDynamicContentTxt();
            return !current.equals(initialContent) && !current.isEmpty();
        });
    }

    // Método para comprobar si al menos hay una imagen en el área dinámica
    public boolean hasDynamicImage() {
        List<WebElement> images = driver.findElements(imgLocator);
        return !images.isEmpty() && images.get(0).isDisplayed();
    }
}
