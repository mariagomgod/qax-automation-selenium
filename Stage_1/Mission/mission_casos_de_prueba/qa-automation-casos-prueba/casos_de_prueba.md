```javascript
Feature: Prácticas de automatización web
  Como tester de interfaces web
  Quiero practicar distintos flujos de interacción en aplicaciones de prueba
  Para validar comportamientos clave como login, navegación, scroll y registro

  Scenario: CP01 - Login automático con credenciales de prueba
    Given el usuario accede a la página "Login Form"
    And dispone de un usuario y contraseña de prueba
    When el usuario introduce las credenciales en el formulario de login
    And hace clic en el botón de login
    Then el acceso al sistema se procesa correctamente
    And se muestra en consola un mensaje indicando si el login fue exitoso

  Scenario: CP02 - Acciones de menú con clicks y doble click
    Given el usuario accede a la página "Dropdown Menu"
    And se muestran los elementos del menú en pantalla
    When el usuario realiza clic, doble clic y clic derecho sobre los menús definidos
    Then cada acción se ejecuta correctamente sobre el elemento correspondiente
    And se registra en consola la acción realizada para cada elemento

  Scenario: CP03 - Scroll infinito hasta cargar nuevos elementos
    Given el usuario accede a la página "Infinite Scroll"
    When el usuario realiza scroll hacia abajo de forma continuada
    Then se cargan al menos 20 nuevos elementos en la página
    And se muestran en consola los textos de los elementos visibles

  Scenario: CP04 - Operaciones en calculadora aleatoria
    Given el usuario accede a la página "Random Calculator"
    And la calculadora está visible en la página
    When el usuario realiza operaciones básicas de suma, resta y división
    Then cada operación se ejecuta correctamente
    And el resultado de cada operación se muestra en consola

  Scenario: CP05 - Registro y login en Parabank
    Given el usuario accede a la página "Parabank Register"
    And dispone de datos de registro válidos
    When el usuario completa el formulario de registro y envía la información
    Then el registro se completa correctamente
    And el usuario puede iniciar sesión con las mismas credenciales
    And el sistema confirma que el login es exitoso y se muestra un mensaje en consola

  Scenario: CP06 - Login correcto en CURA Healthcare Service
    Given el usuario accede a la página principal de "CURA Healthcare Service"
    And el usuario dispone de credenciales válidas
    When el usuario hace clic en el botón "Make Appointment"
    And el usuario introduce las credenciales válidas en el formulario de login
    And hace clic en el botón "Login"
    Then se muestra el encabezado "Make Appointment"
    And se registra en consola el mensaje "Inicio de sesión exitoso"

  Scenario: CP07 - Login incorrecto en CURA Healthcare Service
    Given el usuario accede a la página principal de "CURA Healthcare Service"
    And el usuario dispone de credenciales inválidas
    When el usuario hace clic en el botón "Make Appointment"
    And el usuario introduce las credenciales inválidas en el formulario de login
    And hace clic en el botón "Login"
    Then se muestra un mensaje de error indicando que el login no ha sido existoso 

  Scenario: CP08 - Agendar una cita médica en CURA Healthcare Service
    Given el usuario ha iniciado sesión correctamente en "CURA Healthcare Service"
    And la página "Make Appointment" está visible
    When el usuario selecciona "Seoul CURA Healthcare Center" en el desplegable
    And marca la opción "Apply for hospital readmission"
    And selecciona el programa "Medicare"
    And abre el calendario y selecciona una fecha disponible
    And escribe "Cita automatizada con QA Xpert" en el campo de comentarios
    And hace clic en el botón "Book Appointment"
    Then se muestra la página de confirmación de cita
    And se visualiza el mensaje "Appointment Confirmation"

  Scenario: CP09 - Navegación en el menú lateral de CURA Healthcare Service
    Given el usuario ha iniciado sesión correctamente en "CURA Healthcare Service"
    And la página principal está visible
    When el usuario abre el menú lateral
    And navega a las secciones "Home", "History", "Profile" y "Logout" una por una
    Then en cada sección se muestra el título o texto principal correspondiente
    And al finalizar la navegación el usuario queda correctamente desconectado del sistema
```