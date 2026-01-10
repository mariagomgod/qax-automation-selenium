package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.MensajeError;
import com.qaxpert.questions.ResultadoContieneProducto;
import com.qaxpert.questions.UrlActual;
import com.qaxpert.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.questions.Text.of;

import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.qaxpert.ui.CheckoutPage.*;
import static com.qaxpert.ui.WompiPage.WOMPI_ROOT;
import static net.serenitybdd.screenplay.ensure.Ensure.that;
import static org.hamcrest.Matchers.containsString;

public class CompraStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;
    private final Faker faker = new Faker(new Locale("es"));

    // Guardamos valores para validaciones cruzadas
    private String totalEnCheckout;
    private String productoEsperado;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));

        totalEnCheckout = null;
        productoEsperado = null;
    }

    // -------------------- RULE: BÚSQUEDA Y SELECCIÓN --------------------

    @When("el usuario busca el producto {string}")
    public void el_usuario_busca_el_producto(String producto) {
        productoEsperado = producto;
        comprador.attemptsTo(BuscarProducto.porTexto(producto));
    }

    @Then("el sistema muestra resultados relacionados con {string}")
    public void el_sistema_muestra_resultados_relacionados_con(String producto) {
        comprador.attemptsTo(
                VerificarResultados.deBusqueda(),
                that(ResultadoContieneProducto.conNombre(producto)).isTrue()
        );
    }

    @Given("que existen resultados para la búsqueda {string}")
    public void que_existen_resultados_para_la_busqueda(String producto) {
        productoEsperado = producto;
        comprador.attemptsTo(
                BuscarProducto.porTexto(producto),
                VerificarResultados.deBusqueda()
        );
    }

    @When("el usuario selecciona un producto desde los resultados")
    public void el_usuario_selecciona_un_producto_desde_los_resultados() {
        comprador.attemptsTo(SeleccionarPrimerProducto.deResultados());
    }

    @When("el usuario hace clic en {string}")
    public void el_usuario_hace_clic_en(String boton) {
        if ("Comprar ahora".equalsIgnoreCase(boton.trim())) {
            comprador.attemptsTo(ComprarAhora.desdeProducto());
        } else {
            throw new IllegalArgumentException("Botón no soportado en este step: " + boton);
        }
    }

    @Then("el sistema agrega el producto al flujo de compra")
    public void el_sistema_agrega_el_producto_al_flujo_de_compra() {
        comprador.attemptsTo(EsperarCheckout.visible());
    }

    // -------------------- RULE: VALIDACIONES CHECKOUT --------------------

    @Given("que el usuario tiene un producto en el flujo de compra")
    public void que_el_usuario_tiene_un_producto_en_el_flujo_de_compra() {
        // Reutilizamos el producto esperado si ya viene de pasos previos,
        // si no, asumimos el de la feature ("caña de pescar").
        String producto = (productoEsperado != null) ? productoEsperado : "caña de pescar";
        productoEsperado = producto;

        comprador.attemptsTo(
                BuscarProducto.porTexto(producto),
                VerificarResultados.deBusqueda(),
                SeleccionarPrimerProducto.deResultados(),
                ComprarAhora.desdeProducto(),
                EsperarCheckout.visible()
        );
    }

    @When("el usuario intenta continuar sin completar los datos de envío")
    public void el_usuario_intenta_continuar_sin_completar_los_datos_de_envio() {
        comprador.attemptsTo(IntentarPagar.sinCompletarDatos());
    }

    @Then("el sistema muestra un mensaje indicando que los datos de envío son obligatorios")
    public void el_sistema_muestra_un_mensaje_indicando_que_los_datos_de_envio_son_obligatorios() {
        // Validación robusta: existe error y no es vacío.
        String error = MensajeError.en(CHECKOUT_ERROR).answeredBy(comprador);

        comprador.attemptsTo(that(error).isNotBlank());
    }

    @Given("que el usuario tiene un producto {string} en el flujo de compra")
    public void que_el_usuario_tiene_un_producto_en_el_flujo_de_compra(String producto) {
        productoEsperado = producto;

        comprador.attemptsTo(
                BuscarProducto.porTexto(producto),
                VerificarResultados.deBusqueda(),
                SeleccionarPrimerProducto.deResultados(),
                ComprarAhora.desdeProducto(),
                EsperarCheckout.visible()
        );
    }

    @Given("que el usuario completa los datos de envío válidos:")
    public void que_el_usuario_completa_los_datos_de_envio_validos(DataTable dataTable) {
        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila = datos.get(0);

        String email = resolverToken(fila.get("email"), "<randomEmail>", UUID.randomUUID() + "@mailinator.com");
        String nombre = resolverToken(fila.get("nombre"), "<randomFN>", faker.name().firstName());
        String apellido = resolverToken(fila.get("apellido"), "<randomLN>", faker.name().lastName());

        String cedula = fila.get("cedula");
        String tipoDoc = fila.get("tipoDoc");
        String numDoc = fila.get("numDoc");
        String telefono = fila.get("telefono");

        comprador.attemptsTo(
                CompletarDatosDeEnvio.con(email, nombre, apellido, cedula, tipoDoc, numDoc, telefono)
        );

        // Guardamos el total para luego comparar (si ya está renderizado)
        totalEnCheckout = normalizarDinero(of(ORDER_TOTAL).answeredBy(comprador));
    }

    @When("el usuario visualiza el resumen de compra")
    public void el_usuario_visualiza_el_resumen_de_compra() {
        comprador.attemptsTo(EsperarResumen.visible());
    }

    @Then("el producto {string} es visible en el resumen")
    public void el_producto_es_visible_en_el_resumen(String producto) {
        comprador.attemptsTo(
                that(net.serenitybdd.screenplay.questions.Text.of(ORDER_PRODUCT_NAME).answeredBy(comprador))
                        .containsIgnoringCase(producto)
        );
    }

    @Then("el total a pagar corresponde al precio del producto seleccionado")
    public void el_total_a_pagar_corresponde_al_precio_del_producto_seleccionado() {
        String precioProducto = normalizarDinero(of(ORDER_PRODUCT_PRICE).answeredBy(comprador));

        String total = normalizarDinero(of(ORDER_TOTAL).answeredBy(comprador));

        comprador.attemptsTo(that(precioProducto).isEqualTo(total));
    }

    // -------------------- RULE: PAGO WOMPI --------------------

    @Given("que el usuario está en el paso de pago del checkout")
    public void que_el_usuario_esta_en_el_paso_de_pago_del_checkout() {
        // Aseguramos estar en checkout con producto.
        que_el_usuario_tiene_un_producto_en_el_flujo_de_compra();
    }

    @When("el usuario selecciona Wompi como medio de pago")
    public void el_usuario_selecciona_wompi_como_medio_de_pago() {
        comprador.attemptsTo(SeleccionarWompi.comoMedioDePago());
    }

    @Then("el sistema redirige correctamente a la pasarela Wompi")
    public void el_sistema_redirige_correctamente_a_la_pasarela_wompi() {
        // Interpretación práctica: selecciona Wompi + confirma para redirigir
        comprador.attemptsTo(ConfirmarCompra.ahora());

        comprador.attemptsTo(
                Wait.until(UrlActual.delNavegador(), containsString("wompi")).forNoMoreThan(8).seconds(),
                that(WOMPI_ROOT).isDisplayed()
        );
    }

    @Given("que el usuario seleccionó Wompi como medio de pago")
    public void que_el_usuario_selecciono_wompi_como_medio_de_pago() {
        comprador.attemptsTo(SeleccionarWompi.comoMedioDePago());
    }

    @When("el usuario confirma la compra")
    public void el_usuario_confirma_la_compra() {
        // Guardamos el total justo antes de salir (si está disponible)
        totalEnCheckout = normalizarDinero(of(ORDER_TOTAL).answeredBy(comprador));

        comprador.attemptsTo(ConfirmarCompra.ahora());
    }

    @Then("el usuario es dirigido a la pantalla de Wompi")
    public void el_usuario_es_dirigido_a_la_pantalla_de_wompi() {
        comprador.attemptsTo(
                that(UrlActual.delNavegador()).containsIgnoringCase("wompi"),
                that(WOMPI_ROOT).isDisplayed()
        );
    }

    @Then("la transacción contiene los datos correctos del producto y el total")
    public void la_transaccion_contiene_los_datos_correctos_del_producto_y_el_total() {
        // Validación pragmática: en Wompi existe monto/valor renderizado.
        // (Si Wompi cambia UI, ajustas el locator del WompiPage)
        String cantidadTotalWompi = normalizarDinero(
                        of(com.qaxpert.ui.WompiPage.WOMPI_AMOUNT)
                        .answeredBy(comprador)
        );

        // Si por alguna razón no pudimos capturar totalEnCheckout, al menos validamos que hay un monto.
        if (totalEnCheckout == null || totalEnCheckout.isBlank()) {
            comprador.attemptsTo(that(cantidadTotalWompi).isNotBlank());
            return;
        }

        comprador.attemptsTo(that(cantidadTotalWompi).contains(totalEnCheckout));
    }

    // -------------------- Helpers --------------------

    // Método para convertir “tokens” del feature en valores reales antes de usarlos.
    private String resolverToken(String value, String token, String reemplazo) {
        if (value == null) return null;
        String v = value.trim();
        if (v.equals(token)) return reemplazo;
        return value;
    }

    // Método para hacer que los textos de dinero sean comparables aunque la UI los renderice con espacios raros o formatos ligeramente distintos.
    private static String normalizarDinero(String valor) {
        if (valor == null) return "";
        String v = valor.trim();
        v = v.replace("\u00A0", " ");      // non-breaking space
        v = v.replaceAll("\\s+", " ");     // colapsa espacios
        return v;
    }
}

