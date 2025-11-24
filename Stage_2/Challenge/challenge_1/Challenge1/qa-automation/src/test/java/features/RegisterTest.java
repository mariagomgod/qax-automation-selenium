package features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pagesObject.RegisterPage;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RegisterTest {

    private static final String BASE_URL = "https://demo.automationtesting.in/Index.html";
    private static final String TEST_EMAIL = "ninja4testing@qaxpert.com";
    private static final String TEST_PHONE = "3110000000";
    private static final String UPLOAD_FILE_RELATIVE_PATH = "src/test/resources/upload/small.png";

    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);  // Selenium Manager auto-resolverá ChromeDriver
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        registerPage = new RegisterPage(driver);
    }

    @Test
    public void shouldRegisterUserSuccessfully() {
        // Vamos desde la pantalla principal al formulario de registro
        registerPage.intoRegister(TEST_EMAIL);
        // Cerramos el banner de consentimiento
        registerPage.clickDoNotConsent();
        // Rellenamos el formulario con datos válidos
        fillRegistrationFormWithValidData();
        // Enviamos el formulario
        registerPage.clickSubmit();
        // Verificamos que el registro ha sido exitoso
        Assert.assertTrue(registerPage.isRegistrationSuccessful());
    }

    @Test
    public void shouldNotRegisterWithEmptyRequiredFields() {
        // Vamos desde la pantalla principal al formulario de registro
        registerPage.intoRegister(TEST_EMAIL);
        // Cerramos el banner de consentimiento
        registerPage.clickDoNotConsent();
        // Enviamos el formulario
        registerPage.clickSubmit();
        // Verificamos que el campo "Full name" es inválido según el navegador
        Assert.assertTrue(
                registerPage.isFirstNameInvalid(),
                "Se esperaba que el campo Full Name fuera inválido"
        );
        // Verificamos que existe un mensaje de validación nativo
        String validationMessage = registerPage.getFirstNameValidationMessage();
        Assert.assertFalse(
                validationMessage.isEmpty(),
                "Completa este campo"
        );
    }

    @Test
    public void shouldNotRegisterWithInvalidPhoneNumber() {
        // Vamos desde la pantalla principal al formulario de registro
        registerPage.intoRegister(TEST_EMAIL);
        // Cerramos el banner de consentimiento
        registerPage.clickDoNotConsent();
        // Rellenamos el formulario con datos válidos, excepto el teléfono
        fillRegistrationFormWithValidData();
        registerPage.setPhone("eee"); // teléfono inválido a propósito
        // Enviamos el formulario
        registerPage.clickSubmit();
        // Verificamos que el teléfono es inválido según el navegador
        Assert.assertTrue(
                registerPage.isPhoneInvalid(),
                "Se esperaba que el campo Phone fuera inválido"
        );
        // Verificamos que existe un mensaje de validación nativo
        String validationMessage = registerPage.getPhoneValidationMessage();
        Assert.assertFalse(
                validationMessage.isEmpty(),
                "Utiliza un formato que coincida con el solicitado"
        );
    }

    @Test
    public void shouldNotRegisterWhenPasswordsDoNotMatch() throws InterruptedException {
        // Vamos desde la pantalla principal al formulario de registro
        registerPage.intoRegister(TEST_EMAIL);
        // Cerramos el banner de consentimiento
        registerPage.clickDoNotConsent();
        // Rellenamos el formulario con datos válidos, excepto confirm password
        fillRegistrationFormWithValidData();
        registerPage.setPassword("Testing@@123");
        registerPage.setConfirmPassword("Testing@123");// password diferente a propósito
        // Enviamos el formulario
        registerPage.clickSubmit();
        // VERIFICACION: No la podemos hacer hasta que se corrija el Endpoint de Country (no nos deja avanzar).
        // Por tanto, el mensaje no lo podemos validar
    }

    private void fillRegistrationFormWithValidData() {
        registerPage.setFirstName("Luis");
        registerPage.setLastName("QAXpert");
        registerPage.setAddress("Calle 123");
        registerPage.setEmail(TEST_EMAIL);
        registerPage.setPhone(TEST_PHONE);
        registerPage.selectGenderMale();
        registerPage.selectHobbyMovies();
        registerPage.selectSkill("Java");

        // SOLUCION TEMPORAL MIENTRAS SE ARREGLA EL ENDPOINT DEL DROPDOWN DE COUNTRY
        try {
            registerPage.selectCountry("Australia");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // El dropdown Country está vacío (endpoint roto), seguimos sin seleccionarlo
            System.out.println("Country 'Australia' no disponible, continúo sin seleccionarlo.");
        }

        String filePath = Paths.get(
                System.getProperty("user.dir"),
                UPLOAD_FILE_RELATIVE_PATH
        ).toString();

        registerPage.uploadFile(filePath);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
