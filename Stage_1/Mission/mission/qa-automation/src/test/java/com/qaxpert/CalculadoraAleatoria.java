package com.qaxpert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class CalculadoraAleatoria {

    @Test
    public void calculadoraAleatoria() {

        // Configuramos el driver de Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperamos explícitamente 15 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Abrimos la web de Boni García
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/random-calculator.html");

        // Localizamos los botones de la calculadora usando XPath o CSS Selectors
        WebElement botonC = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='clear btn btn-outline-danger' and normalize-space()='C']")));

        WebElement botonSuma = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='+']")));

        WebElement botonResta = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='-']")));

        WebElement botonDivision = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='÷']")));

        WebElement botonIgual = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-warning' and normalize-space()='=']")));

        WebElement botonDos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='2']")));

        WebElement botonSeis = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='6']")));

        WebElement display = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='screen']")));

        // Realizamos operaciones básicas: resta, suma, división e imprimimos los resultados de cada operación

        // Hacemos 5 operaciones sin asserts para desactivar la aleatoriedad que indica la página
        for (int i = 0; i < 5; i++) {
            botonSeis.click();
            botonSuma.click();
            botonDos.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);
            botonC.click();
        }

        // Operación resta
        botonSeis.click();
        botonResta.click();
        botonDos.click();
        // Utilizo JavaScript para el clic, invocándolo directamente, debido al diseño de la página y no por error de los localizadores
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);

        String resultadoResta = display.getText().trim();
        System.out.println("Resultado: " + resultadoResta);
        assertEquals("4", resultadoResta);

        botonC.click();

        // Operación suma
        botonSeis.click();
        botonSuma.click();
        botonDos.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);

        String resultadoSuma = display.getText().trim();
        System.out.println("Resultado: " + resultadoSuma);
        assertEquals("8", resultadoSuma);

        botonC.click();

        // Operación división
        botonSeis.click();
        botonDivision.click();
        botonDos.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);

        String resultadoDivision = display.getText().trim();
        System.out.println("Resultado: " + resultadoDivision);
        assertEquals("3", resultadoDivision);

        // Cerramos el navegador
        driver.quit();
    }
}
