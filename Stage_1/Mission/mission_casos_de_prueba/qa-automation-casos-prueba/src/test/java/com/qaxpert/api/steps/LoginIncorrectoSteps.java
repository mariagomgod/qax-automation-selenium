package com.qaxpert.api.steps;

import com.qaxpert.api.pages.LoginIncorrectoPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.HEROKUAPP_PAGE;

public class LoginIncorrectoSteps {

    private static LoginIncorrectoPage loginIncorrectoPage;

    @BeforeAll
    public static void setUp() {
        loginIncorrectoPage = new LoginIncorrectoPage(RunContext.getDriver());
    }

    @Given("que estoy en la página principal de Herokuapp")
    public void navegarAPaginaPrincipalHerokuapp() {
        RunContext.getDriver().get(HEROKUAPP_PAGE);
    }

    @When("hago clic en el botón Make Appointment")
    public void clickBtnMakeAppointment() {
        loginIncorrectoPage.clickBtnMakeAppointment();
    }

    @When("inicio sesión con credenciales inválidas")
    public void iniciarSesionConCredencialesInvalidas() {
        loginIncorrectoPage.iniciarSesionConCredencialesInvalidas();
    }

    @Then("veo un mensaje de login fallido")
    public void msjLoginFallido() {
        loginIncorrectoPage.msjLoginFallido();
    }
}
