Feature: Gestión de direcciones #7 - OnlineShop TestingYes
  Como usuario autenticado
  Quiero agregar una nueva dirección de envío
  Para usarla durante el checkout

  Background:
    Given que existe un usuario registrado previamente con email
    When el usuario inicia sesión con los siguientes datos:
      | email           | password           |
      | <existingEmail> | <existingPassword> |

  Rule: Agregar nueva dirección
    @direcciones @smoke
    Scenario: Agregar una nueva dirección de envío y verla disponible en checkout
      When el usuario agrega una nueva dirección de envío con los siguientes datos:
        | alias         | direccion       | ciudad       | postal  | telefono   | pais          | state       |
        | <randomAlias> | <randomAddress> | <randomCity> | 28080   | 3001234567 | United States | California  |
      Then la dirección queda disponible para selección durante el checkout