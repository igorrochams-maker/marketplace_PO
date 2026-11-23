package marketplace_PO;

// Aluno 2/3 (Carlos) -> implementar as subclasses de acordo com suas peculiaridades
// Alimentos devem ter atenção especial com: data de validade
// Segundo o pedido da atividade, alimentos devem ficar mais baratos quanto mais perto ele estiver de vencer
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


// diferenca dele: tem validade, nao vende vencido e da desconto automatico qnd ta perto de vencer
public class Alimento extends Produto {

    private LocalDate dataValidade;

    public Alimento(String nome, float preco, String departamento, LocalDate data_fabricacao,
                    int desconto, int cod_produto,
                    LocalDate dataValidade) {
        super(nome, preco, departamento, data_fabricacao, desconto, cod_produto);
        setDataValidade(dataValidade);
    }

    // true se a validade ja passou
    public boolean estaVencido() {
        return LocalDate.now().isAfter(dataValidade);
    }

    // quantos dias faltam pra vencer
    public long diasParaVencer() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataValidade);
    }

    // desconto progressivo: quanto mais perto de vencer, maior o desconto
    // ate 30 dias = 10%, ate 15 = 20%, ate 7 = 30%, ate 3 = 50%
    public float percentualDescontoValidade() {
        long dias = diasParaVencer();
        if (dias <= 3) return 0.50f;
        if (dias <= 7) return 0.30f;
        if (dias <= 15) return 0.20f;
        if (dias <= 30) return 0.10f;
        return 0f;
    }

    // sobrescreve o da mae -> Polimorfismo
    // vencido lanca excecao o menu tem q tratar com try/catch, senao aplica o desconto da validade
    @Override
    public float calcularPrecoFinal() {
        if (estaVencido()) {
            throw new IllegalStateException("O produto a seguir está vencido não pode ser vendido: " + getNome());
        }
        return getPreco() * (1 - percentualDescontoValidade());
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    // nao aceita nulo
    public final void setDataValidade(LocalDate dataValidade) {
        LocalDate hoje = LocalDate.now();
        if (dataValidade == null) {
            throw new IllegalArgumentException("Data de validade não pode ser nula.");
        }
        if(dataValidade.isBefore(hoje)) {
            throw new IllegalArgumentException("Você não pode cadastrar um produto já vencido!");
        }
        this.dataValidade = dataValidade;
    }
}
