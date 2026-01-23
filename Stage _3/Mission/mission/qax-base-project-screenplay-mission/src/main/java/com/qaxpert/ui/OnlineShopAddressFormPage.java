package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopAddressFormPage {

    public static final Target ALIAS = Target.the("campo Alias")
            .locatedBy("//input[@name='alias']");

    public static final Target ADDRESS1 = Target.the("campo Address")
            .locatedBy("//input[@name='address1']");

    public static final Target CITY = Target.the("campo City")
            .locatedBy("//input[@name='city']");

    public static final Target STATE = Target.the("select State")
            .locatedBy("//div[contains(@class, 'form-group')]//select[@name='id_state']");

    public static final Target POSTCODE = Target.the("campo Postcode")
            .locatedBy("//input[@name='postcode']");

    public static final Target PHONE = Target.the("campo Phone")
            .locatedBy("//input[@name='phone']");

    public static final Target COUNTRY = Target.the("select Country")
            .locatedBy("//select[@name='id_country']");

    public static final Target BTN_SAVE = Target.the("botón Save address")
            .locatedBy("//button[@type='submit' and (contains(@class,'btn'))]");
}