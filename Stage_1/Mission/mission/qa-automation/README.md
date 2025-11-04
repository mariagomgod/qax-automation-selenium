## Challenge 01: Abriendo la aplicación y validando el título de la página de registro

### Objetivo

Automatizar la apertura de la aplicación web Parabank, navegar al formulario de registro y extraer el texto del encabezado principal (`<h1>`), verificando que el entorno de automatización funciona correctamente.

### Contexto

Después de probar tu entorno con un "Hola Mundo", ahora interactuarás con una aplicación real: Parabank. El objetivo es asegurarte de que:

- El navegador se abre correctamente.
- Puedes navegar a la URL: [https://parabank.parasoft.com/parabank/index.htm](https://parabank.parasoft.com/parabank/index.htm)
- Puedes hacer clic en el enlace **Register**.
- Puedes capturar y mostrar en consola el texto del encabezado `<h1>`.

### Requisitos Previos

- Proyecto de referencia en `Stage_1/WarmUp/project_initial/qa-automation`.
- Dependencias de Selenium/Playwright (según el stack en Java) y Maven instaladas.
- Conexión a Internet.

### Pasos

1. **Abrir la aplicación Parabank:**
    ```java
    driver.get("https://parabank.parasoft.com/parabank/index.htm");
    ```

2. **Hacer clic en "Register":**
    - Localiza el enlace de registro por su texto o selector CSS.

3. **Obtener el texto del `<h1>` en la página de registro:**
    - Localizadores sugeridos:
    - Imprime el texto en consola.

4. **Cerrar el navegador.**

### Criterio de Éxito

El test debe imprimir en consola algo como:

```
Página de Registro cargada. Encabezado encontrado: Signing up is easy!
```
