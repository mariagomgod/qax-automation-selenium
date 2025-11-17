package com.qaxpert.api.steps;

import com.qaxpert.api.pages.LoginAutomaticoPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.LOGIN_PAGE;

public class LoginAutomaticoSteps {

    private static LoginAutomaticoPage loginPage;

    @BeforeAll
    public static void setUp() {
        loginPage = new LoginAutomaticoPage(RunContext.getDriver());
    }

    @Given("que el usuario está en la página de login")
    public void navegarAPaginaLogin() {
        RunContext.getDriver().get(LOGIN_PAGE); // define LOGIN_PAGE en config/constantes
    }

    @When("ingresa el usuario y la contraseña correctos")
    public void ingresarCredencialesValidas() {
        loginPage.ingresarCredencialesValidas();
    }

    @When("hace click en el botón de login")
    public void clickLoginBtn() {
        loginPage.clickLoginBtn();
    }

    @Then("el sistema muestra que el login fue exitoso")
    public void verificarLoginExitoso() {
        loginPage.verificarLoginExitoso();
    }

    @When("ingresa un usuario válido pero una contraseña incorrecta")
    public void ingresarCredencialesInvalidas() {
        loginPage.ingresarCredencialesInvalidas();
    }

    @Then("el sistema muestra un mensaje de error de credenciales")
    public void verificarLoginFallido() {
        loginPage.verificarLoginFallido();
    }

    @Then("deja los campos de usuario y contraseña vacíos")
    public void credencialesVacias() {
        loginPage.credencialesVacias();
    }
}
