package pagesObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // espera global
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Devuelve true si el input indicado es inválido según HTML5 (required, pattern, etc.).
     */
    protected boolean isFieldInvalid(By locator) {
        WebElement element = waitForVisibility(locator);

        // Hecho así porque es un tooltip nativo de validación HTML5 del navegador, por lo que no aparece en el DOM localizable
        // ni tampoco se puede manejar como alert. Solo se puede verificar la validación con JavascriptExecutor.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isValid = (Boolean) js.executeScript(
                "return arguments[0].checkValidity();",
                element
        );
        // Devuelve true cuando isValid no es true (es decir, cuando es false o null).
        return !Boolean.TRUE.equals(isValid);
    }

    /**
     * Devuelve el mensaje nativo de validación HTML5 para el input indicado.
     */
    protected String getFieldValidationMessage(By locator) {
        WebElement element = waitForVisibility(locator);

        // Hecho así porque es un tooltip nativo de validación HTML5 del navegador, por lo que no aparece en el DOM localizable
        // ni tampoco se puede manejar como alert. Solo se puede verificar la validación con JavascriptExecutor.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
                "return arguments[0].validationMessage;",
                element
        );
    }
}
