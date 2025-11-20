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

public class ShadowPopupTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
    }

    @Test
    public void shadowPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click para abrir la alerta 1
        driver.findElement(By.id("my-alert")).click();
        // Cambiar el foco a la alerta
        Alert alerta = driver.switchTo().alert();
        // Leer el mensaje
        System.out.println("Texto de la alerta: " + alerta.getText());
        // Aceptar la alerta
        alerta.accept();
        // Imprimimos por consola
        System.out.println("Alerta aceptada correctamente.");

        // Click para abrir la alerta 2
        driver.findElement(By.id("my-confirm")).click();
        // Cambiar el foco a la alerta
        Alert alerta2 = driver.switchTo().alert();
        // Leer el mensaje
        System.out.println("Texto de la alerta: " + alerta2.getText());
        // Aceptar la alerta
        alerta2.accept();
        // Imprimimos por consola
        System.out.println("Alerta aceptada correctamente.");

        // Click para abrir la alerta 3
        driver.findElement(By.id("my-prompt")).click();
        // Cambiar el foco a la alerta
        Alert alerta3 = driver.switchTo().alert();
        // Leer el mensaje
        System.out.println("Texto de la alerta: " + alerta3.getText());
        // Aceptar la alerta
        alerta3.accept();
        // Imprimimos por consola
        System.out.println("Alerta aceptada correctamente.");


        // Abrimos el modal
        driver.findElement(By.id("my-modal")).click();

        // Esperamos a que el modal esté visible
        WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#example-modal.modal.show")
                )
        );

        WebElement modalTitle = modal.findElement(By.id("exampleModalLabel"));
        WebElement modalBody  = modal.findElement(By.cssSelector("div.modal-body"));

        System.out.println("Título modal: " + modalTitle.getText());
        System.out.println("Cuerpo modal: " + modalBody.getText());

        // Ahora buscamos el botón Close **dentro del modal visible**
        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#example-modal.modal.show button.btn.btn-secondary.model-button[data-bs-dismiss='modal']")
                )
        );
        closeButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
