package marketplace_PO;

import org.junit.Test;
import static org.junit.Assert.*;

public class EletronicoTest {

    @Test
    public void testCriacaoEletronicoValido() {
        Eletronico e = new Eletronico("Smartphone", 1500.0f, "Telefonia", "10/05/2026", 0, 201, 12, "Samsung");
        assertEquals("Smartphone", e.getNome());
        assertEquals(1500.0f, e.getPreco(), 0.001f);
        assertEquals("Telefonia", e.getDepartamento());
        assertEquals("10/05/2026", e.getData_fab());
        assertEquals(201, e.getCod_prod());
        assertEquals(12, e.getMesesGarantia());
        assertEquals("Samsung", e.getMarca());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGarantiaNegativaNoConstrutor() {
        new Eletronico("TV", 2000.0f, "Vídeo", "10/05/2026", 0, 202, -1, "LG");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGarantiaNegativaNoSetter() {
        Eletronico e = new Eletronico("TV", 2000.0f, "Vídeo", "10/05/2026", 0, 203, 0, "LG");
        e.setMesesGarantia(-6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarcaNula() {
        new Eletronico("TV", 2000.0f, "Vídeo", "10/05/2026", 0, 204, 12, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMarcaVazia() {
        new Eletronico("TV", 2000.0f, "Vídeo", "10/05/2026", 0, 205, 12, "   ");
    }

    @Test
    public void testCalculoPrecoFinalComGarantiaPadrao() {
        // Preço 1000, 12 meses: taxa = 1000 * 0.10 * (12/12) = 100 -> Total = 1100
        Eletronico e = new Eletronico("Notebook", 1000.0f, "Informática", "01/01/2026", 0, 206, 12, "Dell");
        assertEquals(1100.0f, e.calcularPrecoFinal(), 0.001f);

        // Preço 1000, 0 meses: sem taxa extra -> Total = 1000
        Eletronico eZero = new Eletronico("Mouse", 100.0f, "Periféricos", "01/01/2026", 0, 207, 0, "Logitech");
        assertEquals(100.0f, eZero.calcularPrecoFinal(), 0.001f);

        // Preço 1000, 24 meses: taxa = 1000 * 0.10 * 2 = 200 -> Total = 1200
        Eletronico e24 = new Eletronico("Monitor", 1000.0f, "Vídeo", "01/01/2026", 0, 208, 24, "AOC");
        assertEquals(1200.0f, e24.calcularPrecoFinal(), 0.001f);
    }

    @Test
    public void testCalculoPrecoFinalComSobrecargaMeses() {
        Eletronico e = new Eletronico("Notebook", 1000.0f, "Informática", "01/01/2026", 0, 209, 12, "Dell");
        // Simulando 6 meses: taxa = 1000 * 0.10 * 0.5 = 50 -> Total = 1050
        assertEquals(1050.0f, e.calcularPrecoFinal(6), 0.001f);
        // Simulando 0 meses: Total = 1000
        assertEquals(1000.0f, e.calcularPrecoFinal(0), 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculoPrecoFinalSobrecargaMesesNegativo() {
        Eletronico e = new Eletronico("Notebook", 1000.0f, "Informática", "01/01/2026", 0, 210, 12, "Dell");
        e.calcularPrecoFinal(-3);
    }
}
