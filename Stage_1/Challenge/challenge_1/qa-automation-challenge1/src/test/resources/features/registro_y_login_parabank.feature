Feature: Registro y login en Parabank
  Como usuario
  Quiero registrar una cuenta y luego iniciar sesión
  Para validar que el flujo completo funciona

  Scenario: CP1 - Registrar usuario
    Given que navego a la página principal de Parabank
    And accedo a la página de registro
    And la página de registro se muestra correctamente
    When completo el formulario de registro con datos válidos
    And clico en el botón Register
    Then debería ver que la cuenta se ha creado correctamente

  Scenario: CP2 - Login
    When cierro sesión si estoy autenticado
    And inicio sesión con las credenciales válidas
    Then debería ver que el login ha sido exitoso