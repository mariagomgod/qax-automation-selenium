package com.qaxpert.api.steps;

import com.qaxpert.api.pages.AccionesDeMenuPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.DROPDOWN_PAGE;

public class AccionesDeMenuSteps {

    private static AccionesDeMenuPage accionesDeMenuPage;

    @BeforeAll
    public static void setUp() {
        accionesDeMenuPage = new AccionesDeMenuPage(RunContext.getDriver());
    }

    @Given("que el usuario está en la página de dropdown menu")
    public void navegarAPaginaDropdownMenu() {
        RunContext.getDriver().get(DROPDOWN_PAGE); // define tu constante o léela de config
    }

    @When("hago click en el menú Use left-click here")
    public void useLeftClickHereMenu() {
        accionesDeMenuPage.useLeftClickHereMenu();
    }

    @Then("imprimo la acción realizada para Use left-click here")
    public void imprimirAccionClickBasico() {
        accionesDeMenuPage.imprimirAccionClickBasico();
    }

    @When("hago click en el menú Use right-click here")
    public void useRightClickHereMenu() {
        accionesDeMenuPage.useRightClickHereMenu();
    }

    @Then("imprimo la acción realizada para Use right-click here")
    public void imprimirAccionClickDerecho() {
        accionesDeMenuPage.imprimirAccionClickDerecho();
    }

    @When("hago click en el menú Use double-click here")
    public void useDoubleClickHereMenu() {
        accionesDeMenuPage.useDoubleClickHereMenu();
    }

    @Then("imprimo la acción realizada para Use double-click here")
    public void imprimirAccionClickDoble() {
        accionesDeMenuPage.imprimirAccionClickDoble();
    }
}
