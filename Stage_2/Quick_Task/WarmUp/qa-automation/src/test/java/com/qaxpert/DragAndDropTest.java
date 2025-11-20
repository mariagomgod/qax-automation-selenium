package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
    }

    @Test
    public void dragAndDrop() {
        WebElement origen = driver.findElement(By.id("draggable"));
        WebElement destino = driver.findElement(By.id("target"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(origen, destino).perform();

        System.out.println("Arrastre completado correctamente.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
