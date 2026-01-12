package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class WompiPage {

    // Contenedor / marca general de Wompi
    public static final Target WOMPI_ROOT = Target.the("pantalla de Wompi")
            .locatedBy("//*[contains(translate(., 'WOMPI', 'wompi'), 'wompi')]");

    // Cantidad total visible
    public static final Target WOMPI_AMOUNT = Target.the("Cantidad total visible en Wompi")
            .locatedBy("(//*[contains(.,'COP') or contains(.,'$')])[1]");
}

