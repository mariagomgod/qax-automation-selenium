# Mission 2: Automatizando un E2E con comportamientos avanzados

Esta misión ha sido diseñada para elevar su nivel como automatizador a una nueva categoría. Dejaremos atrás las interacciones básicas para enfocarnos en la simulación de flujos de usuario completos y complejos, teniendo en cuenta los comportamientos avanzados y los desafíos reales del navegador, como la gestión de ventanas, iframes y las acciones precisas del ratón.

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
- TestNG
- JavaFaker (para generar datos variables)
- Conexión a Internet

---
## 📂 Instrucciones

- Crear proyecto Maven. Incluir dependencias para Selenium, TestNG, WebDriverManager y JavaFaker.

- Implementar setup/tearDown.

- Implementar todos los métodos de interacción avanzada de forma genérica (recibiendo localizadores):

- Crear clases Page Object (PO) por funcionalidad  que hereden de BasePage.

- Clases de Prueba: Crear una clase de prueba por cada HU.

- Cada Criterio de Aceptación (AC) se convierte en un método @Test.

- Utilizar JavaFaker dentro de la capa de pruebas para generar datos variables, garantizando que el código sea reproducible y escalable.

- Usar aserciones explícitas de TestNG para confirmar cada caso de prueba

- Documentar el comando para ejecutar la suite completa enviando variables por consola

- Ejecutar las pruebas de forma separada y conjunta para validar la estabilidad de la automatización.

- Crear un README.md con las instrucciones de ejecución. Si se encuentra un error, reportar la excepción de Selenium y el contexto del fallo.

---

**Base url: https://demoqa.com/**

## Historia de Usuario: Módulo de Elementos
Como un usuario de prueba,
Quiero Interactuar y validar correctamente cada subsección del módulo "Elements" (TextBox, CheckBox, RadioButton, WebTables, Buttons, Links, Broken Links, Upload/Download, Dynamic Properties),
Para Asegurar que todos los elementos de entrada, selección, formularios, navegación y acciones dinámicas del sitio funcionan según lo esperado

### Criterios de Aceptación

1. `TextBox` (Formulario de Entrada)
   Interacción y Envío:
- Se ingresa texto en los campos Full Name, Email, Current Address y Permanent Address.
- Se hace clic en el botón 'Submit'.
- Se valida que la información ingresada se muestra correctamente en el área de salida debajo del formulario.

2. `CheckBox` (Selección Jerárquica)
-  Selección de Nivel Superior:
    - Se hace clic en el CheckBox 'Home'.
    - Se valida que todos los subelementos (Desktop, Documents, Downloads, etc.) se seleccionan automáticamente.
- Selección Parcial:
    - Se desmarca 'Home'.
    - Se seleccionan individualmente 'Desktop' y 'Downloads'.
    - Se valida que la casilla 'Home' muestra el estado de selección parcial (guion o cuadro gris).

3. `Radio Button `(Selección Exclusiva)
- Selección y Validación de Mensajes:
    - Se hace clic en la opción 'Yes'. Se valida que aparece el mensaje: "You have selected Yes".
    - Se hace clic en la opción 'Impressive'. Se valida que aparece el mensaje: "You have selected Impressive".
    - Se verifica que la opción 'No' está deshabilitada y no se puede seleccionar.

4. `Web Tables` (Formulario de Registro y Edición)
- Añadir Nuevo Registro:
    - Se hace clic en el botón 'Add'.
    - Se rellena el formulario modal (First Name, Last Name, Email, Age, Salary, Department).
    - Se envía el formulario y se valida que el nuevo registro aparece como la última fila de la tabla.
- Edición y Eliminación:
    - Se edita un registro existente (cambiando el Email o Salary). Se valida que el cambio se refleja en la tabla.
    - Se elimina el registro creado en el paso 1. Se valida que la fila desaparece de la tabla.

5. `Buttons` (Acciones de Clic)
- Validación de los Tres Tipos de Clics:
    - Se ejecuta un **Doble Clic** en el botón correspondiente. Se valida que el mensaje "You have done a double click" aparece.
    - Se ejecuta un **Clic Derecho** en el botón correspondiente. Se valida que el mensaje "You have done a right click" aparece.
    - Se ejecuta un **Clic Simple** en el botón restante. Se valida que el mensaje "You have done a dynamic click" aparece.

6. `Links` (Navegación y Llamadas API)
- Validación de Links que Abren Nueva Pestaña:
    - Se hace clic en el enlace 'Home' (el primer enlace).
    - Se utiliza la gestión de ventanas  para verificar que la nueva pestaña se abre correctamente y que su URL es la esperada.
    - Se regresa el foco a la pestaña original.
- Validación de Links de Llamada API (Broken Links Avanzado):
    - Se hace clic en cada uno de los enlaces que simulan llamadas API (Created, No Content, Moved, etc.).
    - Aunque la prueba visual no muestra una acción, se valida que el clic se realiza sin errores de navegación o *timeout*.

