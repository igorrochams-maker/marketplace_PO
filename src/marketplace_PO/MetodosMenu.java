package marketplace_PO;

import java.net.SocketTimeoutException;
import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

public class MetodosMenu {

    private ArrayList<Produto> listaProdutos;
    private ArrayList<Cupom> listaCupons;
    private Carrinho carrinho;
    private Scanner scanner; // instancia única do scanner pra evitar empilhamento
    private boolean sairDoSistema;

    public MetodosMenu() {
        this.listaProdutos = new ArrayList<Produto>();
        this.listaCupons = new ArrayList<Cupom>();
        this.carrinho = new Carrinho();
        this.scanner = new Scanner(System.in);
        this.sairDoSistema = false;
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
                    break;
                }
            }
        } else {
            throw new InvalidAlgorithmParameterException(
                "Não existem produtos com código '0' ou menor."
            );
        }
    }

    public ArrayList<Produto> getListaProdutos() {
        return this.listaProdutos;
    }

    public ArrayList<Cupom> getListaCupons() {
        return this.listaCupons;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
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
        if (codigo != null && codigo.length() > 0) {
            for (int i = 0; i < this.listaCupons.size(); i++) {
                if (this.listaCupons.get(i).getCodigo().equalsIgnoreCase(codigo.trim())) {
                    this.listaCupons.remove(i);
                    return;
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
        System.out.println("----------------------------------");
        System.out.println("--------Lista de Cupons---------");
        if (this.listaCupons.size() < 1) {
            System.out.println("A lista não possui cupons!");
        } else {
            for (int i = 0; i < this.listaCupons.size(); i++) {
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
        // estados pra controle da execução do menu
        this.sairDoSistema = false;
        boolean executando = true;

        while (executando && !this.sairDoSistema) {
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
            System.out.println("( 3 ) Remover produto do carrinho");
            System.out.println("( 4 ) Checar carrinho de compras");
            System.out.println("( 5 ) Mostrar lista de cupons");
            System.out.println("( 6 ) Aplicar cupom");
            System.out.println("( 7 ) Acessar a área da gestão");
            System.out.println("( 8 ) Sair");
            System.out.println("----------------------------------");

            int resposta;
            try {
                resposta = this.scanner.nextInt();
            } catch (InputMismatchException e) {
                this.scanner.nextLine(); // limpa o que digitou
                System.out.println("Digite apenas números.");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                continue;
            }

            switch (resposta) {
                case 1 -> {
                    limparTela();
                    this.scanner.nextLine();
                    // printar lista de produtos
                    mostrarLista();
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 2 -> {
                    limparTela();
                    // add produto no carrinho
                    mostrarLista();
                    // logica pro usuario escolher um produto e colocar no carrinho
                    System.out.println(
                        "Digite o código do produto que deseja adicionar no carrinho:"
                    );

                    int codProduto;
                    try {
                        codProduto = this.scanner.nextInt();
                    } catch (InputMismatchException e) {
                        this.scanner.nextLine();
                        System.out.println("Digite apenas números.");
                        System.out.println("Pressione qualquer tecla para voltar para o menu...");
                        this.scanner.nextLine();
                        break;
                    }

                    this.scanner.nextLine(); // limpa o enter do nextInt
                    boolean achou = false;
                    // checar aonde o código bate na lista de produtos
                    for (int i = 0; i < this.listaProdutos.size(); i++) {
                        Produto atual = this.listaProdutos.get(i);
                        if (atual.getCod_prod() == codProduto) {
                            achou = true;
                            // adicionar no carrinho
                            try {
                                this.carrinho.adicionarProduto(atual);
                                System.out.println("O produto foi adicionado com sucesso!");
                            } catch (IllegalStateException e) {
                                System.out.println("Erro: " + e.getMessage()); // alimento vencido
                            }
                            break;
                        }
                    }

                    if (!achou) {
                        System.out.println("Produto não encontrado!");
                    }
                    System.out.println("----------------------------------");
                    System.out.println("Pressione qualquer tecla para voltar para o menu...");
                    this.scanner.nextLine();
                    break;
                }

                case 3 -> {
                    limparTela();
                    // remover produto do carrinho
                    this.carrinho.exibirCarrinho();
                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");
                    System.out.println(
                        "Digite o Código do produto que deseja remover do carrinho:"
                    );
                    int codigoRemover;
                    try {
                        codigoRemover = this.scanner.nextInt();
                    } catch (InputMismatchException e) {
                        this.scanner.nextLine();
                        System.out.println("Digite apenas números.");
                        System.out.println("Pressione qualquer tecla para voltar para o menu...");
                        this.scanner.nextLine();
                        break;
                    }

                    this.scanner.nextLine(); // limpa o enter do nextInt
                    this.carrinho.removerProduto(codigoRemover); // ja imprime se removeu ou nao achou
                    System.out.println("Pressione qualquer tecla para voltar para o menu...");
                    this.scanner.nextLine();
                    break;
                }
                case 4 -> {
                    this.scanner.nextLine();
                    limparTela();
                    // printar o carrinho de compras
                    this.carrinho.exibirCarrinho();
                    System.out.println("----------------------------------");
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 5 -> {
                    this.scanner.nextLine();
                    limparTela();
                    // printar a lista de cupons disponíveis
                    mostrarCupons();
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 6 -> {
                    this.scanner.nextLine();
                    limparTela();
                    // aplicar cupom
                    System.out.println(
                        "Digite o código do cupom que deseja aplicar:"
                    );
                    String cupomAplicar = this.scanner.nextLine();

                    boolean achou = false;
                    for (int i = 0; i < this.listaCupons.size(); i++) {
                        Cupom atual = this.listaCupons.get(i);
                        if (atual.validarCupom(cupomAplicar)) {
                            this.carrinho.aplicarCupom(atual);
                            System.out.println("O cupom foi aplicado com sucesso!");
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        System.out.println("Cupom inválido!");
                    }

                    System.out.println("Pressione qualquer tecla para voltar para o menu...");
                    this.scanner.nextLine();
                    break;
                }
                case 7 -> {
                    // mostrar menu de gestão
                    menuGestao();
                    if (this.sairDoSistema) {
                        executando = false;
                    }
                    break;
                }
                case 8 -> {
                    // sai da aplicação
                    executando = false;
                    break;
                }
                default -> {
                    try {
                        System.out.println("Você digitou um número inválido.");
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    break;
                }
            }
        }
    }

    public void menuGestao() {
        // estado pra guardar se o usuário vai querer estar no menu da gestao ainda ou nao
        boolean emGestao = true;

        while (emGestao && !this.sairDoSistema) {
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

            int resposta;
            try {
                resposta = this.scanner.nextInt();
            } catch (InputMismatchException e) {
                this.scanner.nextLine(); // limpa o que digitou
                System.out.println("Digite apenas números.");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                continue;
            }

            switch (resposta) {
                case 1 -> {
                    this.scanner.nextLine();
                    // printar lista de produtos
                    limparTela();
                    mostrarLista();
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 2 -> {
                    // adicionar produto
                    limparTela();
                    this.scanner.nextLine();
                    try {
                        System.out.println("Selecione a categoria do produto:");
                        System.out.println("( 1 ) Eletrônico");
                        System.out.println("( 2 ) Vestuário");
                        System.out.println("( 3 ) Alimento");
                        int categoria = this.scanner.nextInt();
                        limparTela();
                        this.scanner.nextLine();
                        System.out.println("Digite o nome do produto:");
                        String nome = this.scanner.nextLine();
                        System.out.println("Digite o preço do produto");
                        float preco = this.scanner.nextFloat();
                        this.scanner.nextLine();
                        System.out.println(
                            "Digite o departamento (subcategoria) do produto:"
                        );
                        String departamento = this.scanner.nextLine();
                        System.out.println(
                            "Informe a data de fabricação do produto no formato 'ano-mes-dia' ex: 2026-12-31:"
                        );
                        String dataFabricacaoString = this.scanner.nextLine();
                        LocalDate dataFabricacao = LocalDate.parse(
                            dataFabricacaoString.trim()
                        );
                        System.out.println("Informe o código do produto:");
                        int codProduto = this.scanner.nextInt();
                        this.scanner.nextLine(); // limpa o enter que sobrou do nextInt

                        if (categoria == 1) {
                            System.out.println(
                                "Digite quantos meses de garantia o produto irá possuir:"
                            );
                            int mesesGarantia = this.scanner.nextInt();
                            this.scanner.nextLine();
                            System.out.println("Digite a marca do eletrônico:");
                            String marca = this.scanner.nextLine();
                            Eletronico novoEletronico = new Eletronico(
                                nome,
                                preco,
                                departamento,
                                dataFabricacao,
                                0,
                                codProduto,
                                mesesGarantia,
                                marca
                            );
                            this.listaProdutos.add(novoEletronico);
                            System.out.println("Produto cadastrado com sucesso!");
                        } else if (categoria == 2) {
                            System.out.println(
                                "Digite o tamanho da roupa (P, M, G ou GG):"
                            );
                            String tamanhoRoupa = this.scanner.nextLine();
                            System.out.println("Informe o tecido que compõe a roupa: ");
                            String tecidoRoupa = this.scanner.nextLine();
                            Vestuario novoVestuario = new Vestuario(
                                nome,
                                preco,
                                departamento,
                                dataFabricacao,
                                0,
                                codProduto,
                                tamanhoRoupa,
                                tecidoRoupa
                            );
                            this.listaProdutos.add(novoVestuario);
                            System.out.println("Produto cadastrado com sucesso!");
                        } else if (categoria == 3) {
                            System.out.println(
                                "Informe a data de validade do alimento no formato 'ano-mes-dia' ex: 2026-12-31:"
                            );
                            String dataValidadeString = this.scanner.nextLine();
                            LocalDate dataValidade = LocalDate.parse(
                                dataValidadeString.trim()
                            );
                            Alimento novoAlimento = new Alimento(
                                nome,
                                preco,
                                departamento,
                                dataFabricacao,
                                0,
                                codProduto,
                                dataValidade
                            );
                            this.listaProdutos.add(novoAlimento);
                            System.out.println("Produto cadastrado com sucesso!");
                        } else {
                            System.out.println("A categoria informada não existe!");
                        }
                    } catch (InputMismatchException e) {
                        // digitou letra onde era numero
                        this.scanner.nextLine();
                        System.out.println("Erro: digite um número válido.");
                    } catch (IllegalArgumentException e) {
                        // preco negativo, nome vazio, tamanho invalido etc
                        System.out.println("Erro: " + e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println(
                            "Erro: data inválida, use o formato 2026-12-31"
                        );
                    }
                    System.out.println("----------------------------------");
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 3 -> {
                    this.scanner.nextLine();
                    // tela pra excluir um produto
                    limparTela();
                    mostrarLista();
                    // pedir o código do produto que deseja excluir
                    System.out.println(
                        "Digite o código do produto que deseja excluir: "
                    );

                    int codigoExcluir;

                    try {
                        codigoExcluir = this.scanner.nextInt();
                    } catch (InputMismatchException e) {
                        this.scanner.nextLine();
                        System.out.println("Digite apenas números.");
                        System.out.println("Pressione qualquer tecla para voltar para o menu...");
                        this.scanner.nextLine();
                        break;
                    }
                    this.scanner.nextLine(); // limpa o enter do nextInt

                    boolean achou = false;
                    for (int i = 0; i < this.listaProdutos.size(); i++) {
                        Produto atual = this.listaProdutos.get(i);
                        if (atual.getCod_prod() == codigoExcluir) {
                            this.listaProdutos.remove(i);
                            System.out.println("O produto foi removido.");
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        System.out.println("Produto não encontrado!");
                    }
                    System.out.println("----------------------------------");
                    System.out.println("Pressione qualquer tecla para voltar para o menu...");
                    this.scanner.nextLine();
                    break;
                }
                case 4 -> {
                    this.scanner.nextLine();
                    // tela pra adicionar cupom
                    // pedir as informacoes do cupom e adicionar (talvez checar se ja nao existe um igual)
                    limparTela();
                    System.out.println(
                        "Digite o código do cupom que deseja adicionar:"
                    );
                    String codigoCupom = this.scanner.nextLine();

                    System.out.println(
                        "Digite o valor (em %) que deseja que o cupom aplique:"
                    );

                    try {
                        int valorCupom = this.scanner.nextInt();
                        this.scanner.nextLine();
                        Cupom novoCupom = new Cupom(codigoCupom, valorCupom);
                        this.listaCupons.add(novoCupom);
                        System.out.println("O cupom foi criado com sucesso!");
                    } catch (InputMismatchException e) {
                        this.scanner.nextLine();
                        System.out.println("Erro: digite um número válido.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage()); // percentual fora de 1 a 100
                    }

                    System.out.println("----------------------------------");
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 5 -> {
                    this.scanner.nextLine();
                    limparTela();
                    // tela pra excluir cupom
                    mostrarCupons();
                    // digitar o nome do cupom que deseja excluir e procurar se existe e dai apagar
                    System.out.println(
                        "Digite o nome do cupom que deseja excluir:"
                    );
                    String cupomExcluir = this.scanner.nextLine();

                    boolean achou = false;
                    for (int i = 0; i < this.listaCupons.size(); i++) {
                        Cupom atual = this.listaCupons.get(i);
                        if (atual.getCodigo().equals(cupomExcluir)) {
                            this.listaCupons.remove(i);
                            System.out.println("Cupom removido com sucesso!");
                            achou = true;
                            break;
                        }
                    }

                    if (!achou) {
                        System.out.println("Cupom não encontrado!");
                    }
                    System.out.println("----------------------------------");
                    System.out.println("Pressione qualquer tecla para voltar para o menu...");
                    this.scanner.nextLine();
                    break;
                }
                case 6 -> {
                    this.scanner.nextLine();
                    limparTela();
                    // tela pra ver os cupons
                    mostrarCupons();
                    System.out.println(
                        "Pressione qualquer tecla para voltar para o menu..."
                    );
                    this.scanner.nextLine();
                    break;
                }
                case 7 -> {
                    // voltar menu usuario
                    emGestao = false;
                    break;
                }
                case 8 -> {
                    // sair
                    this.sairDoSistema = true;
                    emGestao = false;
                    break;
                }
                default -> {
                    try {
                        System.out.println("Você digitou um número inválido.");
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    break;
                }
            }
        }
    }
}
