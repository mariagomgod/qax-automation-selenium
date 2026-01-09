package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target TXT_USERNAME = Target.the("campo usuario / email")
            .locatedBy("//input[@id='user_login']");

    public static final Target TXT_PASSWORD = Target.the("campo contraseña")
            .locatedBy("//input[@id='user_pass']");

    public static final Target BTN_LOGIN = Target.the("botón iniciar sesión")
            .locatedBy("//input[@name='wp-submit' or @id='wp-submit']");

    public static final Target MESSAGE_ERROR = Target.the("mensaje de error")
            .locatedBy("//div[@class='jet-login-message']");
}
