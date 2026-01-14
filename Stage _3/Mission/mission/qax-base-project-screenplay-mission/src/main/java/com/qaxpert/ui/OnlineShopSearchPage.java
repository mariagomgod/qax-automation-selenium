package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class OnlineShopSearchPage {

    public static final Target SEARCH_INPUT = Target.the("campo de búsqueda")
            .locatedBy("//input[@name='s' and @type='text' and @aria-label='Search']");
}
