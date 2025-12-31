```javascript
Feature: Registro y login en Parabank
  Como tester de interfaces web
  Quiero registrar un nuevo usuario y hacer login en Parabank
  Para comprobar que el flujo de registro y autenticación funciona correctamente

  Scenario: CP01 - Registro y login en Parabank
    Given el usuario accede a la página "Parabank"
    When el usuario hace clic en el enlace "Register"
    Then la página de registro se muestra correctamente
    And el encabezado de la página de registro muestra el texto "Signing up is easy!"
    And el texto del encabezado se imprime en consola

    When el usuario completa el formulario de registro con datos válidos
    And envía el formulario de registro
    And el usuario inicia sesión con las credenciales registradas
    Then el login se realiza correctamente
    And el navegador se cierra
```
```javascript
Feature: Navegación de ofertas y categorías en Amazon
  Como tester de interfaces web
  Quiero navegar por las ofertas y categorías de Amazon
  Para comprobar que puedo llegar a la sección de "Compra juguetes más vendidos"

  Scenario: CP02 - Navegar hasta "Compra juguetes más vendidos" en Amazon
    Given el usuario accede a la página principal de Amazon
    When el usuario navega por las secciones de ofertas y categorías de productos
    And el usuario aplica los filtros necesarios en las categorías de juguetes
    And el usuario navega hasta la sección "Compra juguetes más vendidos"
    Then la sección "Compra juguetes más vendidos" se muestra correctamente
    And el navegador se cierra
```