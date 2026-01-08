package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.qaxpert.ui.RegisterPage.MESSAGE_ERROR;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class MensajeError implements Question<String> {
    public static MensajeError visible() {
        return new MensajeError();
    }

    @Override
    public String answeredBy(Actor actor) {

        actor.attemptsTo(
                WaitUntil.the(
                        MESSAGE_ERROR,
                        isVisible()
                ).forNoMoreThan(5).seconds()
        );
        // Solo devuelve texto
        return Text.of(MESSAGE_ERROR)
                .answeredBy(actor);
    }
}
