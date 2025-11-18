package com.qaxpert.api.steps;

import com.qaxpert.api.pages.AmazonPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.AMAZON_PAGE;

public class AmazonSteps {

    private static AmazonPage amazonPage;

    @BeforeAll
    public static void setUp() {
        amazonPage = new AmazonPage(RunContext.getDriver());
    }

    @Given("que navego a la web de Amazon")
    public void navegarALaPaginaDeAmazon() {
        RunContext.getDriver().get(AMAZON_PAGE);
    }

    @When("hago clic en el botón Continue shopping si aparece")
    public void clickContinueShoppingBtnIfPresent() {
        amazonPage.clickContinueShoppingBtnIfPresent();
    }

    @When("clico en el botón CONTINUAR")
    public void clickEnContinuarBtn() {
        amazonPage.clickEnContinuarBtn();
    }

    @When("clico en la pestaña Ofertas del Día")
    public void clickEnOfertasDelDiaTab() {
        amazonPage.clickEnOfertasDelDiaTab();
    }

    @When("aplico el filtro Ofertas relámpago")
    public void selectOfertasRelampagoFilter() {
        amazonPage.selectOfertasRelampagoFilter();
    }

    @When("clico en la pestaña Outlet")
    public void clickEnOutletTab() {
        amazonPage.clickEnOutletTab();
    }

    @When("clico en el enlace JUGUETES Y JUEGOS")
    public void clickEnJuguetesYJuegosLink() {
        amazonPage.clickEnJuguetesYJuegosLink();
    }

    @When("clico en la subcategoría Peluches")
    public void clickEnPeluchesSubcategory() {
        amazonPage.clickEnPeluchesSubcategory();
    }

    @When("activo el filtro Prime")
    public void clickEnPrimeFilter() {
        amazonPage.clickEnPrimeFilter();
    }

    @When("clico en la subcategoría Monederos de Felpa")
    public void clickEnMonederosDeFelpaSubcategory() {
        amazonPage.clickEnMonederosDeFelpaSubcategory();
    }

    @When("clico en la pestaña Compra juguetes más vendidos")
    public void clickEnCompraDeJuguetesMasVendidosTab() {
        amazonPage.clickEnCompraDeJuguetesMasVendidosTab();
    }

    @Then("debería ver el listado de juguetes más vendidos cargado correctamente")
    public void ListadoJuguetesMasVendidosCargadoCorrectamente() {
        amazonPage.ListadoJuguetesMasVendidosCargadoCorrectamente();
    }
}
