package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElementsModulePage extends BasePage {

    public ElementsModulePage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------
    //  Localizadores Text-box (sin By.id/ By.name)
    // ------------------------------
    private final By fullNameInput = By.xpath("//label[text()='Full Name']/following::input[1]");
    private final By emailInput = By.xpath("//label[text()='Email']/following::input[1]");
    private final By currentAddressInput = By.xpath("//label[text()='Current Address']/following::textarea[1]");
    private final By permanentAddressInput = By.xpath("//label[text()='Permanent Address']/following::textarea[1]");
    private final By submitBtn = By.xpath("//button[normalize-space(.)='Submit']");

    private final By outputName = By.xpath("//p[starts-with(normalize-space(),'Name:')]");
    private final By outputEmail = By.xpath("//p[starts-with(normalize-space(),'Email:')]");
    private final By outputCurrentAddress = By.xpath("//p[starts-with(normalize-space(),'Current Address :')]");
    private final By outputPermanentAddress = By.xpath("//p[contains(normalize-space(),'Permananet Address') " +
            " or contains(normalize-space(),'Permanent Address')]");

    // ==============================
    //  Acciones Text-box
    // ==============================

    public void fillFormAndClick(String fullName, String email, String currentAddress, String permanentAddress) {
        type(fullNameInput, fullName);
        type(emailInput, email);
        type(currentAddressInput, currentAddress);
        type(permanentAddressInput, permanentAddress);

        scrollToElement(submitBtn);
        click(submitBtn);
    }

    public String getOutputName() {
        return getText(outputName);
    }

    public String getOutputEmail() {
        return getText(outputEmail);
    }

    public String getOutputCurrentAddress() {
        return getText(outputCurrentAddress);
    }

    public String getOutputPermanentAddress() {
        return getText(outputPermanentAddress);
    }

    // ------------------------------
    //  Localizadores Checkbox (sin By.id/ By.name)
    // ------------------------------
    private final By expandAllButton = By.cssSelector("button[title='Expand all']");
    private final By resultContainer = By.cssSelector("div#result");
    private final By homeCheckbox =
            By.xpath("//span[@class='rct-title' and text()='Home']" +
                    "/preceding-sibling::span[contains(@class,'rct-checkbox')]");
    private final By desktopCheckbox =
            By.xpath("//span[@class='rct-title' and text()='Desktop']" +
                    "/preceding-sibling::span[contains(@class,'rct-checkbox')]");
    private final By downloadsCheckbox =
            By.xpath("//span[@class='rct-title' and text()='Downloads']" +
                    "/preceding-sibling::span[contains(@class,'rct-checkbox')]");
    private final By homePartialIcon =
            By.xpath("//span[@class='rct-title' and text()='Home']" +
                    "/preceding-sibling::span[contains(@class,'rct-checkbox')]" +
                    "//*[contains(@class,'rct-icon-half-check') " +
                    "   or contains(@class,'rct-icon-indeterminate')]");

    // ==============================
    //  Acciones Checkbox
    // ==============================

    public void expandAllTree() {
        click(expandAllButton);
    }

    public void selectHome() {
        click(homeCheckbox);
    }

    public void deselectHome() {
        click(homeCheckbox); // toggle
    }

    public void selectDesktopAndDownloads() {
        click(desktopCheckbox);
        click(downloadsCheckbox);
    }

    public boolean areHomeChildrenSelected() {
        String result = getText(resultContainer).toLowerCase();
        return result.contains("desktop")
                && result.contains("documents")
                && result.contains("downloads");
    }

    public boolean isHomePartiallySelected() {
        waitForVisibility(homePartialIcon);
        return find(homePartialIcon).isDisplayed();
    }

    // ------------------------------
    //  Localizadores Radio Button (sin By.id/ By.name)
    // ------------------------------
    private final By yesRadioLabel = By.cssSelector("label[for='yesRadio']");
    private final By impressiveRadioLabel = By.cssSelector("label[for='impressiveRadio']");
    private final By noRadioInput = By.cssSelector("input#noRadio");
    private final By radioResultText = By.cssSelector("p.mt-3");

    // ==============================
    //  Acciones Radio Button
    // ==============================
    public void selectYesOption() {
        click(yesRadioLabel);
    }

    public void selectImpressiveOption() {
        click(impressiveRadioLabel);
    }

    public String getRadioResultText() {
        return getText(radioResultText); // "You have selected Yes" / "You have selected Impressive"
    }

    public boolean isNoOptionDisabled() {
        return !waitForPresence(noRadioInput).isEnabled();
    }

    // ------------------------------
    //  Localizadores Web Tables (sin By.id/ By.name)
    // ------------------------------
    private final By addRecordButton = By.cssSelector("button#addNewRecordButton");
    private final By firstNameInputWT  = By.cssSelector("input#firstName");
    private final By lastNameInputWT   = By.cssSelector("input#lastName");
    private final By emailInputWT      = By.cssSelector("input#userEmail");
    private final By ageInputWT        = By.cssSelector("input#age");
    private final By salaryInputWT     = By.cssSelector("input#salary");
    private final By departmentInputWT = By.cssSelector("input#department");
    private final By submitWebTableBtn = By.cssSelector("button#submit");
    private final By tableRows = By.cssSelector("div.rt-tbody div.rt-tr-group");

    // ==============================
    //  Acciones Web Tables
    // ==============================
    public void fillWebTableFormAndSubmit(String firstName,
                                         String lastName,
                                         String email,
                                         String age,
                                         String salary,
                                         String department) {
        click(addRecordButton);

        type(firstNameInputWT, firstName);
        type(lastNameInputWT, lastName);
        type(emailInputWT, email);
        type(ageInputWT, age);
        type(salaryInputWT, salary);
        type(departmentInputWT, department);

        scrollToElement(submitWebTableBtn);
        click(submitWebTableBtn);
    }

    // Método para comprobar si existe al menos una fila que contenga el email indicado
    public boolean isRecordPresentByEmail(String email) {
        By rowByEmail = By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + email + "']]");
        return !findAll(rowByEmail).isEmpty();
    }

    public void editSalaryByEmail(String email, String newSalary) {
        By editBtnByEmail = By.xpath(
                "//div[@class='rt-tr-group'][.//div[text()='" + email + "']]//span[@title='Edit']"
        );

        click(editBtnByEmail);

        // Actualizamos el campo Salary
        type(salaryInputWT, newSalary);
        click(submitWebTableBtn);
    }

    public boolean rowHasSalaryForEmail(String email, String salary) {
        By rowByEmail = By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + email + "']]");
        String rowText = waitForVisibility(rowByEmail).getText();
        return rowText.contains(salary);
    }

    public void deleteRecordByEmail(String email) {
        By deleteButtonByEmail = By.xpath(
                "//div[@class='rt-tr-group'][.//div[text()='" + email + "']]//span[@title='Delete']"
        );
        click(deleteButtonByEmail);
    }

    // ------------------------------
    //  Localizadores Buttons (sin By.id/ By.name)
    // ------------------------------
    private final By doubleClickButton = By.xpath("//button[normalize-space(.)='Double Click Me']");
    private final By rightClickButton = By.xpath("//button[normalize-space(.)='Right Click Me']");
    private final By dynamicClickButton = By.xpath("//button[normalize-space(.)='Click Me']");
    private final By doubleClickMessage = By.cssSelector("#doubleClickMessage");
    private final By rightClickMessage = By.cssSelector("#rightClickMessage");
    private final By dynamicClickMessage = By.cssSelector("#dynamicClickMessage");

    // ==============================
    //  Acciones Buttons
    // ==============================

    public void performDoubleClick() {
        scrollToElement(doubleClickButton);
        WebElement button = waitForVisibility(doubleClickButton);
        actions.doubleClick(button).perform();
    }

    public void performRightClick() {
        scrollToElement(rightClickButton);
        WebElement button = waitForVisibility(rightClickButton);
        actions.contextClick(button).perform();
    }

    public void performDynamicClick() {
        scrollToElement(dynamicClickButton);
        click(dynamicClickButton);
    }

    public String getDoubleClickResult() {
        return getText(doubleClickMessage); // "You have done a double click"
    }

    public String getRightClickResult() {
        return getText(rightClickMessage);  // "You have done a right click"
    }

    public String getDynamicClickResult() {
        return getText(dynamicClickMessage); // "You have done a dynamic click"
    }

    // ------------------------------
    //  Localizadores Links (sin By.id/ By.name)
    // ------------------------------
    private final By homeSimpleLink = By.cssSelector("a#simpleLink");
    private final By createdLink      = By.cssSelector("a#created");
    private final By noContentLink    = By.cssSelector("a#no-content");
    private final By movedLink        = By.cssSelector("a#moved");
    private final By badRequestLink   = By.cssSelector("a#bad-request");
    private final By unauthorizedLink = By.cssSelector("a#unauthorized");
    private final By forbiddenLink    = By.cssSelector("a#forbidden");
    private final By invalidUrlLink   = By.cssSelector("a#invalid-url");

    // ==============================
    //  Acciones Links
    // ==============================
    public void clickHomeLinkAndWaitForNewTab() {
        int initialWindows = driver.getWindowHandles().size();
        scrollToElement(homeSimpleLink);
        click(homeSimpleLink);

        // Esperar a que se abra una nueva pestaña
        wait.until(ExpectedConditions.numberOfWindowsToBe(initialWindows + 1));
    }

    public void goToTab(int index) {
        switchToWindow(index); // método de BasePage
    }
    public String getCurrentPageUrl() {
        return getCurrentUrl(); // método de BasePage
    }

    public void clickCreatedLink() {
        scrollToElement(createdLink);
        click(createdLink);
    }

    public void clickNoContentLink() {
        scrollToElement(noContentLink);
        click(noContentLink);
    }

    public void clickMovedLink() {
        scrollToElement(movedLink);
        click(movedLink);
    }

    public void clickBadRequestLink() {
        scrollToElement(badRequestLink);
        click(badRequestLink);
    }

    public void clickUnauthorizedLink() {
        scrollToElement(unauthorizedLink);
        click(unauthorizedLink);
    }

    public void clickForbiddenLink() {
        scrollToElement(forbiddenLink);
        click(forbiddenLink);
    }

    public void clickInvalidUrlLink() {
        scrollToElement(invalidUrlLink);
        click(invalidUrlLink);
    }

    // ------------------------------
    //  Localizadores Broken Links (sin By.id/ By.name)
    // ------------------------------
    private final By validImage = By.xpath("//*[text()='Valid image']/following::img[1]");
    private final By brokenImage = By.xpath("//*[text()='Broken image']/following::img[1]");
    private final By validLink = By.xpath("//a[normalize-space()='Click Here for Valid Link']");
    private final By brokenLink = By.xpath("//a[normalize-space()='Click Here for Broken Link']");
    // Cuerpo de la página (para leer mensajes de error tras el Broken Link)
    private final By pageBody = By.cssSelector("body");

    // ==============================
    //  Acciones  Broken Links
    // ==============================

    public boolean isValidImageLoaded() {
        scrollToElement(validImage);
        return isImageLoaded(validImage);   // helper de BasePage
    }

    public boolean isBrokenImageReallyBroken() {
        scrollToElement(brokenImage);
        return !isImageLoaded(brokenImage); // si no está cargada, está rota
    }

    public void clickOnValidLink() {
        scrollToElement(validLink);
        click(validLink);
    }

    public void clickOnBrokenLink() {
        scrollToElement(brokenLink);
        click(brokenLink);
    }
    public String getPageBodyText() {
        return getText(pageBody);
    }

    // ------------------------------
    //  Localizadores Upload and Download (sin By.id/ By.name)
    // ------------------------------
    private final By uploadInput = By.cssSelector("input#uploadFile");
    private final By uploadedFilePathTxt = By.cssSelector("p#uploadedFilePath");
    private final By downloadButton = By.cssSelector("a#downloadButton");

    // ==============================
    //  Acciones  Upload and Download
    // ==============================

    public void uploadFileFromPath(String absolutePath) {
        scrollToElement(uploadInput);
        uploadFile(uploadInput, absolutePath); // helper de BasePage
    }

    public String getUploadedFilePathText() {
        return getText(uploadedFilePathTxt);
    }

    public void clickDownloadButton() {
        scrollToElement(downloadButton);
        click(downloadButton);
    }

    // ------------------------------
    //  Localizadores Dynamic Properties (sin By.id/ By.name)
    // ------------------------------
    private final By enableAfterBtn   = By.cssSelector("button#enableAfter");
    private final By colorChangeBtn   = By.cssSelector("button#colorChange");
    private final By visibleAfterBtn  = By.cssSelector("button#visibleAfter");

    // ==============================
    //  Acciones Dynamic Properties
    // ==============================

    public boolean isEnableAfterFiveSecondsBtn() {
        scrollToElement(enableAfterBtn);
        return waitForVisibility(enableAfterBtn).isEnabled();
    }

    public void waitUntilEnableAfterFiveSecondsBtnIsEnabled() {
        scrollToElement(enableAfterBtn);
        waitForClickable(enableAfterBtn); // cuando llega aquí ya está enabled y clicable
    }


    // Espera a que el botón Color Change tenga la clase CSS que indica el cambio de color
    public void waitUntilColorHasChanged() {
        scrollToElement(colorChangeBtn);
        // Esperamos a que la clase contenga "text-danger"
        wait.until(ExpectedConditions.attributeContains(colorChangeBtn, "class", "text-danger"));
    }

    // Devuelve true si el botón Color Change ya tiene la clase de color cambiado
    public boolean isColorChanged() {
        scrollToElement(colorChangeBtn);
        String cssClass = waitForVisibility(colorChangeBtn).getAttribute("class");
        return cssClass.contains("text-danger");
    }

    // Espera a que el botón 'Visible After 5 Seconds' sea visible en la página. */
    public void waitUntilVisibleAfterIsVisible() {
        scrollToElement(visibleAfterBtn);
        waitForVisibility(visibleAfterBtn);
    }

    // Devuelve si el botón 'Visible After 5 Seconds' está visible
    public boolean isVisibleAfterDisplayed() {
        scrollToElement(visibleAfterBtn);
        return waitForVisibility(visibleAfterBtn).isDisplayed();
    }
}
