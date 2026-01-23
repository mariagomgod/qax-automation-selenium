package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.tasks.AbrirDetalleProductoExistenteOnlineShop;
import com.qaxpert.tasks.AgregarAlCarritoDesdeDetalleOnlineShop;
import com.qaxpert.tasks.BuscarProductoOnlineShop;
import com.qaxpert.tasks.CambiarTallaYColorOnlineShop;
import com.qaxpert.tasks.CrearDireccionOnlineShop;
import com.qaxpert.tasks.IrACheckoutDesdeLaModalOnlineShop;
import com.qaxpert.tasks.NavegarADireccionesOnlineShop;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.qaxpert.ui.OnlineShopCheckoutPage.ALIAS_ADDRESS;
import static net.serenitybdd.screenplay.ensure.Ensure.that;
import static stepdefinitions.CommonUsuarioSteps.comprador;

public class GestionDireccionesOnlineShopStepDefinitions {

    private final Faker faker = new Faker(new Locale("es"));

    @When("el usuario agrega una nueva dirección de envío con los siguientes datos:")
    public void el_usuario_agrega_una_nueva_direccion_de_envio_con_los_siguientes_datos(DataTable dataTable) {

        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila = datos.get(0);

        String alias = resolverToken(fila.get("alias"), "<randomAlias>", "Dir_" + System.currentTimeMillis());
        String direccion = resolverToken(fila.get("direccion"), "<randomAddress>", faker.address().streetAddress());
        String ciudad = resolverToken(fila.get("ciudad"), "<randomCity>", faker.address().city());
        String postal = fila.get("postal") == null ? "" : fila.get("postal").trim();
        String telefono = fila.get("telefono") == null ? "" : fila.get("telefono").trim();
        String pais = fila.get("pais") == null ? "United States" : fila.get("pais").trim();
        String state = fila.get("state") == null ? "California" : fila.get("state").trim();

        UsuarioContext.aliasDireccionCreada = alias;

        comprador.attemptsTo(
                NavegarADireccionesOnlineShop.porLink(),
                CrearDireccionOnlineShop.conDatos(alias, direccion, ciudad, postal, telefono, pais, state)
        );
    }

    @Then("la dirección queda disponible para selección durante el checkout")
    public void la_direccion_queda_disponible_para_seleccion_durante_el_checkout() {

        // Reutilizo meter un producto al carrito para poder entrar a checkout
        comprador.attemptsTo(
                BuscarProductoOnlineShop.porTermino("hummingbird"),
                AbrirDetalleProductoExistenteOnlineShop.desdeElListado(),
                CambiarTallaYColorOnlineShop.enElDetalle(),
                AgregarAlCarritoDesdeDetalleOnlineShop.ahora(),
                IrACheckoutDesdeLaModalOnlineShop.ahora()
        );

        String alias = UsuarioContext.aliasDireccionCreada;

        comprador.attemptsTo(
                that(Text.of(ALIAS_ADDRESS).answeredBy(comprador)).isEqualTo(alias)
        );
    }

    private String resolverToken(String value, String token, String reemplazo) {
        if (value == null) return "";
        String v = value.trim();
        if (v.equals(token)) return reemplazo;
        return value;
    }
}




