package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopRegisterPage.FORM_CUSTOMER;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarARegistroOnlineShop implements Task {

    public static NavegarARegistroOnlineShop porUrl() {
        return instrumented(NavegarARegistroOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://www.testingyes.com/onlineshop/login?create_account=1"),
                WaitUntil.the(FORM_CUSTOMER, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}


