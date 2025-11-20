package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class ShadowDOMTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
    }

    @Test
    public void ShadowDOMText() {
        // Crear un objeto Shadow para poder interactuar con elementos dentro del Shadow DOM
        Shadow shadow = new Shadow(driver);

        // Localizar el párrafo <p> que está dentro del elemento con id="content" en el Shadow DOM
        WebElement paragraph = shadow.findElement("#content p");
        // Imprimir por consola el texto que contiene ese párrafo
        System.out.println(paragraph.getText());
        // Verificar que el texto del párrafo es exactamente "Hello Shadow DOM"
        assertEquals("Hello Shadow DOM", paragraph.getText());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
