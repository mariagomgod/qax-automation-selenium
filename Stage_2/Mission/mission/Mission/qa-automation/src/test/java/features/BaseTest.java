package features;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;

    @BeforeMethod
    public void setup() {
        // Instanciamos Faker
        faker = new Faker();
        // Leer la variable 'browser' pasada por consola
        String browserName = System.getProperty("browser", "chrome"); // 'chrome' es el valor por defecto si no se pasa nada.
        // Leer la variable 'baseURL' pasada por consola, con un valor por defecto
        String baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        // Inicializar el driver basado en el valor
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            // ===== Configuración de descargas ===== Para no harcodear rutas personales
            // Raíz del proyecto (donde está el pom.xml)
            String projectRoot = System.getProperty("user.dir");
            // Carpeta de descargas por defecto dentro del proyecto
            String defaultDownloadDir = Paths.get(projectRoot, "target", "downloads").toString();
            // Permitir sobreescribirla con -DdownloadDir=...
            String downloadDir = System.getProperty("downloadDir", defaultDownloadDir);

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            // Dirección donde Chrome guardará los archivos descargados
            prefs.put("download.default_directory", downloadDir);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options); // Selenium Manager auto-resuelve el driver

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Navegador no soportado: " + browserName);
        }

        // Configuración inicial
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Usar la URL enviada por consola
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}