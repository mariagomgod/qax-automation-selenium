package features;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
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
    protected String baseURL;

    @BeforeMethod
    public void setup() {
        // Instanciamos Faker
        faker = new Faker();
        // Leer la variable 'browser' pasada por consola
        String browserName = System.getProperty("browser", "chrome"); // 'chrome' es el valor por defecto si no se pasa nada.
        // Leer la variable 'baseURL' pasada por consola, con un valor por defecto
        baseURL = System.getProperty("baseURL", "https://demoqa.com/");
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

        // === Quitar google_vignette si aparece al cargar la página inicial ===
        removeGoogleVignetteIfPresent();
    }

    /**
     * Elimina el overlay de google_vignette y limpia la URL si ha sido alterada (?google_vignette)
     */
    protected void removeGoogleVignetteIfPresent() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1) Quitar overlays típicos de google_vignette
            js.executeScript(
                    "const selectors = \"[id*='google_vignette'], [id*='google-vignette'], [class*='vignette']\";" +
                            "document.querySelectorAll(selectors).forEach(e => e.remove());"
            );

            // 2) Limpiar la URL si lleva ?google_vignette o #google_vignette
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("google_vignette")) {
                int cut = currentUrl.length();
                int qIndex = currentUrl.indexOf('?');
                int hIndex = currentUrl.indexOf('#');

                if (qIndex != -1 && qIndex < cut) cut = qIndex;
                if (hIndex != -1 && hIndex < cut) cut = hIndex;

                String cleanUrl = currentUrl.substring(0, cut);
                driver.get(cleanUrl);
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar google-vignette: " + e.getMessage());
        }
    }

    /**
     * Navega a una ruta relativa (por ejemplo "sortable", "droppable"...)
     * y limpia google_vignette si se cuela.
     */
    protected void openPath(String path) {
        // Por si acaso baseURL aún es null (defensa extra)
        if (baseURL == null) {
            baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        }

        String url = baseURL.endsWith("/") ? baseURL + path : baseURL + "/" + path;

        // Vamos a intentar hasta 3 veces dejar una URL limpia de google_vignette
        for (int i = 0; i < 3; i++) {
            driver.get(url);

            String currentUrl = driver.getCurrentUrl();

            // Si nos han redirigido a otro dominio (googleads, etc.), reintentamos
            if (!currentUrl.contains("demoqa.com")) {
                continue;
            }

            // Intentar limpiar overlay + URL
            removeGoogleVignetteIfPresent();

            // Volver a leer la URL después de limpiar
            currentUrl = driver.getCurrentUrl();

            // Si ya estamos en demoqa y sin google_vignette, salimos del bucle
            if (currentUrl.contains("demoqa.com") && !currentUrl.contains("google_vignette")) {
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}