package cadpessoas;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import java.util.Scanner;

public class Main {
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("=== Sistema de Cadastro de Pessoas ===");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    atualizarPessoa();
                    break;
                case 4:
                    deletarPessoa();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void adicionarPessoa() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Pessoa pessoa = new Pessoa(nome, sexo, cpf);
        pessoaDAO.addPessoa(pessoa);
        System.out.println("Pessoa adicionada com sucesso!");
    }

    private static void listarPessoas() {
        System.out.println("=== Lista de Pessoas ===");
        for (Pessoa pessoa : pessoaDAO.getPessoas()) {
            System.out.println(pessoa);
        }
    }

    private static void atualizarPessoa() {
        System.out.print("CPF da pessoa a ser atualizada: ");
        String cpf = scanner.nextLine();
        Optional<Pessoa> pessoaOptional = pessoaDAO.getPessoaByCpf(cpf);
        
       
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo Sexo: ");
            String novoSexo = scanner.nextLine();
            System.out.print("Novo CPF: ");
            String novoCpf = scanner.nextLine();
            Pessoa pessoaAtualizada = new Pessoa(novoNome, novoSexo, novoCpf);
            pessoaDAO.updatePessoa(cpf, pessoaAtualizada);
            System.out.println("Pessoa atualizada com sucesso!");
        } else {
            System.out.println("Pessoa com CPF " + cpf + " não encontrada.");
        }
    }

    private static void deletarPessoa() {
        System.out.print("CPF da pessoa a ser deletada: ");
        String cpf = scanner.nextLine();
        if (pessoaDAO.deletePessoa(cpf)) {
            System.out.println("Pessoa deletada com sucesso!");
        } else {
            System.out.println("Pessoa com CPF " + cpf + " não encontrada.");
        }
    }
}