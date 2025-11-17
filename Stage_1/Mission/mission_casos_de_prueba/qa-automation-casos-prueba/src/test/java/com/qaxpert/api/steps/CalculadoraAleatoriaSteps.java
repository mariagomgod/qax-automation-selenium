package com.qaxpert.api.steps;

import com.qaxpert.api.pages.CalculadoraAleatoriaPage;
import com.qaxpert.api.utils.RunContext;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.qaxpert.api.config.Config.CALCULATOR_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraAleatoriaSteps {

    private static CalculadoraAleatoriaPage calculadoraAleatoriaPage;

    @BeforeAll
    public static void setUp() {
        calculadoraAleatoriaPage = new CalculadoraAleatoriaPage(RunContext.getDriver());
    }

    @Before
    // Empezamos de 0 para cada escenario limpiando el RunContext (no marcamos ningún número)
    public void deleteRunContext() {
        RunContext.setOperandoUno(null);
        RunContext.setOperandoDos(null);
    }

    @Given("que abro la página de la calculadora")
    public void navegarAPaginaCalculadora() {

        RunContext.getDriver().get(CALCULATOR_PAGE);
    }

    @Given("el primer operando es {int}")
    public void primerOperando(Integer operandoUno) {
        RunContext.setOperandoUno(operandoUno);
    }

    @Given("el segundo operando es {int}")
    public void segundoOperando(Integer operandoDos) {
        RunContext.setOperandoDos(operandoDos);
    }

    @When("calculo la suma")
    public void calcularSuma() {
        Integer operandoUno = RunContext.getOperandoUno();
        Integer operandoDos = RunContext.getOperandoDos();
        calculadoraAleatoriaPage.calcularSuma(operandoUno, operandoDos);
    }

    @Then("el resultado mostrado es {string}")
    public void resultado(String resultado) {
        assertEquals(resultado, calculadoraAleatoriaPage.resultado());
    }

    @When("calculo la resta")
    public void calcularResta() {
        Integer operandoUno = RunContext.getOperandoUno();
        Integer operandoDos = RunContext.getOperandoDos();
        calculadoraAleatoriaPage.calcularResta(operandoUno, operandoDos);
    }

    @When("calculo la división")
    public void calcularDivision() {
        Integer operandoUno = RunContext.getOperandoUno();
        Integer operandoDos = RunContext.getOperandoDos();
        calculadoraAleatoriaPage.calcularDivision(operandoUno, operandoDos);
    }
}
