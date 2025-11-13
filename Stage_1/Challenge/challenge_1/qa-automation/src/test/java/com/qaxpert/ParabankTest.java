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

        // Ingresar datos solicitados en el formulario
        WebElement txtFirstName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.firstName"))
        );
        WebElement txtLastName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.lastName"))
        );
        WebElement txtAddress = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.address.street"))
        );
        WebElement txtCity = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.address.city"))
        );
        WebElement txtState = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.address.state"))
        );
        WebElement txtZipCode = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.address.zipCode"))
        );
        WebElement txtPhoneNumber = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.phoneNumber"))
        );
        WebElement txtSsn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.ssn"))
        );
        WebElement txtUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.username"))
        );
        WebElement txtPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("customer.password"))
        );
        WebElement txtConfirm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("repeatedPassword"))
        );
        WebElement btnRegister = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Register']"))
        );

        txtFirstName.sendKeys("José");
        txtLastName.sendKeys("López");
        txtAddress.sendKeys("Calle Alamos, 2");
        txtCity.sendKeys("Córdoba");
        txtState.sendKeys("España");
        txtZipCode.sendKeys("1234");
        txtPhoneNumber.sendKeys("12345678");
        txtSsn.sendKeys("76879");
        txtUsername.sendKeys("josel");
        txtPassword.sendKeys("Testing@@123");
        txtConfirm.sendKeys("Testing@@123");
        btnRegister.click();

        // Imprimimos en consola: Página de Registro cargada. Encabezado encontrado: Signing up is easy!
        System.out.println("Página de Registro cargada. Encabezado encontrado: " + titulo.getText());

        // Cerramos el navegador
        driver.quit();
    }
}
