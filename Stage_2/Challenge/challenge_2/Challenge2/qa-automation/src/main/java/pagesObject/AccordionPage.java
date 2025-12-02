package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AccordionPage extends BasePage {

    public AccordionPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    // Header de cada sección del accordion
    private By accordionHeaders = By.cssSelector(".panel-heading a");
    // Paneles de contenido
    private By accordionPanels  = By.cssSelector(".panel-collapse");

    // ==============================
    //  Acciones
    // ==============================


    // Devuelve la lista de headers
    public List<WebElement> getAccordionHeaders() {
        return driver.findElements(accordionHeaders);
    }

    // Devuelve la lista de paneles
    public List<WebElement> getAccordionPanels() {
        return driver.findElements(accordionPanels);
    }

    public int getNumberOfPanels() {
        return getAccordionHeaders().size();
    }

    // Devuelve el texto del panel index
    public String getPanelText(int index) {
        WebElement panel = getAccordionPanels().get(index);
        return panel.getText().trim();
    }

    public boolean isPanelExpanded(int index) {
        WebElement panel = getAccordionPanels().get(index);

        String classes = panel.getAttribute("class");
        String expanded = panel.getAttribute("aria-expanded");

        boolean byClass = classes != null && classes.contains("in");
        boolean byAria  = "true".equalsIgnoreCase(expanded);

        return byClass || byAria;
    }

    public void clickHeaderAndWait(int index) {
        WebElement header = getAccordionHeaders().get(index);

        // Hacemos scroll hasta el header para asegurarnos de que está en el centro
        jsClickWebElement(header);

        // Hacemos click con JavaScript para evitar el problema del iframe de publicidad
        jsScrollWebElement(header);

        WebElement panel = getAccordionPanels().get(index);
        // esperamos a que el panel se marque como expandido (clase "in")
        wait.until(ExpectedConditions.attributeContains(panel, "class", "in"));
    }
}
