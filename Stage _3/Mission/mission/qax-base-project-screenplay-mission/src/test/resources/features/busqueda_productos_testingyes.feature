Feature: Búsqueda de productos - OnlineShop TestingYes
  Como usuario de la tienda
  Quiero buscar productos por nombre o categoría
  Para encontrar rápidamente lo que deseo comprar

  Background:
    Given que el usuario accede a la tienda online TestingYes

  Rule: Búsqueda por palabra clave
    @busqueda @smoke
    Scenario: Búsqueda por palabra clave muestra resultados
      When el usuario realiza una búsqueda con un producto existente
      Then el sistema muestra productos relacionados con la búsqueda

  Rule: Búsqueda sin resultados
    @busqueda @negativo
    Scenario: Búsqueda sin resultados muestra mensaje informativo
      When el usuario realiza una búsqueda con un producto que no existe
      Then el sistema muestra un mensaje indicando que no hay resultados