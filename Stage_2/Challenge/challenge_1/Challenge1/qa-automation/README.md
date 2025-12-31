## Challenge 01: Completar la automatización

En este reto vas a ponerte en modo QA Automation real.
La idea es practicar lo que pasa en un proyecto de verdad: leer el código de otro, mejorar los locators, refactorizar donde haga falta, agregar asserts que tengan sentido de negocio y sumar nuevos tests sin romper lo que ya funciona.

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
---

## Historia de Usuario – Registro de Nuevo Usuario

**Como** usuario que desea crear una cuenta,
**quiero** ingresar primero mi correo electrónico
**para** acceder al formulario de registro completo, para poder completar mis datos personales y finalizar mi registro en la plataforma sin confusiones y de manera guiada.

---

### Criterios de Aceptación (CAs)

###  1. Pantalla Inicial: Ingreso de Email

| Componente | Acción/Validación | Criterio Específico |
| :--- | :--- | :--- |
| **Campo 'Email'** | Ingreso de dirección. | Solo formatos válidos. Muestra error si es inválido. |
| **Botón 'Continuar'** | Habilitación y Navegación. | Se habilita SÓLO con email válido. Al hacer clic, navega al formulario completo. |

###  2. Formulario de Registro Completo

#### Campos Requeridos (Listado)
* First Name, Last Name, Address
* Email address (precargado y no editable)
* Phone
* Gender, Hobbies, Languages, Skills, Country, Select Country (Custom Dropdown)
* Date of Birth (Year, Month, Day)
* Password, Confirm Password

####  Criterios por Campo

| Campo | Restricciones/Requisitos | Obligatorio |
| :--- | :--- | :--- |
| **First/Last Name** | Solo texto. No acepta números. | Sí |
| **Address** | Permite texto multilínea. | Sí |
| **Email address** | Precargado, no editable. Mantiene formato válido. | Sí |
| **Phone** | Solo números. Longitud mínima (ej: 8 dígitos). | Sí |
| **Gender** | Selección única. Muestra aviso si está vacío al hacer Submit. | Sí |
| **Hobbies** | Selección múltiple (una o varias opciones). | No |
| **Languages** | Selección múltiple desde dropdown. Muestra seleccionados como etiquetas. | No |
| **Skills** | Dropdown de selección única. Valor por defecto: "Select Skills". | Sí |
| **Country** | Dropdown de selección única. | Sí |
| **Select Country (Custom)** | Permite buscar y seleccionar desde lista custom. | Sí |
| **Date of Birth** | Selección obligatoria de Año, Mes, y Día. | Sí |
| **Password / Confirm Pwd** | Ambos obligatorios. **Deben coincidir** (mostrar error si no coinciden). | Sí |

### Botón Submit

| Condición | Resultado |
| :--- | :--- |
| **Habilitación** | El botón solo debe estar habilitado si **todos los campos obligatorios** están completos. |
| **Validación** | Al hacer clic, se valida el formulario completo. |
| **Errores** | Si hay fallas, se muestran **mensajes de error claros** bajo cada campo. |
| **Éxito** | Si todo es correcto, el registro se completa y se muestra una **confirmación exitosa**. |

---
## Instrucciones

### Análisis y Diseño de Casos de Prueba

* **Revisión de la Historia de Usuario (HU):** Analiza a fondo los **Criterios de Aceptación (CAs)** para entender el comportamiento esperado del formulario.
* **Diseño de Casos de Prueba:** Crea y documenta un conjunto de pruebas que cubra los requisitos. Debes incluir al menos:
    * **1 *Happy Path* (Ruta Exitosa):** Flujo completo, llenando campos obligatorios y validando la confirmación de registro.
    * **3 Escenarios Negativos:** Cubrir fallos críticos como **campos obligatorios vacíos**, **formatos incorrectos** (letras en teléfono) y **mismatch de Password**.
* **Formato:** Escribir los casos de prueba de forma **Gherkin** en archivo `.md`

### Automatización de los Casos de Prueba

Utilizar el proyecto base del `exercise_1` para codificar los casos de prueba diseñados.

* **Reutilización:** Evitar duplicar código. Extraer secuencias de pasos repetidas **métodos reutilizables** en tu Page Object.

### Validación y Reporte de Bugs

* **Ejecución y Verificación:** Correr todas las pruebas. Asegurarse de que los *asserts* (validaciones) sean **correctos y significativos**.
* **Reporte de Bugs:** Si una prueba falla por un **comportamiento real incorrecto** del sitio (y no por tu código), documenta el error.

### Entrega y Repositorio

* **Generación de Reporte:** Ejecutar las pruebas utilizando el comando Maven:
    ```bash
    mvn clean test
    ```