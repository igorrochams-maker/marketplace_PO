
package marketplace_PO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
    private final ArrayList<Produto> produtos;
    private Cupom cupomAplicado;
    
    public Carrinho() {
        this.produtos = new ArrayList<>();
        this.cupomAplicado = null;
    }
    
    public void adicionarProduto(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        
        if (produto instanceof Alimento) {
             Alimento alimento = (Alimento) produto;
             if (alimento.estaVencido()){
                 throw new IllegalStateException("O produto " + alimento.getNome() + " está vencido e não pode ser adicionado no carrinho.");
             }
        }
        
        this.produtos.add(produto);
        System.out.println(produto.getNome() + " adicionado ao carrinho!");
    }
    
    public boolean removerProduto(int codProduto){
        for (int i = 0; i < produtos.size(); i++){
            if (produtos.get(i).getCod_prod() == codProduto){
                Produto removido = produtos.remove(i);
                System.out.println(removido.getNome() + "removido do carrinho.");
                
                return true;
            }
        }
        
        System.out.println("Produto com código " + codProduto + "não encontrado no carrinho.");
        return false;
    }
    
    public void esvaziarCarrinho(){
        this.produtos.clear();
        this.cupomAplicado = null;
    }
    
    
    
    public void aplicarCupom(Cupom cupom){
        if(cupom == null){
            throw new IllegalArgumentException("Cupom Inválido!");
        }
        this.cupomAplicado = cupom;
        System.out.println("Cupom " + cupom.getCodigo() + " (" + cupom.getPercentual() + "% OFF, aplicado!");
    }
    
    public void removerCupom(){
        this.cupomAplicado = null;
    }
    
    public float calcularSubtotal(){
        float subtotal = 0.0f;
        
        for(Produto p : produtos) {
            subtotal += p.calcularPrecoFinal();
        }
        return subtotal;
    }
    
    public float calcularDescontoCupom(){
       if (cupomAplicado == null || produtos.isEmpty()){
           return 0.0f;
       }
       
       float baseCalculo = 0.0f;
       
       for(Produto p : produtos) {
           if(isProdutoElegivelParaCupom(p, cupomAplicado)) {
               baseCalculo += p.calcularPrecoFinal();
           }
       }
       
       return baseCalculo * (cupomAplicado.getPercentual()/100.0f);
    }
    
    private boolean isProdutoElegivelParaCupom(Produto produto, Cupom cupom){
        return true;
    }
    
    public float calcularTotalFinal(){
        float total = calcularSubtotal  () - calcularDescontoCupom();
        
        return Math.max(0.0f, total); 
    }
    
     public void exibirCarrinho() {
        System.out.println("\n========== CARRINHO DE COMPRAS ==========");
        if (produtos.isEmpty()) {
            System.out.println("Seu carrinho está vazio!");
            System.out.println("=========================================\n");
            return;
        }
        for (Produto p : produtos) {
            System.out.printf("- [Cód %d] %-20s | Dep: %-12s | Preço: R$ %.2f%n",
                    p.getCod_prod(), p.getNome(), p.getDepartamento(), p.calcularPrecoFinal());
        }
        System.out.println("-----------------------------------------");
        System.out.printf("Subtotal:               R$ %.2f%n", calcularSubtotal());
        
        if (cupomAplicado != null) {
            System.out.printf("Cupom (%s - %d%%):   - R$ %.2f%n",
                    cupomAplicado.getCodigo(), cupomAplicado.getPercentual(), calcularDescontoCupom());
        }
        
        System.out.printf("TOTAL FINAL:            R$ %.2f%n", calcularTotalFinal());
        System.out.println("=========================================\n");
    }
     
    public Cupom getCupomAplicado() {
        return cupomAplicado;
    }
    public int getQuantidadeItens() {
        return produtos.size();
    }
    /**
     * Retorna uma lista somente-leitura para proteger a lista original contra alterações externas não controladas.
     */
    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(this.produtos);
    }
}
