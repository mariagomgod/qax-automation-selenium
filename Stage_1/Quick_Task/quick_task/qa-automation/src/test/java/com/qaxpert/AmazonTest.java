package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AmazonTest {

    @Test
    public void navegarPorLaWeb() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrimos la web de Amazon
        driver.get("https://www.amazon.com/");

        // Hacemos click en botón "Continue shopping" cuando sea clicable
        WebElement botonContinueShopping = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[alt='Continue shopping']"))
        );
        botonContinueShopping.click();

        // Hacemos click en el botón "CONTINUAR"
        Actions actions = new Actions(driver);
        WebElement menuContinuar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[@class='a-button-text' and normalize-space()='CONTINUAR']")
                )
        );
        actions.moveToElement(menuContinuar).click().perform();

        // Hacemos click en la pestaña "Ofertas del Día"
        WebElement ofertasDelDia = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[data-csa-c-content-id='nav_cs_gb']")
                )
        );
        ofertasDelDia.click();

        // Hacemos click en el botón "Ofertas relámpago"
        WebElement ofertasRelampago = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#discount-bubble-deals-collection-lightning-deals button[data-testid='filter-bubble-deals-collection-lightning-deals']")
                )
        );
        ofertasRelampago.click();

        // Hacemos click en la pestaña "Outlet"
        WebElement outlet = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[@class='nav-a-content' and normalize-space()='Outlet']")
                )
        );
        outlet.click();

        // Hacemos click en el botón "Juguetes y juegos"
        WebElement botonJuguetesYJuegos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("JUGUETES Y JUEGOS")
                )
        );
        botonJuguetesYJuegos.click();

        // Hacemos click en "Peluches"
        WebElement peluches = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.//span[normalize-space()='Peluches']]")
                )
        );
        peluches.click();

        // Seleccionamos icono de Prime
        WebElement iconoPrime = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[.//i[contains(@class,'a-icon-checkbox')]]")
                )
        );
        iconoPrime.click();

        // Seleccionamos "Monederos de felpa"
        WebElement monederosDeFelpa = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.//span[normalize-space()='Monederos de Felpa']]")
                )
        );
        monederosDeFelpa.click();

        // Clicamos en la pestaña "Compra juguetes más vendidos"
        WebElement compraJuguetesMasVendidos = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.//span[normalize-space()='Compra juguetes más vendidos']]")
                )
        );
        compraJuguetesMasVendidos.click();

        // Cerramos el navegador
        driver.quit();
    }
}
