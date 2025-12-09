package features;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.InteractionsPage;

import java.util.List;

public class InteractionsTest extends BaseTest {

    private InteractionsPage interactionsPage;
    private String baseURL;

    @BeforeMethod // Se ejecuta una sola vez para la clase de prueba
    public void setUpPage() {
        baseURL = System.getProperty("baseURL", "https://demoqa.com/");
        interactionsPage = new InteractionsPage(driver);
    }

    @Test(priority = 1)
    public void sortable_reorder_vertical_list() {
        // Navegar a la página Sortable
        openPath("sortable");

        // Arrastrar "Item 4" a la posición de "Item 1"
        interactionsPage.dragItemInVerticalList("Four", "One");

        // Verificar que "Item 4" queda como primer elemento de la lista
        String firstItemTxt = interactionsPage.getFirstItemInVerticalList();

        Assert.assertEquals(firstItemTxt, "Four");
    }

    @Test(priority = 2)
    public void sortable_reorder_grid_list() {
        // Navegar a la página Sortable
        openPath("sortable");

        // Navegar a la pestaña "Grid"
        interactionsPage.openSortableGridTab();

        // Obtener el orden actual de los elementos del grid
        List<String> before = interactionsPage.getGridItemsTextsInOrder();

        // Guardar la posición (índice) de "One" ANTES de arrastrar
        int beforeIndexOfOne = before.indexOf("One");

        // Arrastrar el elemento "One" a la posición del elemento "Nine"
        interactionsPage.dragItemInGrid("One", "Nine");

        // Obtener el orden de los elementos del grid DESPUÉS del drag and drop
        List<String> after = interactionsPage.getGridItemsTextsInOrder();

        // Guardar la nueva posición (índice) de "One" DESPUÉS de arrastrar
        int afterIndexOfOne = after.indexOf("One");

        // Verificar que la posición de "One" ha cambiado en la cuadrícula
        Assert.assertNotEquals(beforeIndexOfOne, afterIndexOfOne);
    }

    @Test(priority = 3)
    public void selectable_individual_list_selection() {
        // Navegar a la página Selectable
        openPath("selectable");

        // Hacer clic sobre "Item 3"
        interactionsPage.clickOnItemInSelectableList("Morbi leo risus");

        // Verificar que el elemento permanece seleccionado
        boolean selected = interactionsPage.isItemSelectedInSelectableList("Morbi leo risus");
        Assert.assertTrue(selected, "\"Morbi leo risus\" should be selected");
    }

    @Test(priority = 4)
    public void selectable_grid_list_selection() {
        // Navegar a la página Selectable
        openPath("selectable");

        // Navegar a la pestaña "Grid"
        interactionsPage.openSelectableGridTab();

        // Seleccionar varios elementos de la cuadrícula usando selección múltiple
        String[] items = {"One", "Three", "Five"};
        interactionsPage.selectMultipleItemsInSelectableGrid(items);

        // Verificar que todos los elementos seleccionados permanecen seleccionados
        boolean allSelected = interactionsPage.areItemsSelectedInSelectableGrid(items);
        Assert.assertTrue(allSelected, "All items should be selected");
    }

    @Test(priority = 5)
    public void resizable_resize_to_maximum_size() {
        // Navegar a la página Resizable
        openPath("resizable");

        // Obtener el tamaño inicial (antes de redimensionar)
        Dimension before = interactionsPage.getFreeResizableBoxSize();

        // Arrastrar el control de redimensión hacia afuera con un offset grande
        interactionsPage.resizeFreeBox(200, 100);

        // Obtener el tamaño después de redimensionar
        Dimension after = interactionsPage.getFreeResizableBoxSize();

        // Verificar que el cuadro aumenta de tamaño hasta la dimensión proporcionada
        Assert.assertTrue(
                after.getWidth() > before.getWidth(),
                "El ancho debería haberse incrementado"
        );

        Assert.assertTrue(
                after.getHeight() > before.getHeight(),
                "La altura debería haberse incrementado"
        );
    }

