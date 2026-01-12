package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {
    /**
     * Esta clase representa la PANTALLA DEL HOME
     */
     public static final Target BTN_GO_TO_REGISTER = Target.the("Boton de registro de usuario desde el home")
            .located(By.xpath("//a[contains(@href, 'registro')]"));

    public static final Target BTN_LOGOUT = Target.the("Boton de cierre sesión")
            .located(By.xpath("//a[contains(@href, 'logout')]"));


}