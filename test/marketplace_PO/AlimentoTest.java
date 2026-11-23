package marketplace_PO;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class AlimentoTest {

    @Test
    public void testCriacaoAlimentoValido() {
        LocalDate futuro = LocalDate.now().plusDays(20);
        Alimento a = new Alimento("Arroz", 25.0f, "Grãos", LocalDate.of(2026, 1, 1), 0, 10, futuro);
        assertEquals("Arroz", a.getNome());
        assertEquals(25.0f, a.getPreco(), 0.001f);
        assertEquals("Grãos", a.getDepartamento());
        assertEquals(LocalDate.of(2026, 1, 1), a.getData_fab());
        assertEquals(10, a.getCod_prod());
        assertEquals(futuro, a.getDataValidade());
        assertFalse(a.estaVencido());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataValidadeNula() {
        new Alimento("Feijão", 10.0f, "Grãos", LocalDate.of(2026, 1, 1), 0, 11, null);
    }

    @Test
    public void testAlimentoVencido() {
        LocalDate passado = LocalDate.now().minusDays(1);
        Alimento a = new Alimento("Leite", 5.0f, "Laticínios", LocalDate.of(2026, 1, 1), 0, 12, passado);
        assertTrue(a.estaVencido());
    }

    @Test(expected = IllegalStateException.class)
    public void testCalcularPrecoFinalAlimentoVencidoLancaExcecao() {
        LocalDate passado = LocalDate.now().minusDays(2);
        Alimento a = new Alimento("Iogurte", 7.0f, "Laticínios", LocalDate.of(2026, 1, 1), 0, 13, passado);
        a.calcularPrecoFinal();
    }

    @Test
    public void testDescontosProgressivosPorValidade() {
        // Mais de 30 dias: sem desconto (0%)
        Alimento aMais30 = new Alimento("Macarrão", 10.0f, "Massas", LocalDate.of(2026, 1, 1), 0, 14, LocalDate.now().plusDays(40));
        assertEquals(0.0f, aMais30.percentualDescontoValidade(), 0.001f);
        assertEquals(10.0f, aMais30.calcularPrecoFinal(), 0.001f);

        // Até 30 dias (ex: 20 dias): 10%
        Alimento a20 = new Alimento("Biscoito", 10.0f, "Doces", LocalDate.of(2026, 1, 1), 0, 15, LocalDate.now().plusDays(20));
        assertEquals(0.10f, a20.percentualDescontoValidade(), 0.001f);
        assertEquals(9.0f, a20.calcularPrecoFinal(), 0.001f);

        // Até 15 dias (ex: 10 dias): 20%
        Alimento a10 = new Alimento("Queijo", 10.0f, "Laticínios", LocalDate.of(2026, 1, 1), 0, 16, LocalDate.now().plusDays(10));
        assertEquals(0.20f, a10.percentualDescontoValidade(), 0.001f);
        assertEquals(8.0f, a10.calcularPrecoFinal(), 0.001f);

        // Até 7 dias (ex: 5 dias): 30%
        Alimento a5 = new Alimento("Presunto", 10.0f, "Frios", LocalDate.of(2026, 1, 1), 0, 17, LocalDate.now().plusDays(5));
        assertEquals(0.30f, a5.percentualDescontoValidade(), 0.001f);
        assertEquals(7.0f, a5.calcularPrecoFinal(), 0.001f);

        // Até 3 dias (ex: 2 dias): 50%
        Alimento a2 = new Alimento("Pão", 10.0f, "Padaria", LocalDate.of(2026, 1, 1), 0, 18, LocalDate.now().plusDays(2));
        assertEquals(0.50f, a2.percentualDescontoValidade(), 0.001f);
        assertEquals(5.0f, a2.calcularPrecoFinal(), 0.001f);

        // Vence hoje (0 dias): <= 3 dias => 50%
        Alimento aHoje = new Alimento("Salada", 10.0f, "Hortifruti", LocalDate.of(2026, 1, 1), 0, 19, LocalDate.now());
        assertEquals(0.50f, aHoje.percentualDescontoValidade(), 0.001f);
        assertEquals(5.0f, aHoje.calcularPrecoFinal(), 0.001f);
    }
}
