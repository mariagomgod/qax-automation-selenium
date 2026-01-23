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

    public static Actor comprador;
    private final Faker faker = new Faker(new Locale("es"));

    @Before
    public void setUpCommon() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
        UsuarioContext.reset();
        CarritoContext.reset();
    }

    // Único step para el Background
    @Given("que el usuario accede a la tienda online TestingYes")
    public void que_el_usuario_accede_a_la_tienda_online_testingyes() {
        comprador.attemptsTo(
                NavegarAlHomeOnlineShop.porUrl()
        );
    }

    @Given("que existe un usuario registrado previamente")
    public void que_existe_un_usuario_registrado_previamente() {

        UsuarioContext.emailCreado = "qa_" + System.currentTimeMillis() + "_"
                + UUID.randomUUID().toString().substring(0, 6)
                + "@mailinator.com";

        String nombre = faker.name().firstName();
        String apellido = faker.name().lastName();

        comprador.attemptsTo(
                NavegarAlHomeOnlineShop.porUrl(),
                NavegarARegistroOnlineShop.porUrl(),
                RegistrarUsuarioOnlineShop.conLosDatos(nombre, apellido, UsuarioContext.emailCreado, "123456")
        );

        comprador.attemptsTo(
                that(UsuarioAutenticadoOnlineShop.exitosamente()).isTrue(),
                LogoutOnlineShop.porUrl()
        );
    }

    @Given("que existe un usuario registrado previamente con email")
    public void que_existe_un_usuario_registrado_previamente_con_email() {
        que_existe_un_usuario_registrado_previamente();
        UsuarioContext.passwordCreado = "123456";
    }

    // ---------------- HELPERS (reutilizables) ----------------

    public static String resolverToken(String value, String token, String reemplazo) {
        if (value == null) return "";
        String v = value.trim();
        if (v.isEmpty()) return "";
        return v.equals(token) ? reemplazo : value;
    }

    public static String resolveEmailToken(String value) {
        if (value == null) return "";
        String v = value.trim();
        if (v.isEmpty()) return "";

        if (v.equals("<randomEmail>")) {
            return "qa_" + System.currentTimeMillis() + "_"
                    + java.util.UUID.randomUUID().toString().substring(0, 6)
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

    public static String resolvePasswordToken(String value) {
        if (value == null) return "";
        String v = value.trim();
        if (v.isEmpty()) return "";

        if (v.equals("<existingPassword>")) {
            if (UsuarioContext.passwordCreado == null) {
                throw new IllegalStateException("UsuarioContext.passwordCreado es null. Asegúrate de setearla en CommonUsuarioSteps.");
            }
            return UsuarioContext.passwordCreado;
        }

        return value;
    }

}
