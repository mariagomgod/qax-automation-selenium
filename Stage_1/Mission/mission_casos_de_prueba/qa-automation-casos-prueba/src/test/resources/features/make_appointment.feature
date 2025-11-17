Feature: Agendar una cita médica
  Como usuario de la aplicación
  Quiero iniciar sesión
  Para acceder al sistema y agendar una cita médica

  Scenario: CP01 - Agendar cita exitosamente
    Given que estoy en la página Make Appointment
    And hago login
    When completo el formulario de cita con datos válidos
    And hago clic en Book Appointment
    Then veo el mensaje Appointment Confirmation