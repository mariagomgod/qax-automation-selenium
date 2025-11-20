package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShadowIframeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
    }

    @Test
    public void ShadowIframe() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar al iframe y cambiar el foco a él
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("my-iframe"));

        // Esperar a que haya al menos un <p> dentro del iframe
        By pName = By.tagName("p");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pName, 0));

        // Trabajar con el contenido del iframe (no hay Shadow DOM aquí)
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
        System.out.println("Número de <p> dentro del iframe: " + paragraphs.size());
        // Recorremos cada elemento de la lista e imprimimos el texto por consola
        paragraphs.forEach(p -> System.out.println(p.getText()));

        // Volver al contexto principal
        driver.switchTo().defaultContent();
        System.out.println("Volviendo al contexto principal.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
