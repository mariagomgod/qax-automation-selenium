Feature: Compra de una caña de pescar en la tienda virtual Amantes a Pescar
  Como usuario de la tienda Amantes a Pescar
  Quiero buscar una caña de pescar y completar el proceso de compra
  Para adquirir productos de pesca de forma rápida y segura

  Background:
    Given que el usuario accede a la tienda virtual amantes a pescar

  Rule: Búsqueda y selección de producto

    @compra @smoke
    Scenario: Búsqueda exitosa del producto "caña de pescar"
      When el usuario busca el producto "caña de pescar"
      Then el sistema muestra resultados relacionados con "caña de pescar"

    @compra
    Scenario: Selección del producto y compra inmediata desde resultados
      Given que existen resultados para la búsqueda "caña de pescar"
      When el usuario selecciona un producto desde los resultados
      And el usuario hace clic en "Comprar ahora"
      Then el sistema agrega el producto al flujo de compra

  Rule: Validaciones del checkout

     @compra @negativo
     Scenario: No permitir continuar si faltan datos de envío obligatorios
      Given que el usuario tiene un producto en el flujo de compra
      When el usuario intenta continuar sin completar los datos de envío
      Then el sistema muestra un mensaje indicando que los datos de envío son obligatorios

    @compra
    Scenario: Validación del resumen de compra antes del pago
      Given que el usuario tiene un producto "caña de pescar" en el flujo de compra
      When el usuario visualiza el resumen de compra
      Then el producto "caña" es visible en el resumen
      And el total a pagar corresponde al precio del producto seleccionado

  Rule: Selección de pasarela de pago y redirección

    @compra
    Scenario: Selección del medio de pago Wompi
      Given que el usuario está en el paso de pago del checkout
      And que el usuario completa los datos de envío válidos:
        | nombre     | apellido   | email         | cedula   | tipoDoc | numDoc   | telefono   |
        | <randomFN> | <randomLN> | <randomEmail> | 12345678 | cc      | 12345678 | 3001234567 |
      When el usuario selecciona Wompi como medio de pago
      Then el sistema redirige correctamente a la pasarela Wompi

    @compra
    Scenario: Redirección a servicio externo de pago con datos de transacción
      Given que el usuario está en el paso de pago del checkout
      And que el usuario completa los datos de envío válidos:
        | nombre     | apellido   | email         | cedula   | tipoDoc | numDoc   | telefono   |
        | <randomFN> | <randomLN> | <randomEmail> | 12345678 | cc      | 12345678 | 3001234567 |
      When el usuario selecciona Wompi como medio de pago
      And el usuario confirma la compra
      Then el usuario es dirigido a la pantalla de Wompi
      And la transacción contiene los datos correctos del producto y el total