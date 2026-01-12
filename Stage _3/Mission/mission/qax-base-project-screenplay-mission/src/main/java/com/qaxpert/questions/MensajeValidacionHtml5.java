package com.qaxpert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MensajeValidacionHtml5 implements Question<String> {

    private final Target campo;

    public MensajeValidacionHtml5(Target campo) {
        this.campo = campo;
    }

    public static MensajeValidacionHtml5 delCampo(Target campo) {
        return new MensajeValidacionHtml5(campo);
    }

    @Override
    public String answeredBy(Actor actor) {
        WebElement el = campo.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        Object msg = js.executeScript("return arguments[0].validationMessage;", el);
        return msg == null ? "" : msg.toString();
    }
}