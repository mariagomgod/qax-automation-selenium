package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginCorrectoPage extends BasePage {

    private final By btnMakeAppointmentLocator = By.id("btn-make-appointment");
    private final By usernameLocator = By.cssSelector("#txt-username");
    private final By passwordLocator = By.cssSelector("#txt-password");
    private final By btnLoginLocator = By.id("btn-login");
    private final By encabezadoMakeAppointmentLocator = By.xpath("//h2[normalize-space()='Make Appointment']");
    private final By burgerMenuLocator = By.id("menu-toggle");
    private final By logoutLinkLocator = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a");

    public LoginCorrectoPage(WebDriver driver) {
        super(driver);
    }

    public void clickMakeAppointment() {
        WebElement btnMakeAppoinment = wait.until(
                ExpectedConditions.elementToBeClickable(btnMakeAppointmentLocator)
        );
        btnMakeAppoinment.click();
    }

    public void iniciarSesion() {
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
        txtUsername.sendKeys("John Doe");

        txtPassword.clear();
        txtPassword.sendKeys("ThisIsNotAPassword");

        btnLogin.click();
    }

    public void encabezadoMakeAppointment() {
        WebElement encabezadoMakeAppointment = wait.until(
                ExpectedConditions.visibilityOfElementLocated(encabezadoMakeAppointmentLocator)
        );
        System.out.println("Inicio de sesión exitoso: " + encabezadoMakeAppointment.getText());
        assertTrue(encabezadoMakeAppointment.isDisplayed());
    }

    public void cerrarSesion() {
        WebElement burgerMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(burgerMenuLocator)
        );
        burgerMenu.click();

        WebElement logoutLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(logoutLinkLocator)
        );
        logoutLink.click();
    }
}
