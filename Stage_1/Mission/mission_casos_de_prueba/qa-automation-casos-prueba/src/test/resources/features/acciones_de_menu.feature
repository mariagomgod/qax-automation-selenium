Feature: Interacción con menús usando selectores avanzados
  Como usuario de la aplicación
  Quiero interactuar con menús localizados por CSS o XPath avanzado
  Para ejecutar clicks, doble clicks y clicks derecho e imprimir cada acción realizada

  Scenario: CP1 - Click básico
    Given que el usuario está en la página de dropdown menu
    When hago click en el menú Use left-click here
    Then imprimo la acción realizada para Use left-click here

  Scenario: CP2 - Click derecho
    Given que el usuario está en la página de dropdown menu
    When hago click en el menú Use right-click here
    Then imprimo la acción realizada para Use right-click here

  Scenario: CP3 - Doble click
    Given que el usuario está en la página de dropdown menu
    When hago click en el menú Use double-click here
    Then imprimo la acción realizada para Use double-click here