# Challenge 1 – Automation Web (Serenity BDD + Screenplay) 
Proyecto base de automatización web usando **Java**, **Serenity BDD** y el **patrón Screenplay**.  

## 🎯 Objetivo
- Aumentar la cobertura de pruebas automatizadas partiendo del proyecto actual, aplicando correctamente el patrón Screenplay, buenas prácticas de diseño y una mentalidad orientada al valor.
---

## ⚙ Tecnologías usadas

- Java 11
- Maven
- Serenity BDD
- Screenplay Pattern
- JUnit
- Cucumber

---
## 📘 Ejercicios:

## Historia de Usuario: Registro de un nuevo usuario  #1

> **Como** usuario nuevo  
> **Quiero** registrarme en la tienda amantes a pescar
> **Para** poder realizar compras de productos de pesca

* **Base url**: https://amantesapescar.co/

### Criterio de aceptación

1. Usuario duplicado

    - **Regla de negocio:** No se permite registrar un usuario con un nombre de usuario que ya existe en el sistema.

    - **Mensaje de error:** "An account is already registered with that username. Please choose another."

2. Email duplicado

    - **Regla de negocio:** No se permite registrar más de una cuenta con el mismo correo electrónico.

    - **Mensaje de error**: "An account is already registered with your email address. Please log in."

3. Contraseña con menos de 6 caracteres

    - **Regla de negocio**: La contraseña debe tener un mínimo de 6 caracteres por políticas de seguridad.Criterio de aceptación
    - **Mensaje de error**: "Password must be at least 6 characters long."

#### NOTA: Test fallido (bug):
- El sistema no valida la longitud mínima de la contraseña y permite registrar una cuenta con menos de 6 caracteres.
- La evidencia la encontramos en el reporte de serenity: target/site/serenity/index.html
---
4. Email con formato incorrecto (sin @)
    - **Regla de negocio**: El email debe cumplir un formato válido antes de ser enviado al sistema.
    - **popup muestra el mensaje**: "Please enter a valid email address including '@'."
   
#### NOTA: Test fallido (bug):
- El popup no muestra el mensaje "Please enter a valid email address including '@'.", si no otro diferente.
- Evidencia: ![Evidencia](src/test/resources/upload/Captura%20de%20pantalla%202026-01-08%20123817.png)
---

## Historia de Usuario: Inicio de sesión de usuario #2

- **Como** usuario registrado
- **Quiero** iniciar sesión en la tienda Amantes a Pescar
- **Para** acceder a mi cuenta y realizar compras de forma segura

Base url: https://amantesapescar.co/

### Criterio de aceptación

1. Login exitoso
    - Regla de negocio: El sistema debe permitir el acceso cuando el usuario y la contraseña son correctos.
    - Resultado esperado: El usuario ingresa correctamente a su cuenta y visualiza su sesión iniciada.

2. Usuario no registrado
    - Regla de negocio: No se permite iniciar sesión con un usuario que no exista en el sistema.
    - Mensaje de error: "No account found with that username."

3. Contraseña incorrecta
    - Regla de negocio: El sistema debe validar que la contraseña corresponda al usuario registrado.
    - Mensaje de error: "The password you entered is incorrect. Please try again."

4. Campos obligatorios vacíos
    - Regla de negocio: El sistema debe validar que los campos usuario y contraseña sean obligatorios.
    - Mensaje de error: "Username and password are required."
---
## Historia de Usuario: Compra de una caña de pescar #3

- **Como** usuario de la tienda Amantes a Pescar
- **Quiero** buscar una caña de pescar y completar el proceso de compra
- **Para** adquirir productos de pesca de forma rápida y segura


## Criterio de aceptación

1. Búsqueda exitosa del producto
    - Regla de negocio: El sistema debe permitir buscar productos por nombre.
    - Resultado esperado: Al buscar "caña de pescar", el sistema muestra productos relacionados con ese criterio.

2. Selección del producto y compra inmediata
    - Regla de negocio: El usuario puede seleccionar un producto desde los resultados de búsqueda.
    - Resultado esperado: Al hacer clic en "Comprar ahora", el producto se agrega al flujo de compra.

3. Ingreso de datos de envío
    - Regla de negocio: Los datos de envío son obligatorios para completar la compra.
    - Resultado esperado: El sistema permite continuar solo si la información de envío está completa y es válida.

4. Validación de productos y total antes del pago
    - Regla de negocio: El sistema debe mostrar el resumen de compra antes de redirigir al servicio externo.
    - Resultado esperado:
        - El producto "caña de pescar" es visible en el resumen
        - El total a pagar corresponde al precio del producto seleccionado

5. Selección del medio de pago Wompi
    - Regla de negocio: El usuario puede seleccionar Wompi como pasarela de pago.
    - Resultado esperado: El sistema redirige correctamente a la pasarela Wompi.

6. Redirección a servicio externo de pago
    - **Regla de negocio**: La compra debe finalizar en la pasarela de pago externa.
    - Resultado esperado: El usuario es dirigido a la pantalla de Wompi con los datos correctos de la transacción.

---

## Comandos de ejecución:

```bash
mvn clean
mvn compile
```
- Ejecutar toda la suite de tests y generar los reportes de Serenity utilizando el comando Maven (Chrome):
    ```bash
    mvn clean verify
    ```
  - **¿Dónde encontramos el reporte generado?**
    - Después de ejecutar:
    ```
    Lo encontramos en: target/site/serenity/index.html
    
    Para abrir el reporte: Botón derecho del ratón sobre el archivo index.hmtl -> Open In -> Browser -> ej: Chrome
    ```
- Ejecutar un escenario por tag:
    ```bash
    mvn clean verify -Dcucumber.filter.tags="@smoke"
    mvn clean verify -Dcucumber.filter.tags="@negativo"
    mvn clean verify -Dcucumber.filter.tags="@registro" 
    ```