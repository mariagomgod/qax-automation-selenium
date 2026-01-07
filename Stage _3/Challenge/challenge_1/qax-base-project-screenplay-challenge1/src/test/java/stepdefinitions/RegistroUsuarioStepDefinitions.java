package stepdefinitions;

import com.qaxpert.questions.CuentaCreada;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import com.qaxpert.tasks.NavegarAlRegistro;
import com.qaxpert.tasks.RegistrarUsuario;
import com.qaxpert.tasks.RegistrarUsuarioErroneo;
import com.qaxpert.questions.MensajeError;
import com.qaxpert.questions.CuentaCreada;
import java.util.List;
import java.util.Map;

public class RegistroUsuarioStepDefinitions {

    @Managed
    WebDriver browser;
    private Actor comprador;

    @Before
    public void setUp() {
        // asignando habilidad al actor
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    // -------------------- BACKGROUND --------------------

    @Given("que el comprador accede a la tienda viritual amantes a pescar")
    public void que_el_comprador_accede_a_la_tienda_virtual() {
        comprador.attemptsTo(
                Open.url("https://amantesapescar.co/")
        );
    }

    @Given("que el comprador navega a la opción de registro")
    public void que_el_comprador_navega_a_la_opcion_de_registro() {
        comprador.attemptsTo(NavegarAlRegistro.desdeElHome());
    }

    // -------------------- ESCENARIO POSITIVO --------------------

    @When("el comprador se registra con los siguientes datos:")
    public void el_comprador_se_registra_con_los_siguientes_datos(DataTable dataTable) {

        // Convertimos la tabla de Cucumber en una lista de mapas
        // Cada fila es un Map: columna -> valor
        List<Map<String, String>> datos = dataTable.asMaps();

        // Tomamos la primera fila de la tabla
        Map<String, String> usuarioData = datos.get(0);

        comprador.attemptsTo(
                RegistrarUsuario.conLosDatos(
                        usuarioData.get("usuario"),
                        usuarioData.get("email"),
                        usuarioData.get("password")
                )
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
    public void ingresa_contraseñas_diferentes_en_el_formulario_de_registro(DataTable dataTable) {
        List<Map<String, String>> datos = dataTable.asMaps();
        Map<String, String> usuarioData = datos.get(0);
        comprador.attemptsTo(
                RegistrarUsuarioErroneo.conContraseñaDiferentes(
                        usuarioData.get("usuario"),
                        usuarioData.get("email"),
                        usuarioData.get("password")
                )
        );
    }

    @Then("el sistema muestra un mensaje de error indicando que las contraseñas no coinciden")
    public void muestra_error_contrasenas_no_coinciden() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Entered passwords don't matchh")
        );
    }
    @Then("el sistema muestra un mensaje indicando que los campos son obligatorios")
    public void muestra_mensaje_campos_obligatorios() {
        comprador.attemptsTo(
                that(MensajeError.visible())
                        .contains("Please enter a valid account username")
        );
    }
}