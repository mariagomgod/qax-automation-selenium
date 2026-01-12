package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.qaxpert.ui.OnlineShopRegisterPage.LINK_SIGN_OUT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class UsuarioAutenticadoOnlineShop implements Question<Boolean> {

    public static UsuarioAutenticadoOnlineShop exitosamente() {
        return new UsuarioAutenticadoOnlineShop();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(LINK_SIGN_OUT, isVisible()).forNoMoreThan(10).seconds()
            );
        } catch (Exception ignored) {}

        return WebElementQuestion.the(LINK_SIGN_OUT)
                .answeredBy(actor)
                .isCurrentlyVisible();
    }
}

