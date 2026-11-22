package marketplace_PO;

// Algum aluno -> Criar uma classe focada para o tratamento de cupons e tal
// Cupons devem ter:
// Categoria da qual eles se adequam
// Código do cupom

// cupom como classe propria, nao fica mais dentro do produto
// o carrinho guarda o cupom aplicado e usa o percentual no total
public class Cupom implements Validavel {

    private String codigo;
    private int percentual; // 1 a 100

    // usa os setters pra ja validar na criacao
    public Cupom(String codigo, int percentual) {
        setCodigo(codigo);
        setPercentual(percentual);
    }

    // da da interface Validavel
    // ignora maiuscula,minuscula e espaco nas pontas
    @Override
    public boolean validarCupom(String codigoDigitado) {
        return codigoDigitado != null && codigo.equalsIgnoreCase(codigoDigitado.trim());
    }

    public String getCodigo() {
        return codigo;
    }

    // rejeita nulo e vazio, guarda sempre em maiusculo
    // final pq e chamado no construtor tira o aviso do netbeans
    public final void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do cupom não pode ser nulo ou vazio.");
        }
        this.codigo = codigo.trim().toUpperCase();
    }

    public int getPercentual() {
        return percentual;
    }

    // percentual tem q ser de 1 a 100
    public final void setPercentual(int percentual) {
        if (percentual <= 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual do cupom deve estar entre 1 e 100.");
        }
        this.percentual = percentual;
    }
}
