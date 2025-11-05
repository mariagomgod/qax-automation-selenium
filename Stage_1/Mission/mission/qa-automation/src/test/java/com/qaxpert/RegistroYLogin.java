package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RegistroYLogin {

    @Test
    public void registroYLoginParabank() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Parabank
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        // Localizamos los campos de texto del formulario de registro
        List<WebElement> camposTexto = driver.findElements(
                By.cssSelector("form[action='register.htm'] input.input[type='text']")
        );

        if (camposTexto.size() != 9) {
            throw new IllegalStateException("Se esperaban 9 campos de texto y hay: " + camposTexto.size());
        }

        camposTexto.get(0).sendKeys("NombreTest");
        camposTexto.get(1).sendKeys("ApellidoTest");
        camposTexto.get(2).sendKeys("Calle Falsa 123");
        camposTexto.get(3).sendKeys("Madrid");
        camposTexto.get(4).sendKeys("Madrid");
        camposTexto.get(5).sendKeys("28080");
        camposTexto.get(6).sendKeys("600000000");
        camposTexto.get(7).sendKeys("123-45-6789");
        // Creamos un username único
        String username = "user_" + System.currentTimeMillis();
        camposTexto.get(8).sendKeys(username);

        List<WebElement> passwords = driver.findElements(By.cssSelector("form[action='register.htm'] input.input[type='password']"));

        WebElement campoPassword = passwords.get(0);
        WebElement campoConfirmPassword = passwords.get(1);

        String password = "Pwd-" + UUID.randomUUID().toString().substring(0, 8);
        campoPassword.sendKeys(password);
        campoConfirmPassword.sendKeys(password);

        // Clicamos en botón de Register
        WebElement botonRegister = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("form#customerForm input.button[type='submit'][value='Register']")
                )
        );
        botonRegister.click();

        // Verificamos el mensaje de éxito
        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel p"))
        );

        assertEquals("Your account was created successfully. You are now logged in.", mensaje.getText());

        System.out.println("Registro correcto: " + mensaje.getText());
        System.out.println("Usuario creado: " + username);
        System.out.println("Password: " + password);

        // Después del registro, hacemos login con el mismo usuario
        WebElement linkLogOut = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))
        );
        linkLogOut.click();

        WebElement campoUsernameLogin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        campoUsernameLogin.sendKeys(username);

        WebElement campoPasswordLogin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        campoPasswordLogin.sendKeys(password);

        WebElement buttonLogin = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input.button[value='Log In']")
                )
        );
        buttonLogin.click();

        // Verificamos que el login fue existoso
        WebElement tituloAccounts = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[normalize-space()='Accounts Overview']")
                )
        );
        assertTrue(tituloAccounts.isDisplayed());

        WebElement accountTable = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("accountTable"))
        );
        assertTrue(accountTable.isDisplayed());

        // Cerramos el navegador
        driver.quit();
    }
}
