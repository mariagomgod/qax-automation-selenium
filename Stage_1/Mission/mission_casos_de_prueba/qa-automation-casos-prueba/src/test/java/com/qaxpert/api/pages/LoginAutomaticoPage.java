package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomaticoPage extends BasePage {

    private final By loginLocator = By.xpath("//input[@type='text' and @class='form-control']");
    private final By passwordLocator = By.xpath("//input[@type='password' and @class='form-control']");
    private final By btnSubmitLocator = By.xpath("//button[@type='submit' and @class='btn btn-outline-primary mt-2']");
    private final By successLocator = By.id("success");
    private final By errorLocator = By.id("invalid");

    public LoginAutomaticoPage(WebDriver driver) {
        super(driver);
    }

    private void escribirCredenciales(String user, String password) {
        WebElement txtLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(loginLocator));
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));

        txtLogin.clear();
        txtLogin.sendKeys(user);

        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void ingresarCredencialesValidas() {
        escribirCredenciales("user", "user");
    }

    public void clickLoginBtn() {
        WebElement btnSubmit = wait.until(ExpectedConditions.elementToBeClickable(btnSubmitLocator));
        btnSubmit.click();
    }

    public void verificarLoginExitoso() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successLocator));

        System.out.println("Login exitoso: " + successMsg.getText());
        assertTrue(successMsg.isDisplayed());
    }

    public void ingresarCredencialesInvalidas() {
        escribirCredenciales("user", "testing");
    }

    public boolean loginFallidoMostrado() {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));

        System.out.println("Login exitoso: " + errorMsg.getText());
        return errorMsg.isDisplayed();
    }

    public void credencialesVacias() {
        escribirCredenciales("", "");
    }
}
