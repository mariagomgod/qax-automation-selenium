package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsFramesWindowsPage extends BasePage {

    public AlertsFramesWindowsPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores Browser Windows (sin By.id/ By.name)
    // ------------------------------
    private final By newTabBtn = By.xpath("//button[normalize-space(text())='New Tab']");
    private final By newWindowBtn = By.xpath("//button[normalize-space(text())='New Window']");
    private final By sampleHeading = By.xpath("//h1[contains(text(),'This is a sample page')]");

    // ==============================
    //  Acciones Browser Windows
    // ==============================
    public String getCurrentPageUrl() {
        return getCurrentUrl();
    }

    public void openNewTabAndSwitch() {
        click(newTabBtn);
        switchToWindow(1);
    }

    public void switchToMainWindow() {
        switchToWindow(0);
    }

    public String getSampleHeadingTxt() {
        return getText(sampleHeading);
    }

    public void closeCurrentTab() {
        driver.close();
    }

    public void clickOnNewWindowBtn() {
        click(newWindowBtn);
    }

    public void openNewWindowAndSwitch() {
        clickOnNewWindowBtn();
        switchToWindow(1); // 0 = principal, 1 = nueva ventana
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    // ------------------------------
    //  Localizadores Alerts (sin By.id/ By.name)
    // ------------------------------
    private final By simpleAlertBtn = By.xpath("//button[@id='alertButton' and normalize-space()='Click me']");
    private final By promptAlertBtn = By.xpath("//button[@id='promtButton' and normalize-space()='Click me']");
    private final By alertResultTxt = By.xpath("//span[starts-with(normalize-space(text()),'You ')]");
    private final By promptResultTxt = By.xpath("//span[starts-with(normalize-space(text()),'You entered')]");

    // ==============================
    //  Acciones Alerts
    // ==============================
    public void clickOnSimpleAlertBtn() {
        click(simpleAlertBtn);
    }

    public void acceptSimpleAlert() {
        acceptAlert();
    }

    public boolean isAlertResultVisible() {
        return !findAll(alertResultTxt).isEmpty();
    }

    public void clickOnPromptAlertBtn() {
        click(promptAlertBtn);
    }

    public void typeInPromptAlert(String text) {
        sendKeysToAlert(text); // de BasePage
    }

    public void acceptPromptAlert() {
        acceptAlert(); // de BasePage
    }

    public String getPromptResultTxt() {
        return getText(promptResultTxt);
    }

    // ------------------------------
    //  Localizadores Frames (sin By.id/ By.name)
    // ------------------------------
    private final By sampleFrame = By.id("frame1");
    private final By headingInsideFrame = By.xpath("//h1[contains(normalize-space(),'This is a sample page')]");
    private final By framesTitle =  By.xpath("//*[contains(@class,'text-center') and normalize-space()='Frames']");

    // ==============================
    //  Acciones Frames
    // ==============================
    public void switchToSampleFrame() {
        switchToFrame(sampleFrame);
    }

    public String getTxtInsideFrame() {
        return getText(headingInsideFrame);
    }

    public void switchBackToMainContent() {
        switchToDefault();
    }

    public boolean isFramesMainPageVisible() {
        scrollToElement(framesTitle);
        return waitForVisibility(framesTitle).isDisplayed();
    }

    // ------------------------------
    //  Localizadores Nested Frames (sin By.id/ By.name)
    // ------------------------------
    private final By parentNestedFrame = By.cssSelector("#frame1Wrapper iframe");
    private final By childNestedFrame = By.cssSelector("iframe[srcdoc*='Child Iframe']");
    private final By childFrameTxt = By.xpath("//*[contains(text(),'Child') or contains(text(),'Child Iframe')]");
    private final By nestedFramesTitle = By.xpath("//*[contains(@class,'text-center') and text()='Nested Frames']");

    // ==============================
    //  Acciones Nested Frames
    // ==============================
    public boolean isNestedFramesMainPageVisible() {
        return waitForVisibility(nestedFramesTitle).isDisplayed();
    }

    public void switchToParentNestedFrame() {
        switchToFrame(parentNestedFrame);
    }

    public void switchToChildNestedFrame() {
        switchToFrame(childNestedFrame);
    }

    public String getChildFrameTxt() {
        return getText(childFrameTxt);    // "Child Iframe"
    }

    // Volver secuencialmente: hijo -> padre -> contenido principal
    public void returnFromChildFrameToMainContent() {
        driver.switchTo().parentFrame();   // volvemos al padre
        switchToDefault();                 // volvemos al contenido principal
    }

    // ------------------------------
    //  Localizadores Modal Dialogs (sin By.id/ By.name)
    // ------------------------------
    private final By smallModalBtn = By.xpath("//button[normalize-space(text())='Small modal']");
    private final By modalContent = By.cssSelector(".modal-content");
    private final By modalTitle = By.cssSelector(".modal-content .modal-title");
    private final By modalCloseBtn = By.xpath("//div[contains(@class,'modal-content')]//button[normalize-space(text())='Close']");
    private final By modalBackdrop = By.cssSelector("div.modal-backdrop");

    // ==============================
    //  Acciones Modal Dialogs
    // ==============================
    public boolean isModalDialogsMainPageVisible() {
        // si vemos el botón de Small modal, la página está cargada
        return waitForVisibility(smallModalBtn).isDisplayed();
    }

    public void clickOnSmallModalBtn() {
        click(smallModalBtn);
    }

    public boolean isModalVisible() {
        return waitForVisibility(modalContent).isDisplayed();
    }

    public boolean isModalBackdropVisible() {
        return waitForVisibility(modalBackdrop).isDisplayed();
    }

    public String getModalTitleTxt() {
        return getText(modalTitle);
    }

    public void closeSmallModalBtn() {
        click(modalCloseBtn);
    }

    public void waitUntilModalIsClosed() {
        waitForInvisibility(modalContent);
    }

    public boolean isModalClosed() {
        return findAll(modalContent).isEmpty();
    }
}
