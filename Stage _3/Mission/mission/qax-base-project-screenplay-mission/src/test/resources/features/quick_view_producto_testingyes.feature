Feature: Quick View de producto - OnlineShop TestingYes
  Como usuario de la tienda
  Quiero usar la vista rápida del producto
  Para revisar información sin salir del listado

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Visualización de Quick View
    @quickview @smoke
    Scenario: Visualización de Quick View muestra información básica del producto
      When el usuario abre la vista rápida Quick View desde el listado
      Then el sistema muestra una ventana modal con información básica
      And se visualiza el nombre, el precio y la opción de compra