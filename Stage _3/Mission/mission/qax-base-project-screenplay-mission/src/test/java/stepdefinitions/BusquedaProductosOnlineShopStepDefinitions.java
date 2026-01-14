package stepdefinitions;

import com.qaxpert.questions.HayResultadosDeBusqueda;
import com.qaxpert.questions.MensajeError;
import com.qaxpert.tasks.BuscarProductoOnlineShop;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static com.qaxpert.ui.OnlineShopSearchResultsPage.NO_RESULTS_MESSAGE;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class BusquedaProductosOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    // Términos “por debajo” (para que pasen los tests sin ponerlos en la feature)
    private static final String TERMINO_EXISTENTE = "hummingbird";
    private static final String TERMINO_NO_EXISTE = "producto_que_no_existe_12345";

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    @When("el usuario realiza una búsqueda con un producto existente")
    public void el_usuario_realiza_una_busqueda_con_un_producto_existente() {
        comprador.attemptsTo(
                BuscarProductoOnlineShop.porTermino(TERMINO_EXISTENTE)
        );
    }

    @When("el usuario realiza una búsqueda con un producto que no existe")
    public void el_usuario_realiza_una_busqueda_con_un_producto_que_no_existe() {
        comprador.attemptsTo(
                BuscarProductoOnlineShop.porTermino(TERMINO_NO_EXISTE)
        );
    }

    @Then("el sistema muestra productos relacionados con la búsqueda")
    public void el_sistema_muestra_productos_relacionados_con_la_busqueda() {
        comprador.attemptsTo(
                that(HayResultadosDeBusqueda.visibles()).isTrue()
        );
    }

    @Then("el sistema muestra un mensaje indicando que no hay resultados")
    public void el_sistema_muestra_un_mensaje_indicando_que_no_hay_resultados() {
        comprador.attemptsTo(
                that(MensajeError.en(NO_RESULTS_MESSAGE)).contains("No products were found.")
        );
    }
}

