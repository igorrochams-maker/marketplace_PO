package marketplace_PO;

// Aluno 1 (Maycon) -> Criação da interface "Promocao" que traz métodos relacionados a mecânica de cupons
public interface Promocao {
    void aplicarDesconto(double percentual);
    
    boolean validarCupom(String cod_cupom);
}
