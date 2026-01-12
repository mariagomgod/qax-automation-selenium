package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

import static com.qaxpert.ui.HomePage.BTN_LOGOUT;

public class CuentaCreada implements Question<Boolean> {

    public static CuentaCreada exitosamente() {
        return new CuentaCreada();
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        // Espera explícita porque el sistema tarda en crear la cuenta
        actor.attemptsTo(
                WaitUntil.the(
                        BTN_LOGOUT,
                        WebElementStateMatchers.isVisible()
                ).forNoMoreThan(5).seconds()
        );

        // Solo devuelve true o false
        return WebElementQuestion.the(BTN_LOGOUT)
                .answeredBy(actor)
                .isCurrentlyVisible();
    }
}