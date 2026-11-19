package marketplace_PO;

import java.util.Scanner;
import java.util.ArrayList;

public class marketplace_PO {
    
    public static void menuGestao(ArrayList listaProdutos) {
        
    }

    public static void main(String[] args) {
        // Aluno 4 (Igor) -> vai instanciar as listas para gerenciar os produtos disponíveis e etc

        // Aluno 5 (Gustavo) -> vai codar o menu de interação, tratamento de erros e etc (experiência de usuário)
        System.out.println("----------------------------------");
        System.out.println("Marketplace - TechStore");
        System.out.println("Seja bem vindo!! - Selecione uma opção da nossa loja");
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
                break;
            }
            case 5 -> {
                // sai da aplicação
                break;
            }
            default -> {
                break;
            }
        }
    }

}
