package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.qaxpert.ui.OnlineShopCartPage.BTN_PROCEED_TO_CHECKOUT;
import static com.qaxpert.ui.OnlineShopProductDetailPage.MODAL_BTN_PROCEED_TO_CHECKOUT;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IrACheckoutDesdeLaModalOnlineShop implements Task {


    public static IrACheckoutDesdeLaModalOnlineShop ahora() {
        return instrumented(IrACheckoutDesdeLaModalOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(MODAL_BTN_PROCEED_TO_CHECKOUT),
                Click.on(BTN_PROCEED_TO_CHECKOUT)
        );
    }
}