    @Test(priority = 6)
    public void resizable_redimensioning_limited_by_boundaries() {
        // Navegar a la página Resizable
        openPath("resizable");

        // Obtener el tamaño inicial del cuadro limitado (antes de redimensionar)
        Dimension beforeFirst = interactionsPage.getLimitedResizableBoxSize();

        // Primer intento de redimensionar con un offset grande
        interactionsPage.resizeLimitedBox(400, 200);
        Dimension afterFirst = interactionsPage.getLimitedResizableBoxSize();

        // Segundo intento de redimensionar con el mismo offset (intento ir más allá del límite)
        interactionsPage.resizeLimitedBox(100, 100);
        Dimension afterSecond = interactionsPage.getLimitedResizableBoxSize();

        // Valores máximos permitidos
        int maxWidth = 500;
        int maxHeight = 300;

        // Verificar que tras el primer intento de redimensionar el cuadro ha crecido (ancho y alto)
        Assert.assertTrue(
                afterFirst.getWidth() >= beforeFirst.getWidth(),
                "El ancho debería ser igual o mayor que el inicial después del primer intento"
        );
        System.out.println("before = " + beforeFirst);

        Assert.assertTrue(
                afterFirst.getHeight() >= beforeFirst.getHeight(),
                "La altura debería ser igual o mayor que el inicial después del primer intento"
        );
        System.out.println("afterFirst = " + afterFirst);

        // Verificar que tras el segundo intento de redimensión del cuadro NO exceden los límites predefinidos por la aplicación
        Assert.assertTrue(
                afterSecond.getWidth() <= maxWidth,
                "El ancho no debería exceder el límite máximo de 500"
        );

        Assert.assertTrue(
                afterSecond.getHeight() <= maxHeight,
                "La altura no debería exceder el límite máximo de 300"
        );
        System.out.println("afterSecond = " + afterSecond);
    }

    @Test(priority = 7)
    public void droppable_basic_drag() {
        // Navegar a la página Resizable
        openPath("droppable");

        // Guardar la posición inicial del elemento draggable
        Point beforeLocation = interactionsPage.getSimpleDraggableLocation();

        // Verificar que el texto inicial del área de destino debería ser "Drop here"
        String initialTxt = interactionsPage.getSimpleDroppableTxt();
        Assert.assertEquals(initialTxt, "Drop here", "El texto inicial debería ser 'Drop here'");

        // Arrastrar el elemento "Draggable" al área "Droppable"
        interactionsPage.performBasicDroppable();

        // Verificar que el texto del área de destino ha cambiado a "Dropped!"
        String finalTxt = interactionsPage.getSimpleDroppableTxt();
        Assert.assertEquals(finalTxt, "Dropped!", "El texto después del drop debería ser 'Dropped!'");

        // Verificar que el elemento ya no está en su posición inicial
        Point afterLocation = interactionsPage.getSimpleDraggableLocation();
        Assert.assertNotEquals(
                afterLocation,
                beforeLocation,
                "La posición del elemento draggable debería cambiar después del drag and drop"
        );
    }

    @Test(priority = 8)
    public void droppable_prevent_propogation_drag_outer() {
        // Navegar a la página Resizable
        openPath("droppable");

        // Clicar en la pestaña "Prevent Propogation"
        interactionsPage.openDroppablePreventTab();

        // Guardar la posición inicial de los textos iniciales del Outer droppable y del Inner droppable
        String outerBefore = interactionsPage.getNotGreedyOuterDropTxt();
        String innerBefore = interactionsPage.getNotGreedyInnerDropTxt();

        System.out.println("Outer before = " + outerBefore);
        System.out.println("Inner before = " + innerBefore);

        // Drop en Outer
        interactionsPage.dragToNotGreedyOuter();

        // Obtener los textos después del drop
        String outerAfter = interactionsPage.getNotGreedyOuterDropTxt();
        String innerAfter  = interactionsPage.getNotGreedyInnerDropTxt();

        System.out.println("Outer after = " + outerAfter);
        System.out.println("Inner after = " + innerAfter);

        // Verificar que solo el área inmediata registra la caída sin afectar contenedores padre o hijo
        // Solo cambia el Outer
        Assert.assertTrue(
                outerAfter.contains("Dropped!"),
                "Outer debería reflejar el drop con 'Dropped!'"
        );

        Assert.assertTrue(
                innerAfter.contains("Inner droppable (not greedy)"),
                "Inner no debería cambiar al hacer drop solo en Outer"
        );
    }

