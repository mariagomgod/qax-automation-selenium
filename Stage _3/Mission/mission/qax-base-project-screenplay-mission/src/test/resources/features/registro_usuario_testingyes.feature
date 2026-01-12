Feature: Creación de cuenta de usuario - OnlineShop TestingYes
  Como visitante de la tienda online
  Quiero crear una cuenta con mis datos personales
  Para poder gestionar pedidos y realizar compras

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Registro exitoso
    @registro @smoke
    Scenario: Registro exitoso con datos válidos
      When el usuario se registra con los siguientes datos:
        | nombre     | apellido   | email         | password |
        | <randomFN> | <randomLN> | <randomEmail> | 123456   |
      Then el sistema crea la cuenta correctamente y el usuario queda autenticado

  Rule: Email duplicado
    @registro @negativo
    Scenario: No permitir registro con un email ya existente
      Given que existe un usuario registrado previamente con email
      When el usuario se registra con los siguientes datos:
        | nombre     | apellido   | email           | password |
        | <randomFN> | <randomLN> | <existingEmail> | 123456   |
      Then el sistema muestra un mensaje de error indicando email duplicado

  Rule: Campos obligatorios
    @registro @negativo
    Scenario: No permitir registro si faltan campos obligatorios
      When el usuario se registra con los siguientes datos:
        | nombre | apellido | email | password |
        |        |          |       |          |
      Then el sistema muestra un mensaje de error indicando campos obligatorios

