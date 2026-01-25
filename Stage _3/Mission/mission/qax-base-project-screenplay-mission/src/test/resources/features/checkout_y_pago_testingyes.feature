Feature: Proceso de checkout y pago #8 - OnlineShop TestingYes
  Como usuario de la tienda
  Quiero completar el proceso de pago
  Para finalizar mi compra exitosamente

  Background:
    Given que existe un usuario registrado previamente con email
    When el usuario inicia sesión con los siguientes datos:
      | email           | password           |
      | <existingEmail> | <existingPassword> |
    And el usuario agrega una nueva dirección de envío con los siguientes datos:
      | alias         | direccion       | ciudad       | postal  | telefono   | pais          | state       |
      | <randomAlias> | <randomAddress> | <randomCity> | 28080   | 3001234567 | United States | California  |

  Rule: Pago con cheque
    @checkout @pago @cheque @smoke
    Scenario: Pago con cheque confirma el pedido correctamente
      When el usuario procede al checkout con "cheque"
      Then el sistema muestra el resumen final del pedido

  Rule: Pago por transferencia bancaria
    @checkout @pago @bankwire
    Scenario: Pago por transferencia bancaria muestra instrucciones y confirma
      When el usuario procede al checkout con "transferencia"
      Then el sistema muestra el resumen final del pedido