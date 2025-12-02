package features;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.AdvancedInteractionsPage;

import java.lang.reflect.Method;

public class DragAndDropTest extends BaseTest {

    private AdvancedInteractionsPage advancedPage;
    private static final String STATIC_DRAG_AND_DROP = "Static.html";
    private static final String DYNAMIC_DRAG_AND_DROP = "Dynamic.html";

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage(Method method) {
        String baseURL = System.getProperty("baseURL", "https://demo.automationtesting.in/");
        // Según el nombre del test, vamos a una URL u otra
        if (method.getName().equals("testDragAndDropEstatico")) {
            driver.get(baseURL + STATIC_DRAG_AND_DROP);
        } else if (method.getName().equals("testDragAndDropDinamico")) {
            driver.get(baseURL + DYNAMIC_DRAG_AND_DROP);
        }
        advancedPage = new AdvancedInteractionsPage(driver);
    }

    // ========================================
    //          TEST: DRAG AND DROP ESTATICO
    // ========================================
    @Test(priority = 1)
    public void testDragAndDropEstatico() {
        runDragAndDropScenario("estático");
    }

    // ========================================
    //          TEST: DRAG AND DROP DINAMICO
    // ========================================
    @Test(priority = 2)
    public void testDragAndDropDinamico() {
        runDragAndDropScenario("dinámico");
    }

    private void runDragAndDropScenario(String label) {
        advancedPage.doDragAndDrop();
        Assert.assertTrue(advancedPage.isElementInsideDropArea(), "El elemento no se encuentra dentro del área de destino en el escenario: " + label);
        System.out.println("Test " + label + " done");
    }

}
