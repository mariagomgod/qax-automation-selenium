package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InteractionsPage extends BasePage {

    public InteractionsPage(WebDriver driver) { super( driver); }

    // ------------------------------
    //  Localizadores Sortable (sin By.id/ By.name)
    // ------------------------------
    private final By sortableGridTab  = By.xpath("//nav[contains(@class,'nav')]//a[contains(@class,'nav-link') and normalize-space()='Grid']");
    private final By sortableListItems = By.cssSelector("#demo-tabpane-list .list-group-item");
    private final By sortableGridItems = By.cssSelector("#demo-tabpane-grid .list-group-item");

    // ==============================
    //  Acciones Sortable
    // ==============================
    public void dragItemInVerticalList(String fromText, String toText) {
        dragItemInSortableContainer("vertical-list-container", fromText, toText);
    }

    public String getFirstItemInVerticalList() {
        return getFirstItemTextInContainer(sortableListItems);
    }

    public void openSortableGridTab() {
        scrollToElement(sortableGridTab);
        click(sortableGridTab);
    }

    public void dragItemInGrid(String fromText, String toText) {
        dragItemInSortableContainer("create-grid", fromText, toText);
    }

    public List<String> getGridItemsTextsInOrder() {
        return getAllItemsTextsInContainer(sortableGridItems);
    }

    // Método genérico para desplazar items (list o grid)
    private void dragItemInSortableContainer(String containerClass, String fromTxt, String toTxt) {
        By fromLocator = By.xpath(
                "//div[contains(@class,'" + containerClass + "')]" +
                        "//div[contains(@class,'list-group-item') and normalize-space()='" + fromTxt + "']"
        );

        By toLocator = By.xpath(
                "//div[contains(@class,'" + containerClass + "')]" +
                        "//div[contains(@class,'list-group-item') and normalize-space()='" + toTxt + "']"
        );

        dragAndDrop(fromLocator, toLocator);
    }

    // Método genérico para traer el primer item de un contenedor
    private String getFirstItemTextInContainer(By itemsLocator) {
        List<WebElement> items = findAll(itemsLocator);
        WebElement first = items.get(0);
        return first.getText().trim();
    }

    // Método genérico para recorrer todos los items
    private List<String> getAllItemsTextsInContainer(By itemsLocator) {
        List<WebElement> items = findAll(itemsLocator);
        List<String> txts = new ArrayList<>();

        for (WebElement item : items) {
            txts.add(item.getText().trim());
        }
        return txts;
    }

    // ------------------------------
    //  Localizadores Selectable (sin By.id/ By.name)
    // ------------------------------
    private final By selectableActiveItems = By.cssSelector("div.tab-pane.active .list-group-item");
    private final By selectableGridTab = By.xpath("//nav[contains(@class,'nav')]//a[normalize-space()='Grid']");

    // ==============================
    //  Acciones Selectable
    // ==============================
    public void clickOnItemInSelectableList(String txt) {
        scrollToElement(selectableActiveItems);
        WebElement item = findSelectableItemByTxt(txt);
        item.click();
    }

    public boolean isItemSelectedInSelectableList(String txt) {
        WebElement item = findSelectableItemByTxt(txt);
        String classes = item.getAttribute("class");

        return classes != null && classes.toLowerCase().contains("active");
    }

    public void openSelectableGridTab() {
        scrollToElement(selectableGridTab);
        click(selectableGridTab);
    }

    public void selectMultipleItemsInSelectableGrid(String[] txts) {
        //actions.keyDown(Keys.CONTROL);

        for (String txt : txts) {
            WebElement item = findSelectableItemByTxt(txt);
            item.click();
        }

        //actions.keyUp(Keys.CONTROL).perform();
    }

    public boolean areItemsSelectedInSelectableGrid(String[] txts) {
        for (String txt : txts) {
            WebElement item = findSelectableItemByTxt(txt);
            String classes = item.getAttribute("class");

            return classes != null && classes.toLowerCase().contains("active");
        }
        return false;
    }

    // Método genérico para encontrar un item por texto en una pestaña activa (List or Grid)
    private WebElement findSelectableItemByTxt(String txt) {
        By locator = By.xpath(
                "//div[contains(@class,'tab-pane') and contains(@class,'active')]" +
                        "//li[contains(@class,'list-group-item') and normalize-space()='" + txt + "']"
        );
        scrollToElement(locator);
        return waitForVisibility(locator);
    }

    // ------------------------------
    //  Localizadores Resizable (sin By.id/ By.name)
    // ------------------------------
    private final By resizableFreeBox = By.xpath("(//div[contains(@class,'react-resizable')])[2]");
    private final By resizableFreeHandle = By.xpath("(//div[contains(@class,'react-resizable')])[2]" +
                    "//span[contains(@class,'react-resizable-handle')]"
    );
    private final By resizableLimitedBox = By.xpath("(//div[contains(@class,'react-resizable')])[1]");
    private final By resizableLimitedHandle = By.xpath("(//div[contains(@class,'react-resizable')])[1]" +
            "//span[contains(@class,'react-resizable-handle')]"
    );

    // ==============================
    //  Acciones Resizable
    // ==============================

    public Dimension getFreeResizableBoxSize() {
        return getBoxSize(resizableFreeBox);
    }

    public void resizeFreeBox(int xOffset, int yOffset) {
        resizeBox(resizableFreeHandle, xOffset, yOffset);
    }

    public Dimension getLimitedResizableBoxSize() {
        return getBoxSize(resizableLimitedBox);
    }

    public void resizeLimitedBox (int xOffset, int yOffset) {
        resizeBox(resizableLimitedHandle, xOffset, yOffset);
    }

    // Método genérico para traernos el tamaño del cuadro por Locator
    private org.openqa.selenium.Dimension getBoxSize(By boxLocator) {
        WebElement box = waitForVisibility(boxLocator);
        return box.getSize();
    }

    // Método genérico para redimensionar un cuadro por su handle locator
    private void resizeBox(By handleLocator, int xOffset, int yOffset) {
        dragAndDropByOffset(handleLocator, xOffset, yOffset);
    }

    // ------------------------------
    //  Localizadores Droppable (sin By.id/ By.name)
    // ------------------------------
    private final By droppableSimpleDraggable = By.xpath("//div[contains(@class,'simple-drop-container')]//div[contains(@class,'drag-box') and normalize-space()='Drag me']");
    private final By droppableSimpleTarget = By.xpath("//div[contains(@class,'simple-drop-container')]//div[contains(@class,'drop-box')]");
    private final By droppableSimpleTargetTxt = By.xpath("//div[contains(@class,'simple-drop-container')]//div[contains(@class,'drop-box')]//p");
    private final By droppablePreventTab = By.xpath("//a[contains(@class,'nav-link') and contains(normalize-space(),'Prevent')]");
    private final By preventDraggable = By.xpath("//div[contains(@class,'tab-pane') and contains(@class,'active')]" +
                    "//div[contains(@class,'drag-box')]"
    );
    private final By notGreedyOuterBox = By.xpath("//div[contains(@class,'drop-box-outer')]//p");
    private final By notGreedyInnerBox = By.xpath("//div[contains(@class,'drop-box-outer')]//div[contains(@class,'drop-box')]//p");

    // ==============================
    //  Acciones Droppable
    // ==============================
    public Point getSimpleDraggableLocation() {
        WebElement draggable = waitForVisibility(droppableSimpleDraggable);
        return draggable.getLocation();
    }

    public void performBasicDroppable() {
        dragInDroppable(droppableSimpleDraggable, droppableSimpleTarget);
    }

    public String getSimpleDroppableTxt() {
        return getDroppableAreaTxt(droppableSimpleTargetTxt);
    }

    public void openDroppablePreventTab() {
        scrollToElement(droppablePreventTab);
        click(droppablePreventTab);
    }

    public void dragToNotGreedyOuter() {
        dragInDroppable(preventDraggable, notGreedyOuterBox);
    }

    public void dragToNotGreedyInner() {
        dragInDroppable(preventDraggable, notGreedyInnerBox);
    }

    // Get Outer text (Not greedy)
    public String getNotGreedyOuterDropTxt() {
        return getDroppableAreaTxt(notGreedyOuterBox);
    }

    // Get Inner text (Not greedy)
    public String getNotGreedyInnerDropTxt() {
        return getDroppableAreaTxt(notGreedyInnerBox);
    }

    // Método genérico para arrastrar a cualquier destino
    private void dragInDroppable(By sourceLocator, By targetLocator) {
        dragAndDrop(sourceLocator, targetLocator);  // helper de BasePage
    }

    // Método genérico para obtener el texto de cualquier área de cualquier sección
    private String getDroppableAreaTxt(By txtLocator) {
        return getText(txtLocator);  // helper de BasePage
    }

    // ------------------------------
    //  Localizadores Draggable (sin By.id/ By.name)
    // ------------------------------
    private final By simpleDraggableBox = By.xpath("//div[contains(@class,'tab-pane') and contains(@class,'active')]" +
                    "//div[contains(@class,'drag-box') and normalize-space()='Drag me']"
    );
    private final By draggableAxisTab = By.xpath("//a[contains(@class,'nav-link') and normalize-space()='Axis Restricted']");
    private final By axisRestrictedXBox = By.xpath("//div[contains(@class,'tab-pane') and contains(@class,'active')]" +
                    "//div[contains(@class,'drag-box') and normalize-space()='Only X']"
    );

    // ==============================
    //  Acciones Draggable
    // ==============================
    public Point getSimpleDraggableBoxLocation() {
        scrollToElement(simpleDraggableBox);
        return getDraggableLocation(simpleDraggableBox);
    }

    public void dragSimpleDraggable(int xOffset, int yOffset) {
        dragInDraggable(simpleDraggableBox, xOffset, yOffset);
    }

    public void openDraggableAxisRestrictedTab() {
        scrollToElement(draggableAxisTab);
        click(draggableAxisTab);
    }

    public Point getAxisRestrictedXBoxLocation() {
        scrollToElement(axisRestrictedXBox);
        return getDraggableLocation(axisRestrictedXBox);
    }

    public void dragAxisXDraggable(int xOffset, int yOffset) {
        dragInDraggable(axisRestrictedXBox, xOffset, yOffset);
    }

    // Método genérico para traernos la localización actual (x, y) de un elemento draggable
    private Point getDraggableLocation(By locator) {
        WebElement draggableElement = waitForVisibility(locator);
        return draggableElement.getLocation();
    }

    // Método para arrastrar cualquier elemento draggable mediante un desplazamiento (xOffset, yOffset)
    private void dragInDraggable(By locator, int xOffset, int yOffset) {
        dragAndDropByOffset(locator, xOffset, yOffset);
    }
}
