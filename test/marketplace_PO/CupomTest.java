package marketplace_PO;

import org.junit.Test;
import static org.junit.Assert.*;

public class CupomTest {

    @Test
    public void testCriarCupomValido() {
        Cupom c = new Cupom("DESCONTO10", 10);
        assertEquals("DESCONTO10", c.getCodigo());
        assertEquals(10, c.getPercentual());
    }

    @Test
    public void testCriarCupomComEspacosETrailing() {
        Cupom c = new Cupom("  blackfriday  ", 50);
        assertEquals("BLACKFRIDAY", c.getCodigo());
        assertEquals(50, c.getPercentual());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCupomCodigoNulo() {
        new Cupom(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCupomCodigoVazio() {
        new Cupom("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCupomPercentualMenorQueUm() {
        new Cupom("CUPOM0", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCupomPercentualNegativo() {
        new Cupom("CUPOMNEG", -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCupomPercentualMaiorQue100() {
        new Cupom("CUPOM101", 101);
    }

    @Test
    public void testCupomPercentualLimites() {
        Cupom c1 = new Cupom("MIN", 1);
        assertEquals(1, c1.getPercentual());

        Cupom c100 = new Cupom("MAX", 100);
        assertEquals(100, c100.getPercentual());
    }

    @Test
    public void testValidarCupom() {
        Cupom c = new Cupom("PROMO20", 20);
        assertTrue(c.validarCupom("PROMO20"));
        assertTrue(c.validarCupom("promo20"));
        assertTrue(c.validarCupom("  PROMO20  "));
        assertFalse(c.validarCupom("PROMO10"));
        assertFalse(c.validarCupom(null));
        assertFalse(c.validarCupom(""));
    }
}
