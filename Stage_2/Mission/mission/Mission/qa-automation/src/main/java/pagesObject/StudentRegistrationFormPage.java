package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class StudentRegistrationFormPage extends BasePage {

    public StudentRegistrationFormPage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores Escenario 01 (sin By.id/ By.name)
    // ------------------------------
    private final By firstNameInput = By.cssSelector("#firstName");
    private final By lastNameInput = By.cssSelector("#lastName");
    private final By emailInput = By.cssSelector("#userEmail");
    private final By mobileInput = By.cssSelector("#userNumber");

    // ==============================
    //  Acciones Escenario 01
    // ==============================
    public void fillFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void fillLastName(String lastName) {
        scrollToElement(lastNameInput);
        type(lastNameInput, lastName);
    }

    public void setEmail(String email) {
        scrollToElement(emailInput);
        type(emailInput, email);
    }

    public void setMobile(String mobile) {
        scrollToElement(mobileInput);
        type(mobileInput, mobile);
    }

    public void fillBasicContactInfo(String firstName, String lastName, String email, String mobile) {
        fillFirstName(firstName);
        fillLastName(lastName);
        setEmail(email);
        setMobile(mobile);
    }

    public String getFirstNameValue() {
        return waitForVisibility(firstNameInput).getAttribute("value");
    }

    public String getLastNameValue() {
        return waitForVisibility(lastNameInput).getAttribute("value");
    }

    public String getEmailValue() {
        return waitForVisibility(emailInput).getAttribute("value");
    }

    public String getMobileValue() {
        return waitForVisibility(mobileInput).getAttribute("value");
    }

    // ------------------------------
    //  Localizadores Escenario 02 (sin By.id/ By.name)
    // ------------------------------
    private final By dateOfBirthInput = By.cssSelector("#dateOfBirthInput");
    private final By monthSelect = By.cssSelector(".react-datepicker__month-select");
    private final By yearSelect = By.cssSelector(".react-datepicker__year-select");

    // ==============================
    //  Acciones Escenario 02
    // ==============================
    public void selectDateOfBirth(String day, String month, String year) {
        click(dateOfBirthInput);

        Select monthDropdown = new Select(waitForVisibility(monthSelect));
        monthDropdown.selectByVisibleText(month);

        Select yearDropdown = new Select(waitForVisibility(yearSelect));
        yearDropdown.selectByVisibleText(year);

        String dayXpath = String.format(
                "//div[contains(@class,'react-datepicker__day') " +
                        "and text()='%s' and not(contains(@class,'outside-month'))]",
                day
        );
        By dayLocator = By.xpath(dayXpath);
        click(dayLocator);
    }

    public String getDateOfBirthValue() {
        return waitForVisibility(dateOfBirthInput).getAttribute("value");
    }

    // ------------------------------
    //  Localizadores Escenario 03 (sin By.id/ By.name)
    // ------------------------------
    private final By subjectsInput = By.cssSelector("#subjectsInput");
    private final By selectedSubjectsLabels = By.cssSelector(".subjects-auto-complete__multi-value__label");

    // ==============================
    //  Acciones Escenario 03
    // ==============================
    private By subjectOptionByTxt(String txt) {
        return By.xpath("//div[contains(@class,'subjects-auto-complete__option') and text()='" + txt + "']");
    }

    public void selectSubjectFromAutocomplete(String subjectTxt) {
        WebElement input = waitForVisibility(subjectsInput);
        input.sendKeys(subjectTxt);
        input.sendKeys(Keys.ENTER);
    }

    // Método que devuelve la lista de subjects actualmente seleccionados
    public List<String> getSelectedSubjects() {
        List<WebElement> chips = findAll(selectedSubjectsLabels);
        List<String> subjects = new LinkedList<>();

        for (WebElement chip : chips) {
            subjects.add(chip.getText().trim());
        }
        return subjects;
    }

    // ------------------------------
    //  Localizadores Escenario 04 (sin By.id/ By.name)
    // ------------------------------
    private final By sportsHobbyLabel = By.xpath("//label[@for='hobbies-checkbox-1']");
    private final By musicHobbyLabel = By.xpath("//label[@for='hobbies-checkbox-3']");
    private final By sportsHobbyInput = By.cssSelector("#hobbies-checkbox-1");
    private final By musicHobbyInput = By.cssSelector("#hobbies-checkbox-3");
    private final By pictureInput = By.cssSelector("#uploadPicture");

    // ==============================
    //  Acciones Escenario 04
    // ==============================
    public void selectSportsHobby() {
        click(sportsHobbyLabel);
    }

    public void selectMusicHobby() {
        click(musicHobbyLabel);
    }

    public boolean isSportsHobbySelected() {
        WebElement sportsCheckbox = waitForPresence(sportsHobbyInput);
        return sportsCheckbox.isSelected();
    }

    public boolean isMusicHobbySelected() {
        WebElement musicCheckbox = waitForPresence(musicHobbyInput);
        return musicCheckbox.isSelected();
    }

    public void uploadPicture(String absolutePath) {
        scrollToElement(pictureInput);
        uploadFile(pictureInput, absolutePath); // helper de BasePage
    }

    public String getUploadedPictureValue() {
        return waitForVisibility(pictureInput).getAttribute("value");
    }

    // ------------------------------
    //  Localizadores Escenario 05 (sin By.id/ By.name)
    // ------------------------------
    private final By currentAddressTextArea = By.cssSelector("#currentAddress");

    // ==============================
    //  Acciones Escenario 05
    // ==============================
    public void setCurrentAddress(String address) {
        type(currentAddressTextArea, address);
    }

    public String getCurrentAddressValue() {
        return waitForVisibility(currentAddressTextArea).getAttribute("value");
    }

    // ------------------------------
    //  Localizadores Escenario 06 (sin By.id/ By.name)
    // ------------------------------
    private final By stateDropdown = By.cssSelector("#state");
    private final By cityDropdown  = By.cssSelector("#city");
    private final By stateInput = By.cssSelector("input[id^='react-select-3-input']");
    private final By cityInput  = By.cssSelector("input[id^='react-select-4-input']");
    private final By stateSelectedValue = By.cssSelector("#state .css-1uccc91-singleValue");
    private final By citySelectedValue  = By.cssSelector("#city .css-1uccc91-singleValue");

    // ==============================
    //  Acciones Escenario 06
    // ==============================
    public void selectState(String stateTxt) {
        // abrir dropdown de State
        click(stateDropdown);
        // escribir el texto en el input interno y pulsar ENTER
        WebElement input = waitForVisibility(stateInput);
        input.sendKeys(stateTxt);
        input.sendKeys(Keys.ENTER);
    }

    public void selectCity(String cityTxt) {
        // abrir dropdown de City
        click(cityDropdown);
        // escribir el texto en el input interno y pulsar ENTER
        WebElement input = waitForVisibility(cityInput);
        input.sendKeys(cityTxt);
        input.sendKeys(Keys.ENTER);
    }

    public String getSelectedState() {
        return getText(stateSelectedValue);
    }

    public String getSelectedCity() {
        return getText(citySelectedValue);
    }

    // ------------------------------
    //  Localizadores Escenario 07 (sin By.id/ By.name)
    // ------------------------------
    private final By submitButton = By.cssSelector("#submit");
    private final By confirmationModal = By.xpath("//div[contains(@class,'modal-content')]");
    private final By genderFemaleLabel = By.xpath("//label[@for='gender-radio-2']");
    private final By studentNameCell = By.xpath("//td[text()='Student Name']/following-sibling::td");
    private final By genderCell = By.xpath("//td[text()='Gender']/following-sibling::td");
    private final By dateOfBirthCell = By.xpath("//td[text()='Date of Birth']/following-sibling::td");
    private final By subjectsCell = By.xpath("//td[text()='Subjects']/following-sibling::td");
    private final By addressCell = By.xpath("//td[text()='Address']/following-sibling::td");
    private final By stateAndCityCell = By.xpath("//td[text()='State and City']/following-sibling::td");

    // ==============================
    //  Acciones Escenario 07
    // ==============================

    public void selectFemaleGender() {
        click(genderFemaleLabel);
    }

    public void submitForm() {
        click(submitButton);
    }

    public boolean isConfirmationModalVisible() {
        return waitForVisibility(confirmationModal).isDisplayed();
    }

    public String getStudentNameFromModal() {
        return getText(studentNameCell);
    }

    public String getGenderFromModal() {
        return getText(genderCell);
    }

    public String getDateOfBirthFromModal() {
        return getText(dateOfBirthCell);
    }

    public String getSubjectsFromModal() {
        return getText(subjectsCell);
    }

    public String getAddressFromModal() {
        return getText(addressCell);
    }

    public String getStateAndCityFromModal() {
        return getText(stateAndCityCell);
    }
}
