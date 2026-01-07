package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Esta clase representa la PANTALLA DE REGISTRO
 */
public class RegisterPage {

    public static final Target TXT_USERNAME = Target.the("campo nombre de usuario")
            .locatedBy("//input[@id='jet_username']");

    public static final Target TXT_EMAIL = Target.the("campo correo electrónico")
            .locatedBy("//input[@id='jet_email']");

    public static final Target TXT_PASSWORD = Target.the("campo contraseña")
            .locatedBy("//input[@id='jet_password']");

    public static final Target TXT_CONFIRM_PASSWORD = Target.the("campo confirmar contraseña")
            .locatedBy("//input[@id='jet_password_confirm']");

    public static final Target BTN_REGISTER = Target.the("botón registrarse")
            .locatedBy("//button[@name='register']");

    public static final Target MESSAGE_ERROR = Target.the("Mensaje de error")
            .locatedBy("//div[@class='jet-register-message']");


}