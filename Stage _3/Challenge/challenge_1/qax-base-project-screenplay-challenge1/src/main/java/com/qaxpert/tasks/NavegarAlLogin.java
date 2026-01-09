package com.qaxpert.tasks;

import com.qaxpert.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavegarAlLogin implements Task {

    public static NavegarAlLogin porUrl() {
        return instrumented(NavegarAlLogin.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("https://amantesapescar.co/iniciar-sesion/"),
                WaitUntil.the(LoginPage.TXT_USERNAME, isVisible()).forNoMoreThan(5).seconds()
        );
    }
}
