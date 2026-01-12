package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopRegisterPage.LINK_SIGN_OUT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class LogoutOnlineShop implements Task {

    public static LogoutOnlineShop porUrl() {
        return instrumented(LogoutOnlineShop.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LINK_SIGN_OUT , isClickable()).forNoMoreThan(10).seconds(),
                Click.on(LINK_SIGN_OUT)
        );
    }
}
