package pagesObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage  extends  BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    // ===================
    // Elementos del Form
    // ===================

    // Locators pantalla inicial registro de usuario
    private By btn_doNotConsent = By.cssSelector("button.fc-cta-do-not-consent[aria-label='Do not consent']");
    private By txt_email_into = By.id("email");
    private By btn_enter = By.id("enterimg");
    private By formHeader = By.xpath("//h2[normalize-space()='Register']");
    // First / Last Name (obligatorio)
    private By txt_firstName = By.cssSelector("input[placeholder='First Name']");
    private By txt_lastName = By.cssSelector("input[placeholder='Last Name']");
    // Address
    private By txt_address = By.cssSelector("textarea[ng-model='Adress']");
    // Email (obligatorio)
    private By txt_email = By.cssSelector("input[ng-model='EmailAdress']");
    // Phone (con pattern de 10 dígitos, obligatorio)
    private By txt_phone = By.cssSelector("input[ng-model='Phone'][type='tel']");
    // Gender (obligatorio)
    private By rdo_genderMale = By.cssSelector("input[type='radio'][name='radiooptions'][value='Male']");
    // Hobbies
    private By chk_hobbiesMovies = By.id("checkbox2");
    // Skills
    private By ddl_skills = By.id("Skills");
    // Country (obligatorio)
    private By ddl_country = By.id("countries");
    // Passwords (obligatorias ambas)
    private By txt_password = By.id("firstpassword");
    private By txt_confirmPassword = By.id("secondpassword");
    // Upload de foto
    private By file_upload = By.id("imagesrc");
    // Botón "Submit"
    private By btn_submit = By.id("submitbtn");

    // ===================
    // Acciones
    // ===================
    public void intoRegister(String email){
        waitForVisibility(txt_email_into).sendKeys(email);
        waitForClickable(btn_enter).click();
    }

    public void setFirstName(String name) {
        waitForVisibility(txt_firstName).sendKeys(name);
    }

    public void setLastName(String last) {
        waitForVisibility(txt_lastName).sendKeys(last);
    }

    public void setAddress(String address) {
        waitForVisibility(txt_address).sendKeys(address);
    }

    public void setEmail(String email) {
        waitForVisibility(txt_email).sendKeys(email);
    }

    public void setPhone(String phone) {
        waitForVisibility(txt_phone).sendKeys(phone);
    }

    public void selectGenderMale() {
        WebElement radio = waitForVisibility(rdo_genderMale);
        // Aseguramos que el radio esté centrado en pantalla
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", radio);
        // Click vía JS para evitar que el iframe de anuncio intercepte el click
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", radio);
    }

    public void selectHobbyMovies() {
        WebElement checkbox = waitForVisibility(chk_hobbiesMovies);
        // Click vía JS para evitar que el iframe de anuncio intercepte el click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    public void selectSkill(String skill) {
        WebElement element = waitForVisibility(ddl_skills);
        new Select(element).selectByVisibleText(skill);
    }

    public void selectCountry(String country) {
        WebElement element = waitForVisibility(ddl_country);
        new Select(element).selectByVisibleText(country);
    }

    public void setPassword(String password) {
        waitForVisibility(txt_password).sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        waitForVisibility(txt_confirmPassword).sendKeys(password);
    }

    public void uploadFile(String absolutePath) {
        waitForVisibility(file_upload).sendKeys(absolutePath);
    }

    public void clickSubmit() {
        WebElement submit = waitForVisibility(btn_submit);

        // Aseguramos que está visible en el viewport
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", submit);

        // Click por JavaScript -> no le afectan iframes que tapen la zona
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", submit);
    }

    public boolean isRegistrationSuccessful() {
        try {
            waitForVisibility(formHeader);
            return true;
        } catch (TimeoutException e) {
            // Si ni siquiera encontramos el título, algo raro ha pasado
            return false;
        }
    }

    public boolean isFirstNameInvalid() {
        return isFieldInvalid(txt_firstName);
    }

    public String getFirstNameValidationMessage() {
        return getFieldValidationMessage(txt_firstName);
    }

    public boolean isPhoneInvalid() {
        return isFieldInvalid(txt_phone);
    }

    public String getPhoneValidationMessage() {
        return getFieldValidationMessage(txt_phone);
    }

    // Manejo de iframes y banners
    public void clickDoNotConsent() {
        waitForClickable(btn_doNotConsent).click();
    }
}
