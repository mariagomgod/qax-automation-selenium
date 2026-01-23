package stepdefinitions;

import com.qaxpert.questions.MensajeError;
import com.qaxpert.questions.UsuarioAutenticadoOnlineShop;
import com.qaxpert.tasks.IniciarSesionOnlineShop;
import com.qaxpert.tasks.NavegarALoginOnlineShop;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static com.qaxpert.ui.OnlineShopLoginPage.LOGIN_ERROR;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class LoginUsuarioOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    @And("el usuario inicia sesión con los siguientes datos:")
    public void el_usuario_inicia_sesion_con_los_siguientes_datos(DataTable dataTable) {

        List<Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila = datos.get(0);

        String email = CommonUsuarioSteps.resolveEmailToken(fila.get("email"));
        String password = CommonUsuarioSteps.resolvePasswordToken(fila.get("password"));

        comprador.attemptsTo(
                NavegarALoginOnlineShop.porUrl(),
                IniciarSesionOnlineShop.conCredenciales(email, password)
        );
    }

    @Then("el sistema permite el acceso y muestra la sesión iniciada")
    public void el_sistema_permite_el_acceso_y_muestra_la_sesion_iniciada() {
        comprador.attemptsTo(
                that(UsuarioAutenticadoOnlineShop.exitosamente()).isTrue()
        );
    }

    @Then("el sistema muestra un mensaje de error indicando credenciales inválidas")
    public void el_sistema_muestra_un_mensaje_de_error_indicando_credenciales_invalidas() {
        comprador.attemptsTo(
                that(MensajeError.en(LOGIN_ERROR)).contains("Authentication failed.")
        );
    }
}


