package features;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.*;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.AlertsPage;

public class AlertsTest extends BaseTest {

    // URL específica de la página de alertas (la pagina)
    private static final String ALERT_URL = "Alerts.html";
    private AdvancedInteractionsPage advancedPage;
    private AlertsPage alertsPage;

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        // Leer la URL base de la consola (o usar el valor por defecto)
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        // Navegar a la URL completa (BaseURL + Pagina)
        driver.get(baseURL + ALERT_URL);
        // Inicializar el Page Object, usando el driver ya inicializado por BaseTest
        alertsPage = new AlertsPage(driver);
        // Inicializar el Page Object, usando el driver ya inicializado por BaseTest
        advancedPage = new AdvancedInteractionsPage(driver);
    }


    // ========================================
    //          TEST: ALERT SIMPLE (OK)
    // ========================================
    @Test(priority = 1)
    public void testSimpleAlert() {
        // Cerrar el tooltip de consentimiento
        advancedPage.clickDoNotConsent();
        alertsPage.openSimpleAlertTab();
        alertsPage.clickSimpleAlertButton();

        Alert alert = alertsPage.getAlert();// heredado de BasePage
        System.out.println("El texto del popup:" + alert.getText());
        Assert.assertEquals(alert.getText(), "I am an alert box!");
        alert.accept();
    }

    // ========================================
    //        TEST: ALERT OK / CANCEL
    // ========================================
    @Test(priority = 2)
    public void testConfirmAlert() {
        // Cerrar el tooltip de consentimiento
        advancedPage.clickDoNotConsent();
        // cambiar al tab correcto
        alertsPage.openConfirmAlertTab();

        // hacer clic en el botón del confirm alert
        alertsPage.clickConfirmAlertButton();
        Alert alert = alertsPage.getAlert();
        alert.dismiss(); // Presionar "Cancel"
        String message = alertsPage.getConfirmMessage();
        System.out.println("El Mensaje de confirmacion es:" + message);
        Assert.assertEquals(message, "You Pressed Cancel");
    }

    // ========================================
    //        TEST: ALERT TIPO PROMPT
    // ========================================
    @Test(priority = 3)
    public void testPromptAlert() {
        // Cerrar el tooltip de consentimiento
        advancedPage.clickDoNotConsent();
        alertsPage.openPromptAlertTab();
        alertsPage.clickPromptAlertButton();

        Alert alert = alertsPage.getAlert();
        alert.sendKeys("Texto de Prueba");
        alert.accept();

        String response = alertsPage.getPromptMessage();
        System.out.println("La respueta del prompt es: " + response);
        Assert.assertTrue(response.contains("Hello Texto de Prueba How are you today"));
    }


}
