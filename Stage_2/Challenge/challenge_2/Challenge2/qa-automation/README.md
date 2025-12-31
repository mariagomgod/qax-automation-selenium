## Challenge 02: Aumentando cobertura y escalabilidad

## 🎯 Objetivos

- Dominar Comportamientos Avanzados
- Optimizar la Ejecución con TestNG
- Implementar POM
- Validar la Estabilidad Continua


## ⚙ Requerimientos

- Java 17 o superior
- Maven
- IDE (IntelliJ IDEA o VS Code)
- WebDriverManager
- Selenium 4.x
- TestNG
- Conexión a Internet

---

## Historia de Usuario: Gestión de Alertas

- **URL:** `https://demo.automationtesting.in/Alerts.html`
- **Como:** Un usuario que interactúa con el sitio
- **Quiero:** Poder confirmar, cancelar o ingresar texto correctamente en las tres variantes de alertas del navegador
- **Para:** Asegurarme de que el sistema maneja mis respuestas y actualiza la página con el mensaje de resultado correcto

### Criterios de Aceptación

1. **Alerta Simple (Alert)**
    - Acción: Hacer clic en el botón que muestra la alerta simple.
    - Validación: Cambiar el foco a la alerta y ejecutar
    - Resultado esperado: La alerta desaparece y no se muestra texto de resultado en la página.

2. **Alerta de Confirmación (Confirm)**
    - Escenario A — Aceptar (OK):
        - Acción: Hacer clic en el botón y ejecutar
        - Resultado esperado: La página muestra el texto `You pressed Ok`.
    - Escenario B — Cancelar (Cancel):
        - Acción: Hacer clic en el botón y ejecutar
        - Resultado esperado: La página muestra el texto `You Pressed Cancel`.

3. **Alerta de Prompt (Prompt)**
    - Acción: Hacer clic en el botón que dispara el prompt.
    - Interacción: Ingresar texto de prueba generado dinamicamente.
    - Resultado esperado: La página muestra el mensaje que contiene el texto ingresado (por ejemplo: `Hello Texto de Prueba How are you today`).
---

## ️ Historia de Usuario: Drag and Drop

**Componente:** Drag and Drop  
**Título:** Mover Elementos con Precisión (Drag and Drop)  
**URL Estático:** https://demo.automationtesting.in/Static.html  
**URL Dinámico:** https://demo.automationtesting.in/Dynamic.html  
**Como:** Un usuario interactivo  
**Quiero:** Arrastrar y soltar elementos a sus áreas de destino designadas  
**Para:** Verificar que la funcionalidad de arrastre posiciona el elemento correctamente

###  Criterios de Aceptación

1. **Validación de Drag and Drop Estático**
    - Acción: Navegar a la página estática. Localizar `#draggable` y `#droppable`.
    - Resultado esperado: El texto del destino cambia a \"Dropped!\" (o texto de éxito) y cambian estilos CSS (ej. color de fondo).

