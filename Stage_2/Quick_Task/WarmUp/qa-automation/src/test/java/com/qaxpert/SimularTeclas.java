package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimularTeclas {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }

    // Aquí definimos los valores que se inyectarán en el @Test
    @DataProvider(name = "operandos")
    public Object[][] operandos() {
        return new Object[][]{
                {7, 3}, // primera ejecución: 7 - 3
                {9, 4}, // segunda ejecución: 9 - 4
                {5, 2}  // tercera ejecución: 5 - 2
        };
    }

    // Ahora el @Test usa esos operandos
    @Test(dataProvider = "operandos")
    public void simularTeclas(int operandoUno, int operandoDos) {

        WebElement btnOperandoUno = driver.findElement(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoUno + "']"));

        WebElement btnResta = driver.findElement(By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='-']"));

        WebElement btnOperandoDos = driver.findElement(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoDos + "']"));

        WebElement btnIgual = driver.findElement(By.xpath("//span[@class='btn btn-outline-warning' and normalize-space()='=']"));

        WebElement btnC = driver.findElement(By.xpath("//span[@class='clear btn btn-outline-danger' and normalize-space()='C']"));

        // Hacemos 5 operaciones sin asserts para desactivar la aleatoriedad que indica la página
        for (int i = 0; i < 5; i++) {
            btnOperandoUno.click();
            btnResta.click();
            btnOperandoDos.click();
            // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
            btnC.click();
        }

        btnOperandoUno.click();
        btnResta.click();
        btnOperandoDos.click();
        // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
