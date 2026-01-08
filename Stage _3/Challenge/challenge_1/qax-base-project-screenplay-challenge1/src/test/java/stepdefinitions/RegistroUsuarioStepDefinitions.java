package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.CuentaCreada;
import com.qaxpert.tasks.CerrarSesion;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

import com.qaxpert.tasks.NavegarAlRegistro;
import com.qaxpert.tasks.RegistrarUsuario;
import com.qaxpert.tasks.RegistrarUsuarioErroneo;
import com.qaxpert.questions.MensajeError;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
public class RegistroUsuarioStepDefinitions {

    @Managed
    WebDriver browser;
    private Actor comprador;
    // Faker para generar datos aleatorios
    private final Faker faker = new Faker(new Locale("es"));
    // Variables para guardar el usuario/email creados en el Background del Rule
    private String usuarioCreado;
    private String emailCreado;

    @Before
    public void setUp() {
        // asignando habilidad al actor
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));

        // Reset de valores para cada escenario, es decir, cada vez que arranca un escenario, deja esas variables vacías
        usuarioCreado = null;
        emailCreado = null;
    }

    // Método para resolver tokens como <randomEmail>, <randomUser>, etc
    private String resolveToken(String value) {
        if (value == null) return null;

        String v = value.trim();

        // Si viene vacío, se deja vacío (para escenarios negativos de campos obligatorios)
        if (v.isEmpty()) return v;

        switch (v) {
            case "<randomUser>":
                return ("user_" + faker.name().username())
                        .replaceAll("[^a-zA-Z0-9_]", "")
                        + "_" + System.currentTimeMillis();

            case "<randomEmail>":
                return "qa_" + System.currentTimeMillis() + "_"
                        + UUID.randomUUID().toString().substring(0, 6)
                        + "@mailinator.com"; // cambia el dominio si tu sistema bloquea mails temporales

            case "<existingUser>":
                return usuarioCreado;

            case "<existingEmail>":
                return emailCreado;

            default:
                return value; // si no es token, se respeta
        }
    }

    // -------------------- BACKGROUND GENERAL --------------------

    @Given("que el comprador accede a la tienda virtual amantes a pescar")
    public void que_el_comprador_accede_a_la_tienda_virtual() {
        comprador.attemptsTo(
                Open.url("https://amantesapescar.co/")
        );
    }

    @Given("que el comprador navega a la opción de registro")
    public void que_el_comprador_navega_a_la_opcion_de_registro() {
        comprador.attemptsTo(
                NavegarAlRegistro.desdeElHome());
    }


    // -------------------- RULE BACKGROUND (DUPLICADOS) --------------------

    @Given("que existe un usuario registrado previamente")
    public void que_existe_un_usuario_registrado_previamente() {

        // Creamos un usuario real (único) para luego provocar duplicados
        usuarioCreado = resolveToken("<randomUser>");
        emailCreado = resolveToken("<randomEmail>");

        comprador.attemptsTo(
                RegistrarUsuario.conLosDatos(usuarioCreado, emailCreado, "123456")
        );

        // Validamos que se creó (si no se crea, el escenario de duplicado no tendría sentido)
        comprador.attemptsTo(
                that(CuentaCreada.exitosamente()).isTrue()
        );

        // Evita intermitencias: salimos de la sesión y volvemos a registro
        comprador.attemptsTo(
                CerrarSesion.ahora()
        );
    }

    // -------------------- ESCENARIO POSITIVO --------------------

    @When("el comprador se registra con los siguientes datos:")
    public void el_comprador_se_registra_con_los_siguientes_datos(DataTable dataTable) {

        // Convertimos la tabla de Cucumber en una lista de mapas
        // Cada fila es un Map: columna -> valor
        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);

        // Tomamos la primera fila de la tabla
        Map<String, String> usuarioData = datos.get(0);

        // Usamos resolveToken() antes de enviar al Task
        String usuario = resolveToken(usuarioData.get("usuario"));
        String email = resolveToken(usuarioData.get("email"));
        String password = resolveToken(usuarioData.get("password"));

        comprador.attemptsTo(
                RegistrarUsuario.conLosDatos(usuario, email, password)
        );
    }

    @Then("el sistema crea la cuenta correctamente")
    public void el_sistema_crea_la_cuenta_correctamente() {
        comprador.attemptsTo(
                that(CuentaCreada.exitosamente()).isTrue()
        );
    }

    // -------------------- ESCENARIOS NEGATIVOS --------------------

    @When("ingresa contraseñas diferentes en el formulario de registro")
    public void ingresa_contrasenias_diferentes_en_el_formulario_de_registro(DataTable dataTable) {
        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> usuarioData = datos.get(0);

        // Usamos resolveToken() antes de enviar al Task
        String usuario = resolveToken(usuarioData.get("usuario"));
        String email = resolveToken(usuarioData.get("email"));
        String password = resolveToken(usuarioData.get("password"));

        comprador.attemptsTo(
                RegistrarUsuarioErroneo.conContraseniasDiferentes(usuario, email, password)
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que las contraseñas no coinciden")
    public void muestra_error_contrasenas_no_coinciden() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Entered passwords don't match")
        );
    }

    @Then("el sistema muestra un mensaje indicando que los campos son obligatorios")
    public void muestra_mensaje_campos_obligatorios() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Please enter a valid account username")
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que la contraseña es demasiado corta")
    public void muestra_error_password_corta() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Password must be at least 6 characters long.")
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que el usuario ya existe")
    public void muestra_error_usuario_duplicado() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("An account is already registered with that username. Please choose another.")
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que el email ya está registrado")
    public void muestra_error_email_duplicado() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("An account is already registered with your email address. Please log in.")
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que el email no es válido")
    public void muestra_error_email_invalido() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Please enter a valid email address including '@'.")
        );
    }
}
