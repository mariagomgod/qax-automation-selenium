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
import java.util.List;

public class IframeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Frames.html");
    }

    @Test
    public void iframe() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar al iframe y cambiar el foco a él
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("singleframe"));

        // Trabajar con el contenido del iframe
        By inputLocator = By.xpath("//input[@type='text']");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        input.clear();
        input.sendKeys("Escribiendo dentro del iframe");

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
