package com.qaxpert.api.steps;

import com.qaxpert.api.pages.MakeAppointmentPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.HEROKUAPP_APPOINTMENT_PAGE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakeAppointmentSteps {

    private static MakeAppointmentPage makeAppointmentPage;

    @BeforeAll
    public static void setUp() {
        makeAppointmentPage = new MakeAppointmentPage(RunContext.getDriver());
    }

    @Given("que estoy en la página Make Appointment")
    public void navegarAPaginaMakeAppointment() {
        RunContext.getDriver().get(HEROKUAPP_APPOINTMENT_PAGE);
    }

    @Given("hago login")
    public void hacerLogin() {
        makeAppointmentPage.hacerLogin();
    }

    @When("completo el formulario de cita con datos válidos")
    public void completarFormularioCita() {
        makeAppointmentPage.completarFormularioCita();
    }

    @When("hago clic en Book Appointment")
    public void clickBtnBookAppointment() {
        makeAppointmentPage.clickBtnBookAppointment();
    }

    @Then("veo el mensaje Appointment Confirmation")
    public void msjConfirmacionCitaExistoso() {
        assertTrue(makeAppointmentPage.msjConfirmacionCitaExistoso());
    }
}
