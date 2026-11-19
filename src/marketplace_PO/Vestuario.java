package marketplace_PO;


// Aluno 2/3 (Carlos) -> implementar as subclasses de acordo com suas peculiaridades
// Atenção especial para Vestuário -> Variações de tamanho (P, M, G e GG)
// Aparentemente, os preços devem mudar conforme o tamanho das roupas.

// diferenca dele: preco varia por tamanho e tecido, e NAO tem garantia
public class Vestuario extends Produto {

    private String tamanho; // P, M, G ou GG
    private String tecido;

    public Vestuario(String nome, float preco, String departamento, String data_fabricacao,
                     int desconto, int cod_produto,
                     String tamanho, String tecido) {
        super(nome, preco, departamento, data_fabricacao, desconto, cod_produto);
        setTamanho(tamanho);
        setTecido(tecido);
    }

    // sobrescreve o da mae polimorfismo
    // GG = +5% (mais tecido), seda ou linho = +10% (tecido premium), os dois somam
    @Override
    public float calcularPrecoFinal() {
        float adicional = 0f;

        if (tamanho.equals("GG")) {
            adicional += 0.05f;
        }

        String t = tecido.toLowerCase();
        if (t.equals("seda") || t.equals("linho")) {
            adicional += 0.10f;
        }

        return getPreco() * (1 + adicional);
    }

    public String getTamanho() {
        return tamanho;
    }

    // so aceita P, M, G ou GG guarda sempre em maiusculo
    public final void setTamanho(String tamanho) {
        if (tamanho == null || tamanho.trim().isEmpty()) {
            throw new IllegalArgumentException("Tamanho não pode ser nulo ou vazio.");
        }
        String t = tamanho.trim().toUpperCase();
        if (!t.equals("P") && !t.equals("M") && !t.equals("G") && !t.equals("GG")) {
            throw new IllegalArgumentException("Tamanho inválido. Use P, M, G ou GG.");
        }
        this.tamanho = t;
    }

    public String getTecido() {
        return tecido;
    }

    // nao aceita nulo nem vazio
    public final void setTecido(String tecido) {
        if (tecido == null || tecido.trim().isEmpty()) {
            throw new IllegalArgumentException("Tecido não pode ser nulo ou vazio.");
        }
        this.tecido = tecido.trim();
    }
}