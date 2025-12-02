package features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Leer la variable 'browser' pasada por consola
        String browserName = System.getProperty("browser", "chrome"); // 'chrome' es el valor por defecto si no se pasa nada.
        // Leer la variable 'baseURL' pasada por consola, con un valor por defecto
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        // Inicializar el driver basado en el valor
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
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