```javascript
Feature: Navegación y localización de elementos en Amazon
  Como tester de interfaces web
  Quiero recorrer distintas secciones de la web de Amazon
  Para validar que los elementos clave se pueden localizar y que la navegación funciona correctamente

  Scenario: CP01 - Recorrido por la web de Amazon usando distintos elementos de navegación
    Given el usuario accede a la página principal de Amazon
    And el navegador está preparado para interactuar con la página
    When el usuario continúa la navegación desde la página inicial
    And el usuario accede a las secciones de ofertas de la página (Ofertas del Día, Ofertas relámpago y Outlet)
    And el usuario navega a la categoría "Juguetes y juegos" y a sus subcategorías relacionadas
    Then cada sección se muestra correctamente durante el recorrido
    And los elementos de navegación utilizados son visibles y clicables
    And al finalizar el recorrido el navegador se cierra correctamente
```