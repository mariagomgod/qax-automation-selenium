package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.MensajeError;
import com.qaxpert.questions.SesionIniciada;
import com.qaxpert.tasks.IniciarSesion;
import com.qaxpert.tasks.NavegarAlLogin;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.qaxpert.ui.LoginPage.MESSAGE_ERROR;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class LoginUsuarioStepDefinitions {

    @Managed
    WebDriver browser;
    private Actor comprador;
    // Faker para generar datos aleatorios
    private final Faker faker = new Faker(new Locale("es"));

    @Before
    public void setUp() {
        // asignando habilidad al actor
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    // Método para resolver tokens como <existingUser>
    private String resolveToken(String value) {
        if (value == null) return null;

        String v = value.trim();

        if (v.isEmpty()) return v;

        if (v.equals("<existingUser>")) {
            return UsuarioContext.usuarioCreado;
        }

        if (v.equals("<existingEmail>")) {
            return UsuarioContext.emailCreado;
        }

        return value;
    }

    // -------------------- BACKGROUND GENERAL --------------------
    @Given("que el usuario accede a la tienda virtual amantes a pescar")
    public void acceder_a_la_tienda() {
        comprador.attemptsTo(Open.url("https://amantesapescar.co/"));
    }

    @Given("que el usuario navega a la opción de inicio de sesión")
    public void navegar_a_login() {
        comprador.attemptsTo(NavegarAlLogin.porUrl());
    }

    @When("el usuario inicia sesión con los siguientes datos:")
    public void el_usuario_inicia_sesion_con_los_siguientes_datos(DataTable dataTable) {

        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila = datos.get(0);

        String username = resolveToken(fila.get("usuario"));
        String password = resolveToken(fila.get("password"));

        comprador.attemptsTo(
                IniciarSesion.conCredenciales(username, password)
        );
    }

    @Then("el sistema permite el acceso y muestra la sesión iniciada")
    public void el_sistema_permite_el_acceso_y_muestra_sesion() {
        comprador.attemptsTo(
                that(SesionIniciada.visible()).isTrue()
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que el usuario no existe")
    public void error_usuario_no_existe() {
        comprador.attemptsTo(
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("Error:"),
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("el nombre de usuario"),
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("no está registrado en este sitio")
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que la contraseña es incorrecta")
    public void error_contrasena_incorrecta() {
        comprador.attemptsTo(
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("Error:"),
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("la contraseña que has introducido"),
                that(MensajeError.en(MESSAGE_ERROR)).containsIgnoringCase("no es correcta")
        );
    }

    @Then("el sistema muestra un mensaje indicando que el usuario y la contraseña son obligatorios")
    public void error_campos_obligatorios() {
        comprador.attemptsTo(
                that(MensajeError.en(MESSAGE_ERROR))
                        .contains("Username and password are required.")
        );
    }
}
