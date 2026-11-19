package marketplace_PO;

// interface de validacao, quem implementa Cupom sabe dizer se um codigo e valido
public interface Validavel {

    // true se o codigo digitado bate com o do cupom
    boolean validarCupom(String codigo);
}