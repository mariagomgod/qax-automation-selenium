package com.qaxpert.api.steps;

import com.qaxpert.api.pages.ScrollInfinitoPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ScrollInfinitoSteps {

    private static ScrollInfinitoPage scrollInfinitoPage;

    @BeforeAll
    public static void setUp() {
        scrollInfinitoPage = new ScrollInfinitoPage(RunContext.getDriver());
    }

    @Given("que el usuario está en la página de scroll infinito")
    public void navegarAPaginaScrollInfinito() {

        scrollInfinitoPage.navegarAPaginaScrollInfinito();
    }

    @When("hace scroll hasta cargar al menos 20 elementos nuevos")
    public void hacerScroll() {

        scrollInfinitoPage.hacerScroll();
    }

    @Then("imprime los textos de los elementos visibles")
    public void imprimirTextos() {

        scrollInfinitoPage.imprimirTextos();
    }
}