7. `Broken Links` - Images (Validación de Recursos)
- Validación de Imágenes y Enlaces:
    - Se verifica que la **"Valid image"** se carga correctamente (se comprueba su altura/anchura o se usa Javascript para verificar el estado de carga).
    - Se verifica que la **"Broken image"** no se carga o tiene un error (se comprueba la URL o el estado).
    - Se hace clic en el **"Valid Link"** y se verifica que navega a una página válida.
    - Se hace clic en el **"Broken Link"** y se verifica que devuelve un error de HTTP 404/500 (o un mensaje de error).

8. Upload and Download (Carga y Descarga de Archivos)
- Funcionalidad de Carga:
    - Se utiliza el método `sendKeys()` en el campo de carga (`input type="file"`) para enviar la ruta absoluta de un archivo de prueba.
    - Se valida que el mensaje de éxito de la carga aparece con el nombre del archivo.
- Funcionalidad de Descarga:
    - Se hace clic en el botón 'Download'.
    - Se verifica que el archivo se ha descargado exitosamente al directorio predeterminado configurado en el *driver*.

9. `Dynamic Properties` (Elementos Dinámicos)
- Validación de Propiedades Dinámicas:
    - Se localiza el botón 'Enable After 5 Seconds' y se usa una Espera Explícita para validar que se vuelve *enabled* (habilitado) después del tiempo de espera.
    - Se localiza el botón 'Color Change' y se usa una Espera Explícita para validar que su color cambia (verificando el atributo CSS `color` o `class`).
    - Se localiza el botón 'Visible After 5 Seconds' y se usa una Espera Explícita para validar que se vuelve visible.

---

## Historia de Usuario: Formulario de Registro de Estudiantes
**Como** Un usuario de prueba,
**Quiero** Rellenar y enviar todos los campos obligatorios del formulario de registro de estudiantes, incluyendo entradas de texto, selección de género, fecha, checkboxes, carga de archivos y dropdowns dependientes.
**Para** Asegurar que la aplicación procesa y muestra correctamente la información compleja en la tabla de confirmación final.

### Criterios de Aceptación

1. Interacción Básica y Campos Requeridos
- Campos de Nombre y Contacto:
    - Se ingresan valores válidos en los campos **First Name** y **Last Name**.
    - Se ingresa un correo electrónico válido en **Email**.
    - Se selecciona una de las opciones de **Gender** (Male/Female/Other).
    - Se ingresa un número de 10 dígitos válido en **Mobile**.

2. Selección de Fecha de Nacimiento:
    - Se utiliza el Datepicker para seleccionar una fecha de nacimiento, navegando entre meses y años (ej. seleccionar una fecha del año 1990).
    - Se valida que el campo muestra la fecha seleccionada en el formato esperado.
2. Campos de Entrada Compleja (Subjects y Address)
- Campo Subjects (Autocompletar/Múltiple):
    - Se ingresa texto en el campo **Subjects** y se selecciona al menos dos opciones de la lista de autocompletar.
    - Se valida que ambas opciones permanecen seleccionadas dentro del campo.

2. Hobbies y Carga de Archivo:
    - Se seleccionan al menos dos **Hobbies** (Sports, Reading, Music).
    - Se utiliza el campo **Picture** (`Seleccionar archivo`) para cargar un archivo de imagen de prueba (requiere `sendKeys(ruta_absoluta)`).

3. Dirección Actual:
    - Se ingresa texto descriptivo en el campo **Current Address**.
3. Dropdowns Dependientes y Envío
- Selección de Estado y Ciudad (Dropdowns Dependientes):
    - Se selecciona una opción válida en el dropdown **State**.
    - Se espera explícitamente a que el dropdown **City** se cargue con las opciones dependientes del Estado seleccionado.
    - Se selecciona una opción válida en el dropdown **City**.

2. Envío y Validación del Formulario:
    - Se hace clic en el botón de **Submit**.
    - Se verifica que, en caso de éxito, aparece una **Tabla de Confirmación (Modal)** con el resumen de los datos.
    - Se valida que la tabla de confirmación muestre correctamente **todos los datos ingresados**, incluyendo el nombre completo, género, fecha de nacimiento, *subjects*, y la dirección combinada.
---
## Historia de Usuario: Gestión de Contextos de Ejecución (Alerts, Frames & Windows)
**Como** Un usuario de prueba avanzado,
**Quiero** Poder iniciar, interactuar y cerrar correctamente todos los elementos que cambian el foco del driver (ventanas, pestañas, alertas, iFrames y modales),
**Para** Asegurar que el sistema maneja la transición de contexto y que la información se intercambia o persiste correctamente en la página principal.

