package com.qaxpert.api.pages;

import com.qaxpert.api.utils.RunContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParabankPage extends BasePage {

    private final By registerLinkLocator = By.linkText("Register");
    private final By signupTitleLocator = By.xpath("//h1[@class='title' and normalize-space()='Signing up is easy!']");
    private final By txtFirstNameLocator = By.id("customer.firstName");
    private final By txtLastNameLocator = By.id("customer.lastName");
    private final By txtAddressLocator = By.id("customer.address.street");
    private final By txtCityLocator = By.id("customer.address.city");
    private final By txtStateLocator = By.id("customer.address.state");
    private final By txtZipCodeLocator = By.id("customer.address.zipCode");
    private final By txtPhoneNumberLocator = By.id("customer.phoneNumber");
    private final By txtSsnLocator = By.id("customer.ssn");
    private final By txtUsernameLocator = By.id("customer.username");
    private final By txtPasswordLocator = By.id("customer.password");
    private final By txtConfirmLocator = By.id("repeatedPassword");
    private final By registerBtnLocator = By.xpath("//input[@value='Register']");
    private final By msjRegistroExitosoLocator = By.cssSelector("#rightPanel > p");
    private final By logoutLinkLocator = By.linkText("Log Out");
    private final By txtLoginUsernameLocator = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private final By txtLoginPasswordLocator = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");
    private final By btnLoginLocator = By.cssSelector("input[type='submit'][value='Log In']");
    private final By headingLocator = By.xpath("//h1[contains(@class,'title')][normalize-space()='Accounts Overview']");

    public ParabankPage(WebDriver driver) {
        super(driver);
    }

    public void accederALaPaginaDeRegister() {
        WebElement linkRegister = wait.until(
                ExpectedConditions.elementToBeClickable(registerLinkLocator)
        );
        linkRegister.click();
    }

    public void paginaRegistroExitosa() {
        WebElement msjPaginaResgistroExitosa = wait.until(
                ExpectedConditions.visibilityOfElementLocated(signupTitleLocator)
        );

        String text = msjPaginaResgistroExitosa.getText().trim();
        assertEquals("Signing up is easy!", text);
        System.out.println("Página de Registro cargada. Encabezado encontrado: " + text);
    }

    public void rellenarFormularioRegistro() {
        WebElement txtFirstName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtFirstNameLocator)
        );

        WebElement txtLastName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtLastNameLocator)
        );

        WebElement txtAddress = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtAddressLocator)
        );

        WebElement txtCity = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtCityLocator)
        );

        WebElement txtState = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtStateLocator)
        );

        WebElement txtZipCode = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtZipCodeLocator)
        );

        WebElement txtPhoneNumber = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtPhoneNumberLocator)
        );

        WebElement txtSsn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtSsnLocator)
        );

        WebElement txtUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtUsernameLocator)
        );

        WebElement txtPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtPasswordLocator)
        );

        WebElement txtConfirm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtConfirmLocator)
        );

        txtFirstName.sendKeys("Ana");
        txtLastName.sendKeys("López");
        txtAddress.sendKeys("Calle Inventada, 4");
        txtCity.sendKeys("Sevilla");
        txtState.sendKeys("España");
        txtZipCode.sendKeys("1234");
        txtPhoneNumber.sendKeys("12345678");
        txtSsn.sendKeys("76879");

        String randomUsername = "qa_" + UUID.randomUUID().toString().replace("-", "").substring(0,8);
        RunContext.setUsername(randomUsername);
        txtUsername.sendKeys(randomUsername);

        txtPassword.sendKeys("Testing@@123");
        txtConfirm.sendKeys("Testing@@123");
    }

    public void clicarRegisterBtn() {
        WebElement btnRegister = wait.until(
                ExpectedConditions.elementToBeClickable(registerBtnLocator)
        );
        btnRegister.click();
    }

    public void registroExitoso() {
        WebElement msjRegistroExitoso = wait.until(
                ExpectedConditions.visibilityOfElementLocated(msjRegistroExitosoLocator)
        );

        System.out.println("Cuenta creada exitosamente: " + msjRegistroExitoso.getText());
        assertTrue(msjRegistroExitoso.isDisplayed());
    }

    public void cerrarSesionParabank() {
        WebElement logoutLink = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLinkLocator)
        );
        logoutLink.click();
    }

    public void iniciarSesionParabank() {
        WebElement txtLoginUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtLoginUsernameLocator)
        );

        String username = RunContext.getUsername();
        if (username == null || username.isBlank()) {
            throw new IllegalStateException("No hay username en RunContext. Asegúrate de llamarlo tras registrar.");
        }
        txtLoginUsername.sendKeys(username);

        WebElement txtLoginPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtLoginPasswordLocator)
        );
        txtLoginPassword.sendKeys("Testing@@123");

        WebElement btnLogin = wait.until(
                ExpectedConditions.elementToBeClickable(btnLoginLocator)
        );
        btnLogin.click();
    }

    public void loginExitoso() {
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(headingLocator)
        );

        String text = heading.getText().trim();
        assertEquals("Accounts Overview", text);
        System.out.println("Login exitoso: " + text);
    }
}
