Feature: Inicio de sesión de usuario en la tienda virtual Amantes a Pescar
  Como usuario registrado
  Quiero iniciar sesión en la tienda Amantes a Pescar
  Para acceder a mi cuenta y realizar compras de forma segura

  Background:
    Given que el usuario accede a la tienda virtual amantes a pescar
    And que el usuario navega a la opción de inicio de sesión

  Rule: Autenticación con usuario existente

    Background:
      Given que existe un usuario registrado previamente
      And que el usuario navega a la opción de inicio de sesión

    @login @smoke
    Scenario: Login exitoso
      When el usuario inicia sesión con los siguientes datos:
        | usuario        | password |
        | <existingUser> | 123456   |
      Then el sistema permite el acceso y muestra la sesión iniciada

    @login @negativo
    Scenario: Login fallido por contraseña incorrecta
      When el usuario inicia sesión con los siguientes datos:
        | usuario        | password  |
        | <existingUser> | wrongPass |
      Then el sistema muestra un mensaje de error indicando que la contraseña es incorrecta

  Rule: Validaciones de inicio de sesión

    @login @negativo
    Scenario: Login fallido por usuario no registrado
      When el usuario inicia sesión con los siguientes datos:
        | usuario     | password |
        | fakeUser123 | 123456   |
      Then el sistema muestra un mensaje de error indicando que el usuario no existe

    @login @negativo
    Scenario: Login fallido por campos obligatorios vacíos
      When el usuario inicia sesión con los siguientes datos:
        | usuario | password |
        |         |          |
      Then el sistema muestra un mensaje indicando que el usuario y la contraseña son obligatorios