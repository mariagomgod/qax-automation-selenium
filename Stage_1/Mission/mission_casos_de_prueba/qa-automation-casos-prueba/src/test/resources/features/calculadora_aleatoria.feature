Feature: Operaciones básicas en la calculadora
  Como usuario de la aplicación
  Quiero usar la calculadora web
  Para realizar operaciones básicas y ver/imprimir los resultados

  Scenario: Sumar
    Given que abro la página de la calculadora
    And el primer operando es 6
    And el segundo operando es 2
    When calculo la suma
    Then el resultado mostrado es "8"

  Scenario: Restar
    Given que abro la página de la calculadora
    And el primer operando es 6
    And el segundo operando es 2
    When calculo la resta
    Then el resultado mostrado es "4"

  Scenario: Dividir
    Given que abro la página de la calculadora
    And el primer operando es 6
    And el segundo operando es 2
    When calculo la división
    Then el resultado mostrado es "3"

  Scenario: División por cero muestra
    Given que abro la página de la calculadora
    And el primer operando es 6
    And el segundo operando es 0
    When calculo la división
    Then el resultado mostrado es "Infinity"

  Scenario: Operación con operando faltante
    Given que abro la página de la calculadora
    And el primer operando es 6
    When calculo la suma
    Then el resultado mostrado es "6"
