package marketplace_PO;

import java.util.ArrayList;

public class marketplace_PO {

    public static void main(String[] args) {
        // instanciando a lista de produtos iniciais
        MetodosMenu menu = new MetodosMenu();

        Eletronico Notebook = new Eletronico("Notebook Dell", 2500.0f, "computadores", "15/10/2025", 0, 143, 12, "Dell");
        Vestuario CamisaPolo = new Vestuario("Camisa Polo", 60.0f, "camisas", "23/01/2026", 0, 8237, "P", "seda");

        menu.addProdutoLista(Notebook);
        menu.addProdutoLista(CamisaPolo);

        menu.menuPadrao();
    }
}
