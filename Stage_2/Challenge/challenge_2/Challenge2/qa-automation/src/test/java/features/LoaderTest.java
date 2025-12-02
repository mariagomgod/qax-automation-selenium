package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.LoaderPage;

public class LoaderTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private LoaderPage loaderPage;
    private static final String Loader_Url = "Loader.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Loader_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        loaderPage = new LoaderPage(driver);
    }

    @Test(priority = 1)
    public void testSlider() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el botón "Run" está habilitado y clicar
        Assert.assertTrue(
                loaderPage.isRunBtnEnabled(),
                "El botón Run debería estar habilitado"
        );
        loaderPage.clickOnRunBtn();

        // Verificar que se muestra un loader indicando que hay una operación en progreso
        loaderPage.waitForLoaderToAppear();
        Assert.assertTrue(
                loaderPage.isLoaderVisible(),
                "El texto del loader 'Please wait...' debería mostrarse mientras la carga está en progreso"
        );

        // Verificar que al completarse la carga el loader desaparece y la página vuelve a ser interactuable
        loaderPage.waitForFinalContentToBeVisible();
        Assert.assertTrue(
                loaderPage.isFinalContentVisible(),
                "El contenido final debería estar visible tras la carga"
        );

        loaderPage.clickOnCloseBtn();

        Assert.assertTrue(
                loaderPage.isRunBtnEnabled(),
                "Tras cerrar el modal, la página debería volver a ser interactuable"
        );
    }
}
