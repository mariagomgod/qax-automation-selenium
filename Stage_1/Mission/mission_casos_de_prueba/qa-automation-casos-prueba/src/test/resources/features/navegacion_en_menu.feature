Feature: Navegación en el menú lateral de CURA Healthcare Service
  Como usuario
  Quiero navegar por el menú lateral
  Para validar que los enlaces funcionan y ver sus títulos

  Scenario: CP01 - Validar enlace Home
    Given que estoy en la página principal de CURA Healthcare Service
    When abro el menú lateral y navego a "Home"
    Then imprimo en consola el título de la homepage
    And cierro sesión

  Scenario: CP02 - Validar enlace History
    Given que estoy en la página principal de CURA Healthcare Service
    When abro el menú lateral y navego a "Login"
    And inicio sesión
    And abro el menú lateral y navego a "History"
    Then imprimo en consola el título History
    And cierro sesión

  Scenario: CP03 - Validar enlace Profile
    Given que estoy en la página principal de CURA Healthcare Service
    When abro el menú lateral y navego a "Login"
    And inicio sesión
    And abro el menú lateral y navego a "Profile"
    Then imprimo en consola el título Profile
    And cierro sesión

  Scenario: CP04 - Validar enlace Logout
    Given que estoy en la página principal de CURA Healthcare Service
    When abro el menú lateral y navego a "Login"
    And inicio sesión
    And abro el menú lateral y navego a "Logout"
    Then imprimo en consola el título de la homepage