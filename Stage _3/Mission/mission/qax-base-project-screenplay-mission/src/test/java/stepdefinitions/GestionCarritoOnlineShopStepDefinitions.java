package stepdefinitions;

import com.qaxpert.tasks.CambiarTallaYColorOnlineShop;
import com.qaxpert.tasks.EliminarProductoDelCarritoOnlineShop;
import com.qaxpert.tasks.IrAlCarritoOnlineShop;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.screenplay.questions.Text;

import java.math.BigDecimal;

import static com.qaxpert.ui.OnlineShopCartPage.*;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class GestionCarritoOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    private String precioUnitarioCarrito;
    private String totalCarrito;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
        precioUnitarioCarrito = null;
        totalCarrito = null;
    }

    @When("el usuario navega al carrito")
    public void el_usuario_navega_al_carrito() {
        comprador.attemptsTo(
                IrAlCarritoOnlineShop.porUrl()
        );
    }

    @Then("el producto aparece listado con precio correcto")
    public void el_producto_aparece_listado_con_precio_correcto() {
        precioUnitarioCarrito = Text.of(FIRST_ITEM_UNIT_PRICE).answeredBy(comprador);

        comprador.attemptsTo(
                that(CART_ITEMS).isDisplayed(),
                that(FIRST_ITEM_NAME).isDisplayed(),
                that(precioUnitarioCarrito).isNotBlank(),
                that(CarritoContext.precioDetalle).isNotBlank(),

                // “precio correcto”: carrito coincide con el detalle (normalizado)
                that(normalizarDinero(precioUnitarioCarrito))
                        .contains(normalizarDinero(CarritoContext.precioDetalle))
        );
    }

    @Given("que el usuario tiene un producto {string} en el carrito")
    public void que_el_usuario_tiene_un_producto_en_el_carrito(String producto) {
        // Reutiliza steps ya existentes llamando a Tasks directamente (sin duplicar anotaciones)
        comprador.attemptsTo(
                com.qaxpert.tasks.BuscarProductoOnlineShop.porTermino(producto),
                com.qaxpert.tasks.AbrirDetalleProductoExistenteOnlineShop.desdeElListado()
        );

        CarritoContext.precioDetalle = net.serenitybdd.screenplay.questions.Text
                .of(com.qaxpert.ui.OnlineShopProductDetailPage.UNIT_PRICE)
                .answeredBy(comprador);

        comprador.attemptsTo(
                CambiarTallaYColorOnlineShop.enElDetalle(),
                com.qaxpert.tasks.AgregarAlCarritoDesdeDetalleOnlineShop.ahora(),
                IrAlCarritoOnlineShop.porUrl()
        );

        totalCarrito = Text.of(CART_TOTAL).answeredBy(comprador);
        precioUnitarioCarrito = Text.of(FIRST_ITEM_UNIT_PRICE).answeredBy(comprador);
    }

    @When("el usuario elimina el producto del carrito")
    public void el_usuario_elimina_el_producto_del_carrito() {
        comprador.attemptsTo(
                EliminarProductoDelCarritoOnlineShop.primero()
        );
    }

    @Then("el producto se elimina y el total se actualiza")
    public void el_producto_se_elimina_y_el_total_se_actualiza() {
        comprador.attemptsTo(
                that(EMPTY_CART_MESSAGE).isDisplayed()
        );
    }

    @Then("el total del carrito corresponde a la suma de los productos")
    public void el_total_del_carrito_corresponde_a_la_suma_de_los_productos() {
        // Para 1 producto en carrito, “suma” = unit (qty=1)
        String unit = normalizarDinero(precioUnitarioCarrito);
        String total = normalizarDinero(totalCarrito);

        comprador.attemptsTo(
                that(unit).isNotBlank(),
                that(total).isNotBlank(),
                that(new BigDecimal(total)).isEqualTo(new BigDecimal(unit).multiply(new BigDecimal(2)))
        );
    }

    private static String normalizarDinero(String valor) {
        if (valor == null) return "";
        String v = valor.trim();
        v = v.replace("\u00A0", " ");
        v = v.replaceAll("\\s+", " ");
        v = v.replaceAll("\\$+", " ");
        return v.trim();
    }
}

