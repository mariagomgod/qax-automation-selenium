package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginAutomatico {

    @Test
    public void login() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Boni García
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        // Localizamos los campos de usuario y contraseña sin usar id ni name
        WebElement txtLogin = driver.findElement(By.xpath("//input[@type='text' and @class='form-control']"));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @class='form-control']"));

        // Ingresamos los datos de prueba
        txtLogin.sendKeys("user");
        txtPassword.sendKeys("user");

        // Hacemos click en el botón Submit
        WebElement btnSubmit = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='btn btn-outline-primary mt-2']"))
        );
        btnSubmit.click();

        // Imprimimos si el login fue exitoso
        List<WebElement> loginExitoso = driver.findElements(By.id("success"));
        List<WebElement> loginFallido = driver.findElements(By.id("invalid"));

        boolean esExitoso = !loginExitoso.isEmpty() && loginExitoso.get(0).isDisplayed();
        boolean esFallido = !loginFallido.isEmpty() && loginFallido.get(0).isDisplayed();

        if (esExitoso) {
            System.out.println("Login existoso");
        } else if (esFallido) {
            System.out.println("Login fallido");
        } else {
            System.out.println("No se mostró ningún mensaje");
        }

        // Cerramos el navegador
        driver.quit();
    }
}
