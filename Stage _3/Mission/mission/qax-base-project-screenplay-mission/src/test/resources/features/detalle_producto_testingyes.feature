Feature: Detalle del producto - OnlineShop TestingYes
  Como usuario de la tienda
  Quiero ver el detalle completo del producto
  Para seleccionar talla, cantidad y contactar al vendedor

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Selección de talla/color
    @detalle @smoke
    Scenario: El usuario puede cambiar la talla y el color del producto
      When el usuario abre el detalle de un producto existente
      And el usuario cambia la talla y el color
      Then el sistema refleja la talla y el color seleccionados

  Rule: Selección de cantidad
    @detalle
    Scenario: El usuario puede modificar la cantidad y el precio se ajusta según la cantidad
      When el usuario abre el detalle de un producto existente
      And el usuario cambia la talla y el color
      And el usuario cambia la cantidad
      And el usuario agrega el producto al carrito desde el detalle
      Then el total en el carrito corresponde al precio unitario por la cantidad
