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
        // Generamos datos dinámicos
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());

        String firstName      = "Nombre_" + uniqueSuffix;
        String lastName       = "Apellido_" + uniqueSuffix;
        String street         = "Calle_" + uniqueSuffix;
        String city           = "Ciudad_" + uniqueSuffix;
        String state          = "Estado_" + uniqueSuffix;
        String zipCode        = String.valueOf((int)(Math.random() * 90000) + 10000); // 5 dígitos
        String phoneNumber    = "6" + (int)(Math.random() * 1_000_0000);             // algo tipo 6XXXXXXX
        String ssn            = uniqueSuffix.substring(uniqueSuffix.length() - 9);    // últimos 9 dígitos
        String username       = "user_" + uniqueSuffix;
        String password       = "Pwd-" + UUID.randomUUID().toString().substring(0, 8);

        // Rellenamos el formulario por atributos (name)
        driver.findElement(By.name("customer.firstName")).sendKeys(firstName);
        driver.findElement(By.name("customer.lastName")).sendKeys(lastName);
        driver.findElement(By.name("customer.address.street")).sendKeys(street);
        driver.findElement(By.name("customer.address.city")).sendKeys(city);
        driver.findElement(By.name("customer.address.state")).sendKeys(state);
        driver.findElement(By.name("customer.address.zipCode")).sendKeys(zipCode);
        driver.findElement(By.name("customer.phoneNumber")).sendKeys(phoneNumber);
        driver.findElement(By.name("customer.ssn")).sendKeys(ssn);

        driver.findElement(By.name("customer.username")).sendKeys(username);
        driver.findElement(By.name("customer.password")).sendKeys(password);
        driver.findElement(By.name("repeatedPassword")).sendKeys(password);

        // Clicamos en botón de Register
        WebElement btnRegister = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("form#customerForm input.button[type='submit'][value='Register']")
                )
        );
        btnRegister.click();

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

        WebElement txtUsernameLogin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        txtUsernameLogin.sendKeys(username);

        WebElement txtPasswordLogin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        txtPasswordLogin.sendKeys(password);

        WebElement btnLogin = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input.button[value='Log In']")
                )
        );
        btnLogin.click();

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
