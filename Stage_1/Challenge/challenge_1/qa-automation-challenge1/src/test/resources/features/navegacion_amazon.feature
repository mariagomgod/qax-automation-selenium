Feature: Navegación de ofertas y categorías en Amazon
  Como usuario
  Quiero navegar por Amazon hasta una categoría concreta
  Para filtrar productos y llegar a “Compra juguetes más vendidos”

  Scenario: CP1 - Registrar usuario
    Given que navego a la web de Amazon
    When hago clic en el botón Continue shopping si aparece
    And clico en el botón CONTINUAR
    And clico en la pestaña Ofertas del Día
    And aplico el filtro Ofertas relámpago
    And clico en la pestaña Outlet
    And clico en el enlace JUGUETES Y JUEGOS
    And clico en la subcategoría Peluches
    And activo el filtro Prime
    And clico en la subcategoría Monederos de Felpa
    And clico en la pestaña Compra juguetes más vendidos
    Then debería ver el listado de juguetes más vendidos cargado correctamente
