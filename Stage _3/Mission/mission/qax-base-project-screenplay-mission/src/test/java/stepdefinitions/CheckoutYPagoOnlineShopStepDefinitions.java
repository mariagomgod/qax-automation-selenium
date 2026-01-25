package stepdefinitions;

import com.qaxpert.tasks.AbrirDetalleProductoExistenteOnlineShop;
import com.qaxpert.tasks.AgregarAlCarritoDesdeDetalleOnlineShop;
import com.qaxpert.tasks.BuscarProductoOnlineShop;
import com.qaxpert.tasks.CambiarTallaYColorOnlineShop;
import com.qaxpert.tasks.IrACheckoutDesdeLaModalOnlineShop;
import com.qaxpert.tasks.IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop;
import com.qaxpert.tasks.IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop;
import com.qaxpert.ui.OnlineShopProductDetailPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.WebDriver;

import static com.qaxpert.ui.OnlineShopOrderConfirmationPage.FINAL_ORDER_ITEMS;
import static com.qaxpert.ui.OnlineShopOrderConfirmationPage.PAYMENT_METHOD;
import static com.qaxpert.ui.OnlineShopOrderConfirmationPage.PRICE_ORDER;
import static net.serenitybdd.screenplay.ensure.Ensure.that;

public class CheckoutYPagoOnlineShopStepDefinitions {

    @Managed
    WebDriver browser;

    private Actor comprador;

    @Before
    public void setUp() {
        comprador = Actor.named("Anderson Montoya");
        comprador.can(BrowseTheWeb.with(browser));
    }

    @When("el usuario procede al checkout con {string}")
    public void el_usuario_procede_al_checkout(String pago) {
        comprador.attemptsTo(
                BuscarProductoOnlineShop.porTermino("hummingbird"),
                AbrirDetalleProductoExistenteOnlineShop.desdeElListado(),
                CambiarTallaYColorOnlineShop.enElDetalle()
        );

        CarritoContext.precioDetalle = Text.of(OnlineShopProductDetailPage.UNIT_PRICE)
                .answeredBy(comprador);

        comprador.attemptsTo(
                AgregarAlCarritoDesdeDetalleOnlineShop.ahora(),
                IrACheckoutDesdeLaModalOnlineShop.ahora(),
                pago.equals("cheque") ?
                        IrAlProcesoDeCheckoutYPagoProductoConChequeOnlineShop.ahora() :
                        IrAlProcesoDeCheckoutYPagoProductoConTransferenciaOnlineShop.ahora()
        );

        CarritoContext.metodoPago = "Payment method: " + (pago.equals("cheque") ? "Payments by check" : "Bank transfer");
    }

    @Then("el sistema muestra el resumen final del pedido")
    public void el_sistema_muestra_el_resumen_final_del_pedido() {
        comprador.attemptsTo(
                that(Text.of(FINAL_ORDER_ITEMS)).containsIgnoringCase("hummingbird"),
                that(Text.of(PRICE_ORDER)).isEqualTo(CarritoContext.precioDetalle),
                that(Text.of(PAYMENT_METHOD)).isEqualTo(CarritoContext.metodoPago)
        );
    }
}
