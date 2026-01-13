package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopLoginPage {

    public static final Target LOGIN_FORM = Target.the("formulario de login")
            .locatedBy("//form[@id='login-form']");

    public static final Target EMAIL = Target.the("campo email login")
            .locatedBy("//form[@id='login-form']//input[@name='email' and @type='email']");

    public static final Target PASSWORD = Target.the("campo password login")
            .locatedBy("//form[@id='login-form']//input[@name='password' and @type='password']");

    public static final Target BTN_SIGN_IN = Target.the("botón Sign in")
            .locatedBy("//button[@id='submit-login']");

    public static final Target LOGIN_ERROR = Target.the("mensaje de error login")
            .locatedBy("//li[contains(@class,'alert') and contains(@class,'alert-danger') and normalize-space()='Authentication failed.']");
}

