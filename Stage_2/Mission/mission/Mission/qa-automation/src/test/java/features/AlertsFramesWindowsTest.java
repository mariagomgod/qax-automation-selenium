package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AlertsFramesWindowsPage;

public class AlertsFramesWindowsTest extends BaseTest {

    private AlertsFramesWindowsPage alertsFramesWindowsPage;
    private String baseURL;

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        alertsFramesWindowsPage = new AlertsFramesWindowsPage(driver);
    }

    @Test(priority = 1)
    public void browser_windows_open_new_tab_and_return_to_main_handle() {
        // Navegar directamente a la página de Browser Windows
        driver.get(baseURL + "browser-windows");

        String mainUrlBefore = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL principal (antes): " + mainUrlBefore);

        // Abrir nueva pestaña y hacer switch
        alertsFramesWindowsPage.openNewTabAndSwitch();

        String newTabUrl = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL nueva pestaña: " + newTabUrl);

        // Verificar que la nueva pestaña tiene URL distinta
        Assert.assertNotEquals(
                newTabUrl,
                mainUrlBefore,
                "La nueva pestaña tiene la misma URL que la principal"
        );

        // Verificar el contenido de la pestaña nueva
        String heading = alertsFramesWindowsPage.getSampleHeadingTxt();
        Assert.assertTrue(
                heading.contains("This is a sample page"),
                "El texto de la nueva pestaña no es el esperado"
        );

        // Cerrar la nueva pestaña
        alertsFramesWindowsPage.closeCurrentTab();

        // Volver a la ventana principal
        alertsFramesWindowsPage.switchToMainWindow();

        String mainUrlAfter = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL al volver a la principal: " + mainUrlAfter);

        // Verificar que volvemos a la misma página principal
        Assert.assertTrue(
                mainUrlAfter.equals(mainUrlBefore) || mainUrlAfter.startsWith(mainUrlBefore + "#"),
                "Al volver a la ventana principal, la URL no coincide con la inicial"
        );
    }

    @Test(priority = 2)
    public void browser_windows_open_new_window_close_and_return_to_main_handle() {
        // Navegar directamente a la página de Browser Windows
        driver.get(baseURL + "browser-windows");

        String mainUrlBefore = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL principal (antes): " + mainUrlBefore);

        // Abrir nueva pestaña y hacer switch
        alertsFramesWindowsPage.openNewWindowAndSwitch();

        String newTabUrl = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL nueva pestaña: " + newTabUrl);

        // Verificar que la nueva pestaña tiene URL distinta
        Assert.assertNotEquals(
                newTabUrl,
                mainUrlBefore,
                "La nueva pestaña tiene la misma URL que la principal"
        );

        // Verificar el contenido de la pestaña nueva
        String heading = alertsFramesWindowsPage.getSampleHeadingTxt();
        Assert.assertTrue(
                heading.contains("This is a sample page"),
                "El texto de la nueva pestaña no es el esperado"
        );

        // Cerrar la nueva pestaña
        alertsFramesWindowsPage.closeCurrentWindow();

        // Volver a la ventana principal
        alertsFramesWindowsPage.switchToMainWindow();

        String mainUrlAfter = alertsFramesWindowsPage.getCurrentPageUrl();
        System.out.println("URL al volver a la principal: " + mainUrlAfter);

        // Verificar que volvemos a la misma página principal
        Assert.assertTrue(
                mainUrlAfter.equals(mainUrlBefore) || mainUrlAfter.startsWith(mainUrlBefore + "#"),
                "Al volver a la ventana principal, la URL no coincide con la inicial"
        );
    }

    @Test(priority = 3)
    public void simple_alert_accepted_withouth_result_message() {
        // Navegar directamente a la página de Alerts
        driver.get(baseURL + "alerts");

        // Clicar la alerta simple
        alertsFramesWindowsPage.clickOnSimpleAlertBtn();

        // Aceptar la alerta
        alertsFramesWindowsPage.acceptSimpleAlert();

        // Verificar que no se muestra ningún mensaje de resultado en la página
        Assert.assertFalse(
                alertsFramesWindowsPage.isAlertResultVisible(),
                "Se ha mostrado un mensaje de resultado y no debería para la alerta simple"
        );
    }

    @Test(priority = 4)
    public void alert_prompt_displays_the_entered_text() {
        // Navegar directamente a la página de Alerts
        driver.get(baseURL + "alerts");

        // Dato generado de la clase TestDataFactory
        String txt = TestDataFactory.randomFirstName();

        // Disparar la alerta de prompt
        alertsFramesWindowsPage.clickOnPromptAlertBtn();

        // Escribir el texto en la alerta
        alertsFramesWindowsPage.typeInPromptAlert(txt);

        // Aceptar la alerta
        alertsFramesWindowsPage.acceptPromptAlert();

        // Verificar que el mensaje de resultado en la página contiene el texto ingresado
        String result = alertsFramesWindowsPage.getPromptResultTxt();
        Assert.assertTrue(
                result.contains(txt),
                "El resultado del prompt no contiene el texto ingresado: " + txt
        );
        System.out.println("El texto ingresado es: " + txt);
    }

    @Test(priority = 5)
    public void switch_to_frame_by_id_and_return_to_main_content() {
        // Navegar directamente a la página de Frames
        driver.get(baseURL + "frames");

        // Validar que la página principal de Frames está visible
        Assert.assertTrue(
                alertsFramesWindowsPage.isFramesMainPageVisible(),
                "La página principal de Frames no está visible"
        );

        // Cambiar el foco al iFrame identificado por ID o nombre
        alertsFramesWindowsPage.switchToSampleFrame();

        // Verificar que puedo obtener el texto de un elemento dentro del iFrame
        String txtInsideFrame = alertsFramesWindowsPage.getTxtInsideFrame();

        Assert.assertTrue(
                txtInsideFrame.contains("This is a sample page"),
                "El texto dentro del iFrame no es el esperado"
        );

        // Verficar que el foco regresa al contenido principal es accesible
        alertsFramesWindowsPage.switchBackToMainContent();

        Assert.assertTrue(
                alertsFramesWindowsPage.isFramesMainPageVisible(),
                "Tras volver al contenido principal, la página de Frames no es accesible"
        );
    }

    @Test(priority = 6)
    public void sequential_navigation_in_parent_and_child_iframes_and_return_to_main_content() {
        // Navegar directamente a la página de Frames
        driver.get(baseURL + "nestedframes");

        // Validar que la página principal de Nested Frames está visible
        Assert.assertTrue(alertsFramesWindowsPage.isNestedFramesMainPageVisible(),
                "La página principal de Nested Frames no está visible"
        );

        // Cambiar el foco al iFrame padre
        alertsFramesWindowsPage.switchToParentNestedFrame();

        // Cambiar el foco desde el padre al iFrame hijo
        alertsFramesWindowsPage.switchToChildNestedFrame();

        // Verificar el texto de un elemento dentro del iFrame hijo es correcto
        String childFrameTxt = alertsFramesWindowsPage.getChildFrameTxt();

        Assert.assertTrue(
                childFrameTxt.contains("Child Iframe"),
                "El texto dentro del iFrame no es el esperado"
        );

        // Regresar el foco al contenido principal
        alertsFramesWindowsPage.returnFromChildFrameToMainContent();

        // Verificar que un elemento fuera de los iFrames es accesible
        Assert.assertTrue(
                alertsFramesWindowsPage.isNestedFramesMainPageVisible(),
                "Tras volver al contenido principal, la página de Nested Frames no es accesible"
        );
    }

    @Test(priority = 7)
    public void opening_modal_and_validating_visibility_and_header() {
        // Navegar directamente a la página de Modal Dialogs
        driver.get(baseURL + "modal-dialogs");

        // Verificar que la página principal de Modal Dialogs está visible
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalDialogsMainPageVisible(),
                "La página de Modal Dialogs no está visible"
        );

        // Clicar en el botón Small Modal para abrir la ventana modal
        alertsFramesWindowsPage.clickOnSmallModalBtn();

        // Verificar que la ventana modal es visible
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalVisible(),
                "La página de Modal Dialogs no está visible"
        );

        // Verificar que el fondo de la página principal no es interactuable
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalBackdropVisible(),
                "El fondo no está visible, la página principal podría seguir interactuable"
        );

        // Verificar que el texto del encabezado de la modal es el esperado
        String modalTitle = alertsFramesWindowsPage.getModalTitleTxt();

        Assert.assertTrue(
                modalTitle.contains("Small Modal"),
                "El título de la modal no es el esperado. Valor actual: " + modalTitle
        );
    }

    @Test(priority = 8)
    public void close_modal_and_return_interactivity_to_the_main_page() {
        // Navegar directamente a la página de Modal Dialogs
        driver.get(baseURL + "modal-dialogs");

        // Verificar que la página principal de Modal Dialogs está visible
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalDialogsMainPageVisible(),
                "La página de Modal Dialogs no está visible"
        );

        // Verificar que la modal está abierta
        alertsFramesWindowsPage.clickOnSmallModalBtn();
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalVisible(),
                "La modal debería estar visible antes de intentar cerrarla"
        );

        // Clicar en el botón de cierre de la modal
        alertsFramesWindowsPage.closeSmallModalBtn();

        // Verificar que la ventana modal desaparece del DOM o es invisible
        alertsFramesWindowsPage.waitUntilModalIsClosed();
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalClosed(),
                "La modal sigue presente en el DOM o visible tras intentar cerrarla"
        );

        // Verificar que la página principal vuelve a ser interactuable
        Assert.assertTrue(
                alertsFramesWindowsPage.isModalDialogsMainPageVisible(),
                "Tras cerrar la modal, la página de Modal Dialogs no es interactuable"
        );
    }
}
