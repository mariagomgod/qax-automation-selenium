package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopCheckoutPage {

    public static final Target ALIAS_ADDRESS = Target.the("alias Address")
            .locatedBy("//span[contains(@class, 'address-alias') and normalize-space()]");

}