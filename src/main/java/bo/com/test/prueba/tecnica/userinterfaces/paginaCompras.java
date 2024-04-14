package bo.com.test.prueba.tecnica.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class paginaCompras {
    public static final Target PRODUCTOS = Target.the("Productos listados")
            .locatedBy("//ul[@data-fs-product-grid='true']/li/article");
    public static final Target BOTON_MAS_CANTIDAD = Target.the("Cantidad del producto")
            .locatedBy("//button[contains(@class, 'QuantitySelectorDefault_plus__7TM3c')]");
    public static final Target BOTON_AGREGAR_AL_CARRITO = Target.the("Botón agregar al carrito")
            .locatedBy(".//button[contains(@class,'DefaultStyle_default__vCozi')]");
    public static final Target PRECIO_PRODUCTO = Target.the("Precio del producto seleccionado")
            .locatedBy(".//div[contains(@class, 'ProductPrice_container__CkWjL')]");
    public static final Target NOMBRE_PRODUCTO = Target.the("Nombre del producto seleccionado")
            .locatedBy("//article[@data-fs-product-card=\"true\"]//h3[@data-fs-product-card-title=\"true\"]/a");
}