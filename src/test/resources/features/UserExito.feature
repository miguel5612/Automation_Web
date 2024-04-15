#Autor: Miguel Califa 13/04/2024

@stories
Feature: Comprar productos en Tienda Éxito

  @agregarCarroProductosAleatorios

  Scenario Outline: Comprar 5 productos aleatorios de una subcategoría específica
    Given el usuario está en la página principal de '<unitName>'
    When el usuario navega a una categoría y subcategoría específica
    And el usuario agrega 5 productos aleatorios al carrito con cantidades aleatorias entre 1 y 10
    Then se verifica que los nombres de los productos en el carrito coincidan con los seleccionados
    And se verifica que las cantidades de los productos en el carrito sean correctas
    And se verifica que el total de los precios en el carrito sea correcto
    And se verifica que el número de productos en el carrito sea correcta
    Examples:
      |unitName|
      ##@externaldata@./src/test/resources/datadriven/apiData.xlsx@infoWEB
   |Tiendas Éxito|
