package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdvancedInteractionsPage extends BasePage {

    public AdvancedInteractionsPage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------
    //  Tooltip de consentimiento de la web
    // ------------------------------
    private By btn_doNotConsent = By.cssSelector("button.fc-cta-do-not-consent[aria-label='Do not consent']");

    // ------------------------------
    //  Drag & Drop demo
    // ------------------------------
    private By dragSource      = By.xpath("//img[@id='angular']");
    private By dropTarget      = By.xpath("//div[@id='droparea']");

    // ------------------------------
    //  Hover demo
    // ------------------------------
    private By menuHover       = By.xpath("(//a[@href='SwitchTo.html'])[2]");
    private By submenuTop      = By.linkText("Youtube");

    // ------------------------------
    //  Nueva ventana/pestaña demo
    // ------------------------------
    // Localiza el botón por su contenido de texto visible (ignorando espacios en blanco)
    private By btnNewWindow = By.xpath("//a/button[normalize-space()='click']");

    // ==============================
    //  Acciones
    // ==============================

    // --- Drag & Drop ---
    public void doDragAndDrop() {
        scrollToElement(dragSource);
        dragAndDrop(dragSource, dropTarget); // método de BasePage
    }

    // Método para comprobar si el elemento está dentro del área de drop
    public boolean isElementInsideDropArea() {
        WebElement target = waitForVisibility(dropTarget);
        // Buscamos dentro del dropTarget el elemento dragSource
        return !target.findElements(dragSource).isEmpty();
    }

    // --- Hover ---
    public void hoverMenuAndClickTop() {
        scrollToElement(menuHover);         // por si está abajo
        hover(menuHover);                   // método de BasePage
        click(submenuTop);                  // click en el submenú
    }

    public void clickDoNotConsent() { // Método creado para rechazar consentimiento de la web
        waitForClickable(btn_doNotConsent).click();
    }

    // --- Nueva ventana ---
    public void openNewWindowAndSwitch() {
        click(btnNewWindow);
        switchToWindow(1); // asumimos que 0 = pestaña original, 1 = nueva
    }

    public void switchToMainWindow() {
        switchToWindow(0);
    }
}
