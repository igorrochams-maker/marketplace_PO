package marketplace_PO;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetodosMenu {

    private ArrayList<Produto> listaProdutos;
    private ArrayList<Cupom> listaCupons;
    private Carrinho carrinho;

    public MetodosMenu() {
        this.listaProdutos = new ArrayList<Produto>();
        this.listaCupons = new ArrayList<Cupom>();
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

    public void removerProdutoLista(
        int codigo
    ) throws InvalidAlgorithmParameterException {
        if (codigo > 0) {
            for (int i = 0; i < this.listaProdutos.size(); i++) {
                if (this.listaProdutos.get(i).getCod_prod() == codigo) {
                    this.listaProdutos.remove(i);
                }
            }
        } else {
            throw new InvalidAlgorithmParameterException(
                "Não existem produtos com código '0' ou menor."
            );
        }
    }

    public void addCupomLista(Cupom cupomNovo) {
        if (cupomNovo != null) {
            listaCupons.add(cupomNovo);
        } else {
            throw new NullPointerException("O novo cupom não pode ser nulo.");
        }
    }

    public void removerCupomLista(
        String codigo
    ) throws InvalidAlgorithmParameterException {
        if (codigo.length() > 0) {
            for (int i = 0; i < this.listaProdutos.size(); i++) {
                if (this.listaCupons.get(i).getCodigo().equals(codigo)) {
                    this.listaCupons.remove(i);
                }
            }
        } else {
            throw new InvalidAlgorithmParameterException(
                "Não existem cupons com o código informado."
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
    }

    // funcaozinha pra mostrar a lista de cupons
    private void mostrarCupons() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("--------Lista de Cupons---------");
        if (this.listaProdutos.size() < 1) {
            System.out.println("A lista não possui cupons!");
        } else {
            for (int i = 0; i < this.listaProdutos.size(); i++) {
                Cupom atual = this.listaCupons.get(i);
                System.out.println(
                    "Cod. do Cupom: " +
                        atual.getCodigo() +
                        " | Desconto: " +
                        atual.getPercentual() +
                        "%"
                );
            }
        }
        System.out.println("----------------------------------");
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
        System.out.println("( 2 ) Adicionar produto ao carrinho");
        System.out.println("( 3 ) Checar carrinho de compras");
        System.out.println("( 4 ) Mostrar lista de cupons");
        System.out.println("( 5 ) Acessar a área da gestão");
        System.out.println("( 6 ) Sair");
        System.out.println("----------------------------------");

        // instânciando o scanner da resposta do usuário
        Scanner scanner = new Scanner(System.in);

        int resposta = scanner.nextInt();
        switch (resposta) {
            case 1 -> {
                // printar lista de produtos
                mostrarLista();
                System.out.println(
                    "Pressione qualquer tecla para voltar para o menu..."
                );
                scanner.nextLine();
                menuPadrao();
                break;
            }
            case 2 -> {
                // add produto no carrinho
                mostrarLista();
                // logica pro usuario escolher um produto e colocar no carrinho
                System.out.println("Digite o código do produto que deseja adicionar no carrinho:");
                int codProduto = scanner.nextInt();
                // checar aonde o código bate na lista de produtos
                for(int i = 0; i < this.listaProdutos.size(); i++) {
                    Produto atual = this.listaProdutos.get(i);
                    if(atual.getCod_prod() == codProduto) {
                        // adicionar no carrinho
                    }
                }
            }
            case 3 -> {
                // printar o carrinho de compras
                break;
            }
            case 4 -> {
                // printar a lista de cupons disponíveis
                mostrarCupons();
                System.out.println(
                    "Pressione qualquer tecla para voltar para o menu..."
                );
                scanner.nextLine();
                menuPadrao();
                break;
            }
            case 5 -> {
                // mostrar menu de gestão
                menuGestao();
                break;
            }
            case 6 -> {
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
                System.out.println(
                    "Pressione qualquer tecla para voltar para o menu..."
                );
                scanner.nextLine();
                menuPadrao();
                break;
            }
            case 2 -> {
                // adicionar produto
                scanner.nextLine();
                System.out.println("Selecione a categoria do produto:");
                System.out.println("( 1 ) Eletrônico");
                System.out.println("( 2 ) Vestuário");
                System.out.println("( 3 ) Alimento");
                int categoria = scanner.nextInt();
                limparTela();
                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();
                System.out.println("Digite o preço do produto");
                float preco = scanner.nextFloat();
                System.out.println("Digite o departamento (subcategoria) do produto:");
                String departamento = scanner.nextLine();
                System.out.println("Informe a data de fabricação do produto no formato 'dia/mes/ano':");
                String dataFabricacao = scanner.nextLine();
                System.out.println("Informe o código do produto:");
                int codProduto = scanner.nextInt();

                if(categoria == 1) {
                    System.out.println("Digite quantos meses de garantia o produto irá possuir:");
                    int mesesGarantia = scanner.nextInt();
                    System.out.println("Digite a marca do eletrônico:");
                    String marca = scanner.nextLine();
                    Eletronico novoEletronico = new Eletronico(nome, preco, departamento, dataFabricacao, 0, codProduto, mesesGarantia, marca);
                    this.listaProdutos.add(novoEletronico);
                    menuGestao();
                } else if (categoria == 2) {
                    System.out.println("Digite o tamanho da roupa (P, M, G ou GG):");
                    String tamanhoRoupa = scanner.nextLine();
                    System.out.println("Informe o tecido que compõe a roupa: ");
                    String tecidoRoupa = scanner.nextLine();
                    Vestuario novoVestuario = new Vestuario(nome, preco, departamento, dataFabricacao, 0, codProduto, tamanhoRoupa, tecidoRoupa);
                    this.listaProdutos.add(novoVestuario);
                    menuGestao();
                } else if (categoria == 3) {
                    System.out.println("Informe a data de validade do alimento no formato 'ano/mes/dia':");
                    String dataValidadeString = scanner.nextLine();
                    LocalDate dataValidade = LocalDate.parse(dataValidadeString);
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataValidade.format(formatador);
                    Alimento novoAlimento = new Alimento(nome, preco, departamento, dataFabricacao, 0, codProduto, dataValidade);
                    this.listaProdutos.add(novoAlimento);
                    menuGestao();
                } else {
                    System.out.println("A categoria informada não existe!");
                    System.out.println("----------------------------------");
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    scanner.nextLine();
                    menuPadrao();
                }
                break;
            }
            case 3 -> {
                // tela pra excluir um produto
                mostrarLista();
                // pedir o código do produto que deseja excluir
                break;
            }
            case 4 -> {
                // tela pra adicionar cupom
                // pedir as informacoes do cupom e adicionar (talvez checar se ja nao existe um igual)
                break;
            }
            case 5 -> {
                // tela pra excluir cupom
                mostrarCupons();
                // digitar o nome do cupom que deseja excluir e procurar se existe e dai apagar
                break;
            }
            case 6 -> {
                // tela pra ver os cupons
                mostrarCupons();
                System.out.println(
                    "Pressione qualquer tecla para voltar para o menu..."
                );
                scanner.nextLine();
                menuPadrao();
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
                try {
                    System.out.println("Você digitou um número inválido.");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
                menuGestao();
                break;
            }
        }
    }
}
