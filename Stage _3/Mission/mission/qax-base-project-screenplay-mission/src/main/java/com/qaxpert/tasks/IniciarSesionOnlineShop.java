package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopLoginPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesionOnlineShop implements Task {

    private final String email;
    private final String password;

    public IniciarSesionOnlineShop(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static IniciarSesionOnlineShop conCredenciales(String email, String password) {
        return instrumented(IniciarSesionOnlineShop.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(EMAIL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(email).into(EMAIL),
                Enter.theValue(password).into(PASSWORD),
                WaitUntil.the(BTN_SIGN_IN, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(BTN_SIGN_IN)
        );
    }
}

