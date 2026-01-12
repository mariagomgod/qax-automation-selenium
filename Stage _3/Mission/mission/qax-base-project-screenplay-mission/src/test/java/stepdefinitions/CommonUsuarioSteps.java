package stepdefinitions;

import com.github.javafaker.Faker;
import com.qaxpert.questions.UsuarioAutenticadoOnlineShop;
import com.qaxpert.tasks.LogoutOnlineShop;
import com.qaxpert.tasks.NavegarAlHomeOnlineShop;
import com.qaxpert.tasks.NavegarARegistroOnlineShop;
import com.qaxpert.tasks.RegistrarUsuarioOnlineShop;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
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
        UsuarioContext.reset();
    }

    @Given("que existe un usuario registrado previamente")
    public void que_existe_un_usuario_registrado_previamente() {

        UsuarioContext.emailCreado = "qa_" + System.currentTimeMillis() + "_"
                + UUID.randomUUID().toString().substring(0, 6)
                + "@mailinator.com";

        String nombre = faker.name().firstName();
        String apellido = faker.name().lastName();

        actor.attemptsTo(
                NavegarAlHomeOnlineShop.porUrl(),
                NavegarARegistroOnlineShop.porUrl(),
                RegistrarUsuarioOnlineShop.conLosDatos(nombre, apellido, UsuarioContext.emailCreado, "123456")
        );

        actor.attemptsTo(
                that(UsuarioAutenticadoOnlineShop.exitosamente()).isTrue(),
                LogoutOnlineShop.porUrl()
        );
    }

    @Given("que existe un usuario registrado previamente con email")
    public void que_existe_un_usuario_registrado_previamente_con_email() {
        que_existe_un_usuario_registrado_previamente();
    }
}
