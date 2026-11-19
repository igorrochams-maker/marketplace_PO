package marketplace_PO;

// interface de promocao, quem implementa Produto pode receber desconto
// so tem o aplicarDesconto, o cupom ficou na interface Validavel
public interface Promocao {

    // recebe o percentual 
    void aplicarDesconto(double percentual);
}