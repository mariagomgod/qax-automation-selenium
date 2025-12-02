package features;

import org.testng.Assert;
import org.testng.annotations.*;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.IframePage;

public class IframeTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private IframePage iframePage;
    private static final String FRAME_URL = "Frames.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + FRAME_URL);
        advancedPage = new AdvancedInteractionsPage(driver);
        iframePage = new IframePage(driver);
    }

    // ========================================
    //   TEST 1: Iframe simple (singleframe)
    // ========================================
    @Test(priority = 1)
    public void testWriteInsideSingleIframe() {
        // Cerrar el tooltip de consentimiento
        advancedPage.clickDoNotConsent();
        iframePage.writeInIframeInput("Texto Anidado");

        String value = iframePage.getSingleIframeInputValue();
        System.out.println("Texto dentro del iframe: " + value);
        Assert.assertEquals(
                value,
                "Texto Anidado",
                "El campo de texto dentro del iframe simple no contiene el valor esperado"
        );
    }

    // ========================================
    //   TEST 2: Iframe padre + hijo (nested)
    // ========================================
    @Test(priority = 2)
    public void testWriteInsideNestedIframe() {
        // Cerrar el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Abro la pestaña "Iframe with in an Iframe"
        iframePage.openNestedIframeTab();

        // Cambio el foco al iFrame padre + hijo y escribo en el hijo
        iframePage.writeInChildIframeInput("Texto Anidado");

        // Verifico que el campo de texto dentro del iFrame hijo debe contener el valor "Texto Anidado"
        String childValue = iframePage.getChildIframeInputValue();
        System.out.println("Texto dentro del iframe: " + childValue);
        Assert.assertEquals(
                childValue,
                "Texto Anidado",
                "El campo de texto dentro del iframe hijo no contiene el valor esperado"
        );

        // Regreso desde el iFrame hijo al iFrame padre y escribo el texto "Texto Padre" en un campo de texto dentro del iFrame padre
        iframePage.writeInParentIframeInput("Texto Padre");

        // Verifico que el campo de texto dentro del iFrame padre debe contener el valor "Texto Padre"
        String childValueAfter = iframePage.getChildIframeInputValue();
        System.out.println("Texto dentro del iframe: " + childValueAfter);
        Assert.assertEquals(
                childValueAfter,
                "Texto Padre",
                "El campo de texto dentro del iframe hijo no se ha actualizado con 'Texto Padre'"
        );

        // Verifico que puedo volver al contexto principal (título header)
        Assert.assertTrue(
                iframePage.isHeaderOutsideFramesVisible(),
                "El encabezado fuera de los iframes no es visible; el foco no ha vuelto al contexto principal"
        );
    }
}
