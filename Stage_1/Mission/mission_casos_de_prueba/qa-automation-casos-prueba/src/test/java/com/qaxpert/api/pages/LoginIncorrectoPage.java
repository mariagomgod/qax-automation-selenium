package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginIncorrectoPage extends BasePage {

    private final By btnMakeAppointmentLocator = By.id("btn-make-appointment");
    private final By usernameLocator = By.id("txt-username");
    private final By passwordLocator = By.id("txt-password");
    private final By btnLoginLocator = By.id("btn-login");
    private final By msjLoginFallidoLocator = By.cssSelector("p.lead.text-danger");

    public LoginIncorrectoPage(WebDriver driver) {
        super(driver);
    }

    public void clickBtnMakeAppointment() {
        WebElement btnMakeAppoinment = wait.until(
                ExpectedConditions.elementToBeClickable(btnMakeAppointmentLocator)
        );
        btnMakeAppoinment.click();
    }

    public void iniciarSesionConCredencialesInvalidas() {
        WebElement txtUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameLocator)
        );

        WebElement txtPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordLocator)
        );

        WebElement btnLogin = wait.until(
                ExpectedConditions.elementToBeClickable(btnLoginLocator)
        );

        txtUsername.clear();
        txtUsername.sendKeys("QAX");

        txtPassword.clear();
        txtPassword.sendKeys("ThisIsNotAPassword");

        btnLogin.click();
    }

    public void msjLoginFallido() {
        WebElement msjLoginFallido = wait.until(
                ExpectedConditions.visibilityOfElementLocated(msjLoginFallidoLocator)
        );
        assertEquals("Login failed! Please ensure the username and password are valid.", msjLoginFallido.getText());
        System.out.println(msjLoginFallido.getText());
    }
}
