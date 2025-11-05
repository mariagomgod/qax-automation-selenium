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

public class ParabankTest {

    @Test
    public void clicarEnRegister() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Parabank
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Hacemos clic en el link "Register"
        WebElement linkRegister = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Register"))
        );
        linkRegister.click();

        // Obtenemos el texto del <h1> en la página de registro
        WebElement titulo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.title"))
        );

        // Imprimimos en consola: Página de Registro cargada. Encabezado encontrado: Signing up is easy!
        System.out.println("Página de Registro cargada. Encabezado encontrado: " + titulo.getText());

        // Cerramos el navegador
        driver.quit();
    }
}
