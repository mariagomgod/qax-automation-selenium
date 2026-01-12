package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class MensajeError implements Question<String> {

    private final Target target;

    public MensajeError(Target target) {
        this.target = target;
    }

    public static MensajeError en(Target target) {
        return new MensajeError(target);
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(target, isVisible()).forNoMoreThan(5).seconds()
        );
        return Text.of(target).answeredBy(actor);
    }
}
