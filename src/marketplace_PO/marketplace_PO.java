package marketplace_PO;

import java.util.ArrayList;

public class marketplace_PO {

    public static void main(String[] args) {
        // instanciando a lista de produtos iniciais
        MetodosMenu menu = new MetodosMenu();

        // instanciando e adicionando produtos iniciais na lista de produtos
        Eletronico Notebook = new Eletronico("Notebook Dell", 2500.0f, "computadores", "15/10/2025", 0, 143, 12, "Dell");
        Vestuario CamisaPolo = new Vestuario("Camisa Polo", 60.0f, "camisas", "23/01/2026", 0, 8237, "P", "seda");

        menu.addProdutoLista(Notebook);
        menu.addProdutoLista(CamisaPolo);

        // instanciando e adicionando cupons iniciais na lista de cupons
        Cupom cupom1 = new Cupom("DESCONTO15", 15);
        Cupom cupom2 = new Cupom("DESCONTO50", 50);

        menu.addCupomLista(cupom1);
        menu.addCupomLista(cupom2);

        // chamada pra montar o menu
        menu.menuPadrao();
    }
}
