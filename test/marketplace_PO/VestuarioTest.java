package marketplace_PO;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class VestuarioTest {

    @Test
    public void testCriacaoVestuarioValido() {
        Vestuario v = new Vestuario("Camiseta", 50.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 301, "M", "Algodão");
        assertEquals("Camiseta", v.getNome());
        assertEquals(50.0f, v.getPreco(), 0.001f);
        assertEquals("M", v.getTamanho());
        assertEquals("Algodão", v.getTecido());
    }

    @Test
    public void testTamanhoCaseInsensitive() {
        Vestuario v = new Vestuario("Calça", 80.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 302, "gg", "Jeans");
        assertEquals("GG", v.getTamanho());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTamanhoInvalido() {
        new Vestuario("Calça", 80.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 303, "XG", "Jeans");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTamanhoNulo() {
        new Vestuario("Calça", 80.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 304, null, "Jeans");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTecidoNulo() {
        new Vestuario("Calça", 80.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 305, "P", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTecidoVazio() {
        new Vestuario("Calça", 80.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 306, "P", "   ");
    }

    @Test
    public void testCalcularPrecoFinalPadrao() {
        // Tamanho M, Algodão: nenhum adicional
        Vestuario v = new Vestuario("Camisa", 100.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 307, "M", "algodao");
        assertEquals(100.0f, v.calcularPrecoFinal(), 0.001f);
    }

    @Test
    public void testCalcularPrecoFinalTamanhoGG() {
        // Tamanho GG (+5%), Algodão: 100 * 1.05 = 105
        Vestuario v = new Vestuario("Camisa", 100.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 308, "GG", "algodao");
        assertEquals(105.0f, v.calcularPrecoFinal(), 0.001f);
    }

    @Test
    public void testCalcularPrecoFinalTecidoSeda() {
        // Tamanho P (+0%), Seda (+10%): 100 * 1.10 = 110
        Vestuario v = new Vestuario("Camisa", 100.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 309, "P", "seda");
        assertEquals(110.0f, v.calcularPrecoFinal(), 0.001f);
    }

    @Test
    public void testCalcularPrecoFinalTecidoLinho() {
        // Tamanho G (+0%), Linho (+10%): 100 * 1.10 = 110
        Vestuario v = new Vestuario("Camisa", 100.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 310, "G", "Linho");
        assertEquals(110.0f, v.calcularPrecoFinal(), 0.001f);
    }

    @Test
    public void testCalcularPrecoFinalTamanhoGGEComTecidoPremium() {
        // Tamanho GG (+5%) + Linho (+10%) = +15%: 100 * 1.15 = 115
        Vestuario v = new Vestuario("Camisa", 100.0f, "Roupas", LocalDate.of(2026, 2, 1), 0, 311, "GG", "Linho");
        assertEquals(115.0f, v.calcularPrecoFinal(), 0.001f);
    }
}
