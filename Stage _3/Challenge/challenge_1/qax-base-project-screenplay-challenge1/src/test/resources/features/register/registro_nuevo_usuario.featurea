Feature: Registro de usuario en la tienda virtual Amantes a Pescar
  Como visitante de la tienda Amantes a Pescar
  Quiero registrarme con mis datos personales
  Para comprar mis productos favoritos

  Background:
    Given que el comprador accede a la tienda virtual amantes a pescar
    And que el comprador navega a la opción de registro

  @registro @smoke
  Scenario: Registro exitoso de un nuevo usuario
    When el comprador se registra con los siguientes datos:
      | usuario      | email         | password |
      | <randomUser> | <randomEmail> | 123456   |
    Then el sistema crea la cuenta correctamente

  @registro @negativo
  Scenario: Registro fallido por contraseñas diferentes
    When ingresa contraseñas diferentes en el formulario de registro
      | usuario      | email         | password |
      | <randomUser> | <randomEmail> | 123456   |
    Then el sistema muestra un mensaje de error indicando que las contraseñas no coinciden

  @registro @negativo
  Scenario: Registro fallido por campos obligatorios
    When el comprador se registra con los siguientes datos:
      | usuario | email | password |
      |         |       | 123456   |
    Then el sistema muestra un mensaje indicando que los campos son obligatorios

  @registro @negativo
  Scenario: Registro fallido por contraseña con menos de 6 caracteres
    When el comprador se registra con los siguientes datos:
      | usuario      | email         | password |
      | <randomUser> | <randomEmail> | 12345    |
    Then el sistema muestra un mensaje de error indicando que la contraseña es demasiado corta

  Rule: Validaciones por datos duplicados en registro

    Background:
      Given que existe un usuario registrado previamente
      And que el comprador navega a la opción de registro

    @registro @negativo
    Scenario: Registro fallido por usuario duplicado
      When el comprador se registra con los siguientes datos:
        | usuario        | email         | password |
        | <existingUser> | <randomEmail> | 123456   |
      Then el sistema muestra un mensaje de error indicando que el usuario ya existe

    @registro @negativo
    Scenario: Registro fallido por email duplicado
      When el comprador se registra con los siguientes datos:
        | usuario      | email           | password |
        | <randomUser> | <existingEmail> | 123456   |
      Then el sistema muestra un mensaje de error indicando que el email ya está registrado

    @registro @negativo
    Scenario: Registro fallido por email con formato incorrecto (sin @)
      When el comprador se registra con los siguientes datos:
        | usuario      | email      | password |
        | <randomUser> | prueba.com | 123456   |
      Then el sistema muestra un mensaje de error indicando que el email no es válido