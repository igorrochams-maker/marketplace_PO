package marketplace_PO;

import java.time.LocalDate;

// Aluno 2/3 (Carlos) -> implementar as subclasses de acordo com suas peculiaridades
// Atenção especial para Eletrônicos -> Taxa de Garantia estendida
// Ao pedir um eletrônico, deve-se ter a opção de escolher a garantia estendida que vai somar no valor também

// diferenca dele: soma taxa de garantia estendida no preco
public class Eletronico extends Produto {

    private int mesesGarantia;
    private String marca;

    // 10% do preco a cada 12 meses de garantia
    private static final float TAXA_GARANTIA_ANUAL = 0.10f;

    // repassa o que e do Produto pro super e valida o resto nos setters
    public Eletronico(String nome, float preco, String departamento, LocalDate data_fabricacao,
                      int desconto, int cod_produto,
                      int mesesGarantia, String marca) {
        super(nome, preco, departamento, data_fabricacao, desconto, cod_produto);
        setMesesGarantia(mesesGarantia);
        setMarca(marca);
    }

    // sobrescreve o da mae polimorfismo: usa a garantia do proprio produto
    @Override
    public float calcularPrecoFinal() {
        return calcularPrecoFinal(this.mesesGarantia);
    }

    // sobrecarga: mesmo nome mas recebe os meses, serve pra simular outra garantia
    // preco final = preco + (preco * 10% * anos de garantia)
    public float calcularPrecoFinal(int meses) {
        if (meses < 0) {
            throw new IllegalArgumentException("Meses de garantia não pode ser negativo.");
        }
        float taxa = getPreco() * TAXA_GARANTIA_ANUAL * (meses / 12.0f);
        return getPreco() + taxa;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    // nao aceita negativo / meses garantia = 0 -> sem garantia extra
    public final void setMesesGarantia(int mesesGarantia) {
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("Meses de garantia não pode ser negativo.");
        }
        this.mesesGarantia = mesesGarantia;
    }

    public String getMarca() {
        return marca;
    }

    // nao aceita nulo nem vazio
    public final void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser nula ou vazia.");
        }
        this.marca = marca;
    }
}