### Criterios de Aceptación
1. Browser Windows (Ventanas y Pestañas)
-  Abrir Nueva Pestaña:
- Se hace clic en el botón para abrir una nueva pestaña.
- La prueba debe almacenar el Handle Principal, cambiar el foco al nuevo Handle, validar el contenido de la nueva pestaña y regresar el foco al Handle Principal.
2. Abrir Nueva Ventana:
    - Se hace clic en el botón para abrir una nueva ventana separada.
    - La prueba debe cambiar el foco, cerrar la nueva ventana y validar que el foco ha regresado automáticamente al Handle Principal.
2. Alerts (Alertas del Navegador)
- Alerta Simple (Aceptar):
    - Se dispara la alerta simple y se ejecuta aceptar
    - Se valida que la alerta desaparece sin dejar mensajes de resultado.
2. Alerta de Prompt (Ingreso de Datos):
    - Se dispara la alerta de prompt y se ingresa una cadena de texto dinamico
    - Se acepta la alerta y se valida que el texto ingresado aparece correctamente en el mensaje de resultado de la página.
3. Frames (iFrames Simples)
- Cambio de Foco por ID/Nombre:
    - Cambiar el foco al iFrame.
    - Se valida la capacidad de interactuar con un elemento dentro del iFrame (ej. obtener su texto).
    - Regresar el foco a la página principal.
4. Nested Frames (iFrames Anidados)
- Navegación Anidada:
    - El driver debe cambiar el foco al **iFrame Padre**.
    - Desde el Padre, el driver debe cambiar el foco al **iFrame Hijo**.
    - Se valida que un elemento en el iFrame Hijo es accesible y su texto es correcto.
- Retorno Secuencial:
    - El driver debe regresar el foco al contenido principal  desde el iFrame Hijo.
    - Se valida que un elemento fuera de los iFrames es accesible.
5. Modal Dialogs (Ventanas Modales)
- Apertura y Validación:
    - Se hace clic en el botón para abrir una ventana modal.
    - Se valida que el elemento modal es visible y que el fondo de la página principal está inactivo o difuminado.
    - Se verifica que el texto del encabezado de la modal es el esperado.
- Cierre y Desaparición:
    - Se hace clic en el botón de cierre de la modal ('Close' o la 'x').
    - Se verifica que el elemento modal ha desaparecido del DOM o es invisible, y la página principal es interactuable.

---
## Historia de Usuario: Interacciones Avanzadas del Usuario

**Como**: Un usuario de prueba,  
**Quiero**: Manipular los elementos de la interfaz a través de arrastre, reordenamiento y redimensión, y registrar el éxito de cada interacción,  
**Para**: Asegurar que las funcionalidades dinámicas de la página responden correctamente a las acciones precisas del ratón (Actions).

### Criterios de Aceptación

1. Sortable (Reordenamiento de Lista)

- Reordenamiento Vertical:
    - Se localizan dos elementos de la lista vertical (ej. Item 1 y Item 4).- Se localizan dos elementos de la lista vertical (ej. Item 1 y Item 4).
    - Se utiliza la clase `Actions` para arrastrar el **Item 4** a la posición del **Item 1** y soltarlo.
    - Se verifica que la lista ha reordenado los elementos y que Item 4 ahora aparece como el primer elemento.
- Reordenamiento Horizontal (Grid):
    - Se navega al modo 'Grid'.
    - Se arrastra un elemento a una posición no adyacente (ej. mover Item 1 al lugar de Item 9).
    - Se valida que el elemento ha cambiado de posición dentro de la cuadrícula.
2. Selectable (Selección de Elementos)
- Selección Individual (Lista):
    - Se hace clic en un elemento de la lista (ej. Item 3).
    - Se valida que el elemento adquiere la clase CSS de 'seleccionado' o cambia su color de fondo.
- Selección Múltiple (Grid):
    - Se navega al modo 'Grid'.
    - Se utilizan las `Actions` (o `Keys.CONTROL` / `Keys.SHIFT` si es necesario) para seleccionar múltiples elementos de la cuadrícula a la vez.
    - Se valida que todos los elementos clicados permanecen en estado de 'seleccionado'.
3. Resizable (Redimensión de Cuadro).
- Redimensión al Tamaño Máximo:
    - Se localiza el control de redimensión (la esquina inferior derecha del cuadro).
    - Se utiliza la clase `Actions` y el método `dragAndDropBy(control, X_offset, Y_offset)` con offsets grandes para maximizar el cuadro.
    - Se verifica que las dimensiones del cuadro contenedor (ancho y alto) han aumentado según lo permitido por el *viewport*.
- Redimensión Limitada:
    - Se navega a la opción de cuadro con límites de redimensión.
    - Se intenta redimensionar el cuadro más allá de sus límites.
    - Se verifica que el ancho y el alto del cuadro no exceden los límites predefinidos por la aplicación.
