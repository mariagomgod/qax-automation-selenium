package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.CuentaCreada;
import com.qaxpert.tasks.CerrarSesion;
import com.qaxpert.tasks.RegistrarUsuario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.UUID;

import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class CommonUsuarioSteps {

    @Managed
    WebDriver browser;

    private Actor actor;
    private final Faker faker = new Faker(new Locale("es"));

    @Before
    public void setUpCommon() {
        actor = Actor.named("Anderson Montoya");
        actor.can(BrowseTheWeb.with(browser));

        // Reset por escenario (para no arrastrar datos)
        UsuarioContext.reset();
    }

    @Given("que existe un usuario registrado previamente")
    public void que_existe_un_usuario_registrado_previamente() {

        UsuarioContext.usuarioCreado = ("user_" + faker.name().username())
                .replaceAll("[^a-zA-Z0-9_]", "")
                + "_" + System.currentTimeMillis();

        UsuarioContext.emailCreado = "qa_" + System.currentTimeMillis() + "_"
                + UUID.randomUUID().toString().substring(0, 6)
                + "@mailinator.com";

        actor.attemptsTo(
                Open.url("https://amantesapescar.co/registro/"),
                RegistrarUsuario.conLosDatos(UsuarioContext.usuarioCreado, UsuarioContext.emailCreado, "123456")
        );

        actor.attemptsTo(
                that(CuentaCreada.exitosamente()).isTrue()
        );

        // Dejar sesión limpia para pruebas de login o reintentos
        actor.attemptsTo(
                CerrarSesion.ahora()
        );
    }
}
