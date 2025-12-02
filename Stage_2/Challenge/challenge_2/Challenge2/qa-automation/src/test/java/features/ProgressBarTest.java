package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.ProgressBarPage;

public class ProgressBarTest extends BaseTest{

    private AdvancedInteractionsPage advancedPage;
    private ProgressBarPage progressBarPage;
    private static final String ProgressBar_Url = "ProgressBar.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + ProgressBar_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        progressBarPage = new ProgressBarPage(driver);

    }

    @Test(priority = 1)
    public void testProgressBar() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que la barra de progreso está visible y no iniciada
        Assert.assertTrue(
                progressBarPage.isProgressNotStarted(),
                "La barra de progreso no debería mostrar nada antes de iniciarse"
        );

        // Verificar que botón Download está visible y clicar para iniciar la barra de progreso
        progressBarPage.getDownloadBtn();
        Assert.assertTrue(
                progressBarPage.getDownloadBtn().isDisplayed(),
                "El botón Download debería estar visible"
        );
        progressBarPage.clickOnDownloadBtn();

        // Verificar que la barra de progreso llega al 100%
        progressBarPage.waitForProgressToReachHundred();
        Assert.assertEquals(
                progressBarPage.getProgressValue(),
                100,
                "La barra de progreso debería haber llegado a 100"
        );

        // Mostrar y verificar el estado final esperado
        Assert.assertTrue(
                progressBarPage.isFinalState(),
                "El estado final esperado (100) no se ha alcanzado"
        );
    }
}
