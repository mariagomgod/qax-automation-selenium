package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.HomePage.BTN_LOGOUT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SesionIniciada implements Question<Boolean> {

    public static SesionIniciada visible() {
        return new SesionIniciada();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(BTN_LOGOUT, isVisible()).forNoMoreThan(5).seconds()
        );

        return WebElementQuestion.the(BTN_LOGOUT)
                .answeredBy(actor)
                .isCurrentlyVisible();
    }
}
