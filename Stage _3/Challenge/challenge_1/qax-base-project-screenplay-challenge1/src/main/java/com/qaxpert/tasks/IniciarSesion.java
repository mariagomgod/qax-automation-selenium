package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.LoginPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion implements Task {

    private final String usuario;
    private final String password;

    public IniciarSesion(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public static IniciarSesion conCredenciales(String usuario, String password) {
        return instrumented(IniciarSesion.class, usuario, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(TXT_USERNAME, isVisible()).forNoMoreThan(5).seconds(),
                Enter.theValue(usuario).into(TXT_USERNAME),
                Enter.theValue(password).into(TXT_PASSWORD),
                Click.on(BTN_LOGIN)
        );
    }
}
