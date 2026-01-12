# Mission – Automation Web (Serenity BDD + Screenplay) - Implementando un Framework Profesional desde Cero
En esta misión, pondré en práctica todo lo aprendido en los entrenamientos anteriores para construir un framework de automatización web profesional desde cero utilizando Pattern Screenplay con Serenity BDD y Cucumber (BDD). 

## 🎯 Objetivos
- Construir un framework de automatización web completo y profesional.
- Implementar pruebas automatizadas utilizando Screenplay Pattern y Cucumber (BDD).
- Aplicar buenas prácticas de diseño y arquitectura en la automatización.
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

## 📌 Historia de Usuario: Creación de cuenta de usuario #1

> **Como** visitante de la tienda online  
> **Quiero** crear una cuenta con mis datos personales  
> **Para** poder gestionar pedidos y realizar compras

* **Base url**: http://www.testingyes.com/onlineshop/

### Criterios de aceptación

1. Registro exitoso
    - Regla de negocio: El sistema debe permitir crear una cuenta con datos válidos.
    - Resultado esperado: La cuenta se crea correctamente y el usuario queda autenticado.

2. Email duplicado
    - Regla de negocio: No se permite registrar una cuenta con un email ya existente.
    - Mensaje de error:  "An account using this email address has already been registered."

3. Campos obligatorios
    - Regla de negocio: Todos los campos obligatorios deben ser diligenciados.
    - Mensaje de error: "Please fill in all required fields."

---

## 📌 Historia de Usuario: Inicio de sesión  #2

> **Como** usuario registrado  
> **Quiero** iniciar sesión en la tienda  
> **Para** acceder a mi cuenta y realizar compras

* **Base url**: http://www.testingyes.com/onlineshop/

### Criterios de aceptación

1. Login exitoso
    - Regla de negocio: El sistema permite el acceso con credenciales válidas.
    - Resultado esperado: El usuario accede correctamente a su cuenta.

2. Credenciales inválidas
    - Regla de negocio: El sistema bloquea el acceso con credenciales incorrectas.
    - Mensaje de error: "Authentication failed."

---

## 📌 Historia de Usuario: Búsqueda de productos  #3

> **Como** usuario de la tienda  
> **Quiero** buscar productos por nombre o categoría  
> **Para** encontrar rápidamente lo que deseo comprar

### Criterios de aceptación

1. Búsqueda por palabra clave
    - Regla de negocio: El sistema permite buscar productos por texto.
    - Resultado esperado: Se muestran productos relacionados con la búsqueda.

2. Búsqueda sin resultados
    - Regla de negocio: El sistema informa cuando no hay coincidencias.
    - Mensaje informativo:  "No products were found."

---

## 📌 Historia de Usuario: Quick View de producto  #4

> **Como** usuario de la tienda  
> **Quiero** usar la vista rápida del producto  
> **Para** revisar información sin salir del listado

### 📌 Criterios de aceptación

1. Visualización de Quick View
    - Regla de negocio: El sistema muestra una ventana modal con información básica.
    - Resultado esperado: Se visualizan nombre, precio y opción de compra.

---

## 📌 Historia de Usuario: Detalle del producto  #5

> **Como** usuario de la tienda  
> **Quiero** ver el detalle completo del producto  
> **Para** seleccionar talla, cantidad y contactar al vendedor

### Criterios de aceptación

1. Selección de talla/color
    - Regla de negocio: La talla/color es obligatoria antes de comprar.
    - Resultado esperado: El usuario puede cambiar la talla/color

2. Selección de cantidad
    - Regla de negocio: El usuario puede modificar la cantidad del producto.
    - Resultado esperado: El precio se ajusta según la cantidad.

---

## 📌 Historia de Usuario: Gestión del carrito de compras  #6

> **Como** usuario de la tienda  
> **Quiero** administrar los productos del carrito  
> **Para** controlar mi compra antes de pagar

### Criterios de aceptación

1. Agregar producto al carrito
    - Regla de negocio: El usuario puede agregar productos al carrito.
    - Resultado esperado: El producto aparece listado con precio correcto.

2. Eliminar producto del carrito
    - Regla de negocio: El usuario puede eliminar productos del carrito.
    - Resultado esperado: El producto se elimina y el total se actualiza.

3. Validación del total
    - Regla de negocio: El total del carrito corresponde a la suma de los productos.

---

## 📌 Historia de Usuario: Gestión de direcciones  #7

> **Como** usuario autenticado  
> **Quiero** agregar una nueva dirección de envío  
> **Para** usarla durante el checkout

### Criterios de aceptación

1. Agregar nueva dirección
    - Regla de negocio: El sistema permite registrar direcciones válidas.
    - Resultado esperado: La dirección queda disponible para selección.

---

## 📌 Historia de Usuario: Proceso de checkout y pago  #8

> **Como** usuario de la tienda  
> **Quiero** completar el proceso de pago  
> **Para** finalizar mi compra exitosamente

### Criterios de aceptación

1. Pago con cheque
    - Regla de negocio: El sistema permite pagar mediante cheque.
    - Resultado esperado: El pedido se confirma correctamente.

2. Pago por transferencia bancaria
    - Regla de negocio: El sistema permite pagar mediante banco.
    - Resultado esperado: El pedido se confirma con instrucciones de pago.

3. Confirmación del pedido
    - Regla de negocio: El sistema muestra un resumen final del pedido.
    - Resultado esperado:
        - Productos correctos
        - Precios correctos
        - Dirección seleccionada
        - Método de pago elegido
        - Mensaje : "Your order is confirmed"

---

## ✅ Comandos de ejecución:

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