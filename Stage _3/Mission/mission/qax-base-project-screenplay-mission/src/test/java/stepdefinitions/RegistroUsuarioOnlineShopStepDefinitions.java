package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.MensajeError;
import com.qaxpert.questions.MensajeValidacionHtml5;
import com.qaxpert.questions.UsuarioAutenticadoOnlineShop;
import com.qaxpert.tasks.NavegarAlHomeOnlineShop;
import com.qaxpert.tasks.NavegarARegistroOnlineShop;
import com.qaxpert.tasks.RegistrarUsuarioOnlineShop;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.qaxpert.ui.OnlineShopRegisterPage.FIRST_NAME;
import static com.qaxpert.ui.OnlineShopRegisterPage.REGISTER_ERROR;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class RegistroUsuarioOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;
    private final Faker faker = new Faker(new Locale("es"));

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    @Given("que el usuario accede a la tienda online TestingYes")
    public void que_el_usuario_accede_a_la_tienda_online_testingyes() {
        comprador.attemptsTo(
                NavegarAlHomeOnlineShop.porUrl(),
                NavegarARegistroOnlineShop.porUrl()
        );
    }

    @When("el usuario se registra con los siguientes datos:")
    public void el_usuario_se_registra_con_los_siguientes_datos(DataTable dataTable) {
        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila = datos.get(0);

        String nombre = resolverToken(fila.get("nombre"), "<randomFN>", faker.name().firstName());
        String apellido = resolverToken(fila.get("apellido"), "<randomLN>", faker.name().lastName());
        String email = resolveEmailToken(fila.get("email"));
        String password = fila.get("password") == null ? "" : fila.get("password");

        comprador.attemptsTo(
                NavegarAlHomeOnlineShop.porUrl(),
                NavegarARegistroOnlineShop.porUrl(),
                RegistrarUsuarioOnlineShop.conLosDatos(nombre, apellido, email, password)
        );
    }

    @Then("el sistema crea la cuenta correctamente y el usuario queda autenticado")
    public void el_sistema_crea_la_cuenta_correctamente_y_el_usuario_queda_autenticado() {
        comprador.attemptsTo(
                that(UsuarioAutenticadoOnlineShop.exitosamente()).isTrue()
        );
    }

    @Then("el sistema muestra un mensaje de error indicando email duplicado")
    public void el_sistema_muestra_un_mensaje_de_error_indicando_email_duplicado() {
        comprador.attemptsTo(
                that(MensajeError.en(REGISTER_ERROR)).isNotBlank()
        );
    }

    @Then("el sistema muestra un mensaje de error indicando campos obligatorios")
    public void el_sistema_muestra_un_mensaje_de_error_indicando_campos_obligatorios() {
        comprador.attemptsTo(
                that(MensajeValidacionHtml5.delCampo(FIRST_NAME)).isNotBlank()
        );
    }

    // ---------------- Helpers ----------------

    private String resolverToken(String value, String token, String reemplazo) {
        if (value == null) return "";
        String v = value.trim();
        if (v.isEmpty()) return "";
        if (v.equals(token)) return reemplazo;
        return value;
    }

    private String resolveEmailToken(String value) {
        if (value == null) return "";
        String v = value.trim();

        if (v.isEmpty()) return "";

        if (v.equals("<randomEmail>")) {
            return "qa_" + System.currentTimeMillis() + "_"
                    + UUID.randomUUID().toString().substring(0, 6)
                    + "@mailinator.com";
        }

        if (v.equals("<existingEmail>")) {
            if (UsuarioContext.emailCreado == null) {
                throw new IllegalStateException("UsuarioContext.emailCreado es null. Ejecuta el Given de usuario registrado previamente con email.");
            }
            return UsuarioContext.emailCreado;
        }

        return value;
    }
}

