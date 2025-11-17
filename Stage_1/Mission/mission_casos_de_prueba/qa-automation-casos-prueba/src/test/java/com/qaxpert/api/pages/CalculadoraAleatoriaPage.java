package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CalculadoraAleatoriaPage extends BasePage {

    private final By btnIgualLocator = By.xpath("//span[@class='btn btn-outline-warning' and normalize-space()='=']");
    private final By btnSumaLocator = By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='+']");
    private final By btnRestaLocator = By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='-']");
    private final By btnDivisionLocator = By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='÷']");
    private final By btnCLocator = By.xpath("//span[@class='clear btn btn-outline-danger' and normalize-space()='C']");
    private final By screenLocator = By.xpath("//div[contains(@class,'screen')]");

    public CalculadoraAleatoriaPage(WebDriver driver) {
        super(driver);
    }

    public void calcularSuma(Integer operandoUno, Integer operandoDos) {
        WebElement btnOperandoUno = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoUno + "']")));

        WebElement btnSuma = wait.until(ExpectedConditions.elementToBeClickable(btnSumaLocator));

        WebElement btnOperandoDos = null;
        // Hecho de esta forma para evitar errores cuando no hay segundo número, comprobando antes si existe el operando
        // para no intentar hacer clic en algo que no corresponde
        if (operandoDos != null) {
            btnOperandoDos = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoDos + "']")));
        }

        WebElement btnIgual = wait.until(ExpectedConditions.elementToBeClickable(btnIgualLocator));

        WebElement btnC = wait.until(ExpectedConditions.elementToBeClickable(btnCLocator));

        // Hacemos 5 operaciones sin asserts para desactivar la aleatoriedad que indica la página
        for (int i = 0; i < 5; i++) {
            btnOperandoUno.click();
            btnSuma.click();
            if (btnOperandoDos != null) {
                btnOperandoDos.click();
            }
            // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
            btnC.click();
        }

        btnOperandoUno.click();
        btnSuma.click();
        // Hecho de esta forma para evitar errores cuando no hay segundo número, comprobando antes si existe el operando
        // para no intentar hacer clic en algo que no corresponde
        if (btnOperandoDos != null) {
            btnOperandoDos.click();
        }
        // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
    }

    public String resultado() {
        WebElement screenDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(screenLocator));
        return screenDisplay.getText();
    }

    public void calcularResta(Integer operandoUno, Integer operandoDos) {
        WebElement btnOperandoUno = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoUno + "']")));

        WebElement btnResta = wait.until(ExpectedConditions.elementToBeClickable(btnRestaLocator));

        WebElement btnOperandoDos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoDos + "']")));

        WebElement btnIgual = wait.until(ExpectedConditions.elementToBeClickable(btnIgualLocator));

        WebElement btnC = wait.until(ExpectedConditions.elementToBeClickable(btnCLocator));

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

    public void calcularDivision(Integer operandoUno, Integer operandoDos) {
        WebElement btnOperandoUno = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoUno + "']")));

        WebElement btnDivision = wait.until(ExpectedConditions.elementToBeClickable(btnDivisionLocator));

        WebElement btnOperandoDos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='" + operandoDos + "']")));

        WebElement btnIgual = wait.until(ExpectedConditions.elementToBeClickable(btnIgualLocator));

        WebElement btnC = wait.until(ExpectedConditions.elementToBeClickable(btnCLocator));

        // Hacemos 5 operaciones sin asserts para desactivar la aleatoriedad que indica la página
        for (int i = 0; i < 5; i++) {
            btnOperandoUno.click();
            btnDivision.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnOperandoDos);
            // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
            btnC.click();
        }

        btnOperandoUno.click();
        btnDivision.click();
        // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnOperandoDos);
        // Hecho de esta forma porque cuando se abre la página, el footer tapa parcialmente el igual y Selenium no deja clicar
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
    }
}
