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

## Ejercicio 01: Login Automático
- URL: [Login Form](https://bonigarcia.dev/selenium-webdriver-java/login-form.html)
- Objetivo: Automatizar el login con un usuario y contraseña de prueba.
- Instrucciones:
    1. Localizar los campos de **usuario** y **contraseña** sin usar `id` ni `name`.
    2. Ingresar los datos de prueba.
    3. Hacer click en el botón de login.
    4. Imprimir en consola si el login fue exitoso.
  
### <ins>Explicación paso a paso de lo que he ido realizando en este test:</ins>

1. **Configuración del driver de Chrome**
```
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
```
- Se usa **WebDriverManager** para evitar configurar manualmente la ruta del binario de ChromeDriver. Esto simplifica el setup y hace el código más portable.
- Se crea el objeto ChromeDriver, que es el que permite a Selenium controlar el navegador.
- Se maximiza la ventana para reducir problemas de visibilidad de elementos

2. **Definición de una espera explícita**
```
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```
- Se define una espera explícita de hasta 10 segundos.
- El objetivo es no fallar si la página tarda un poco en cargar o si un botón tarda un instante en estar clicable.

3. **Navegación a la página de login**
```
driver.get("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
```
- Se indica al navegador qué URL abrir.

4. **Localización de los campos de usuario y contraseña con XPath**
```
WebElement txtLogin = driver.findElement(By.xpath("//input[@type='text' and @class='form-control']"));
WebElement txtPassword = driver.findElement(By.xpath("//input[@type='password' and @class='form-control']"));
```
- Se usa **XPath combinando atributos** (type + class) para identificar los inputs correctos.
- Esto entrena a trabajar con escenarios donde no siempre hay un id cómodo disponible.

5. **Relleno del formulario con datos de prueba**
```
txtLogin.sendKeys("user");
txtPassword.sendKeys("user");
```
- Se simula el comportamiento real de un usuario escribiendo en los campos.
- Se usan credenciales de prueba (user / user), que la propia página entiende como válidas o inválidas según su lógica.

6. **Click en el botón Submit usando espera explícita**
```
WebElement btnSubmit = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and @class='btn btn-outline-primary mt-2']"))
);
btnSubmit.click();
```
- Se localiza el botón por XPath, de nuevo combinando atributos (type y class).
- No se hace click directamente: se usa WebDriverWait + elementToBeClickable para asegurarse de que:
  - El botón está presente.
  - Es visible.
  - Está en un estado en el que se puede hacer clic.

7. **Detección del resultado del login**
```
List<WebElement> loginExitoso = driver.findElements(By.id("success"));
List<WebElement> loginFallido = driver.findElements(By.id("invalid"));
```
- Se buscan los mensajes que la página muestra tras intentar el login:
    - id="success" → mensaje de éxito.
    - id="invalid" → mensaje de error.
- Se usa findElements (en plural) para que, si el elemento no existe, devuelva una lista vacía en lugar de lanzar una excepción. Esto permite comprobar de forma más segura si los mensajes están presentes.
```
boolean esExitoso = !loginExitoso.isEmpty() && loginExitoso.get(0).isDisplayed();
boolean esFallido = !loginFallido.isEmpty() && loginFallido.get(0).isDisplayed();
```
- Se considera que el login es exitoso si:
    - La lista de elementos de éxito no está vacía.
    - Y el primer elemento está visible en pantalla.
- Se hace lo mismo para el mensaje de fallo.
- De esta forma se controla tanto la existencia como la visibilidad del mensaje.
```
if (esExitoso) {
    System.out.println("Login existoso");
} else if (esFallido) {
    System.out.println("Login fallido");
} else {
    System.out.println("No se mostró ningún mensaje");
}
```
- Se imprime en consola el resultado, lo que facilita ver qué ha pasado en la ejecución sin necesidad de inspeccionar la página manualmente.

**Aclaración:** Uso findElements en lugar de findElement para obtener una lista de elementos y así evitar excepciones cuando el mensaje no existe (en ese caso Selenium devuelve una lista vacía). De esta forma puedo comprobar primero si la lista está vacía o no y, si hay elementos, acceder al primero con get(0). En este escenario solo espero un único mensaje de éxito o de error, por lo que trabajar con el primer elemento de la lista es suficiente para verificar si el mensaje se está mostrando en la página.

8. **Cierre del navegador**
```
driver.quit();
```
- Se cierra el navegador y se liberan los recursos asociados al WebDriver.
- Es una buena práctica para evitar procesos de Chrome abiertos en segundo plano después de la ejecución de los tests

---

## Ejercicio 02: Acciones de Menú
- URL: [Dropdown Menu](https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html)
- Objetivo: Realizar click, click derecho y doble click en los menús.
- Instrucciones:
    1. Localizar los menús usando CSS Selector o XPath avanzado.
    2. Hacer click, doble click y click derecho en los elementos seleccionados.
    3. Imprimir la acción realizada para cada elemento.

### <ins>Explicación paso a paso de lo que he ido realizando en este test:</ins>

1. **Configuración del driver de Chrome**
```
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
```
- Se usa **WebDriverManager** para evitar configurar manualmente la ruta del binario de ChromeDriver. Esto simplifica el setup y hace el código más portable.
- Se crea el objeto ChromeDriver, que es el que permite a Selenium controlar el navegador.
- Se maximiza la ventana para reducir problemas de visibilidad de elementos

2. **Definición de una espera explícita**
```
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```
- Se define una espera explícita de hasta 10 segundos.
- El objetivo es no fallar si la página tarda un poco en cargar o si un botón tarda un instante en estar clicable.

3. **Navegación a la página de dropdown menu**
```
driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
```
- Se indica al navegador qué URL abrir.

4. **Localizar los menús con CSS Selector**
```
WebElement useLeftClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-1"));
WebElement useRightClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-2"));
WebElement useDoubleClickDropdownMenu = driver.findElement(By.cssSelector("#my-dropdown-3"));
```
- Localizo tres elementos distintos del DOM usando CSS Selector por id:
    - #my-dropdown-1 → menú que reaccionará a un clic normal.
    - #my-dropdown-2 → menú que reaccionará a un clic derecho.
    - #my-dropdown-3 → menú que reaccionará a un doble clic.

5. **Crear el objeto Actions para acciones avanzadas de ratón**
```
Actions actions = new Actions(driver);
```
- Creo una instancia de Actions, la clase de Selenium que permite hacer acciones más avanzadas con el ratón y el teclado (movimientos, drag and drop, doble clic, clic derecho, etc.).

6. **Clic normal en el primer menú**
```
actions.moveToElement(useLeftClickDropdownMenu).perform();
useLeftClickDropdownMenu.click();
System.out.println("Clicado normal en el menú 1 (#my-dropdown-1)");
```
- Muevo el cursor del ratón sobre el primer menú (moveToElement).
- Hago un clic normal sobre ese elemento.
- Imprimo por consola qué acción se ha realizado, a modo de log.

7. **Clic derecho en el segundo menú**
```
actions.moveToElement(useRightClickDropdownMenu).perform();
actions.contextClick(useRightClickDropdownMenu).perform();
System.out.println("Clicado derecho en el menú 2 (#my-dropdown-2)");
```
- Muevo el ratón sobre el segundo menú.
- Ejecuto un clic derecho (contextClick) sobre ese elemento usando Actions.
- Imprimo en consola que se ha realizado un clic derecho sobre el menú 2.

8. **Doble clic en el tercer menú**
```
actions.moveToElement(useDoubleClickDropdownMenu).perform();
actions.doubleClick(useDoubleClickDropdownMenu).perform();
System.out.println("Doble clicado en el menú 3 (#my-dropdown-3)");
```
- Muevo el ratón sobre el tercer menú.
- Ejecuto un doble clic (doubleClick) sobre ese elemento.
- Imprimo en consola que se ha realizado un doble clic sobre el menú 3.

9. **Cierre del navegador**
```
driver.quit();
```
- Se cierra el navegador y se liberan los recursos asociados al WebDriver.
- Es una buena práctica para evitar procesos de Chrome abiertos en segundo plano después de la ejecución de los tests

---

## Ejercicio 03: Scroll Infinito
- URL: [Infinite Scroll](https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html)
- Objetivo: Practicar scroll infinito y detección de nuevos elementos.
- Instrucciones:
    1. Automatizar scroll hacia abajo hasta que se carguen al menos 20 nuevos elementos.
    2. Imprimir en consola los textos de los elementos visibles.

### <ins>Explicación paso a paso de lo que he ido realizando en este test:</ins>

1. **Configuración del driver de Chrome**
```
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
```
- Se usa **WebDriverManager** para evitar configurar manualmente la ruta del binario de ChromeDriver. Esto simplifica el setup y hace el código más portable.
- Se crea el objeto ChromeDriver, que es el que permite a Selenium controlar el navegador.
- Se maximiza la ventana para reducir problemas de visibilidad de elementos

2. **Definición de una espera explícita**
```
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```
- Se define una espera explícita de hasta 10 segundos.
- El objetivo es no fallar si la página tarda un poco en cargar o si un botón tarda un instante en estar clicable.

3. **Navegación a la página de scroll infinito**
```
driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
```
- Se indica al navegador qué URL abrir.

4. **Preparar el uso de JavaScript en el navegador**
```
JavascriptExecutor js = (JavascriptExecutor) driver;
```
- Se usa para poder ejecutar código JavaScript directamente en la página y para controlar el scroll de forma más precisa.

5. **Hacer scroll varias veces hacia abajo**
```
for (int i = 0; i <= 20; i++) {
    js.executeScript("window.scrollBy(0, 1000);");
}
```
- Se ejecuta un bucle que hace scroll hacia abajo 21 veces.
- Cada vez que se ejecuta window.scrollBy(0, 1000); la página baja 1000 píxeles.
- La idea es simular un usuario que va bajando para que se vaya cargando contenido nuevo en el scroll infinito.

6. **Obtener los elementos cargados después del scroll**
```
List<WebElement> elements = driver.findElements(By.cssSelector("#content > p"));
```
- Se buscan todos los párrafos (<p>) que están dentro del contenedor con id content.
- El resultado es una lista de elementos que representa el contenido que se ha ido cargando al hacer scroll.

7. **Hacer scroll hasta el último elemento cargado (scrollIntoView)**
```
if (!elements.isEmpty()) {
    WebElement ultimoElemento = elements.get(elements.size() - 1);
    js.executeScript("arguments[0].scrollIntoView(true);", ultimoElemento);
}
```
- Se comprueba que la lista no está vacía.
- Se obtiene el último elemento de la lista (elements.size() - 1), es decir, el párrafo más reciente que se ha cargado.
- Se llama a scrollIntoView(true) vía JavaScript para desplazar la página hasta ese último elemento, asegurando que se vea en pantalla.

8. **Imprimir el número total de elementos y su contenido**
```
System.out.println("Total de elementos: " + elements.size());

for (WebElement element : elements) {
    System.out.println(element.getText());
}
```
- Se imprime en consola cuántos elementos (<p>) se han encontrado en total después del scroll.
- Se recorre la lista e imprime el texto de cada párrafo, para ver el contenido que se ha ido cargando.

9. **Cierre del navegador**
```
driver.quit();
```
- Se cierra el navegador y se liberan los recursos asociados al WebDriver.
- Es una buena práctica para evitar procesos de Chrome abiertos en segundo plano después de la ejecución de los tests

---

## Ejercicio 04: Calculadora Aleatoria
- URL: [Random Calculator](https://bonigarcia.dev/selenium-webdriver-java/random-calculator.html)
- Objetivo: Realizar operaciones matemáticas automáticamente.
- Instrucciones:
    1. Localizar los botones de la calculadora usando XPath o CSS Selectors.
    2. Realizar operaciones básicas: suma, resta, división.
    3. Imprimir los resultados de cada operación en consola.

### <ins>Explicación paso a paso de lo que he ido realizando en este test:</ins>

1. **Configuración del driver de Chrome**
```
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
```
- Se usa **WebDriverManager** para evitar configurar manualmente la ruta del binario de ChromeDriver. Esto simplifica el setup y hace el código más portable.
- Se crea el objeto ChromeDriver, que es el que permite a Selenium controlar el navegador.
- Se maximiza la ventana para reducir problemas de visibilidad de elementos

2. **Definición de una espera explícita**
```
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```
- Se define una espera explícita de hasta 10 segundos.
- El objetivo es no fallar si la página tarda un poco en cargar o si un botón tarda un instante en estar clicable.

3. **Navegación a la página de calculadora aleatoria**
```
driver.get("https://bonigarcia.dev/selenium-webdriver-java/random-calculator.html");
```
- Se indica al navegador qué URL abrir.

4. **Localización de los botones y el display**
```
WebElement btnC = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='clear btn btn-outline-danger' and normalize-space()='C']")));

WebElement btnSuma = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='+']")));

WebElement btnResta = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='-']")));
                
WebElement btnDivision = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='operator btn btn-outline-success' and normalize-space()='÷']")));

WebElement btnIgual = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-warning' and normalize-space()='=']")));

WebElement btnDos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='2']")));

WebElement btnSeis = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='btn btn-outline-primary' and normalize-space()='6']")));

WebElement display = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='screen']")));

```
- Se localizan todos los botones relevantes (C, +, -, ÷, =, 2, 6) y la pantalla (display) usando XPath.
- En los XPath se combinan:
   - La clase del elemento (class='...').
   - El texto del botón (normalize-space()='2', '+', etc.).
- Se usa wait.until(elementToBeClickable(...)) para garantizar que cada botón está presente y se puede clicar antes de guardarlo en una variable.

5. **Desactivar la aleatoriedad de la calculadora**
```
for (int i = 0; i < 5; i++) {
    btnSeis.click();
    btnSuma.click();
    btnDos.click();
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);
    btnC.click();
}
```
- Según la propia página, la calculadora tiene un comportamiento aleatorio que puede afectar a los resultados.
- Para “normalizar” el comportamiento, se realizan 5 operaciones de prueba sin asserts:
  - Se pulsa C para limpiar.
- En estas operaciones iniciales:
  - Se usan los mismos botones que en los tests reales. 
  - Se utiliza JavaScript para hacer clic en el botón igual =:
     ```
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);
     ```
  - Esto invoca directamente el evento de clic desde JavaScript, evitando posibles problemas de interacción debidos al diseño de la página.

6. Operación de resta: 6 - 2 = 4
```
btnSeis.click();
btnResta.click();
btnDos.click();
((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);

String resultadoResta = display.getText().trim();
System.out.println("Resultado: " + resultadoResta);
assertEquals("4", resultadoResta);

btnC.click();
```
- Se simula la operación 6 - 2 pulsando los botones correspondientes.
- Se vuelve a usar JavaScript para pulsar el botón = por fiabilidad.
- Se lee el texto mostrado en la pantalla (display), se hace trim() para limpiar espacios, y se guarda en resultadoResta.
- Se imprime el resultado en consola.
- Se hace un assert para comprobar que el resultado es "4".
- Se pulsa C para dejar la calculadora limpia para la siguiente operación.

7. **Operación de suma: 6 + 2 = 8**
```
btnSeis.click();
btnSuma.click();
btnDos.click();
((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnIgual);

String resultadoSuma = display.getText().trim();
System.out.println("Resultado: " + resultadoSuma);
assertEquals("8", resultadoSuma);

btnC.click();
```
- Se realiza la operación 6 + 2 con los mismos pasos:
- Pulsar 6, +, 2, =.
- Leer el resultado del display.
- Imprimirlo por consola.
- Verificar con assertEquals que el resultado es "8".
- Limpiar con C.

8. **Operación de división: 6 ÷ 2 = 3**
```
btnSeis.click();
btnDivision.click();
btnDos.click();
((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonIgual);

String resultadoDivision = display.getText().trim();
System.out.println("Resultado: " + resultadoDivision);
assertEquals("3", resultadoDivision);
```
- Se realiza la operación 6 ÷ 2:
- Pulsar 6, ÷, 2, =.
- Se lee el resultado de la pantalla y se imprime.
- Se comprueba con un assertEquals que el resultado es "3".

9. **Cierre del navegador**
```
driver.quit();
```
- Se cierra el navegador y se liberan los recursos asociados al WebDriver.
- Es una buena práctica para evitar procesos de Chrome abiertos en segundo plano después de la ejecución de los tests

---

## Ejercicio 05: Registro y Login
- URL: [Parabank Register](https://parabank.parasoft.com/parabank/register.htm)
- Objetivo: Registrar un nuevo usuario y luego hacer login con los mismos datos.
- Instrucciones:
    1. Localizar los campos del formulario sin usar `id` o `name`.
    2. Ingresar datos de prueba y envía el formulario.
    3. Después del registro, hacer login con el mismo usuario.
    4. Verificar que el login fue exitoso e imprime un mensaje en consola.

### <ins>Explicación paso a paso de lo que he ido realizando en este test:</ins>

1. **Configuración del driver de Chrome**
```
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
```
- Se usa **WebDriverManager** para evitar configurar manualmente la ruta del binario de ChromeDriver. Esto simplifica el setup y hace el código más portable.
- Se crea el objeto ChromeDriver, que es el que permite a Selenium controlar el navegador.
- Se maximiza la ventana para reducir problemas de visibilidad de elementos

2. **Definición de una espera explícita**
```
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```
- Se define una espera explícita de hasta 10 segundos.
- El objetivo es no fallar si la página tarda un poco en cargar o si un botón tarda un instante en estar clicable.

3. **Navegación a la página de register**
```
driver.get("https://parabank.parasoft.com/parabank/register.htm");
```
- Se indica al navegador qué URL abrir.

4. **Generación de datos dinámicos**
```
String uniqueSuffix = String.valueOf(System.currentTimeMillis());
String firstName = "Nombre_" + uniqueSuffix;
String lastName  = "Apellido_" + uniqueSuffix;
String street    = "Calle_" + uniqueSuffix;
String city      = "Ciudad_" + uniqueSuffix;
String state     = "Estado_" + uniqueSuffix;
String zipCode   = String.valueOf((int)(Math.random() * 90000) + 10000); // 5 dígitos
String phoneNumber = "6" + (int)(Math.random() * 1_000_0000);
String ssn       = uniqueSuffix.substring(uniqueSuffix.length() - 9);
String username  = "user_" + uniqueSuffix;
String password  = "Pwd-" + UUID.randomUUID().toString().substring(0, 8);
```
5. **Relleno del formulario de registro (por name)**
```
driver.findElement(By.name("customer.firstName")).sendKeys(firstName);
driver.findElement(By.name("customer.lastName")).sendKeys(lastName);
driver.findElement(By.name("customer.address.street")).sendKeys(street);
driver.findElement(By.name("customer.address.city")).sendKeys(city);
driver.findElement(By.name("customer.address.state")).sendKeys(state);
driver.findElement(By.name("customer.address.zipCode")).sendKeys(zipCode);
driver.findElement(By.name("customer.phoneNumber")).sendKeys(phoneNumber);
driver.findElement(By.name("customer.ssn")).sendKeys(ssn);

driver.findElement(By.name("customer.username")).sendKeys(username);
driver.findElement(By.name("customer.password")).sendKeys(password);
driver.findElement(By.name("repeatedPassword")).sendKeys(password);
```
- Se usan localizadores estables (name) para cada campo.
- La contraseña y su confirmación usan el mismo valor dinámico.

6. **Enviar el registro**
```
WebElement btnRegister = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("form#customerForm input.button[type='submit'][value='Register']")
    )
);
btnRegister.click();
```
- Se espera a que el botón Register sea clicable y se hace clic.

7. **Verificar éxito del registro**
```
WebElement mensaje = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel p"))
);
assertEquals("Your account was created successfully. You are now logged in.", mensaje.getText());
```
- Se valida el mensaje de confirmación de registro y que el usuario ha quedado logueado automáticamente.
- Se muestran por consola el mensaje, el usuario y la contraseña generados.

8. **Cerrar sesión**
```
WebElement linkLogOut = wait.until(
    ExpectedConditions.elementToBeClickable(By.linkText("Log Out"))
);
linkLogOut.click();
```
- Se hace logout para comprobar el login manual con las credenciales recién creadas.

9. **Login con las credenciales creadas**
```
WebElement txtUsernameLogin = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.name("username"))
);
txtUsernameLogin.sendKeys(username);

WebElement txtPasswordLogin = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.name("password"))
);
txtPasswordLogin.sendKeys(password);

WebElement btnLogin = wait.until(
    ExpectedConditions.elementToBeClickable(By.cssSelector("input.button[value='Log In']"))
);
btnLogin.click();
```
- Se rellena el formulario de login con username y password generados y se inicia sesión.

10. **Verificaciones tras el login**
```
WebElement tituloAccounts = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//h1[normalize-space()='Accounts Overview']")
    )
);
assertTrue(tituloAccounts.isDisplayed());

WebElement accountTable = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.id("accountTable"))
);
assertTrue(accountTable.isDisplayed());
```
- Se comprueba que se llega al dashboard: título Accounts Overview visible y tabla de cuentas presente.

11. **Cierre del navegador**
```
driver.quit();
```
- Se cierra el navegador y se liberan los recursos asociados al WebDriver.
- Es una buena práctica para evitar procesos de Chrome abiertos en segundo plano después de la ejecución de los tests

