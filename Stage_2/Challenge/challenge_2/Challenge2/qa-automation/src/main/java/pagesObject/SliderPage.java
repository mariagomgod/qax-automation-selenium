package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SliderPage extends BasePage {

    public SliderPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By sliderContainer = By.id("slider");
    private By sliderHandler = By.cssSelector("#slider > a.ui-slider-handle");

    // ==============================
    //  Acciones
    // ==============================
    public WebElement getSliderContainer() {
        return waitForVisibility(sliderContainer);
    }

    public WebElement getSliderHandler() {
        return waitForVisibility(sliderHandler);
    }

    // Método que devuelve el style inline del handle, por ejemplo "left: 0%;" o "left: 50%;"
    public String getSliderPositionStyle() {
        return getSliderHandler().getAttribute("style");
    }

    // Método que mueve el slider horizontalmente usando el helper genérico de BasePage
    public void moveSliderHorizontally(int xOffset) {
        dragAndDropByOffset(sliderHandler, xOffset, 0);
    }
}
