package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.DynamicDataPage;

public class DynamicDataTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private DynamicDataPage dynamicDataPage;
    private static final String DynamicData_Url = "DynamicData.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + DynamicData_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        dynamicDataPage = new DynamicDataPage(driver);
    }

    @Test(priority = 1)
    public void testDynamicData () {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el área de contenido dinámico es visible
        Assert.assertTrue(
                dynamicDataPage.isDynamicAreaVisible(),
                "El área de contenido dinámico debería ser visible"
        );

        // Guardar el valor inicial del contenido dinámico (aunque esté vacío)
        String initialContent = dynamicDataPage.getDynamicContentTxt();

        // Verificar que el botón "Get dynamic data" es visible y clicar
        Assert.assertTrue(
                dynamicDataPage.getGetDynamicDataBtn().isDisplayed(),
                "El botón Get dynamic data debería ser visible"
        );
        dynamicDataPage.clickOnGetDynamicDataBtn();

        // Verificar que el contenido dinámico cambia con respecto al valor inicial y no está vacío
        dynamicDataPage.waitForDynamicContentToChange(initialContent);
        String newContent = dynamicDataPage.getDynamicContentTxt();

        Assert.assertNotEquals(
                newContent,
                initialContent,
                "El contenido dinámico debería haber cambiado respecto al valor inicial"
        );

        Assert.assertFalse(
                newContent.isEmpty(),
                "El nuevo contenido dinámico no debería estar vacío"
        );

        // Verificar que el nuevo contenido muestra imagen
        Assert.assertTrue(
                dynamicDataPage.hasDynamicImage(),
                "El contenido dinámico debería incluir una imagen de usuario"
        );

        // Verificar que el nuevo contenido incluye nombre y apellido
        Assert.assertTrue(
                newContent.contains("First Name"),
                "El contenido debería incluir First name"
        );

        Assert.assertTrue(
                newContent.contains("Last Name"),
                "El contenido debería incluir Last name"
        );
        System.out.println(newContent);
    }
}
