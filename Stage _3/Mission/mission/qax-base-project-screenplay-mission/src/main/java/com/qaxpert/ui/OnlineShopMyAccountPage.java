package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopMyAccountPage {

    public static final Target LINK_ADDRESSES = Target.the("link Addresses en My Account")
            .locatedBy("//a[@id='addresses-link' or contains(@href,'controller=addresses')]");
}