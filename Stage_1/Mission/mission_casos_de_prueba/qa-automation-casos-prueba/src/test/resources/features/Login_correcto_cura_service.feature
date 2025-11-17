Feature: Login para Make Appointment
  Como usuario de la aplicación
  Quiero iniciar sesión
  Para acceder al sistema con credenciales correctas

  Scenario: CP - 01 Login exitoso
    Given que estoy en la página principal
    When hago clic en Make Appointment
    And inicio sesión
    Then veo el encabezado Make Appointment
    And cierro sesión