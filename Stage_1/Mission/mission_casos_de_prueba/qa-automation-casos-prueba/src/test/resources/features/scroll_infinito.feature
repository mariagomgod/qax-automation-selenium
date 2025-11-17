Feature: Scroll e impresión de elementos
  Como usuario de la aplicación
  Quiero hacer scroll hasta cargar una serie de elementos
  Para que pueda asegurarme de que los elementos son visibles y se cargan correctamente

  Scenario: CP1 - Login exitoso con credenciales válidas
    Given que el usuario está en la página de scroll infinito
    When hace scroll hasta cargar al menos 20 elementos nuevos
    Then imprime los textos de los elementos visibles