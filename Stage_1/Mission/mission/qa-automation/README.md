# Misión Completa: Automatización Web con Selenium 🧪
 
En esta misión se desarrollará un proyecto Maven que incluirá **varios escenarios de automatización**, desde login hasta scroll infinito, pasando por acciones avanzadas y manejo de iframes y modales.

## ⚙ Requerimientos

- Java 17 o superior
- Maven
- IDE (IntelliJ IDEA o VS Code)
- WebDriverManager
- Selenium 4.x
- Conexión a Internet

## 📝 Ejercicios

### Ejercicio 01: Login Automático
- URL: [Login Form](https://bonigarcia.dev/selenium-webdriver-java/login-form.html)
- Objetivo: Automatizar el login con un usuario y contraseña de prueba.
- Instrucciones:
    1. Localizar los campos de **usuario** y **contraseña** sin usar `id` ni `name`.
    2. Ingresar los datos de prueba.
    3. Hacer click en el botón de login.
    4. Imprimir en consola si el login fue exitoso.

### Ejercicio 02: Acciones de Menú
- URL: [Dropdown Menu](https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html)
- Objetivo: Realizar click, click derecho y doble click en los menús.
- Instrucciones:
    1. Localizar los menús usando CSS Selector o XPath avanzado.
    2. Hacer click, doble click y click derecho en los elementos seleccionados.
    3. Imprimir la acción realizada para cada elemento.

### Ejercicio 03: Scroll Infinito
- URL: [Infinite Scroll](https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html)
- Objetivo: Practicar scroll infinito y detección de nuevos elementos.
- Instrucciones:
    1. Automatizar scroll hacia abajo hasta que se carguen al menos 20 nuevos elementos.
    2. Imprimir en consola los textos de los elementos visibles.

### Ejercicio 04: Calculadora Aleatoria
- URL: [Random Calculator](https://bonigarcia.dev/selenium-webdriver-java/random-calculator.html)
- Objetivo: Realizar operaciones matemáticas automáticamente.
- Instrucciones:
    1. Localizar los botones de la calculadora usando XPath o CSS Selectors.
    2. Realizar operaciones básicas: suma, resta, división.
    3. Imprimir los resultados de cada operación en consola.

### Ejercicio 05: Registro y Login
- URL: [Parabank Register](https://parabank.parasoft.com/parabank/register.htm)
- Objetivo: Registrar un nuevo usuario y luego hacer login con los mismos datos.
- Instrucciones:
    1. Localizar los campos del formulario sin usar `id` o `name`.
    2. Ingresar datos de prueba y envía el formulario.
    3. Después del registro, hacer login con el mismo usuario.
    4. Verificar que el login fue exitoso e imprime un mensaje en consola.