    @Test(priority = 9)
    public void droppable_prevent_propogation_drag_inner() {
        // Navegar a la página Resizable
        openPath("droppable");

        // Clicar en la pestaña "Prevent Propogation"
        interactionsPage.openDroppablePreventTab();

        // Guardar la posición inicial de los textos iniciales del Outer droppable y del Inner droppable (not greedy)
        String outerBeforeInnerDrop = interactionsPage.getNotGreedyOuterDropTxt();
        String innerBeforeInnerDrop = interactionsPage.getNotGreedyInnerDropTxt();

        System.out.println("Outer after = " + outerBeforeInnerDrop);
        System.out.println("Inner after = " + outerBeforeInnerDrop);

        // Drop en Inner
        interactionsPage.dragToNotGreedyInner();

        // Textos después del drop en INNER
        String outerAfterInnerDrop = interactionsPage.getNotGreedyOuterDropTxt();
        String innerAfterInnerDrop = interactionsPage.getNotGreedyInnerDropTxt();

        // Verificar que los textos cambian en Outer e Inner
        Assert.assertEquals(
                outerAfterInnerDrop,
                "Dropped!",
                "Al soltar en el Inner, el Outer debería mostrar 'Dropped!'"
        );
        Assert.assertEquals(
                innerAfterInnerDrop,
                "Dropped!",
                "Al soltar en el Inner, el Inner debería mostrar 'Dropped!'"
        );
    }

    @Test(priority = 10)
    public void draggable_simple_drag() {
        // Navegar a la página Draggable
        openPath("dragabble");

        // Guardar la posición inicial del elemento draggable
        Point draggableBeforeDrag = interactionsPage.getSimpleDraggableBoxLocation();

        // Arrastrar el elemento por el contenedor a una posición distinta
        interactionsPage.dragSimpleDraggable(100, 50);
        Point afterFirstDrag = interactionsPage.getSimpleDraggableBoxLocation();

        interactionsPage.dragSimpleDraggable(-40, 20);
        Point afterSecondDrag = interactionsPage.getSimpleDraggableBoxLocation();

        // Verificar que la posición del elemento draggable cambia y se puede mover libremente
        Assert.assertNotEquals(
                afterFirstDrag,
                draggableBeforeDrag,
                "La posición debería cambiar después del primer arrastre"
        );

        Assert.assertNotEquals(
                afterSecondDrag,
                afterFirstDrag,
                "La posición debería cambiar después del segundo arrastre"
        );
    }

    @Test(priority = 11)
    public void draggable_restricted_drag_per_axle() {
        // Navegar a la página Draggable
        openPath("dragabble");

        // Navegar a la pestaña "Axis Restricted"
        interactionsPage.openDraggableAxisRestrictedTab();

        // Guardar las posición inicial del elemento "Only X"
        Point axisXDraggableBeforeDrag = interactionsPage.getAxisRestrictedXBoxLocation();

        // Arrastrar el elemento restringido en X intentando moverlo también en Y
        interactionsPage.dragAxisXDraggable(100, 50);

        // Obtener la posición del elemento "Only X" después del drag
        Point axisXDraggableAfterDrag = interactionsPage.getAxisRestrictedXBoxLocation();

        // Verificar que solo cambia la coordenada X y la coordenada Y permanece igual
        Assert.assertNotEquals(
                axisXDraggableAfterDrag.getX(),
                axisXDraggableBeforeDrag.getX(),
                "La coordenada X debería cambiar al arrastrar el elemento"
        );

        Assert.assertEquals(
                axisXDraggableAfterDrag.getY(),
                axisXDraggableBeforeDrag.getY(),
                "La coordenada Y debería permanecer igual (restricción en el eje X)"
        );
    }
}
