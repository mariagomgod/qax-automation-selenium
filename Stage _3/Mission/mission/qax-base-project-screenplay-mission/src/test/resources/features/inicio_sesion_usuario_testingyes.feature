Feature: Inicio de sesión - OnlineShop TestingYes
  Como usuario registrado
  Quiero iniciar sesión en la tienda
  Para acceder a mi cuenta y realizar compras

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Login exitoso
    @login @smoke
    Scenario: Login exitoso con credenciales válidas
      Given que existe un usuario registrado previamente con email
      When el usuario inicia sesión con los siguientes datos:
        | email           | password           |
        | <existingEmail> | <existingPassword> |
      Then el sistema permite el acceso y muestra la sesión iniciada

  Rule: Credenciales inválidas
    @login @negativo
    Scenario: No permitir acceso con credenciales incorrectas
      When el usuario inicia sesión con los siguientes datos:
        | email         | password |
        | <randomEmail> | 123456   |
      Then el sistema muestra un mensaje de error indicando credenciales inválidas

