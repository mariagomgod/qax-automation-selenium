package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;
import pagesObject.ModalsPage;

public class ModalsTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private ModalsPage modalsPage;
    private static final String Modals_Url = "Modals.html";


    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        driver.get(baseURL + Modals_Url);
        advancedPage = new AdvancedInteractionsPage(driver);
        modalsPage = new ModalsPage(driver);
    }

    @Test(priority = 1)
    public void open_and_close_first_modal_blocking_background() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el botón Launch modal está visible y clic para abrir el modal principal
        modalsPage.getBootstrapModalBtn();
        Assert.assertTrue(modalsPage.getBootstrapModalBtn().isDisplayed(),
                "El botón Launch modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnLaunchBootstrapModalBtn();

        // Verificar que el modal está visible
        modalsPage.getModalTitle();
        Assert.assertTrue(modalsPage.isModalVisible(),
                "El modal debería estar visible"
        );

        // Verificar que el contenido de fondo queda bloqueado
        Assert.assertTrue(
                modalsPage.isBackgroundBlocked(),
                "El contenido de fondo debería estar bloqueado"
        );

        // Verificar que el botón Close dentro del contenido del modal está visible y cerrarlo
        modalsPage.getCloseModalBtnInsideModal();
        Assert.assertTrue(modalsPage.getCloseModalBtnInsideModal().isDisplayed(),
                "El botón Close dentro del contenido del modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnCloseModalBtn();

        // Verificar que el modal deja de ser visible
        modalsPage.waitForModalToDisappear();
        Assert.assertFalse(
                modalsPage.isModalVisible(),
                "El modal debería dejar de ser visible"
        );

        // Verificar que  el contenido de fondo vuelve a estar disponible
        Assert.assertFalse(
                modalsPage.isBackgroundBlocked(),
                "El contenido de fondo debería volver a estar disponible"
        );
    }

    @Test(priority = 2)
    public void interact_with_first_modal_content_and_confirm_action() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el botón Launch modal está visible y clic para abrir el modal principal
        modalsPage.getBootstrapModalBtn();
        Assert.assertTrue(modalsPage.getBootstrapModalBtn().isDisplayed(),
                "El botón Launch modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnLaunchBootstrapModalBtn();

        // Verificar que el modal está visible
        modalsPage.getModalTitle();
        Assert.assertTrue(modalsPage.isModalVisible(),
                "El modal debería estar visible"
        );

        // Verificar que el botón "Save changes" es visible y clicarlo
        modalsPage.getSaveChangesModalBtn();
        Assert.assertTrue(modalsPage.getSaveChangesModalBtn().isDisplayed(),
                "El botón Save changes debería estar visible antes de hacer click"
        );
        modalsPage.clickOnSaveChangesModalBtn();

        // Verificar que el modal deja de ser visible
        modalsPage.waitForModalToDisappear();
        Assert.assertFalse(
                modalsPage.isModalVisible(),
                "El modal debería dejar de ser visible"
        );

        // Verificar el contenido de fondo vuelve a estar disponible
        Assert.assertFalse(
                modalsPage.isBackgroundBlocked(),
                "El contenido de fondo debería volver a estar disponible"
        );
    }

    @Test(priority = 3)
    public void cancel_or_close_multiple_modals() {
        // Cerramos el tooltip de consentimiento
        advancedPage.clickDoNotConsent();

        // Verificar que el botón Launch modal está visible y clicar para abrir el modal principal
        modalsPage.getMultipleModalsBtn();
        Assert.assertTrue(modalsPage.getMultipleModalsBtn().isDisplayed(),
                "El botón Launch modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnMultipleModalsBtn();

        //Verificar que el primer modal está visible
        modalsPage.getFirstModalTitle();
        Assert.assertTrue(modalsPage.isFirstModalVisible(),
                "El primer modal debería estar visible"
        );

        // Verificar que el botón de Launch modal dentro del primer modal está visible y clicar
        modalsPage.getLaunchFirstModalsBtn();
        Assert.assertTrue(modalsPage.getLaunchFirstModalsBtn().isDisplayed(),
                "El botón Launch modal dentro del primer modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnLaunchFirstModalBtn();

        // Verificar que un segundo modal está visible
        modalsPage.getSecondModalTitle();
        Assert.assertTrue(modalsPage.isSecondModalVisible(),
                "El segundo modal debería estar visible"
        );

        // Verificar que el link Close dentro del segundo modal está visible y clicar
        modalsPage.getSecondModalLink();
        Assert.assertTrue(modalsPage.getSecondModalLink().isDisplayed(),
                "El link Close dentro del segundo modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnSecondModalLink();

        // Verificar que el segundo modal deja de ser visible
        modalsPage.waitForSecondModalToDisappear();
        Assert.assertFalse(
                modalsPage.isSecondModalVisible(),
                "El segundo modal debería dejar de ser visible"
        );

        // Verificar que el primer modal está visible
        modalsPage.getFirstModalTitle();
        Assert.assertTrue(modalsPage.isFirstModalVisible(),
                "El primer modal debería estar visible"
        );

        // Verificar que el link Close del primer modal está visible y clicar
        modalsPage.getFirstModalLink();
        Assert.assertTrue(modalsPage.getFirstModalLink().isDisplayed(),
                "El link Close dentro del primer modal debería estar visible antes de hacer click"
        );
        modalsPage.clickOnFirstModalLink();

        // Verificar que el primer modal deja de ser visible
        modalsPage.waitForFirstModalToDisappear();
        Assert.assertFalse(
                modalsPage.isFirstModalVisible(),
                "El primer modal debería dejar de ser visible"
        );

        // Verificar que el contenido de fondo vuelve a estar disponible
        Assert.assertFalse(
                modalsPage.isBackgroundBlocked(),
                "El contenido de fondo debería volver a estar disponible"
        );
    }
}