2. **Validación de Drag and Drop Dinámico**
    - Acción: Navegar a la página dinámica. Localizar el elemento fuente (p. ej. imagen \"Angular\") y el área destino `#droparea`.
    - Resultado esperado: El elemento fuente ya no aparece en su posición inicial (o desaparece) indicando que se movió correctamente.

---

## Historia de Usuario: Navegación de Submenús con Mouse Hover

Componente: Menú de navegación  
Título: Navegación completa y validación de submenús  
URL: https://demo.automationtesting.in/Dynamic.html

Como: Un usuario que navega por la interfaz  
Quiero: Pasar el cursor sobre un elemento de menú principal para desplegar sus opciones y luego hacer clic en todos los submenús  
Para: Asegurar que cada opción del submenú es accesible, abre la página correspondiente y que el título y al menos un elemento clave de esa nueva página se cargan correctamente

### Criterios de Aceptación

1. Despliegue del Menú Principal
    - Acción: Localizar el elemento del menú principal que activa el hover
    - Resultado esperado: El submenú asociado se vuelve visible y accesible en el DOM\.

2. Recorrido y Validación de Submenús
    - Propósito: Iterar por cada opción del submenú para validar la navegación y el contenido de la página destino\.
    - Acción por cada submenú
    - Validaciones
        - Contenido: Al menos un elemento clave de la página destino está visible un botón principal o un identificador específico
    - Retorno: Volver a la página principal del menú usando  o navegando de nuevo para continuar con el siguiente submenú.

---


## Historia de Usuario: iFrames Anidados

**Componente:** iFrames anidados  
**URL Base:** `https://demo.automationtesting.in/Frames.html`
**Como:** Un usuario  
**Quiero:** Cambiar el foco al iFrame padre y luego al iFrame hijo para interactuar con elementos anidados  
**Para:** Verificar la correcta gestión del foco del driver y la interacción con contenido embebido

**Criterios de Aceptación**
1. Cambio de foco al iFrame anidado
    - Acción: Navegar a la sección indicada, localizar iFrame padre y ejecutar, luego localizar iFrame hijo y ejecutar
    - Validación: Ingresar texto (ej. `Texto Anidado`) en un campo dentro del iFrame hijo y verificar que contiene el valor ingresado.
2. Retorno al contenido principal
    - Acción: Volver al iframe padre y escribir `Texto Padre`
    - Resultado esperado: Un elemento fuera de los iFrames (ej. encabezado) es visible/clickeable, confirmando que el foco volvió al contexto principal.

---

## Historia de Usuario: Ventanas y Pestañas (Windows)

**Componente:** Ventanas y pestañas  
**URL:** `https://demo.automationtesting.in/Windows.html`  
**Como:** Un usuario que abre enlaces en nuevas ventanas/pestañas  
**Quiero:** Abrir una nueva pestaña/ventana, cambiar el foco para validarla y volver a la original  
**Para:** Asegurar la correcta gestión de handles de ventana

**Criterios de Aceptación**
1. Apertura y validación de nueva pestaña
    - Acción: Guardar handle principal, abrir pestaña y cambiar foco al nuevo handle.
    - Validación: Verificar el título esperado (ej. `Selenium`) y luego regresar al handle principal.
2. Apertura y cierre de ventana separada
    - Acción: Abrir ventana separada, cambiar foco, ejecutar en la ventana secundaria.
    - Resultado esperado: La ventana secundaria está cerrada y el foco retorna al handle principal activo.

---

## Historias adicionales solicitadas

### Accordion
**Componente:** Accordion  
**URL:** `https://demo.automationtesting.in/Accordion.html`  
**Como:** Un usuario que interactúa con secciones plegables  
**Quiero:** Expandir y contraer cada panel del accordion  
**Para:** Verificar que el contenido correcto se muestra al expandir cada sección  
**Criterio:** Al hacer clic en cada header, el panel asociado se expande y muestra contenido esperado; los otros paneles permanecen en estado correcto.

### Datepicker
**Componente:** Datepicker  
**URL:** `https://demo.automationtesting.in/Datepicker.html`  
**Como:** Un usuario que selecciona fechas  
**Quiero:** Elegir una fecha específica y navegar entre meses/años  
**Para:** Asegurar que la fecha seleccionada se refleja correctamente en el input  
**Criterio:** Selección de fecha dinámica y validación del valor resultante en el campo.

### Modals
**Componente:** Modales  
**URL:** `https://demo.automationtesting.in/Modals.html`  
**Como:** Un usuario que abre diálogos modales  
**Quiero:** Abrir y cerrar modal, interactuar con su contenido  
**Para:** Verificar que el modal bloquea el fondo y que los botones funcionan (aceptar/cerrar)  
**Criterio:** Modal visible tras acción y desaparece al cerrar; acciones internas ejecutan resultados esperados.

### Progress Bar
**Componente:** Barra de progreso  
**URL:** `https://demo.automationtesting.in/ProgressBar.html`  
**Como:** Un usuario observando progreso de tareas  
**Quiero:** Iniciar la barra de progreso y validarla hasta su finalización  
**Para:** Confirmar que el progreso avanza y llega al 100% correctamente  
**Criterio:** La barra muestra incremento y alcanza el estado final esperado.

### Dynamic Data
**Componente:** Datos dinámicos  
**URL:** `https://demo.automationtesting.in/DynamicData.html`  
**Como:** Un usuario que recibe contenido actualizado dinámicamente  
**Quiero:** Validar que el contenido dinámico cambia y se muestra correctamente  
**Para:** Verificar actualización en tiempo real y consistencia visual  
**Criterio:** Detectar al menos un cambio dinámico en el contenido tras una acción o intervalo.

### Slider
**Componente:** Slider  
**URL:** `https://demo.automationtesting.in/Slider.html`  
**Como:** Un usuario que ajusta un control deslizante  
**Quiero:** Mover el slider a posiciones específicas  
**Para:** Asegurar que el valor asociado cambia acorde al movimiento  
**Criterio:** Al arrastrar, el valor visible (o atributo) coincide con la posición esperada.

### Loader
**Componente:** Loader / Carga  
**URL:** `https://demo.automationtesting.in/Loader.html`  
**Como:** Un usuario esperando la finalización de una carga  
**Quiero:** Validar la aparición y desaparición del loader  
**Para:** Confirmar que la interfaz indica correctamente el estado de carga y permite la interacción al terminar  
**Criterio:** Loader visible durante la operación y desaparece mostrando el contenido final al completarse.

---

## Instrucciones

### 1: Refactorización de la clase `BasePage`
Asegurar que los métodos de interacción en `BasePage` sean flexibles, reutilizables y utilicen `Actions` o `JavascriptExecutor` cuando corresponda.

### 2: Implementación de casos de prueba
Crear una clase de prueba dedicada por cada Historia de Usuario (HU). Todas las clases deben heredar de una base común que gestione `setUp()` y `tearDown()`.

- Las pruebas deben incluir aserciones explícitas (`Assert.assertTrue`, `Assert.assertEquals`) según los criterios.

* **Generación de Reporte:** Ejecutar todos los tests utilizando el comando Maven:
    ```bash
    mvn clean test
    ```

* **Comandos de ejecución para tests, browsers y Urls concretos:** 

* AlertsTest: Con un navegador chrome y la url por consola
    ```bash
    mvn clean test -Dtest=AlertsTest -Dbrowser=chrome -DbaseURL=https://demo.automationtesting.in/
    ```
* IframeTest: Con valores por defecto
    ```bash
    mvn clean test -Dtest=IframeTest
    ```
* DragAndDropTest: Con un navegador firefox y la url por defecto
    ```bash
    mvn clean test -Dtest=DragAndDropTest -Dbrowser=firefox
    ```
* ModalsTest: La url por consola
    ```bash
    mvn clean test -Dtest=ModalsTest -DbaseURL=https://demo.automationtesting.in/
    ```
* NewWindowsTest: Con valores por defecto
    ```bash
    mvn clean test -Dtest=NewWindowsTest
    ``` 