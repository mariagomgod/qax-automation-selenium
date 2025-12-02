package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AccordionPage;
import pagesObject.AdvancedInteractionsPage;

public class AccordionTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private AccordionPage accordionPage;
    private static final String Accordion_Url = "Accordion.html";


    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Accordion_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        accordionPage = new AccordionPage(driver);
    }

    @Test(priority = 1)
    public void testAccordion() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificamos número total de paneles
        int totalPanels = accordionPage.getNumberOfPanels();
        System.out.println("El número total de paneles son: " + totalPanels);
        Assert.assertTrue(
                totalPanels > 0,
                "No se encontraron paneles del accordion"
        );

        // Verificamos que el primer panel (índice 0) está expandido por defecto
        Assert.assertTrue(
                accordionPage.isPanelExpanded(0),
                "El primer panel debería estar expandido al cargar la página"
        );

        // Verifico que el contenido del primer panel no está vacío
        String txt = accordionPage.getPanelText(0);
        Assert.assertFalse(
                txt.isEmpty(),
                "El contenido del primer panel no debería estar vacío"
        );

        // Verificamos que el resto no está expandido al inicio
        // Empezamos por íncide 1 ya que el 0 es el que está expadido por defecto
        for (int j = 1; j < totalPanels; j++) {
            Assert.assertFalse(
                    accordionPage.isPanelExpanded(j),
                    "El panel " + j + " debería estar colapsado al inicio"
            );
        }

        // Para cada header/panel desde el panel 1 en adelante
        for (int i = 1; i < totalPanels; i++) {

            // Hago clic en el header i
            accordionPage.clickHeaderAndWait(i);

            // Su panel está expandido
            Assert.assertTrue(
                    accordionPage.isPanelExpanded(i),
                    "El panel " + i + " debería estar expandido tras hacer clic en su header"
            );

            // Verifico que el contenido del panel i no está vacío
            String panelTxt = accordionPage.getPanelText(i);
            Assert.assertFalse(
                    panelTxt.isEmpty(),
                    "El panel " + i + " no debería estar vacío"
            );
        }
    }
}
