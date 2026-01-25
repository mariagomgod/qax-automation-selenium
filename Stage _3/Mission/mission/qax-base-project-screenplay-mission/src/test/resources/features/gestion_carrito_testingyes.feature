Feature: Gestión del carrito de compras #6 - OnlineShop TestingYes
  Como usuario de la tienda
  Quiero administrar los productos del carrito
  Para controlar mi compra antes de pagar

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Agregar producto al carrito
    @carrito @smoke
    Scenario: Agregar producto al carrito
      When el usuario abre el detalle de un producto existente
      And el usuario cambia la talla y el color
      And el usuario agrega el producto al carrito desde el detalle
      And el usuario navega al carrito
      Then el producto aparece listado con precio correcto

  Rule: Eliminar producto del carrito
    @carrito
    Scenario: Eliminar producto del carrito
      Given que el usuario tiene un producto "hummingbird" en el carrito
      When el usuario elimina el producto del carrito
      Then el producto se elimina y el total se actualiza

  Rule: Validación del total
    @carrito
    Scenario: Validación del total
      Given que el usuario tiene un producto "hummingbird" en el carrito
      And que el usuario tiene un producto "hummingbird" en el carrito
      Then el total del carrito corresponde a la suma de los productos