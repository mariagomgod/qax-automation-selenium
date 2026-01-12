package com.qaxpert.ui;

import net.serenitybdd.screenplay.targets.Target;

public class SearchPage {

    public static final Target TXT_SEARCH = Target.the("campo búsqueda")
            .locatedBy("//input[@name='s' and @type='search']");

}