4. Droppable (Arrastrar y Soltar).
- Arrastre Básico:
    - Se localizan los elementos **Draggable** (origen) y **Droppable** (destino).
    - Se usa `actions.dragAndDrop(source, target)`.
    - Se valida que el texto del área de destino cambia de "Drop here" a "Dropped!" (o similar) y que el elemento origen ya no está en su posición inicial.
- Arrastre Restrictivo (Prevent Propogation):
    - Se navega a la subsección 'Prevent Propogation'.
    - Se arrastra el elemento Draggable a las áreas de destino.
    - Se valida que solo el área de destino inmediata registra la caída, sin afectar a los contenedores padre o hijo, demostrando el aislamiento de la acción.
5. Dragabble (Arrastre Libre y Restringido)
- Arrastre Libre:
    - Se localiza el elemento Draggable simple.
    - Se usa `actions.dragAndDropBy(source, X_offset, Y_offset)` para moverlo a una posición aleatoria.
    - Se valida que la posición del elemento ha cambiado y que se puede arrastrar libremente por el contenedor.
- Arrastre Restringido (Axis):
    - Se navega a la subsección 'Axis Restricted'.
    - Se arrastra el elemento restringido a **solo X** (horizontalmente) y se intenta mover verticalmente.
    - Se verifica que solo la coordenada X ha cambiado, y que la coordenada Y permanece inalterada, confirmando la restricción del eje.

---

## Historia de Usuario: Flujo Completo de Book Store
**Como** Un usuario de prueba con credenciales preexistentes (creadas vía API)
**Quiero** Iniciar sesión usando credenciales enviadas por consola, buscar un libro en el catálogo, interactuar con él y acceder a mi perfil,
**Para** Asegurar la funcionalidad completa de autenticación, búsqueda y navegación en el módulo Book Store.

### Criterios de Aceptación
1. Login y Gestión de Parámetros (Consola)
- Recepción de Credenciales:
    - El test debe recibir el **Username** y **Password** como parámetros de ejecución
2. Inicio de Sesión Exitoso:
    - Se navega a la página de Login.
    - Se ingresan las credenciales recibidas en los campos de usuario y contraseña.
    - Se hace clic en el botón 'Login' y se verifica que el encabezado de la página muestra el **Username** del usuario.
    - Se valida que la URL cambia al perfil (`/profile`).
3. Interacción con el Catálogo de Libros
- Funcionalidad de Búsqueda:
    - Estando logueado, se ingresa un término de búsqueda válido (ej. "JavaScript") en el campo de búsqueda.
    - Se verifica que la tabla de libros se actualiza y solo muestra los resultados que coinciden con el término de búsqueda.
- Navegación a la Página del Libro:
    - Se hace clic en el título de un libro listado.
    - Se verifica que la página navega al detalle del libro, validando el título, autor y otros detalles.
4. Flujo de Perfil y Cierre
- Acceso al Perfil:
    - Se hace clic en el elemento de navegación 'Profile' o se navega directamente a la URL de perfil.
    - Se verifica que la tabla de libros del perfil está visible (inicialmente vacía).
-  Cerrar Sesión (Logout):
    - Se hace clic en el botón 'Logout'.
    - Se verifica que la URL regresa a la página de inicio o a la página de Login y que el botón de 'Login' es visible nuevamente.
---
## Generación de Reporte: 
- Ejecutar toda la suite de tests utilizando el comando Maven (Chrome):
    ```bash
    mvn clean test
    ```
- Ejecutar toda la suite de tests utilizando el comando Maven (Firefox):
    ```bash
    mvn clean test -Dbrowser=firefox
    ```
- Ejecutar toda la suite de test en modo headless (Chrome):
    ```bash
    mvn clean test -Dheadless=true
    ```
- Ejecutar toda la suite de test en modo headless (Firefox):
    ```bash
    mvn clean test -Dbrowser=firefox -Dheadless=true
    ```
* **Comandos de ejecución para tests, browsers y Urls concretos:** 

* ElementsModuleTest: Con un navegador chrome y la url por consola
    ```bash
    mvn clean test -Dtest=ElementsModuleTest -Dbrowser=chrome -DbaseURL=https://demoqa.com/
    ```
* InteractionsTest: Con valores por defecto
    ```bash
    mvn clean test -Dtest=InteractionsTest
    ```
* AlertsFramesWindowsTest: Con un navegador firefox y la url por defecto
    ```bash
    mvn clean test -Dtest=AlertsFramesWindowsTest -Dbrowser=firefox
    ```
* ElementsModuleTest: La url por consola
    ```bash
    mvn clean test -Dtest=ElementsModuleTest -DbaseURL=https://demoqa.com/
    ```
* InteractionsTest: Con valores por defecto
    ```bash
    mvn clean test -Dtest=InteractionsTest
    ``` 