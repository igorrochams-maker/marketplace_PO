package marketplace_PO;

// Aluno 1 (Maycon) -> vai construir a classe abstrata principal (produto) e implementar interfaces (resumindo vai montar a base do programa).

// 1 -> Primeiro monta a classe Produto que abstrai tudo que as categorias tem em comum
public abstract class Produto implements Promocao {
    private String nome;
    private float preco;
    private String departamento;
    private String data_fabricacao;
    protected int desconto;
    protected String cupom;
    protected int codigo_produto;

// Construtor
public Produto (String nome, float preco, String departamento, String data_fabricacao, int desconto, String cupom, int cod_produto) {
    this.nome= nome;
    this.preco= preco;
    this.departamento= departamento;
    this.data_fabricacao= data_fabricacao;
    this.desconto= desconto;
    this.cupom= cupom;
    this.codigo_produto= cod_produto;
}

public abstract float calcularPrecoFinal();

    // implementação da interface Promocao
    @Override
    public void aplicarDesconto(double percentual) {
        if (percentual > 0 && percentual <= 100) {
            this.preco -= this.preco * (float)(percentual / 100.0);
        }
    }

    @Override
    public boolean validarCupom(String cod_cupom) {
        return this.cupom != null && this.cupom.equals(cod_cupom);
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
    }
    }

    public float getPreco() {
        return preco;
    }


    public void setPreco(float preco) {
        if (preco >= 0) {
        this.preco = preco;
    }
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String depto) {
        if (depto != null) {
        this.departamento = depto;
    }
    }

    public String getData_fab() {
        return data_fabricacao;
    }

    public void setData_fab(String data_fab) {
        if (nome != null)
        this.data_fabricacao = data_fab;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        if (desconto >= 0) {
        this.desconto = desconto;
    }
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        if (cupom != null){
        this.cupom = cupom;
    }
    }

    public int getCod_prod() {
        return codigo_produto;
    }

    public void setCod_prod(int cod_prod) {
        this.codigo_produto = cod_prod;
    }
}
