package bo.com.test.prueba.tecnica.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class paginaPrincipal {
    public static final Target BTNHamburguesa = Target.the("Boton de categorias")
            .locatedBy("//span[contains(text(),'Menú')]");
    public static final Target ListaCategorias = Target.the("Lista de Categorias")
            .locatedBy("//section[header='Categorías']/div/li[contains(@class, 'Link_link-container__C_KEm')]");
    public static final Target SUBCategoria = Target.the("Lista de Subcategorias")
            .locatedBy("//ul[@data-list-sections='true']/li[@data-link='true']/a");
}
