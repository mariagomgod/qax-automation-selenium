package stepdefinitions;

import com.qaxpert.tasks.AbrirQuickViewOnlineShop;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;

import static com.qaxpert.ui.OnlineShopQuickViewModal.*;
import static net.serenitybdd.screenplay.ensure.Ensure.that;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class QuickViewProductoOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    @When("el usuario abre la vista rápida Quick View desde el listado")
    public void el_usuario_abre_la_vista_rapida_quick_view_desde_el_listado() {
        comprador.attemptsTo(
                AbrirQuickViewOnlineShop.desdeElListado()
        );
    }

    @Then("el sistema muestra una ventana modal con información básica")
    public void el_sistema_muestra_una_ventana_modal_con_informacion_basica() {
        comprador.attemptsTo(
                WaitUntil.the(MODAL, isVisible()).forNoMoreThan(10).seconds(),
                that(MODAL).isDisplayed()
        );
    }

    @Then("se visualiza el nombre, el precio y la opción de compra")
    public void se_visualiza_el_nombre_el_precio_y_la_opcion_de_compra() {
        comprador.attemptsTo(
                that(PRODUCT_NAME).isDisplayed(),
                that(PRODUCT_PRICE).isDisplayed(),
                that(BTN_ADD_TO_CART).isDisplayed()
        );
    }
}
