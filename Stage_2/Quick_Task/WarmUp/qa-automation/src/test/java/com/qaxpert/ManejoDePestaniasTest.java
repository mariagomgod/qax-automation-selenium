package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class ManejoDePestaniasTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Windows.html");
    }

    @Test
    public void manejoPestanias() {

        // Esperar a que desaparezca el overlay de consentimiento (o que no exista)
        By consentBtn = By.xpath("//p[@class='fc-button-label' and normalize-space()='Consent']");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(consentBtn));
            btn.click();
            System.out.println("Consentimiento aceptado.");
        } catch (TimeoutException e) {
            System.out.println("No apareció el banner de consentimiento (o desapareció muy rápido).");
        }

        // Guardar la ventana actual
        String ventanaPrincipal = driver.getWindowHandle();

        // Hacer clic para abrir nueva pestaña
        driver.findElement(By.xpath("//a[@href='#Tabbed' and @data-toggle='tab' and contains(normalize-space(),'Open New Tabbed Windows')]"));
        driver.findElement(By.cssSelector("#Tabbed > a > button")).click();

        // Obtener todas las ventanas abiertas
        Set<String> ventanas = driver.getWindowHandles();

        for (String ventana : ventanas) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);
                System.out.println("Cambiando a nueva pestaña: " + driver.getTitle());
                driver.close(); // Cierra la nueva pestaña
            }
        }

        // Regresar a la ventana principal
        driver.switchTo().window(ventanaPrincipal);
        System.out.println("Regresando a la ventana principal: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
