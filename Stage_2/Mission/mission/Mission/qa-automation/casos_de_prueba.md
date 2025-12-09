# Casos de Prueba en Gherkin – Registro de Nuevo Usuario

## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio https://demoqa.com/
- **Quiero** interactuar y validar correctamente cada subsección del módulo "Elements" (TextBox, CheckBox, RadioButton, WebTables, Buttons, Links, Broken Links, Upload/Download, Dynamic Properties
- **Para** asegurar que todos los elementos de entrada, selección, formularios, navegación y acciones dinámicas del sitio funcionan según lo esperado
---
## Historia de Usuario: Módulo de Elementos
URL: https://demoqa.com/elements
---
**Scenario 01: TextBox (Formulario de Entrada) Interacción y Envío**
```
Given que el usuario navega a "https://demoqa.com/text-box"
When rellena todos los campos del formulario y hace clic en Submit
Then los datos ingresados se muestran correctamente en la sección de resultado
```
**Scenario 02: CheckBox (Selección Jerárquica)**
```
Given que el usuario navega a "https://demoqa.com/checkbox"
When selecciona el checkbox Home
Then todos los elementos hijos quedan seleccionados
When desmarca Home y marca Desktop y Downloads
Then Home aparece en estado de selección parcial
```
**Scenario 03: Radio Button (Selección Exclusiva)**
```
Given que el usuario navega a "https://demoqa.com/radio-button"
When selecciona la opción Yes
Then se muestra el mensaje "You have selected Yes"
When selecciona la opción Impressive
Then se muestra el mensaje "You have selected Impressive"
And la opción No, no se puede seleccionar
```
**Scenario 04: Web Tables (Formulario de Registro y Edición)**
```
Given que el usuario navega a "https://demoqa.com/webtables"
When agrega un nuevo registro con datos válidos
Then el nuevo registro aparece en la tabla
When edita ese registro
Then los cambios se reflejan en la tabla
When elimina ese registro
Then el registro deja de aparecer en la tabla
```
**Scenario 05: Buttons (Acciones de Clic)**
```
Given que el usuario navega a "https://demoqa.com/buttons"
When hace doble clic en el botón de double click
Then se muestra el mensaje de doble clic
When hace clic derecho en el botón de right click
Then se muestra el mensaje de clic derecho
When hace clic simple en el botón de dynamic click
Then se muestra el mensaje de clic dinámico
```
**Scenario 06: Links (Navegación)**
```
Given que el usuario navega a "https://demoqa.com/links"
When hace clic en el enlace Home
Then se abre una nueva pestaña con la página principal
```
**Scenario 07: Links (Llamadas API)**
```
Given que el usuario navega a "https://demoqa.com/links"
When hace clic en los enlaces de API (Created, No Content, Moved, etc.)
Then los enlaces responden sin errores de navegación ni timeout
```
**Scenario 08: Broken Links - Images (Validación de Recursos)**
```
Given que el usuario navega a "https://demoqa.com/broken"
Then la imagen válida se carga correctamente
And la imagen rota no se carga correctamente
When hace clic en el enlace "Valid Link"
Then navega a una página válida
When hace clic en el enlace "Broken Link"
Then la página muestra un error HTTP
```
**Scenario 09: Upload and Download (Carga de Archivos)**
```
Given que el usuario navega a "https://demoqa.com/upload-download"
When sube un archivo de prueba en el campo de upload
Then se muestra el nombre del archivo cargado
```
**Scenario 10: Upload and Download (Descarga de Archivos)**
```
Given que el usuario navega a "https://demoqa.com/upload-download"
When hace clic en el botón Download
Then el archivo se descarga correctamente
```
**Scenario 11: Dynamic Properties (Elementos Dinámicos)**
```
Given que el usuario navega a "https://demoqa.com/dynamic-properties"
When espera a que el botón "Enable After 5 Seconds" esté habilitado
Then el botón se puede clicar
When espera a que el botón "Color Change" cambie
Then su color o clase CSS es diferente a la inicial
When espera a que el botón "Visible After 5 Seconds" sea visible
Then el botón aparece en la página
```
---
## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio https://demoqa.com/
- **Quiero** rellenar y enviar todos los campos obligatorios del formulario de registro de estudiantes, incluyendo entradas de texto, selección de género, fecha, checkboxes, carga de archivos y dropdowns dependientes
- **Para** asegurar que la aplicación procesa y muestra correctamente la información compleja en la tabla de confirmación final
---
## Historia de Usuario: Formulario de Registro de Estudiantes
URL: https://demoqa.com/forms
---
**Scenario 01: Rellenar campos de nombre y contacto con datos válidos**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When relleno los campos del formulario First Name, Last Name, Email, Gender y Mobile con datos válidos
Then los campos muestran los valores ingresados
```
### NOTA: Campo **Gender** – Resumen de lo que se intentó:
- **Contexto**
    - El campo *Gender* en DemoQA usa `input type="radio"` ocultos y elementos `label`/`div` personalizados para el estilo.
    - Objetivo: seleccionar un género (por ejemplo, **Female**) y verificar desde Selenium que el radio está marcado (`checked`).

- **Intentos realizados**
    - Click en el `label` (`//label[text()='Female']`) y búsqueda del `input` asociado con `preceding-sibling::input[2]` → `isSelected()` no devolvía `true` de forma fiable.
    - Localizar el `input` por `id` (`#gender-radio-2`) y usar `waitForVisibility` → lanzaba `TimeoutException` porque el input está oculto.
    - Cambiar a `waitForPresence(#gender-radio-2)` y luego llamar a `isSelected()` → el elemento está presente, pero seguía devolviendo `false`.
    - Usar JavaScript para obtener `input[name='gender']:checked` y leer su `value` → devolvía `null`, indicando que ningún radio aparecía como `checked`.
    - Click en el `div` contenedor del radio (`//input[@name='gender' and @value='Female']/parent::div`) y comprobación con JS (`el.checked`) → en este entorno concreto, seguía sin registrar el radio como marcado de forma verificable.

- **Conclusión**
    - La combinación de:
        - inputs de tipo radio ocultos,
        - estructura HTML específica,
        - y comportamiento particular de esta página de demo,
          hizo muy poco fiable la verificación directa del estado `checked` desde Selenium.
    - Para no bloquear el avance del ejercicio:
        - Se continúa seleccionando el género (haciendo click en el elemento correspondiente).
        - Se evita un assert a bajo nivel sobre el input de *Gender*.
        - Se valida el género donde realmente importa según los criterios de aceptación:  
          en la **tabla de confirmación (modal)** que aparece tras enviar el formulario.

**Scenario 02: Seleccionar fecha de nacimiento mediante el datepicker**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When abro el datepicker de fecha de nacimiento 
And navego hasta el año "1990" y selecciono el día "15" del mes "March"
Then el campo Date of Birth muestra la fecha seleccionada en el formato esperado
```
**Scenario 03: Seleccionar varios subjects desde el autocompletar**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When escribo "Com" en el campo Subjects
And selecciono las opciones "Computer Science" y "Commerce" de la lista de autocompletar
Then ambos subjects permanecen seleccionados en el campo Subjects
```
**Scenario 04: Seleccionar hobbies y subir una imagen de prueba**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When selecciono los hobbies "Sports" y "Music"
And adjunto el archivo de imagen de prueba "test-picture.png" en el campo Picture
Then los hobbies "Sports" y "Music" aparecen seleccionados
And el archivo subido "test-picture.png" aparece asociado al campo Picture
```
**Scenario 05: Rellenar la dirección actual**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When introduzco una dirección en el campo Current Address
Then el campo Current Address muestra lo que se ha escrito
```
**Scenario 06: Seleccionar estado y ciudad dependientes**
```
Given que navego a "https://demoqa.com/automation-practice-form"
When selecciono el estado "NCR" en el dropdown State
And espero a que el dropdown City cargue las opciones para el estado "NCR"
And selecciono la ciudad "Delhi" en el dropdown City
Then el dropdown State muestra "NCR"
And el dropdown City muestra "Delhi"
```
**Scenario 07: Enviar el formulario y validar la tabla de confirmación**
```
Given que navego a "https://demoqa.com/automation-practice-form"
And he completado correctamente todos los campos obligatorios del formulario de registro
When hago clic en el botón Submit
Then se muestra el modal con la tabla de confirmación
And la tabla de confirmación muestra correctamente el nombre completo, género, fecha de nacimiento, subjects y dirección combinada
```
---
## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio https://demoqa.com/
- **Quiero** poder iniciar, interactuar y cerrar correctamente todos los elementos que cambian el foco del driver (ventanas, pestañas, alertas, iFrames y modales)
- **Para** asegurar que el sistema maneja la transición de contexto y que la información se intercambia o persiste correctamente en la página principal
---
## Historia de Usuario: Gestión de Contextos de Ejecución (Alerts, Frames & Windows)
URL: https://demoqa.com/alertsWindows

## Browser Windows
**Scenario 01: Abrir nueva pestaña y regresar al handle principal**
```
Given que navego a "https://demoqa.com/browser-windows"
When el usuario hace clic en el botón para abrir una nueva pestaña
And el driver cambia el foco a la nueva pestaña
Then el contenido esperado en la nueva pestaña es visible
And el driver regresa el foco al handle de la ventana principal
And volvemos a la página principal
```
**Scenario 02: Abrir nueva ventana, cerrarla y volver al handle principal**
```
Given que navego a "https://demoqa.com/browser-windows"
And el driver cambia el foco a la nueva ventana
And valida el contenido esperado en la nueva ventana
And el driver cierra la nueva ventana
Then el foco vuelve automáticamente al handle de la ventana principal
And volvemos a la página principal
```
## Alerts
**Scenario 03: Alerta simple es aceptada sin mensaje de resultado**
```
Given que navego a "https://demoqa.com/alerts"
When disparo la alerta simple
And acepto la alerta
Then la alerta desaparece
And no se muestra mensaje de resultado en la página
```
**Scenario 04: Alerta prompt muestra el texto ingresado**
```
Given que navego a "https://demoqa.com/alerts"
When disparo la alerta de prompt
And ingreso un texto en la alerta
And acepto la alerta
Then el mensaje de resultado en la página contiene el texto ingresado
```
## Frames simples
**Scenario 05: Cambiar al iFrame por ID y volver al contenido principal**
```
Given que navego a "https://demoqa.com/frames"
When cambio el foco al iFrame identificado por ID o nombre
Then puedo obtener el texto de un elemento dentro del iFrame
And el foco regresa al contenido principal y es accesible
```
## Nested Frames
**Scenario 06: Navegación secuencial en iFrame padre e hijo y retorno al contenido principal**
```
Given que navego a "https://demoqa.com/nestedframes"
When cambio el foco al iFrame padre
And cambio el foco desde el padre al iFrame hijo
Then el texto de un elemento dentro del iFrame hijo es correcto
And regreso el foco al contenido principal
And un elemento fuera de los iFrames es accesible
```
## Modal Dialogs
**Scenario 07: Apertura de modal y validación de visibilidad y encabezado**
```
Given que navego a "https://demoqa.com/modal-dialogs"
When el usuario hace clic en el botón Small Modal para abrir la ventana modal
Then la ventana modal es visible
And el fondo de la página principal no es interactuable
And el texto del encabezado de la modal es el esperado
```
**Scenario 08: Cierre de modal y retorno de interactividad a la página principal**
```
Given que navego a "https://demoqa.com/modal-dialogs"
When el usuario hace clic en el botón de cierre de la modal
Then la ventana modal desaparece del DOM o es invisible
And la página principal vuelve a ser interactuable
```
---
## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio https://demoqa.com/
- **Quiero** manipular los elementos de la interfaz a través de arrastre, reordenamiento y redimensión, y registrar el éxito de cada interacción
- **Para** Asegurar que las funcionalidades dinámicas de la página responden correctamente a las acciones precisas del ratón (Actions)
---
## Historia de Usuario: Interacciones Avanzadas del Usuario
URL: https://demoqa.com/interaction

## Sortable
**Scenario 01: Reordenar lista vertical**
```
Given que navego a "https://demoqa.com/sortable" en modo lista
When arrastro "Item 4" a la posición de "Item 1"
Then "Item 4" queda como primer elemento de la lista
```
**Scenario 02: Reordenar grid**
```
Given que navego a "https://demoqa.com/sortable" en modo grid
When arrastro un elemento a una posición no adyacente (por ejemplo "Item 1" al lugar de "Item 9")
Then se actualiza el orden del grid y el elemento aparece en la nueva posición
```
## Selectable
**Scenario 03: Selección individual en lista**
```
Given que navego a "https://demoqa.com/selectable" en modo lista
When hago clic sobre "Item 3"
Then el elemento permanece seleccionado
```
**Scenario 04: Selección múltiple en grid**
```
Given que navego a "https://demoqa.com/selectable" en modo grid
When selecciono varios elementos de la cuadrícula usando selección múltiple
Then todos los elementos seleccionados permanecen seleccionados
```
## Resizable
**Scenario 05: Redimensionar al tamaño máximo**
```
Given que navego a "https://demoqa.com/resizable" 
When arrastro el control de redimensión hacia afuera con un gran offset
Then el cuadro aumenta de tamaño hasta la dimensión proporcionada
```
**Scenario 06: Redimensión limitada por límites**
```
Given que navego a "https://demoqa.com/resizable" con límites
When intento redimensionar el cuadro más allá de sus límites
Then el ancho y alto del cuadro no exceden los límites predefinidos por la aplicación
```
## Droppable
**Scenario 07: Arrastre básico**
```
Given que navego a "https://demoqa.com/droppable"
When arrastro el elemento "Draggable" al área "Droppable"
Then el texto del destino cambia a "Dropped!" 
And el elemento ya no está en su posición inicial
```
**Scenario 08: Arrastre con prevent propagations - Outer**
```
Given que navego a "https://demoqa.com/droppable"
And clico en la pestaña "Prevent Propogation"
When arrastro el elemento a las distintas áreas de destino
Then solo el área inmediata registra la caída sin afectar contenedores padre o hijo 
```
**Scenario 09: Arrastre con prevent propagations - Inner**
```
Given que navego a "https://demoqa.com/droppable"
And clico en la pestaña "Prevent Propogation"
When arrastro el elemento a las distintas áreas de destino
Then solo el área inmediata registra la caída sin afectando a contenedores padre o hijo 
```
## Dragabble
**Scenario 10: Arrastre libre**
```
Given que navego a "https://demoqa.com/dragabble"
When arrastro el elemento por el contenedor a una posición distinta
Then la posición del elemento cambia y se puede mover libremente
```
**Scenario 11: Arrastre restringido por eje**
```
Given que navego a "https://demoqa.com/dragabble" 
And clico en la pestaña "Axis Restricted"
When arrastro el elemento restringido en X intentando moverlo también en Y
Then solo cambia la coordenada X y la coordenada Y permanece igual
```
---
## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio https://demoqa.com/ con credenciales preexistentes (creadas vía API)
- **Quiero** iniciar sesión usando credenciales enviadas por consola, buscar un libro en el catálogo, interactuar con él y acceder a mi perfil
- **Para** asegurar la funcionalidad completa de autenticación, búsqueda y navegación en el módulo Book Store
---
## Historia de Usuario: Flujo Completo de Book Store
URL: https://demoqa.com/books

**Scenario 12: Login exitoso con credenciales creadas por API**
```
Given que navego a la página de Login de Book Store "https://demoqa.com/login" 
When inicio sesión con el username y password recibidos con username y password creados por API previamente
Then veo mi username en la cabecera del perfil
And la URL contiene "/profile"
```
**Scenario 13: Búsqueda de libros en el catálogo**
```
Given que ya estoy autenticado en Book Store
And navego a la página de libros
When busco un libro usando el término "JavaScript"
Then la tabla muestra solo libros que coinciden con el término de búsqueda
```
**Scenario 14: Navegación al detalle de un libro**
```
Given que ya estoy autenticado en Book Store
And navego a la página de libros
And realizo una búsqueda válida de libros
When hago clic en el título del primer libro listado
Then navego a la página de detalle del libro
And el título del detalle coincide con el título clicado
And el autor del libro se muestra en la página de detalle
```
### NOTA: A partir del step "Then navego a la página de detalle del libro":
- **No podemos automatizar** porque la funcionalidad de la propia web de demoQA está rota.
- Al hacer clic en el título de un libro en /books, la aplicación DemoQA navega a una URL del tipo /books?book=9781449325862, pero la página de detalle se queda en blanco.
- No se renderizan los elementos esperados (Title :, Author :, etc.).
- Esto impide validar título y autor del libro.
- **Conclusión:** el fallo se debe al entorno de DemoQA (bug de la aplicación), no al test automatizado.
- Evidencias:
  ![Evidencia_1](src/test/resources/upload/Captura%20de%20pantalla%202025-12-09%20145130.png)
  ![Evidencia_2](src/test/resources/upload/Captura%20de%20pantalla%202025-12-09%20145200.png)

**Scenario 15: Acceso al perfil y cierre de sesión**
```
Given que ya estoy autenticado en Book Store
Then la tabla de libros del perfil es visible 
When hago clic en el botón "Log out"
Then soy redirigido a la página de Login
And el botón "Login" se muestra de nuevo
```