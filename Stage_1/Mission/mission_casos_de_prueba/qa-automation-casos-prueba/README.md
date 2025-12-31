# Misión Completa: Automatización Web con Selenium 🧪

En esta misión se desarrollará un proyecto Maven que incluirá **varios escenarios de automatización**, desde login hasta scroll infinito, pasando por acciones avanzadas y manejo de iframes y modales.

## 🎯 Objetivos

- Crear un proyecto Maven desde cero y estructurarlo correctamente para automatización.
- Localizar elementos web usando XPath, CSS Selectors, y axes avanzados, evitando id o name.
- Automatizar formularios de login y registro.
- Ejecutar acciones de usuario: click, doble click, click derecho, hover, drag & drop, scroll infinito.
- Interactuar con menús, iframes, alerts y modales.
- Imprimir resultados en consola y realizar validaciones básicas.
- Manejar múltiples pruebas en archivos separados dentro de un mismo proyecto.


## ⚙ Requerimientos

- Java 17 o superior
- Maven
- IDE (IntelliJ IDEA o VS Code)
- WebDriverManager
- Selenium 4.x
- JUnit5
- Cucumber
- Conexión a Internet

---

## 🧪 Cómo he realizado los tests de los ejercicios planteados a continuación:
- Me he basado en el **patrón de diseño Page Object Model (POM)** que separa los tests de la UI: cada página se modela como una clase con sus **selectores** y **acciones**.
  **Resultado:** tests más **legibles**, **reutilizables** y **fáciles de mantener** ante cambios en la interfaz.
- He organizado el proyecto en varias carpetas:
    - **Config:** configuración de las páginas web utilizadas en los distintos ejercicios.
    - **Pages:** contiene las clases que representan cada página, con sus selectores y acciones.
    - **Runners:** configuración del runner para ejecutar los tests.
    - **Steps:** una clase por cada ejercicio, donde se definen los pasos de cada test.
    - **Utils:** incluye el **RunContext**, que sirve para compartir estado y dependencias entre los steps.
    - *NOTA:* he ido documentando aquellas líneas de código que he creido más difíciles de entender o su propósito en los tests, con un comentario encima de cada una de ellas.

---

## ⚙ ¿Cómo ejecutar los tests?
Con el comando `mvn clean test` desde la terminal.

---

## 📝 Ejercicios

## Ejercicio 01: Login Automático
- URL: [Login Form](https://bonigarcia.dev/selenium-webdriver-java/login-form.html)
- Objetivo: Automatizar el login con un usuario y contraseña de prueba.
- Instrucciones:
    1. Localizar los campos de **usuario** y **contraseña** sin usar `id` ni `name`.
    2. Ingresar los datos de prueba.
    3. Hacer click en el botón de login.
    4. Imprimir en consola si el login fue exitoso.

---

## Ejercicio 02: Acciones de Menú
- URL: [Dropdown Menu](https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html)
- Objetivo: Realizar click, click derecho y doble click en los menús.
- Instrucciones:
    1. Localizar los menús usando CSS Selector o XPath avanzado.
    2. Hacer click, doble click y click derecho en los elementos seleccionados.
    3. Imprimir la acción realizada para cada elemento.

---

## Ejercicio 03: Scroll Infinito
- URL: [Infinite Scroll](https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html)
- Objetivo: Practicar scroll infinito y detección de nuevos elementos.
- Instrucciones:
    1. Automatizar scroll hacia abajo hasta que se carguen al menos 20 nuevos elementos.
    2. Imprimir en consola los textos de los elementos visibles.

---

## Ejercicio 04: Calculadora Aleatoria
- URL: [Random Calculator](https://bonigarcia.dev/selenium-webdriver-java/random-calculator.html)
- Objetivo: Realizar operaciones matemáticas automáticamente.
- Instrucciones:
    1. Localizar los botones de la calculadora usando XPath o CSS Selectors.
    2. Realizar operaciones básicas: suma, resta, división.
    3. Imprimir los resultados de cada operación en consola.

---

## Ejercicio 05: Registro y Login
- URL: [Parabank Register](https://parabank.parasoft.com/parabank/register.htm)
- Objetivo: Registrar un nuevo usuario y luego hacer login con los mismos datos.
- Instrucciones:
    1. Localizar los campos del formulario sin usar `id` o `name`.
    2. Ingresar datos de prueba y envía el formulario.
    3. Después del registro, hacer login con el mismo usuario.
    4. Verificar que el login fue exitoso e imprime un mensaje en consola.
---

### Exercise 05: Login Correcto
- **URL:** [CURA Healthcare Service - Login](https://katalon-demo-cura.herokuapp.com/profile.php#login)
- **Objetivo:** Validar el acceso al sistema con credenciales correctas.
- **Instrucciones:**
    1. Haz clic en el botón **Make Appointment** desde la página principal.
    2. Ingresa las credenciales:
        - Username: `John Doe`
        - Password: `ThisIsNotAPassword`
    3. Presiona el botón **Login**.
    4. Verifica que se muestre el encabezado **Make Appointment** y muestra en consola:
       ```
       Inicio de sesión exitoso
       ```

---
### Exercise 06: Login incorrecto
- **URL:** [CURA Healthcare Service - Login](https://katalon-demo-cura.herokuapp.com/profile.php#login)
- **Objetivo:** Validar el acceso al sistema con credenciales correctas.
- **Instrucciones:**
    1. Haz clic en el botón **Make Appointment** desde la página principal.
    2. Ingresa las credenciales invalidas:
        - Username: `QAX`
        - Password: `ThisIsNotAPassword`
    3. Presiona el botón **Login**.
    4. Verifica el mensaje
       ```
       Login failed! Please ensure the username and password are valid.
       ```

---

### Exercise 07: Agendar una Cita
- **URL:** [Make Appointment](https://katalon-demo-cura.herokuapp.com/#appointment)
- **Objetivo:** Automatizar el flujo completo de agendamiento de una cita médica.
- **Instrucciones:**
    1. Selecciona la opción **Seoul CURA Healthcare Center** del dropdown.
    2. Marca la casilla **Apply for hospital readmission**.
    3. Elige el programa **Medicare**.
    4. Abre el calendario y selecciona una fecha disponible.
    5. En el campo **Comment**, escribe:
       ```
       Cita automatizada con QA Xpert
       ```
    6. Haz clic en **Book Appointment** y valida que aparezca el mensaje **Appointment Confirmation**.

---

### Exercise 08: Navegación en el Menú
- **URL:** [CURA Healthcare Service](https://katalon-demo-cura.herokuapp.com/)
- **Objetivo:** Validar los enlaces del menú lateral.
- **Instrucciones:**
    1. Abre el menú lateral (ícono ☰).
    2. Haz clic en los enlaces **Home**, **History**, **Profile** y **Logout** uno por uno.
    3. Imprime en consola el título o texto principal de cada página.
    4. Cierra sesión al finalizar la ejecución.
