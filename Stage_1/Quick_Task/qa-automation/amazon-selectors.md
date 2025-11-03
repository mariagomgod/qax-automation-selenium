## Localización de elementos en Amazon

El test `navegarPorLaWeb` automatiza, con Selenium, un recorrido por la web de Amazon:

1. **Configuramos y abrimos el navegador**
    - Usamos `WebDriverManager.chromedriver().setup()` para descargar/configurar el driver de Chrome.
    - Creamos el `WebDriver` con `new ChromeDriver()` y maximizamos la ventana (`driver.manage().window().maximize()`).

2. **Definimos una espera explícita**
    - `WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));`
    - Se utilizará para esperar a que los elementos estén **visibles y clicables** antes de interactuar con ellos.

3. **Navegamos a la web de Amazon**
    - `driver.get("https://www.amazon.com/");`

4. **Clicamos en distintos botones**
    - Hacemos clic en el botón **"Continue shopping"** usando un `cssSelector`.
    - Hacemos clic en el botón **"CONTINUAR"** usando `Actions` (mover ratón + clic) y un `XPath` basado en su texto.

5. **Navegamos por secciones de la página**
    - Clicamos en la pestaña **"Ofertas del Día"** mediante un `cssSelector` con atributo `data-*`.
    - Clicamos en el botón **"Ofertas relámpago"** dentro de un contenedor con `id`.
    - Clicamos en la pestaña **"Outlet"** usando un `XPath` basado en el texto.

6. **Navegamos por distintas categorías de productos**
    - Clicamos en el enlace **"JUGUETES Y JUEGOS"** usando `By.linkText`.
    - Clicamos en **"Peluches"**, **icono Prime**, **"Monederos de Felpa"** y **"Compra juguetes más vendidos"**, todos localizados con `XPath` basados en el texto y la estructura HTML.

7. **Cerramos el navegador**
    - `driver.quit();` cierra la ventana de Chrome y finaliza el test.

## ¿Por qué uso esperas explícitas (`WebDriverWait`)?

- Las páginas modernas (como Amazon) cargan contenido de forma dinámica.
- Si intentamos clicar un elemento antes de que sea visible o esté habilitado, podemos obtener errores.
- Con `wait.until(ExpectedConditions.elementToBeClickable(...))`:
    - Selenium espera hasta que el elemento esté **realmente listo** para ser clicado.
    - Evitamos usar `Thread.sleep(...)`, que es menos fiable y más lento.

## ¿Por qué utilizar `By.cssSelector()` y `By.xpath()` en la mayoría de los pasos?

Porque en webs complejas:

### `By.cssSelector()`
Se usa cuando el elemento se puede identificar por:
- Etiqueta + clase: `button.a-button-text`
- Atributos: `a[data-testid='...']`
- Ids: `#mi-id`

**Ventajas:**
- Sintaxis corta y legible.
- Muy rápido y soportado nativamente por el navegador.

Ejemplos:
- `button[alt='Continue shopping']`
- `a[data-csa-c-content-id='nav_cs_gb']`

### `By.xpath()`
Se usa cuando necesitamos algo que CSS **no** hace bien, como:
- Buscar por **texto visible**: `"Outlet"`, `"Peluches"`, etc.
- Expresar estructuras más complejas:
    - “dame el `<a>` que contiene un `<span>` con este texto”.

Ejemplos:
- `//span[@class='nav-a-content' and normalize-space()='Outlet']`
- `//a[.//span[normalize-space()='Peluches']]`

## ¿Por qué utilizar `normalize-space()` en los XPaths?

Porque: 
- Elimina espacios al inicio y al final.
- Sustituye múltiples espacios internos por uno solo.

Ejemplo:
- Texto original: `"  Compra   juguetes   más vendidos  "`
- Con `normalize-space()` → `"Compra juguetes más vendidos"`

Al usar XPaths como:

```xpath
//span[normalize-space()='Outlet']
