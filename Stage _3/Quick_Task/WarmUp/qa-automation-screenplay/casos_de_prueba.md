## Criterios de Aceptación

## Interacción Básica y Campos Requeridos

- Se ingresan valores válidos en los campos **First Name** y **Last Name**.
- Se ingresa un correo electrónico válido en **Email**.
- Se selecciona una opción válida de **Gender** (Male / Female / Other).
- Se ingresa un número válido de **10 dígitos** en **Mobile**.

---
## Feature: Registro de usuario

## 🎭 Scenario: Usuario se registra con éxito

### 🧾 Escenario (Gherkin)

```gherkin
Feature: Registro de usuario

  Scenario: Usuario se registra con éxito
    Given El usuario está en la página de registro
    When El usuario ingresa datos válidos
    And Hace clic en el botón de registro
    Then El usuario debería ver un mensaje de confirmación
```
---
## 🎭 Mapeo del escenario usando Screenplay (Pseudocódigo)

### Actor

- **Actor:** Usuario

---

### Habilidades (Abilities)

- Navegar por la aplicación web
- Interactuar con formularios web

---

### Given: El usuario está en la página de registro

- **Task:** Abrir página de registro
    - **Interactions:**
        - Navegar a la URL de registro
        - Verificar que la página de registro está visible

---

### When: El usuario ingresa datos válidos

- **Task:** Completar formulario de registro con datos válidos
    - **Interactions:**
        - Ingresar First Name
        - Ingresar Last Name
        - Ingresar Email válido
        - Seleccionar Gender
        - Ingresar Mobile de 10 dígitos

---

### And: Hace clic en el botón de registro

- **Task:** Enviar registro
    - **Interactions:**
        - Hacer clic en el botón de registro

---

### Then: El usuario debería ver un mensaje de confirmación

- **Question:**
    - ¿Se muestra el mensaje de confirmación en pantalla?

---

## ♻️ ¿Qué Task podrías reutilizar si mañana hay un formulario de contacto?

### Task reutilizable
**Completar formulario de registro con datos válidos**

### ¿Por qué?

- No está acoplada a un formulario específico.

**Solo variarían:**
- Los elementos de la UI
- Algunas Interacciones internas