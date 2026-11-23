package marketplace_PO;

import java.time.LocalDate;

// obs: foi corrigida (nomes dos atributos batendo, cupom saiu e virou a classe Cupom)
// todas as validacoes rejeitam nulo,vazio,negativo e lancam IllegalArgumentException
public abstract class Produto implements Promocao {

    private String nome;
    private float preco;
    private String departamento;
    private LocalDate data_fab;
    protected int desconto;   // protected pras filhas poderem acessar
    protected int cod_prod;

    // construtor chama os setters, assim ja valida na criacao do objeto
    public Produto(String nome, float preco, String departamento, LocalDate data_fab,
                   int desconto, int cod_prod) {
        setNome(nome);
        setPreco(preco);
        setDepartamento(departamento);
        setData_fab(data_fab);
        setDesconto(desconto);
        setCod_prod(cod_prod);
    }

    // cada filha implementa do seu jeito (polimorfismo)
    public abstract float calcularPrecoFinal();

    // da interface Promocao
    // isso MUDA o preco do produto, nao usar pro cupom, cupom é calculado no carrinho
    //igor tem que implementar isso dentro do carrinho n é só copiar e colar
    @Override
    public void aplicarDesconto(double percentual) {
        if (percentual <= 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 1 e 100.");
        }
        this.preco -= this.preco * (float) (percentual / 100.0);
    }


    // getters e setters
    // os setters sao final pq sao chamados no construtor
    // (se uma filha sobrescrevesse ia rodar antes da hora e burlar a validacao)

    public String getNome() {
        return nome;
    }

    // nao aceita nulo nem vazio
    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public float getPreco() {
        return preco;
    }

    // nao aceita negativo
    public final void setPreco(float preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public String getDepartamento() {
        return departamento;
    }

    public final void setDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Departamento não pode ser nulo ou vazio.");
        }
        this.departamento = departamento.trim();
    }

    public LocalDate getData_fab() {
        return data_fab;
    }

    public final void setData_fab(LocalDate data_fab) {
        LocalDate hoje = LocalDate.now();
        if (data_fab == null) {
            throw new IllegalArgumentException("Data de fabricação não pode ser nula.");
        }
        if(data_fab.isAfter(hoje)) {
            throw new IllegalArgumentException("O produto não pode ser sido fabricado depois de hoje.");
        }
        this.data_fab = data_fab;
    }

    public int getDesconto() {
        return desconto;
    }

    // percentual, entao so de 0 a 100
    public final void setDesconto(int desconto) {
        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100.");
        }
        this.desconto = desconto;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    // codigo tem q ser maior q zero
    public final void setCod_prod(int cod_prod) {
        if (cod_prod <= 0) {
            throw new IllegalArgumentException("Código do produto deve ser positivo.");
        }
        this.cod_prod = cod_prod;
    }
}
