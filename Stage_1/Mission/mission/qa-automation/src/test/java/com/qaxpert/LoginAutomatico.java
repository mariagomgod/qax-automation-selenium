package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

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

        // Localizo los campos de usuario y contraseña sin usar id ni name
        WebElement campoLogin = driver.findElement(By.xpath("//input[@type='text' and @class='form-control']"));
        WebElement campoPassword = driver.findElement(By.xpath("//input[@type='password' and @class='form-control']"));

        // Ingreso los datos de prueba
        campoLogin.sendKeys("user");
        campoPassword.sendKeys("user");

        // Hacemos click en el botón Submit
        WebElement botonSubmit = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='btn btn-outline-primary mt-2']"))
        );
        botonSubmit.click();

        // Imprimo si el login fue exitoso
        WebElement loginExitoso = driver.findElement(By.id("success"));
        WebElement loginFallido = driver.findElement(By.id("invalid"));


    }
}
