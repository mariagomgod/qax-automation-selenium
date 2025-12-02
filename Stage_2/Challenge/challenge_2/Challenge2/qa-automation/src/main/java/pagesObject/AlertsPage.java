package pagesObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------
    //  Localizadores
    // ------------------------------
    private By tabSimpleAlert     = By.cssSelector("a[href='#OKTab']");
    private By tabConfirmAlert    = By.cssSelector("a[href='#CancelTab']");
    private By tabPromptAlert     = By.cssSelector("a[href='#Textbox']");

    private By btnSimpleAlert     = By.cssSelector("#OKTab button");
    private By btnConfirmAlert    = By.cssSelector("#CancelTab button");
    private By btnPromptAlert     = By.cssSelector("#Textbox button");

    private By msgConfirm         = By.id("demo");
    private By msgPrompt          = By.id("demo1");


    // ------------------------------
    //  Acciones de Navegación entre Tabs
    // ------------------------------
    public void openSimpleAlertTab() {
        click(tabSimpleAlert); // ← método `click` heredado de BasePage
    }

    public void openConfirmAlertTab() {
        click(tabConfirmAlert);
    }

    public void openPromptAlertTab() {
        click(tabPromptAlert);
    }


    // ------------------------------
    //  Acciones sobre los Botones
    // ------------------------------
    public void clickSimpleAlertButton() {
        click(btnSimpleAlert);
    }

    public void clickConfirmAlertButton() {
        click(btnConfirmAlert);
    }

    public void clickPromptAlertButton() {
        click(btnPromptAlert);
    }


    // ------------------------------
    //  Lectura de Mensajes
    // ------------------------------
    public String getConfirmMessage() {
        return getText(msgConfirm); // ← usando método `getText de BasePage
    }

    public String getPromptMessage() {
        return getText(msgPrompt);
    }

    public Alert getAlert() {
        return waitForAlert(); // usando el método protected de BasePage
    }

}
