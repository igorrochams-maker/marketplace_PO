package marketplace_PO;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class CarrinhoTest {

    @Test
    public void testAdicionarEObterProdutos() {
        Carrinho c = new Carrinho();
        Eletronico e = new Eletronico("Fone", 200.0f, "Áudio", LocalDate.of(2026, 1, 1), 0, 1, 0, "Sony");
        c.adicionarProduto(e);

        assertEquals(1, c.getQuantidadeItens());
        assertEquals(1, c.getProdutos().size());
        assertEquals(e, c.getProdutos().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdicionarProdutoNulo() {
        Carrinho c = new Carrinho();
        c.adicionarProduto(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAdicionarAlimentoVencidoLancaExcecao() {
        Carrinho c = new Carrinho();
        Alimento vencido = new Alimento("Iogurte", 5.0f, "Laticínios", LocalDate.of(2026, 1, 1), 0, 2, LocalDate.now().minusDays(1));
        c.adicionarProduto(vencido);
    }

    @Test
    public void testAdicionarAlimentoValido() {
        Carrinho c = new Carrinho();
        Alimento valido = new Alimento("Iogurte", 5.0f, "Laticínios", LocalDate.of(2026, 1, 1), 0, 2, LocalDate.now().plusDays(5));
        c.adicionarProduto(valido);
        assertEquals(1, c.getQuantidadeItens());
    }

    @Test
    public void testRemoverProdutoExistente() {
        Carrinho c = new Carrinho();
        Eletronico e = new Eletronico("Fone", 200.0f, "Áudio", LocalDate.of(2026, 1, 1), 0, 10, 0, "Sony");
        c.adicionarProduto(e);

        boolean removido = c.removerProduto(10);
        assertTrue(removido);
        assertEquals(0, c.getQuantidadeItens());
    }

    @Test
    public void testRemoverProdutoInexistente() {
        Carrinho c = new Carrinho();
        Eletronico e = new Eletronico("Fone", 200.0f, "Áudio", LocalDate.of(2026, 1, 1), 0, 10, 0, "Sony");
        c.adicionarProduto(e);

        boolean removido = c.removerProduto(999);
        assertFalse(removido);
        assertEquals(1, c.getQuantidadeItens());
    }

    @Test
    public void testEsvaziarCarrinho() {
        Carrinho c = new Carrinho();
        c.adicionarProduto(new Eletronico("Fone", 200.0f, "Áudio", LocalDate.of(2026, 1, 1), 0, 1, 0, "Sony"));
        c.aplicarCupom(new Cupom("DESC10", 10));

        c.esvaziarCarrinho();
        assertEquals(0, c.getQuantidadeItens());
        assertNull(c.getCupomAplicado());
    }

    @Test
    public void testAplicarERemoverCupom() {
        Carrinho c = new Carrinho();
        Cupom cupom = new Cupom("DESC20", 20);
        c.aplicarCupom(cupom);

        assertEquals(cupom, c.getCupomAplicado());

        c.removerCupom();
        assertNull(c.getCupomAplicado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAplicarCupomNulo() {
        Carrinho c = new Carrinho();
        c.aplicarCupom(null);
    }

    @Test
    public void testCalcularSubtotalETotalFinalSemCupom() {
        Carrinho c = new Carrinho();
        // Eletronico: 1000 + 10% (12 meses) = 1100
        Eletronico e = new Eletronico("Notebook", 1000.0f, "Informática", LocalDate.of(2026, 1, 1), 0, 1, 12, "Dell");
        // Vestuario: 100 + 5% (GG) = 105
        Vestuario v = new Vestuario("Camisa", 100.0f, "Vestuário", LocalDate.of(2026, 1, 1), 0, 2, "GG", "Algodão");

        c.adicionarProduto(e);
        c.adicionarProduto(v);

        assertEquals(1205.0f, c.calcularSubtotal(), 0.01f);
        assertEquals(0.0f, c.calcularDescontoCupom(), 0.01f);
        assertEquals(1205.0f, c.calcularTotalFinal(), 0.01f);
    }

    @Test
    public void testCalcularTotalFinalComCupom() {
        Carrinho c = new Carrinho();
        Vestuario v = new Vestuario("Camisa", 100.0f, "Vestuário", LocalDate.of(2026, 1, 1), 0, 1, "M", "Algodão"); // 100.0
        c.adicionarProduto(v);

        Cupom cupom = new Cupom("DESC15", 15);
        c.aplicarCupom(cupom);

        assertEquals(100.0f, c.calcularSubtotal(), 0.01f);
        assertEquals(15.0f, c.calcularDescontoCupom(), 0.01f);
        assertEquals(85.0f, c.calcularTotalFinal(), 0.01f);
    }

    @Test
    public void testCalcularDescontoCupomCarrinhoVazio() {
        Carrinho c = new Carrinho();
        Cupom cupom = new Cupom("DESC50", 50);
        c.aplicarCupom(cupom);

        assertEquals(0.0f, c.calcularDescontoCupom(), 0.01f);
        assertEquals(0.0f, c.calcularTotalFinal(), 0.01f);
    }

    @Test
    public void testExibirCarrinhoNaoLancaExcecao() {
        Carrinho c = new Carrinho();
        c.exibirCarrinho(); // Vazio

        c.adicionarProduto(new Vestuario("Camisa", 100.0f, "Vestuário", LocalDate.of(2026, 1, 1), 0, 1, "M", "Algodão"));
        c.aplicarCupom(new Cupom("DESC10", 10));
        c.exibirCarrinho(); // Com itens e cupom
    }
}
