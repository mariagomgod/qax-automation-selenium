package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopRegisterPage {

    public static final Target FORM_CUSTOMER = Target.the("formulario de creación de cuenta")
            .locatedBy("//form[@id='customer-form']");

    public static final Target FIRST_NAME = Target.the("campo First name")
            .locatedBy("//input[@name='firstname' and @type='text']");

    public static final Target LAST_NAME = Target.the("campo Last name")
            .locatedBy("//input[@name='lastname' and @type='text']");

    public static final Target EMAIL = Target.the("campo Email")
            .locatedBy("//input[@name='email' and @type='email']");

    public static final Target PASSWORD = Target.the("campo Password")
            .locatedBy("//input[@name='password' and @type='password']");

    public static final Target TERMS_PRIVACY = Target.the("checkbox términos y privacidad (psgdpr)")
            .locatedBy("//input[@name='psgdpr' and @type='checkbox']");

    public static final Target BTN_SAVE = Target.the("botón Save")
            .locatedBy("//button[@type='submit' and @data-link-action='save-customer']");

    public static final Target REGISTER_ERROR = Target.the("mensaje de error de registro")
            .locatedBy("//*[contains(@class,'alert-danger')][normalize-space()]");

    public static final Target LINK_SIGN_OUT = Target.the("link de cerrar sesión (Sign out)")
            .locatedBy("//a[contains(@class,'logout') and contains(@href,'mylogout')]");
}





