package com.qaxpert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.ScrollTo;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopRegisterPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistrarUsuarioOnlineShop implements Task {

    private final String nombre;
    private final String apellido;
    private final String email;
    private final String password;

    public RegistrarUsuarioOnlineShop(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public static RegistrarUsuarioOnlineShop conLosDatos(String nombre, String apellido, String email, String password) {
        return instrumented(RegistrarUsuarioOnlineShop.class, nombre, apellido, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(FORM_CUSTOMER, isVisible()).forNoMoreThan(10).seconds(),

                Enter.theValue(nombre).into(FIRST_NAME),
                Enter.theValue(apellido).into(LAST_NAME),
                Enter.theValue(email).into(EMAIL),
                Enter.theValue(password).into(PASSWORD)
        );

        // Checkbox required
        try {
            actor.attemptsTo(
                    Scroll.to(TERMS_PRIVACY),
                    Click.on(TERMS_PRIVACY)
            );
        } catch (Exception ignored) {}

        actor.attemptsTo(
                WaitUntil.the(BTN_SAVE, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(BTN_SAVE)
        );
    }
}



