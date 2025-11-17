Feature: Login para Make Appointment
  Como usuario de la aplicación
  Quiero iniciar sesión
  Para acceder al sistema con credenciales incorrectas

  Scenario: CP - 01 Login exitoso
    Given que estoy en la página principal de Herokuapp
    When hago clic en el botón Make Appointment
    And inicio sesión con credenciales inválidas
    Then veo un mensaje de login fallido