# Casos de Prueba en Gherkin – Registro de Nuevo Usuario

## Feature: Interacción con componentes de demo.automationtesting.in
- **Como** un usuario que interactúa con el sitio demo.automationtesting.in
- **Quiero** validar el comportamiento de múltiples componentes de UI
- **Para** asegurar que las interacciones se gestionan correctamente y la interfaz responde como se espera
---
## Historia de Usuario: Gestión de Alertas
URL: https://demo.automationtesting.in/Alerts.html
---
**Scenario 01: Alerta de confirmación - aceptar (OK)**
```
Given que navego a "https://demo.automationtesting.in/Alerts.html"
When hago clic en el botón que dispara la confirm alert
And acepto la alerta
Then debería ver el mensaje "I am an alert box!"
```
**Scenario 02: Alerta de confirmación - cancelar**
```
Given que navego a "https://demo.automationtesting.in/Alerts.html"
When hago clic en el botón que dispara la confirm alert
And cancelo la alerta
Then debería ver el mensaje "You Pressed Cancel"
```
**Scenario 03: Alerta de prompt - ingresar texto dinámico**
```
Given que navego a "https://demo.automationtesting.in/Alerts.html"
When hago clic en el botón que dispara la alerta prompt
And ingreso el texto "Text de prueba" en el prompt
And acepto el prompt
Then debería ver el mensaje "Texto de prueba How are you today"
```
---
## Historia de Usuario: Drag and Drop
URL Estático: https://demo.automationtesting.in/Static.html
---
URL Dinámico: https://demo.automationtesting.in/Dynamic.html
---
**Scenario 01: Drag and Drop estático - elemento se suelta correctamente**
```
Given que navego a "https://demo.automationtesting.in/Static.html"
And localizo el elemento arrastrable 
And localizo el área de destino
When arrastro el elemento y lo suelto sobre el área de destino
Then veo que el elemento está dentro del área de destino
```
**Scenario 02: Drag and Drop dinámico - elemento se mueve al área de destino**
```
Given que navego a "https://demo.automationtesting.in/Dynamic.html"
And localizo el elemento arrastrable
And localizo el área de destino
When arrastro el elemento y lo suelto sobre el área de destino
Then veo que el elemento está dentro del área de destino
```
---
## Historia de Usuario: Navegación de Submenús con Mouse Hover
URL: https://demo.automationtesting.in/WebTable.html
---
**Scenario 01: Navegar por todas las opciones del submenú y validar su contenido**
```
Given que navego a "https://demo.automationtesting.in/WebTable.html"
And localizo el elemento del menú principal que activa el hover
When paso el cursor (mouse hover) sobre el menú principal
Then el submenú asociado se vuelve visible y accesible

When itero por cada opción disponible del submenú
Then para cada opción de submenú:
  """
  - Hago clic en la opción de submenú
  - Se abre la página destino correspondiente
  - El título de la página destino se muestra correctamente
  - Al menos un elemento clave de la página destino está visible
  - Vuelvo a la página principal del menú para continuar con el siguiente submenú
  """
```
**NOTA:** No se puede llegar a comprobar dicho escenario por causas ajenas al código. Es debido a que está llamando a 
endpoints para rellenar la tabla en un host que no existe.
Captura de pantalla con la evidencia:
![Captura de pantalla 2025-12-01 182049.png](src/test/resources/upload/Captura%20de%20pantalla%202025-12-01%20182049.png)
---
## Historia de Usuario: iFrames Anidados
URL Base: https://demo.automationtesting.in/Frames.html
---
**Scenario 01: Cambio de foco a iFrame padre e iFrame hijo e interacción con contenido anidado**
```
Given que navego a "https://demo.automationtesting.in/Frames.html"
When cambio el foco al iFrame padre
And dentro del iFrame padre cambio el foco al iFrame hijo
And ingreso el texto "Texto Anidado" en un campo de texto dentro del iFrame hijo
Then el campo de texto dentro del iFrame hijo debe contener el valor "Texto Anidado"

When regreso desde el iFrame hijo al iFrame padre
And escribo el texto "Texto Padre" en un campo de texto dentro del iFrame padre
Then un elemento fuera de los iFrames, por ejemplo un encabezado, debe ser visible o clickeable
And se confirma que el foco ha vuelto al contexto principal
```
---
## Historia de Usuario: Ventanas y Pestañas (Windows)
URL: https://demo.automationtesting.in/Windows.html
---
**Scenario 01: Apertura de nueva pestaña, validación y regreso**
```
Given que navego a "https://demo.automationtesting.in/Windows.html"
And guardo el handle de la ventana principal
When hago clic en el enlace o botón que abre una nueva pestaña o ventana
And cambio el foco al nuevo handle de ventana
Then el título de la nueva pestaña es "Selenium" o el título esperado

When cambio el foco de vuelta
Then regreso al handle de la ventana principal
And la ventana principal sigue activa y operativa
```
**Scenario 02: Apertura y cierre de ventana separada**
```
Given que navego a "https://demo.automationtesting.in/Windows.html"
And guardo el handle de la ventana principal
When hago clic en el botón que abre una nueva ventana separada
And cambio el foco al handle de la ventana secundaria
And realizo una o más validaciones en la ventana secundaria (por ejemplo, título o contenido)
And cierro la ventana secundaria
Then la ventana secundaria está cerrada
And el foco retorna automáticamente al handle principal activo
```
---
## Historia adicional: Accordion
URL: https://demo.automationtesting.in/Accordion.html
---
**Scenario 01: Expandir y contraer paneles del accordion**
```
Given que navego a "https://demo.automationtesting.in/Accordion.html"
And los headers de cada sección del accordion están visibles
When hago clic en el header del primer panel
Then el panel asociado se expande y muestra el contenido esperado
And los otros paneles permanecen en su estado previo (plegados si así se espera)

When hago clic en el header del segundo panel
Then el segundo panel se expande y muestra el contenido correcto
And el resto de paneles permanece en el estado correcto según el comportamiento del componente
And repito la acción para cada header del accordion validando que:
"""
- El panel clicado se expande
- El contenido se corresponde con el header seleccionado
- Los otros paneles mantienen su estado esperado (expandido o colapsado)
"""
```
---
## Historia adicional: Datepicker
URL: https://demo.automationtesting.in/Datepicker.html
---
**Scenario 01: Selección de fecha navegando entre meses y años**
```
Given que navego a "https://demo.automationtesting.in/Datepicker.html"
And el componente Datepicker está visible
When hago clic en el input de fecha para abrir el calendario
And navego entre meses y años hasta la fecha objetivo
And selecciono la fecha específica
Then el valor del input de fecha refleja correctamente la fecha seleccionada
And el formato del valor es el esperado por la aplicación
```
---
## Historia adicional: Modals
URL: https://demo.automationtesting.in/Modals.html
---
**Scenario 01: Abrir y cerrar el primer modal bloqueando el fondo**
```
Given que navego a "https://demo.automationtesting.in/Modals.html"
When hago clic en el botón para abrir el modal principal
Then el modal es visible
And el contenido de fondo queda bloqueado
When cierro el modal usando el botón de cerrar dentro del contenido del modal
Then el modal deja de ser visible
And el contenido de fondo vuelve a estar disponible
```
**Scenario 02: Interactuar con el contenido del primer modal y confirmar la acción**
```
Given que navego a "https://demo.automationtesting.in/Modals.html"
When hago clic en el botón para abrir el modal principal
Then el modal es visible     
And el botón "Save changes" es visible          

When hago clic en el botón de Save Changes del modal
Then el modal deja de ser visible
And el contenido de fondo vuelve a estar disponible
```
**Scenario 03: Cancelar o cerrar modales múltiples**
```
Given que navego a "https://demo.automationtesting.in/Modals.html"
When hago clic en el botón para abrir el modal principal múltiple
Then el modal es visible

When hago clic en el botón de Launch modal dentro del primer modal
Then aparece un segundo modal
And el segundo modal es visible

When hago click en el botón Close dentro del segundo modal 
Then el segundo modal deja de ser visible
And el primer modal es visible

When hago click en el botón Close del primer modal
Then el primer modal deja de ser visible
And el contenido de fondo vuelve a estar disponible
```
---
## Historia adicional: Progress Bar
URL: https://demo.automationtesting.in/ProgressBar.html
---
**Scenario 01: Barra de progreso avanza hasta completarse**
```
Given que navego a "https://demo.automationtesting.in/ProgressBar.html"
And la barra de progreso está visible pero no iniciada
When inicio la barra de progreso mediante el control correspondiente
Then la barra de progreso llega al 100%
And el estado final esperado se muestra 
```
---
## Historia adicional: Dynamic Data
URL: https://demo.automationtesting.in/DynamicData.html
---
**Scenario 01: Validación de contenido dinámico actualizado**
```
Given que navego a "https://demo.automationtesting.in/DynamicData.html"
And el área de contenido dinámico es visible
And guardo el valor inicial del contenido dinámico (aunque esté vacío)
When clico el botón "Get dynamic data"
Then el contenido dinámico cambia con respecto al valor inicial
And el nuevo contenido muestra una imagen
And el nuevo contenido incluye nombre y apellido
```
---
## Historia adicional: Slider
URL: https://demo.automationtesting.in/Slider.html
---
**Scenario 01: Mover el slider a posiciones específicas**
```
Given que navego a "https://demo.automationtesting.in/Slider.html"
And el slider está visible
And guardo el valor inicial asociado al slider
When arrastro el slider handler hasta una posición específica
Then el valor visible o atributo asociado al slider cambia con respecto al valor inicial
```
---
## Historia adicional: Loader
URL: https://demo.automationtesting.in/Loader.html
---
**Scenario 01: Loader indica el estado de carga y desaparece al terminar**
```
Given que navego a "https://demo.automationtesting.in/Loader.html"
When inicio la carga haciendo clic en "Run"
Then se muestra un loader indicando que hay una operación en progreso
And al completarse la carga el loader desaparece y la página vuelve a ser interactuable
```