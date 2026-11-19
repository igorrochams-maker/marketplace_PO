package marketplace_PO;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class MetodosMenu {

    private ArrayList<Produto> listaProdutos;

    public MetodosMenu() {
        this.listaProdutos = new ArrayList<Produto>();
    }

    public void addProdutoLista(Produto produtoNovo) {
        if (produtoNovo != null) {
            listaProdutos.add(produtoNovo);
        } else {
            throw new NullPointerException(
                "O novo produto deve não pode ser nulo."
            );
        }
    }

    public void removerProdutoLista(int codigo) throws InvalidAlgorithmParameterException {
        if(codigo > 0) {
            for(int i = 0; i < this.listaProdutos.size(); i++) {
                if(this.listaProdutos.get(i).getCod_prod() == codigo) {
                    this.listaProdutos.remove(i);
                }
            }
        } else {
            throw new InvalidAlgorithmParameterException(
                "Não existem produtos com código '0' ou menor."
            );
        }
    }

    // funçaozinha basics pra limpar a tela
    private static void limparTela() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println(
                "Ocorreu um erro ao tentar limpar a tela: " + e.getMessage()
            );
        }
    }

    // funcaozinha pra mostrar a lista de produtos
    private void mostrarLista() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("--------Lista de Produtos---------");
        if (this.listaProdutos.size() < 1) {
            System.out.println("A lista não possui produtos!");
        } else {
            for (int i = 0; i < this.listaProdutos.size(); i++) {
                Produto atual = this.listaProdutos.get(i);
                System.out.println(
                    "Cod. do Produto: " +
                        atual.getCod_prod() +
                        " | Nome: " +
                        atual.getNome() +
                        " | Preço: " +
                        atual.getPreco()
                );
            }
        }
        System.out.println("----------------------------------");
        System.out.println(
            "Pressione qualquer tecla para voltar para o menu..."
        );
        scanner.nextLine();
        menuPadrao();
    }

    public void menuPadrao() {
        // Aluno 5 (Gustavo) -> vai codar o menu de interação, tratamento de erros e etc (experiência de usuário)
        limparTela();
        System.out.println("----------------------------------");
        System.out.println("Marketplace - TechStore");
        System.out.println(
            "Seja bem vindo!! - Selecione uma opção da nossa loja"
        );
        System.out.println("----------------------------------");
        System.out.println("( 1 ) Ver Lista de Produtos");
        System.out.println("( 2 ) Checar carrinho de compras");
        System.out.println("( 3 ) Mostrar lista de cupons");
        System.out.println("( 4 ) Acessar a área da gestão");
        System.out.println("( 5 ) Sair");
        System.out.println("----------------------------------");

        // instânciando o scanner da resposta do usuário
        Scanner scanner = new Scanner(System.in);

        int resposta = scanner.nextInt();
        switch (resposta) {
            case 1 -> {
                // printar lista de produtos
                mostrarLista();
                break;
            }
            case 2 -> {
                // printar o carrinho de compras
                break;
            }
            case 3 -> {
                // printar a lista de cupons disponíveis
                break;
            }
            case 4 -> {
                // mostrar menu de gestão
                menuGestao();
                break;
            }
            case 5 -> {
                // sai da aplicação
                break;
            }
            default -> {
                try {
                    System.out.println("Você digitou um número inválido.");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
                menuPadrao();
                break;
            }
        }
    }

    public void menuGestao() {
        limparTela();
        System.out.println("----------------------------------");
        System.out.println("Marketplace - TechStore - Gestão");
        System.out.println("Selecione uma opção de gerenciamento");
        System.out.println("----------------------------------");
        System.out.println("( 1 ) Ver Lista de Produtos");
        System.out.println("( 2 ) Adicionar Produto");
        System.out.println("( 3 ) Excluir Produto");
        System.out.println("( 4 ) Adicionar Cupom");
        System.out.println("( 5 ) Excluir Cupom");
        System.out.println("( 6 ) Ver lista de Cupons");
        System.out.println("( 7 ) Voltar para menu do usuário");
        System.out.println("( 8 ) Sair");
        System.out.println("----------------------------------");

        Scanner scanner = new Scanner(System.in);

        int resposta = scanner.nextInt();
        switch (resposta) {
            case 1 -> {
                // printar lista de produtos
                mostrarLista();
                break;
            }
            case 2 -> {
                // tela pra adicionar produto
                break;
            }
            case 3 -> {
                // tela pra excluir um produto
                break;
            }
            case 4 -> {
                // tela pra adicionar cupom
                break;
            }
            case 5 -> {
                // tela pra excluir cupom
                break;
            }
            case 6 -> {
                // tela pra ver os cupons
                break;
            }
            case 7 -> {
                // voltar menu usuario
                menuPadrao();
                break;
            }
            case 8 -> {
                // sair
                break;
            }
            default -> {
                break;
            }
        }
    }
}
