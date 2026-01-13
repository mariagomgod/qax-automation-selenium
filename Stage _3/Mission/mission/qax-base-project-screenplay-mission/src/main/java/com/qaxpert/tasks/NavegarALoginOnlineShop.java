package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopLoginPage.EMAIL;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarALoginOnlineShop implements Task {

    public static NavegarALoginOnlineShop porUrl() {
        return instrumented(NavegarALoginOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("http://www.testingyes.com/onlineshop/login?back=my-account"),
                WaitUntil.the(EMAIL, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
