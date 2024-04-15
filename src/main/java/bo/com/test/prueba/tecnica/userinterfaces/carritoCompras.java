package bo.com.test.prueba.tecnica.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class carritoCompras {
    public static final Target NOMBRES_PRODUCTOS = Target.the("Nombres de Productos")
            .locatedBy("//div[@data-molecule-product-detail-name='true']/span[@data-molecule-product-detail-name-span='true']");
    public static final Target CANTIDADES_PRODUCTOS = Target.the("Cantidades de Productos")
            .locatedBy("//div[@data-molecule-product-detail-quantity='true']//span[@data-molecule-quantity-und-value='true']");
    public static final Target CANTIDADES_ELEMENTOS_EN_CARRITO = Target.the("Cantidades de Productos")
            .locatedBy("//div[@data-molecule-product-detail='true']");
}
