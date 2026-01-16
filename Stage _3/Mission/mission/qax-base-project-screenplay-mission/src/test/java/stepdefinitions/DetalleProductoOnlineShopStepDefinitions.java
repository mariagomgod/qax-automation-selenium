package stepdefinitions;

import com.qaxpert.tasks.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.screenplay.questions.Text;

import java.math.BigDecimal;

import static com.qaxpert.ui.OnlineShopProductDetailPage.*;
import static com.qaxpert.ui.OnlineShopCartModal.MODAL_CART_TOTAL;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class DetalleProductoOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    // Cantidad interna (no aparece en la feature)
    private static final int CANTIDAD_PRUEBA = 2;

    private String precioUnitario;
    private int cantidadSeleccionada;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
        precioUnitario = null;
        cantidadSeleccionada = 0;
    }

    @When("el usuario abre el detalle de un producto existente")
    public void el_usuario_abre_el_detalle_de_un_producto_existente() {
        comprador.attemptsTo(
                com.qaxpert.tasks.BuscarProductoOnlineShop.porTermino("hummingbird"),
                AbrirDetalleProductoExistenteOnlineShop.desdeElListado()
        );

        precioUnitario = Text.of(UNIT_PRICE).answeredBy(comprador);
        comprador.attemptsTo(that(PRODUCT_TITLE).isDisplayed());
    }

    @When("el usuario cambia la talla y el color")
    public void el_usuario_cambia_la_talla_y_el_color() {
        comprador.attemptsTo(
                CambiarTallaYColorOnlineShop.enElDetalle()
        );
    }

    @Then("el sistema refleja la talla y el color seleccionados")
    public void el_sistema_refleja_la_talla_y_el_color_seleccionados() {
        comprador.attemptsTo(
                that(SIZE_SELECTED_OPTION).isDisplayed(),
                that(COLOR_RADIOS).isDisplayed()
        );
    }

    @When("el usuario cambia la cantidad")
    public void el_usuario_cambia_la_cantidad() {
        cantidadSeleccionada = CANTIDAD_PRUEBA;
        comprador.attemptsTo(
                CambiarCantidadOnlineShop.a(cantidadSeleccionada)
        );
    }

    @When("el usuario agrega el producto al carrito desde el detalle")
    public void el_usuario_agrega_el_producto_al_carrito_desde_el_detalle() {
        comprador.attemptsTo(
                AgregarAlCarritoDesdeDetalleOnlineShop.ahora()
        );
    }

    @Then("el total en el carrito corresponde al precio unitario por la cantidad")
    public void el_total_en_el_carrito_corresponde_al_precio_unitario_por_la_cantidad() {
        String totalCarrito = Text.of(MODAL_CART_TOTAL).answeredBy(comprador);

        String unit = normalizarDinero(precioUnitario);
        String total = normalizarDinero(totalCarrito);

        comprador.attemptsTo(
                that(unit).isNotBlank(),
                that(total).isNotBlank(),
                that(new BigDecimal(total)).isEqualTo(new BigDecimal(unit).multiply(new BigDecimal(cantidadSeleccionada)))
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

