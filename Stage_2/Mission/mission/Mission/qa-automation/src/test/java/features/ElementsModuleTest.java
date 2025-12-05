package features;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.ElementsModulePage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class ElementsModuleTest extends BaseTest {

    private ElementsModulePage elementsModulePage;
    private String baseURL;

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        elementsModulePage = new ElementsModulePage(driver);
    }

    @Test(priority = 1)
    public void text_box_interaction_and_shipping() {
        // Navegar directamente a la página de Text Box
        driver.get(baseURL + "text-box");

        // Generar datos aleatorios de TestDataFactory
        String fullName = TestDataFactory.randomFirstName() + " " + TestDataFactory.randomLastName();
        String email = TestDataFactory.randomSafeEmail();
        String currentAddress = TestDataFactory.randomStreetAddress();
        String permanentAddress = TestDataFactory.randomParagraph();

        // Rellenar y enviar formulario
        elementsModulePage.fillFormAndClick(fullName, email, currentAddress, permanentAddress);

        // Recuperar datos del output para validar que los datos ingresados se muestran correctamente en la sección de resultado
        String nameOutput = elementsModulePage.getOutputName();
        Assert.assertTrue(nameOutput.contains(fullName),
                "El nombre mostrado no contiene el valor esperado. Output: " + nameOutput);

        String emailOutput = elementsModulePage.getOutputEmail();
        Assert.assertTrue(emailOutput.contains(email),
                "El email mostrado no contiene el valor esperado. Output: " + emailOutput);

        String currentAddressOutput = elementsModulePage.getOutputCurrentAddress();
        Assert.assertTrue(currentAddressOutput.contains(currentAddress),
                "La dirección actual mostrada no contiene el valor esperado. Output: " + currentAddressOutput);

        String permanentAddressOutput = elementsModulePage.getOutputPermanentAddress();
        Assert.assertTrue(permanentAddressOutput.contains(permanentAddress),
                "La dirección permanente mostrada no contiene el valor esperado. Output: " + permanentAddressOutput);
    }

    @Test(priority = 2)
    public void checkbox_hierarchical_selection() {
        // Navegar directamente a la página de Checkbox
        driver.get(baseURL + "checkbox");

        // Seleccionar el checkbox Home y verificar que los elementos hijos quedan seleccionados
        elementsModulePage.expandAllTree(); // Para poder clicar los hijos (Desktop, Documents, Downloads)
        elementsModulePage.selectHome();
        Assert.assertTrue(
                elementsModulePage.areHomeChildrenSelected(),
                "No se han seleccionado correctamente los hijos de Home (Desktop, Documents, Downloads)"
        );

        // Desmarcar Home, marcar Desktop y Downloads y verificar que Home aparece seleccionada parcialmente
        elementsModulePage.deselectHome();
        elementsModulePage.selectDesktopAndDownloads();
        Assert.assertTrue(
                elementsModulePage.isHomePartiallySelected(),
                "Home no aparece seleccionada parcialmente tras marcar Desktop y Downloads"
        );
    }

    @Test(priority = 3)
    public void radio_button_exclusive_selection() {
        // Navegar directamente a la página de Radio Button
        driver.get(baseURL + "radio-button");

        // Seleccionar la opción "Yes"
        elementsModulePage.selectYesOption();

        // Verificar que se muestra el mensaje "You have selected Yes"
        String yesMsg = elementsModulePage.getRadioResultText();
        Assert.assertEquals(
                yesMsg,
                "You have selected Yes",
                "El mensaje para la opción Yes no es el esperado"
        );

        // Seleccionar la opción "Impressive"
        elementsModulePage.selectImpressiveOption();

        // Verificar que se muestra el mensaje "You have selected Impressive"
        String impressiveMsg = elementsModulePage.getRadioResultText();
        Assert.assertEquals(
                impressiveMsg,
                "You have selected Impressive",
                "El mensaje para la opción Impressive no es el esperado"
        );

        // Verificar que la opción "No" está deshabilitada y no se puede seleccionar
        Assert.assertTrue(
                elementsModulePage.isNoOptionDisabled(),
                "La opción 'No' debería estar deshabilitada"
        );
    }

    @Test(priority = 4)
    public void web_tables_registration_and_editing_form() {
        // Navegar directamente a la página de Web Tables
        driver.get(baseURL + "webtables");

        // Generar datos aleatorios de TestDataFactory
        String firstName  = TestDataFactory.randomFirstName();
        String lastName   = TestDataFactory.randomLastName();
        String email      = TestDataFactory.randomSafeEmail();
        String age        = String.valueOf(TestDataFactory.randomInt(18, 60));
        String salary     = String.valueOf(TestDataFactory.randomInt(20000, 80000));
        String department = TestDataFactory.randomJobTitle();
        String newSalary  = String.valueOf(TestDataFactory.randomInt(30000, 90000));

        // Agregar un nuevo registro con datos válidos
        elementsModulePage.fillWebTableFormAndSubmit(firstName, lastName, email, age, salary, department);

        // Verificar que el nuevo registro aparece en la tabla
        Assert.assertTrue(
                elementsModulePage.isRecordPresentByEmail(email),
                "El nuevo registro no aparece en la tabla tras ser creado"
        );

        // Editar ese registro
        elementsModulePage.editSalaryByEmail(email, newSalary);

        // Verificar que los cambios se reflejan en la tabla
        Assert.assertTrue(
                elementsModulePage.rowHasSalaryForEmail(email, newSalary),
                "El salario no se ha actualizado correctamente para el registro con email: " + email
        );

        // Eliminar ese registro
        elementsModulePage.deleteRecordByEmail(email);

        // Verificar que el registro deja de aparecer en la tabla
        Assert.assertFalse(
                elementsModulePage.isRecordPresentByEmail(email),
                "El registro no se ha eliminado correctamente de la tabla"
        );
    }

    @Test(priority = 5)
    public void buttons_click_actions() {
        // Navegar directamente a la página de Buttons
        driver.get(baseURL + "buttons");

        // Hacer doble clic en el botón de double click
        elementsModulePage.performDoubleClick();

        // Verificar que se muestra el mensaje "You have done a double click"
        String doubleClickMsg = elementsModulePage.getDoubleClickResult();
        Assert.assertEquals(
                doubleClickMsg,
                "You have done a double click",
                "El mensaje tras el doble clic no es el esperado"
        );
        System.out.println("El mensaje de double click: " + doubleClickMsg);

        // Hacer clic derecho en el botón de right click
        elementsModulePage.performRightClick();

        // Verificar se muestra el mensaje "You have done a right click"
        String rightClickMsg = elementsModulePage.getRightClickResult();
        Assert.assertEquals(
                rightClickMsg,
                "You have done a right click",
                "El mensaje tras el clic derecho no es el esperado"
        );
        System.out.println("El mensaje de right click: " + rightClickMsg);

        // Hacer clic simple en el botón de dynamic click
        elementsModulePage.performDynamicClick();

        // Verificar se muestra el mensaje "You have done a dynamic click"
        String dynamicClickMsg = elementsModulePage.getDynamicClickResult();
        Assert.assertEquals(
                dynamicClickMsg,
                "You have done a dynamic click",
                "El mensaje tras el clic simple dinámico no es el esperado"
        );
        System.out.println("El mensaje de dynamic click: " + dynamicClickMsg);
    }

    @Test(priority = 6)
    public void links_navigation() {
        // Navegar directamente a la página de Links
        driver.get(baseURL + "links");

        // Guardamos cuántas pestañas había antes
        int initialWindows = driver.getWindowHandles().size();

        // Clicar en el enlace Home y se abre una nueva pestaña
        elementsModulePage.clickHomeLinkAndWaitForNewTab();

        // Comprobamos que ahora hay una pestaña más
        int currentWindows = driver.getWindowHandles().size();
        Assert.assertEquals(
                currentWindows,
                initialWindows + 1,
                "No se ha abierto una nueva pestaña al hacer clic en el enlace Home"
        );

        // Cambiamos a la nueva pestaña (índice 1)
        elementsModulePage.goToTab(1);
        String newTabUrl = elementsModulePage.getCurrentPageUrl();

        // Verificar que la nueva pestaña tiene una URL válida (esperada)
        Assert.assertTrue(
                newTabUrl.contains("demoqa.com"),
                "La URL de la nueva pestaña no parece pertenecer a demoqa.com: " + newTabUrl
        );

        // Regresar el foco a la pestaña original
        elementsModulePage.goToTab(0);
    }

    @Test(priority = 7)
    public void links_api_calls() {
        // Navegar directamente a la página de Links
        driver.get(baseURL + "links");

        // Guardamos la Url antes del click
        String originalUrl = elementsModulePage.getCurrentPageUrl();

        // Created
        elementsModulePage.clickCreatedLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(), // Url después del click
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Created'"
        );

        // No Content
        elementsModulePage.clickNoContentLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'No Content'"
        );

        // Moved
        elementsModulePage.clickMovedLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Moved'"
        );

        // Bad Request
        elementsModulePage.clickBadRequestLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Bad Request'"
        );

        // Unauthorized
        elementsModulePage.clickUnauthorizedLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Unauthorized'"
        );

        // Forbidden
        elementsModulePage.clickForbiddenLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Forbidden'"
        );

        // Invalid URL
        elementsModulePage.clickInvalidUrlLink();
        Assert.assertEquals(
                elementsModulePage.getCurrentPageUrl(),
                originalUrl,
                "La URL no debería cambiar tras hacer clic en 'Invalid URL'"
        );
    }

    @Test(priority = 8)
    public void broken_links_images_resources_validation() {
        // Navegar directamente a la página de Broken Links
        driver.get(baseURL + "broken");

        // Verificar que la imagen válida se carga correctamente
        Assert.assertTrue(
                elementsModulePage.isValidImageLoaded(),
                "La 'Valid image' no parece haberse cargado correctamente"
        );

        // Verificar que la imagen rota no se carga correctamente
        Assert.assertTrue(
                elementsModulePage.isBrokenImageReallyBroken(),
                "La 'Broken image' no parece haberse cargado correctamente"
        );

        // Clicar en el enlace "Valid Link"
        String urlBeforeValidLink = elementsModulePage.getCurrentPageUrl();
        elementsModulePage.clickOnValidLink();
        String urlAfterValidLink = elementsModulePage.getCurrentPageUrl();

        Assert.assertNotEquals(
                urlAfterValidLink,
                urlBeforeValidLink,
                "Tras hacer clic en 'Valid Link' deberíamos haber navegado a otra página"
        );

        // Verificar que navega a una página válida
        Assert.assertTrue(
                urlAfterValidLink.contains("demoqa.com"),
                "La URL tras el 'Valid Link' no parece una página válida de demoqa: " + urlAfterValidLink
        );

        // Volvemos a la página de Broken Links
        driver.navigate().back();

        // Clicar en el enlace "Broken Link"
        elementsModulePage.clickOnBrokenLink();

        // Verificar que la página muestra un error HTTP
        // toLowerCase() -> para hacer las comparaciones case-insensitive
        String brokenPageBody = elementsModulePage.getPageBodyText().toLowerCase();

        Assert.assertTrue(
                brokenPageBody.contains("404") ||
                        brokenPageBody.contains("500") ||
                        brokenPageBody.contains("not found") ||
                        brokenPageBody.contains("error"),
                "El 'Broken Link' no parece llevar a una página de error (404/500)"
        );
    }

    @Test(priority = 9)
    public void upload_files() {
        // Navegar directamente a la página de Upload and Download
        driver.get(baseURL + "upload-download");

        // Ruta relativa dentro del proyecto
        String relativePath = "src/test/resources/files/captura.png";

        // Raíz del proyecto (carpeta donde está el pom.xml y desde donde ejecutamos los tests)
        String projectRoot = System.getProperty("user.dir");

        // Construimos la ruta absoluta con "user.dir + relativePath"
        // .toString() -> pasamos de objeto (Path) a texto (String) para poder dárselo al input del navegador
        String absolutePath = Paths.get(projectRoot, relativePath).toAbsolutePath().toString();

        // Subir un archivo de prueba en el campo de upload
        elementsModulePage.uploadFileFromPath(absolutePath);

        // Verificar que se muestra el nombre del archivo cargado
        String uploadedText = elementsModulePage.getUploadedFilePathText();

        Assert.assertTrue(
                uploadedText.contains("captura.png"),
                "El mensaje de subida no contiene el nombre del archivo. Texto: " + uploadedText
        );
        System.out.println("Nombre del archivo: " + uploadedText);
    }

    @Test(priority = 10)
    public void download_files() throws IOException {
        // Navegar directamente a la página de Upload and Download
        driver.get(baseURL + "upload-download");

        // Directorio de descargas configurado igual que en BaseTest
        String projectRoot = System.getProperty("user.dir");
        String defaultDownloadDir = Paths.get(projectRoot, "target", "downloads").toString();
        String downloadDir = System.getProperty("downloadDir", defaultDownloadDir);

        String expectedFileName = "sampleFile.jpeg"; // es el fichero que baja DemoQA

        Path downloadPath = Paths.get(downloadDir);
        Files.createDirectories(downloadPath); // aseguramos que existe el directorio

        // Cogemos la carpeta (downloadPath) y le añadimos el nombre de archivo (expectedFileName) para formar la ruta completa del archivo
        // Equivale a construir: C:\proyectos\mi-app\target\downloads\sampleFile.jpeg
        Path downloadedFile = downloadPath.resolve(expectedFileName);

        // Borramos si ya existía de pruebas anteriores
        Files.deleteIfExists(downloadedFile);

        // Clicar en el botón Download
        elementsModulePage.clickDownloadButton();

        // Verificar que el archivo se descarga correctamente usando espera explícita
        boolean fileExists = waitForFileToExist(downloadedFile, 15);

        Assert.assertTrue(
                fileExists,
                "El archivo descargado no se ha encontrado en: " + downloadedFile
        );
    }

    private boolean waitForFileToExist(Path file, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // Esperamos hasta que Files.exists(file) devuelva true
        Boolean exists = wait.until(d -> Files.exists(file));

        return Boolean.TRUE.equals(exists);
    }

    @Test(priority = 11)
    public void dynamic_properties() {
        // Navegar directamente a la página de Dynamic Properties
        driver.get(baseURL + "dynamic-properties");

// 1) Enable After 5 Seconds
        // Comprobamos que al inicio está deshabilitado
        Assert.assertFalse(
                elementsModulePage.isEnableAfterFiveSecondsBtn(),
                "El botón 'Enable After 5 Seconds' debería estar deshabilitado al inicio"
        );

        // Esperamos a que se habilite el botón "After 5 seconds"
        elementsModulePage.waitUntilEnableAfterFiveSecondsBtnIsEnabled();

        Assert.assertTrue(
                elementsModulePage.isEnableAfterFiveSecondsBtn(),
                "El botón 'Enable After 5 Seconds' no se ha habilitado tras la espera"
        );

        // Color Change: esperamos a que el botón tenga la clase 'text-danger'
        elementsModulePage.waitUntilColorHasChanged();

        Assert.assertTrue(
                elementsModulePage.isColorChanged(),
                "El botón 'Color Change' no ha cambiado de color (no tiene la clase 'text-danger')"
        );

        // Esperamos a que el botón "After 5 seconds" aparezca / se haga visible
        elementsModulePage.waitUntilVisibleAfterIsVisible();

        Assert.assertTrue(
                elementsModulePage.isVisibleAfterDisplayed(),
                "El botón 'Visible After 5 Seconds' no se ha hecho visible tras la espera"
        );
    }
}
