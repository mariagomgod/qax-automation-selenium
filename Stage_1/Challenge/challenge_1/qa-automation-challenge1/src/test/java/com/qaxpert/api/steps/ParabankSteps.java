package com.qaxpert.api.steps;

import com.qaxpert.api.pages.ParabankPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.PARABANK_PAGE;

public class ParabankSteps {

    private static ParabankPage parabankPage;

    @BeforeAll
    public static void setUp() {
        parabankPage = new ParabankPage(RunContext.getDriver());
    }

    @Given("que navego a la página principal de Parabank")
    public void navegarALaPaginaParabank() {
        RunContext.getDriver().get(PARABANK_PAGE);
    }

    @Given("accedo a la página de registro")
    public void accederALaPaginaDeRegister() {
        parabankPage.accederALaPaginaDeRegister();
    }

    @Given("la página de registro se muestra correctamente")
    public void paginaRegistroExitosa() {
        parabankPage.paginaRegistroExitosa();
    }

    @When("completo el formulario de registro con datos válidos")
    public void rellenarFormularioRegistro() {
        parabankPage.rellenarFormularioRegistro();
    }

    @When("clico en el botón Register")
    public void clicarRegisterBtn() {
        parabankPage.clicarRegisterBtn();
    }

    @Then("debería ver que la cuenta se ha creado correctamente")
    public void registroExitoso() {
        parabankPage.registroExitoso();
    }

    @When("cierro sesión si estoy autenticado")
    public void cerrarSesionParabank() {
        parabankPage.cerrarSesionParabank();
    }

    @When("inicio sesión con las credenciales válidas")
    public void iniciarSesionParabank() {
        parabankPage.iniciarSesionParabank();
    }

    @Then("debería ver que el login ha sido exitoso")
    public void loginExitoso() {
        parabankPage.loginExitoso();
    }
}
