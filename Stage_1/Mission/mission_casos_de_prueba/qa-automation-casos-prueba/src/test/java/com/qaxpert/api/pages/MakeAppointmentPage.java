package com.qaxpert.api.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MakeAppointmentPage extends BasePage {

    private final By btnMakeAppointmentLocator = By.id("btn-make-appointment");
    private final By usernameLocator = By.id("txt-username");
    private final By passwordLocator = By.id("txt-password");
    private final By btnLoginLocator = By.id("btn-login");
    private final By checkboxHospitalReadmissionLocator = By.id("chk_hospotal_readmission");
    private final By checkboxMedicareLocator = By.id("radio_program_medicare");
    private final By calendarLocator = By.cssSelector(".input-group.date");
    private final By nextMonthLocator = By.cssSelector("div.datepicker-days th.next");
    private final By selectAppointmentDateLocator = By.xpath("//td[@class='day' and normalize-space()='1']");
    private final By txtCommentLocator = By.id("txt_comment");
    private final By btnBookAppoinmentLocator = By.id("btn-book-appointment");
    private final By msjAppointmentConfirmationLocator = By.xpath("//h2[normalize-space()='Appointment Confirmation']");

    public MakeAppointmentPage(WebDriver driver) {
        super(driver);
    }

    public void hacerLogin() {
        WebElement btnMakeAppoinment = wait.until(
                ExpectedConditions.elementToBeClickable(btnMakeAppointmentLocator)
        );

        WebElement txtUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameLocator)
        );

        WebElement txtPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordLocator)
        );

        WebElement btnLogin = wait.until(
                ExpectedConditions.elementToBeClickable(btnLoginLocator)
        );

        btnMakeAppoinment.click();

        txtUsername.clear();
        txtUsername.sendKeys("John Doe");

        txtPassword.clear();
        txtPassword.sendKeys("ThisIsNotAPassword");

        btnLogin.click();
    }

    public void completarFormularioCita() {
        WebElement checkboxHospitalReadmission = wait.until(
                ExpectedConditions.elementToBeClickable(checkboxHospitalReadmissionLocator)
        );
        WebElement checkboxMedicare = wait.until(
                ExpectedConditions.elementToBeClickable(checkboxMedicareLocator)
        );
        WebElement calendarIcon = wait.until(
                ExpectedConditions.elementToBeClickable(calendarLocator)
        );
        WebElement txtComment = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtCommentLocator)
        );

        checkboxHospitalReadmission.click();
        checkboxMedicare.click();
        calendarIcon.click();

        WebElement nextMonthIcon = wait.until(
                ExpectedConditions.elementToBeClickable(nextMonthLocator)
        );
        nextMonthIcon.click();

        WebElement selectAppointmentDate = wait.until(
                ExpectedConditions.elementToBeClickable(selectAppointmentDateLocator)
        );
        selectAppointmentDate.click();

        txtComment.clear();
        txtComment.sendKeys("Cita automatizada con QA Xpert");
    }

    public void clickBtnBookAppointment() {
        WebElement btnBookAppointment = wait.until(
                ExpectedConditions.elementToBeClickable(btnBookAppoinmentLocator)
        );
        btnBookAppointment.click();
    }

    public boolean msjConfirmacionCitaExistoso() {
        WebElement msjConfirmacionCitaExistoso = wait.until(
                ExpectedConditions.visibilityOfElementLocated(msjAppointmentConfirmationLocator)
        );
        System.out.println("Inicio de sesión exitoso: " + msjConfirmacionCitaExistoso.getText());
        return msjConfirmacionCitaExistoso.isDisplayed();
    }
}
