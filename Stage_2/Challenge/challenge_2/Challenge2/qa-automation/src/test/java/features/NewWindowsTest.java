package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;

import java.util.Set;

public class NewWindowsTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private static final String Windows_Url = "Windows.html";


    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Windows_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
    }


    @Test(priority = 1)
    public void testNewWindow() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Guardamos el título de la ventana principal antes de abrir nada
        String mainWindowTitleBefore = driver.getTitle();
        System.out.println("Título ventana principal (antes): " + mainWindowTitleBefore);

        // Cambia de URL si el botón de nueva ventana está en otra página
        advancedPage.openNewWindowAndSwitch();
        System.out.println("Título nueva ventana: " + driver.getTitle());
        // Verificamos el título de la nueva ventana
        String newWindowTitle = driver.getTitle();
        Assert.assertEquals(
                newWindowTitle,
                "Selenium",
                "El título de la nueva ventana no es el esperado"
        );
        // Verificamos que el título de la nueva ventana es distinto al de la principal
        Assert.assertNotEquals(
                newWindowTitle,
                mainWindowTitleBefore,
                "La nueva ventana tiene el mismo título que la principal, asegúrate haber cambiado de ventana"
        );

        // Regresar a la ventana principal
        advancedPage.switchToMainWindow();

        String mainWindowTitleAfter = driver.getTitle();
        System.out.println("Regresando a la ventana principal: " + mainWindowTitleAfter);
        // Verificames que al volver, el título es el de la ventana principal
        Assert.assertEquals(
                mainWindowTitleAfter,
                mainWindowTitleBefore,
                "Al volver a la ventana principal, el título no coincide con el esperado"
        );
    }

    @Test(priority = 2)
    public void separateWindowOpeningAndClosing() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Guardamos la ventana actual
        String ventanaPrincipal = driver.getWindowHandle();
        String mainWindowTitleBefore = driver.getTitle();
        System.out.println("Título ventana principal (antes): " + mainWindowTitleBefore);

        // Abrimos nueva pestaña y hacemos switch a la nueva
        advancedPage.openNewWindowAndSwitch();

        String newWindowTitle = driver.getTitle();
        System.out.println("Título nueva ventana: " + newWindowTitle);

        Assert.assertEquals(
                newWindowTitle,
                "Selenium",
                "El título de la nueva ventana no es el esperado"
        );

        Assert.assertNotEquals(
                newWindowTitle,
                mainWindowTitleBefore,
                "La nueva ventana tiene el mismo título que la principal"
        );

        // Obtenemos todas las ventanas abiertas
        Set<String> ventanas = driver.getWindowHandles();

        for (String ventana : ventanas) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                System.out.println("Cambiando a nueva pestaña: " + driver.getTitle());
                driver.close(); // Cierra la nueva pestaña
            }
        }

        // Regresamos a la ventana principal
        driver.switchTo().window(ventanaPrincipal);
        String mainWindowTitleAfter = driver.getTitle();
        System.out.println("Regresando a la ventana principal: " + mainWindowTitleAfter);
        Assert.assertEquals(
                mainWindowTitleAfter,
                mainWindowTitleBefore,
                "Al volver a la ventana principal, el título no coincide con el esperado"
        );
    }
}
