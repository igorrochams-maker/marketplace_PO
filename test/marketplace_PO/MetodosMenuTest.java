package marketplace_PO;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.security.InvalidAlgorithmParameterException;
import static org.junit.Assert.*;

public class MetodosMenuTest {

    private MetodosMenu menu;
    private Eletronico produto1;
    private Vestuario produto2;
    private Cupom cupom1;
    private Cupom cupom2;

    @Before
    public void setUp() {
        menu = new MetodosMenu();
        produto1 = new Eletronico("Notebook Dell", 2500.0f, "computadores", LocalDate.of(2025, 10, 15), 0, 143, 12, "Dell");
        produto2 = new Vestuario("Camisa Polo", 60.0f, "camisas", LocalDate.of(2026, 1, 23), 0, 8237, "P", "seda");
        cupom1 = new Cupom("DESCONTO15", 15);
        cupom2 = new Cupom("DESCONTO50", 50);
    }

    @Test
    public void testAddProdutoLista() {
        menu.addProdutoLista(produto1);
        menu.addProdutoLista(produto2);

        assertEquals(2, menu.getListaProdutos().size());
        assertEquals(produto1, menu.getListaProdutos().get(0));
        assertEquals(produto2, menu.getListaProdutos().get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testAddProdutoNuloLancaExcecao() {
        menu.addProdutoLista(null);
    }

    @Test
    public void testRemoverProdutoListaComSucesso() throws InvalidAlgorithmParameterException {
        menu.addProdutoLista(produto1);
        menu.addProdutoLista(produto2);

        menu.removerProdutoLista(143);
        assertEquals(1, menu.getListaProdutos().size());
        assertEquals(produto2, menu.getListaProdutos().get(0));
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void testRemoverProdutoCodigoZeroOuNegativoLancaExcecao() throws InvalidAlgorithmParameterException {
        menu.removerProdutoLista(0);
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void testRemoverProdutoCodigoNegativoLancaExcecao() throws InvalidAlgorithmParameterException {
        menu.removerProdutoLista(-10);
    }

    @Test
    public void testAddCupomLista() {
        menu.addCupomLista(cupom1);
        menu.addCupomLista(cupom2);

        assertEquals(2, menu.getListaCupons().size());
        assertEquals(cupom1, menu.getListaCupons().get(0));
        assertEquals(cupom2, menu.getListaCupons().get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testAddCupomNuloLancaExcecao() {
        menu.addCupomLista(null);
    }

    @Test
    public void testRemoverCupomListaComSucesso() throws InvalidAlgorithmParameterException {
        menu.addCupomLista(cupom1);
        menu.addCupomLista(cupom2);

        menu.removerCupomLista("DESCONTO15");
        assertEquals(1, menu.getListaCupons().size());
        assertEquals(cupom2, menu.getListaCupons().get(0));
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void testRemoverCupomCodigoVazioLancaExcecao() throws InvalidAlgorithmParameterException {
        menu.removerCupomLista("");
    }
}
