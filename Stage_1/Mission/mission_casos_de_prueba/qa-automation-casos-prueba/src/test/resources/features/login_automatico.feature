Feature: Flujos de la web de Boni García
  Como usuario de la aplicación
  Quiero automatizar varios escenarios de login
  Para que pueda asegurarme de que los flujos se ejecuten según lo esperado

  Scenario: CP1 - Login exitoso con credenciales válidas
    Given que el usuario está en la página de login
    When ingresa el usuario y la contraseña correctos
    And hace click en el botón de login
    Then el sistema muestra que el login fue exitoso

  Scenario: CP2 - Login con contraseña incorrecta
    Given que el usuario está en la página de login
    When ingresa un usuario válido pero una contraseña incorrecta
    And hace click en el botón de login
    Then el sistema muestra un mensaje de error de credenciales

  Scenario: CP3 - Login con campos vacíos
    Given que el usuario está en la página de login
    When deja los campos de usuario y contraseña vacíos
    And hace click en el botón de login
    Then el sistema muestra un mensaje de error de credenciales