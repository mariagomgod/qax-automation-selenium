package com.qaxpert.api.steps;

import com.qaxpert.api.pages.NavegacionEnElMenuPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.HEROKUAPP_PAGE;

public class NavegacionEnElMenuSteps {

    private static NavegacionEnElMenuPage navegacionEnElMenuPage;

    @BeforeAll
    public static void setUp() {
        navegacionEnElMenuPage = new NavegacionEnElMenuPage(RunContext.getDriver());
    }

    @Given("que estoy en la página principal de CURA Healthcare Service")
    public void navegarAPaginaPrincipal() {
        RunContext.getDriver().get(HEROKUAPP_PAGE);
    }

    @When("abro el menú lateral y navego a {string}")
    public void menuLateralYNavegacionAEnlace(String enlace) {
        navegacionEnElMenuPage.menuLateralYNavegacionAEnlace(enlace);
    }

    @Then("imprimo en consola el título de la homepage")
    public void imprimirTituloHomePage() {
        navegacionEnElMenuPage.imprimirTituloHomePage();
    }

    @Then("imprimo en consola el título History")
    public void imprimirTituloHistory() {
        navegacionEnElMenuPage.imprimirTituloHistory();
    }

    @Then("imprimo en consola el título Profile")
    public void imprimirTituloProfile() {
        navegacionEnElMenuPage.imprimirTituloProfile();
    }
}
