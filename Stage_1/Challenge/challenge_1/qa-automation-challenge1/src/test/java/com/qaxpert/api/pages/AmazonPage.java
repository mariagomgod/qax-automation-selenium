package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonPage extends BasePage {

    private final By continueShoppingBtnLocator = By.cssSelector("button[alt='Continue shopping']");
    private final By continuarBtnLocator = By.xpath("//span[@class='a-button-text' and normalize-space()='CONTINUAR']");
    private final By ofertasDelDiaTabLocator = By.cssSelector("a[data-csa-c-content-id='nav_cs_gb']");
    private final By ofertasRelampagoFilterLocator =  By.cssSelector("#discount-bubble-deals-collection-lightning-deals button[data-testid='filter-bubble-deals-collection-lightning-deals']");
    private final By outletTabLocator =  By.xpath("//span[@class='nav-a-content' and normalize-space()='Outlet']");
    private final By juguetesYJuegosLinkLocator =  By.linkText("JUGUETES Y JUEGOS");
    private final By peluchesSubcategoryLocator =  By.xpath("//a[.//span[normalize-space()='Peluches']]");
    private final By monederosDeFelpaSubcategoryLocator =  By.xpath("//label[.//i[contains(@class,'a-icon-checkbox')]]");
    private final By compraJuguetesMasVendidosTabLocator =  By.xpath("//a[.//span[normalize-space()='Compra juguetes más vendidos']]");
    private final By juguetesMasVendidosTitleLocator =  By.cssSelector("h1.a-size-large.a-spacing-medium.a-text-bold");

    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShoppingBtnIfPresent() {

        // Usamos findElements para evitar excepciones si el botón no aparece.
        List<WebElement> elements = driver.findElements(continueShoppingBtnLocator);

        if (elements.isEmpty()) {
            // Si no aparece, continuamos
            return;
        }

        WebElement continueShoppingBtn = elements.getFirst();

        // Solo hacemos clic si está visible y habilitado, cumpliendo el "si aparece".
        if (continueShoppingBtn.isDisplayed() && continueShoppingBtn.isEnabled()) {
            continueShoppingBtn.click();
        }
    }

    public void clickEnContinuarBtn() {
        Actions actions = new Actions(driver);
        WebElement continuarBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continuarBtnLocator)
        );
        actions.moveToElement(continuarBtn).click().perform();
    }

    public void clickEnOfertasDelDiaTab() {
        WebElement ofertasDelDiaTab = wait.until(
                ExpectedConditions.elementToBeClickable(ofertasDelDiaTabLocator)
        );
        ofertasDelDiaTab.click();
    }

    public void selectOfertasRelampagoFilter() {
        WebElement ofertasRelampagoFilter = wait.until(
                ExpectedConditions.elementToBeClickable(ofertasRelampagoFilterLocator)
        );
        ofertasRelampagoFilter.click();
    }

    public void clickEnOutletTab() {
        WebElement outletTab = wait.until(
                ExpectedConditions.elementToBeClickable(outletTabLocator)
        );
        outletTab.click();
    }

    public void clickEnJuguetesYJuegosLink() {
        WebElement juguetesYJuegosLink = wait.until(
                ExpectedConditions.elementToBeClickable(juguetesYJuegosLinkLocator)
        );
        juguetesYJuegosLink.click();
    }

    public void clickEnPeluchesSubcategory() {
        WebElement peluchesSubcategory = wait.until(
                ExpectedConditions.elementToBeClickable(peluchesSubcategoryLocator)
        );
        peluchesSubcategory.click();
    }

    public void clickEnPrimeFilter() {
        WebElement iconoPrime = wait.until(
                ExpectedConditions.elementToBeClickable(monederosDeFelpaSubcategoryLocator)
        );
        iconoPrime.click();
    }

    public void clickEnMonederosDeFelpaSubcategory() {
        WebElement monederosDeFelpaSubcategory = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.//span[normalize-space()='Monederos de Felpa']]")
                )
        );
        monederosDeFelpaSubcategory.click();
    }

    public void clickEnCompraDeJuguetesMasVendidosTab() {
        WebElement compraJuguetesMasVendidosTab = wait.until(
                ExpectedConditions.elementToBeClickable(compraJuguetesMasVendidosTabLocator)
        );
        compraJuguetesMasVendidosTab.click();
    }

    public void ListadoJuguetesMasVendidosCargadoCorrectamente() {
        WebElement juguetesMasVendidosTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(juguetesMasVendidosTitleLocator)
        );

        String txtJuguetesMasVendidos = juguetesMasVendidosTitle.getText().trim();
        assertEquals("Los más vendidos en Juguetes y Juegos", txtJuguetesMasVendidos);
        System.out.println(juguetesMasVendidosTitle.getText());
    }
}
