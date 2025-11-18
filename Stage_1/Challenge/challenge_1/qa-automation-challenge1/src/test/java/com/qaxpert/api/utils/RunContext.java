package com.qaxpert.api.utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class RunContext {

    private static String username;

    //Pasamos Chromedriver por RunContext para que solo se inicialice una vez y se abra una sola
    // ventana de Chrome para todos los tests en lugar de una por cada test
    private static ChromeDriver driver;

    public RunContext() {}

    public static void setDriver(ChromeDriver driver) {
        RunContext.driver = driver;
    }

    public static ChromeDriver getDriver() {
        return driver;
    }

    public static void setUsername(String username) {
        RunContext.username = username;
    }

    public static String getUsername() {
        return username;
    }
}
