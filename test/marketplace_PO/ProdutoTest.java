package marketplace_PO;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class ProdutoTest {

    // Subclasse concreta simples para testar métodos e validações da classe base abstrata Produto
    private static class ProdutoImpl extends Produto {
        public ProdutoImpl(String nome, float preco, String departamento, LocalDate data_fab, int desconto, int cod_prod) {
            super(nome, preco, departamento, data_fab, desconto, cod_prod);
        }

        @Override
        public float calcularPrecoFinal() {
            return getPreco();
        }
    }

    @Test
    public void testCriacaoProdutoValido() {
        Produto p = new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 100);
        assertEquals("Mouse", p.getNome());
        assertEquals(50.0f, p.getPreco(), 0.001f);
        assertEquals("Periféricos", p.getDepartamento());
        assertEquals(LocalDate.of(2026, 1, 1), p.getData_fab());
        assertEquals(0, p.getDesconto());
        assertEquals(100, p.getCod_prod());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeNulo() {
        new ProdutoImpl(null, 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeVazio() {
        new ProdutoImpl("   ", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 102);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrecoNegativo() {
        new ProdutoImpl("Mouse", -10.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 103);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepartamentoNulo() {
        new ProdutoImpl("Mouse", 50.0f, null, LocalDate.of(2026, 1, 1), 0, 104);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepartamentoVazio() {
        new ProdutoImpl("Mouse", 50.0f, "   ", LocalDate.of(2026, 1, 1), 0, 105);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataFabNula() {
        new ProdutoImpl("Mouse", 50.0f, "Periféricos", null, 0, 106);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataFabNula() {
        Produto p = new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 107);
        p.setData_fab(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescontoNegativo() {
        new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), -1, 108);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescontoMaiorQue100() {
        new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 101, 109);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCodigoProdutoZero() {
        new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCodigoProdutoNegativo() {
        new ProdutoImpl("Mouse", 50.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, -5);
    }

    @Test
    public void testAplicarDescontoPromocao() {
        Produto p = new ProdutoImpl("Teclado", 100.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 110);
        p.aplicarDesconto(20.0);
        assertEquals(80.0f, p.getPreco(), 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAplicarDescontoZeroOuNegativo() {
        Produto p = new ProdutoImpl("Teclado", 100.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 111);
        p.aplicarDesconto(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAplicarDescontoMaiorQue100() {
        Produto p = new ProdutoImpl("Teclado", 100.0f, "Periféricos", LocalDate.of(2026, 1, 1), 0, 112);
        p.aplicarDesconto(105);
    }
}
