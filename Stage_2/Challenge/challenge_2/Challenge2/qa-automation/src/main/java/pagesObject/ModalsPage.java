package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ModalsPage extends BasePage {

    public ModalsPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores Bootstrap Modal
    // ------------------------------
    private By bodyLocator = By.tagName("body");
    private By launchBootstrapModalBtn = By.cssSelector("a.btn.btn-primary[data-toggle='modal'][href='#myModal']");
    private By modalTitle = By.xpath("//h4[@class='modal-title' and normalize-space()='Modal title']");
    private By closeModalBtn = By.cssSelector("button.btn.btn-default[data-dismiss='modal']");
    private By saveChangesModalBtn = By.xpath("//button[contains(@class,'btn-primary') and normalize-space()='Save changes']");
    // ------------------------------
    //  Localizadores Multiple Modals
    // ------------------------------
    private By multipleModalsBtn = By.cssSelector("a.btn.btn-primary[data-toggle='modal'][href='#myModalMulti']");
    private By firstModalTitle = By.xpath("//h4[@class='modal-title' and normalize-space()='First Modal']");
    private By launchFirstModalBtn = By.cssSelector("a.btn.btn-primary[data-toggle='modal'][href='#myModal2']");
    private By secondModalTitle = By.xpath("//h4[@class='modal-title' and normalize-space()='Modal 2']");
    private By closeSecondModalLink = By.cssSelector("#myModal2 a.btn[data-dismiss='modal']");
    private By closeFirstModalLink = By.cssSelector("#myModalMulti a.btn[data-dismiss='modal']");


    // ==============================
    //  Acciones Bootstrap Modal
    // ==============================

    public WebElement getBootstrapModalBtn() {
        return waitForClickable(launchBootstrapModalBtn);
    }

    public void clickOnLaunchBootstrapModalBtn() {
        driver.findElement(launchBootstrapModalBtn).click();
    }

    public void getModalTitle() {
        waitForVisibility(modalTitle);
    }

    public WebElement getCloseModalBtnInsideModal() {
        return waitForClickable(closeModalBtn);
    }

    public void clickOnCloseModalBtn() {
        jsScroll(closeModalBtn);
        driver.findElement(closeModalBtn).click();
    }

    public WebElement getSaveChangesModalBtn() {
        return waitForClickable(saveChangesModalBtn);
    }

    public void clickOnSaveChangesModalBtn() {
        driver.findElement(saveChangesModalBtn).click();
    }

    // Devuelve true si el body tiene la clase 'modal-open' (fondo bloqueado)
    public boolean isBackgroundBlocked() {
        String body = driver.findElement(bodyLocator).getAttribute("class");
        return body != null && body.contains("modal-open");
    }

    // Devuelve true si el modal está presente en el DOM y visible
    public boolean isModalVisible() {
        List<WebElement> elements = driver.findElements(modalTitle);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public void waitForModalToDisappear() {
        waitForInvisibility(modalTitle);
    }

    // ==============================
    //  Acciones Multiple Modals
    // ==============================

    public WebElement getMultipleModalsBtn() {
        return waitForClickable(multipleModalsBtn);
    }

    public void clickOnMultipleModalsBtn() {
        driver.findElement(multipleModalsBtn).click();
    }

    public void getFirstModalTitle() {
        waitForVisibility(firstModalTitle);
    }

    public WebElement getLaunchFirstModalsBtn() {
        return waitForClickable(launchFirstModalBtn);
    }

    public void clickOnLaunchFirstModalBtn() {
        jsScroll(launchFirstModalBtn);
        driver.findElement(launchFirstModalBtn).click();
    }

    public void getSecondModalTitle() {
        waitForVisibility(secondModalTitle);
    }

    public WebElement getSecondModalLink() {
        return waitForClickable(closeSecondModalLink);
    }

    public void clickOnSecondModalLink() {
        driver.findElement(closeSecondModalLink).click();
    }

    public WebElement getFirstModalLink() {
        return waitForClickable(closeFirstModalLink);
    }

    public void clickOnFirstModalLink() {
        driver.findElement(closeFirstModalLink).click();
    }

    public void waitForSecondModalToDisappear() {
        waitForInvisibility(secondModalTitle);
    }

    public void waitForFirstModalToDisappear() {
        waitForInvisibility(firstModalTitle);
    }

    public boolean isFirstModalVisible() {
        List<WebElement> elements = driver.findElements(firstModalTitle);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public boolean isSecondModalVisible() {
        List<WebElement> elements = driver.findElements(secondModalTitle);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }
}
