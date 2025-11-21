package com.qaxpert.api.steps;

import com.qaxpert.api.pages.LoginCorrectoPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.HEROKUAPP_PAGE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginCorrectoSteps {

    private static LoginCorrectoPage loginCorrectoPage;

    @BeforeAll
    public static void setUp() {
        loginCorrectoPage = new LoginCorrectoPage(RunContext.getDriver());
    }

    @Given("que estoy en la página principal")
    public void navegarAPaginaPrincipal() {
        RunContext.getDriver().get(HEROKUAPP_PAGE);
    }

    @When("hago clic en Make Appointment")
    public void clickMakeAppointment() {
        loginCorrectoPage.clickMakeAppointment();
    }

    @When("inicio sesión")
    public void iniciarSesion() {
        loginCorrectoPage.iniciarSesion();
    }

    @Then("veo el encabezado Make Appointment")
    public void encabezadoMakeAppointmentMostrado() {
        assertTrue(loginCorrectoPage.encabezadoMakeAppointmentMostrado());
    }

    @Then("cierro sesión")
    public void cerrarSesion() {
        loginCorrectoPage.cerrarSesion();
    }
}
