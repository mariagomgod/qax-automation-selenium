package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopOrderConfirmationPage.CONFIRMATION_ORDER_MESSAGE;
import static com.qaxpert.ui.OnlineShopOrderPage.BTN_CONTINUE_ADDRESSES;
import static com.qaxpert.ui.OnlineShopOrderPage.BTN_CONTINUE_SHIPPING_METHOD;
import static com.qaxpert.ui.OnlineShopOrderPage.BTN_ORDER_WITH_AN_OBLIGATION_TO_PAY;
import static com.qaxpert.ui.OnlineShopOrderPage.SELECT_PAY_BY_CHECK;
import static com.qaxpert.ui.OnlineShopOrderPage.SELECT_SERVICE_AGREEMENT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IrAlProcesoDeCheckoutYPagoProductoOnlineShop implements Task {

    private final Target paymentSelector;

    public IrAlProcesoDeCheckoutYPagoProductoOnlineShop(Target paymentSelector) {
        this.paymentSelector = paymentSelector;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_CONTINUE_ADDRESSES),
                Click.on(BTN_CONTINUE_SHIPPING_METHOD),
                Click.on(paymentSelector),
                Click.on(SELECT_SERVICE_AGREEMENT),
                Click.on(BTN_ORDER_WITH_AN_OBLIGATION_TO_PAY)
        );

        actor.attemptsTo(
                WaitUntil.the(CONFIRMATION_ORDER_MESSAGE, isVisible()).forNoMoreThan(60).seconds()
        );
    }
}
