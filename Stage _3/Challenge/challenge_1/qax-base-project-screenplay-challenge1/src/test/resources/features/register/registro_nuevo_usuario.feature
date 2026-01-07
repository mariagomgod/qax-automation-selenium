Feature: Registro de usuario en la tienda virtual Amantes a Pescar
  Como visitante de la tienda Amantes a Pescar
  Quiero registrarme con mis datos personales
  Para comprar mis productos favoritos

  Background:
    Given que el comprador accede a la tienda viritual amantes a pescar
    And que el comprador navega a la opción de registro

  @registro @smoke
  Scenario: Registro exitoso de un nuevo usuario
    When el comprador se registra con los siguientes datos:
      | usuario      | email                 | password |
      | Julio Cesar12  | JulioCesar12@yahoo.com  | 123456   |
    Then el sistema crea la cuenta correctamente

  @registro @negativo
  Scenario: Registro fallido por contraseñas diferentes
    When ingresa contraseñas diferentes en el formulario de registro
      | usuario       | email                  | password |
      | Julio Cesar1  | JulioCesar1@yahoo.com  | 123456   |
    Then el sistema muestra un mensaje de error indicando que las contraseñas no coinciden

  @registro @negativo
  Scenario: Registro fallido por campos obligatorios
    When el comprador se registra con los siguientes datos:
      | usuario      | email                 | password |
      |  |   | 123456   |
    Then el sistema muestra un mensaje indicando que los campos son obligatorios
