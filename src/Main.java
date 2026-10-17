import java.util.ArrayList; //Adiciona os clientes em um array, para possibilitar listas longas
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ContaBancaria> listaContas = new ArrayList<>(); //Array para guardar todas as contas.

        int opcao;
        do {
            System.out.println("\n====================================");
            System.out.println("          MENU DO BANCO             ");
            System.out.println("====================================");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar contas");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferência");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                System.out.println("\n--- CADASTRO DE CLIENTE ---");

                // Validação: Nome não pode ser vazio
                String nome = "";
                while (nome.trim().isEmpty()) {
                    System.out.print("Nome do cliente: ");
                    nome = scanner.nextLine();
                    if (nome.trim().isEmpty()) {// .trim serve para remover os espaços vazios,emptiy verifica se existe espaço vazio ou nada no campo.
                        System.out.println("Erro: O nome não pode ficar vazio ou em branco!");
                    }
                }

                // Validação: CPF não pode ficar vazio
                String cpf = "";
                while (cpf.trim().isEmpty()) {
                    System.out.print("CPF do cliente: ");
                    cpf = scanner.nextLine();
                    if (cpf.trim().isEmpty()) {
                        System.out.println("Erro: O CPF não pode ficar vazio ou em branco!");
                    }
                }

                Cliente cliente = new Cliente(nome, cpf);

                // Validação: Número da conta não pode ficar vazio
                String numConta = "";
                while (numConta.trim().isEmpty()) {
                    System.out.print("Número da conta: ");
                    numConta = scanner.nextLine();
                    if (numConta.trim().isEmpty()) {
                        System.out.println("Erro: O número da conta não pode ficar vazio!");
                    }
                }

                // Validação: Saldo inicial deve ser um número válido
                System.out.print("Saldo inicial: R$ ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Erro: Digite um valor numérico válido para o saldo.");
                    scanner.next();
                    System.out.print("Saldo inicial: R$ ");
                }
                double saldo = scanner.nextDouble();
                scanner.nextLine(); // Limpa o buffer

                ContaBancaria novaConta = new ContaBancaria(numConta, saldo, cliente);
                listaContas.add(novaConta);
                System.out.println("Conta cadastrada com sucesso!");

            } else if (opcao == 2) {
                if (listaContas.isEmpty()) {
                    System.out.println("Não existem contas cadastradas");
                } else {
                    System.out.println("\n--- LISTA DE CONTAS ---");
                    for (int i = 0; i < listaContas.size(); i++) {
                        System.out.println("[" + i + "] Titular: " + listaContas.get(i).getTitular().getNome() +
                                " | Conta: " + listaContas.get(i).getNumeroConta() +
                                " | Saldo: R$ " + listaContas.get(i).getSaldo());
                    }
                }

            } else if (opcao == 3) {
                if (listaContas.isEmpty()) {
                    System.out.println("Não existem contas cadastradas");
                    continue;
                }
                System.out.print("Digite o número da conta para depósito: ");
                String num = scanner.nextLine();
                ContaBancaria conta = procurarConta(listaContas, num);

                if (conta != null) { // valor do deposito nao pode ser nulo
                    System.out.print("Valor do depósito: R$ ");
                    double val = scanner.nextDouble();
                    conta.depositar(val);
                } else {
                    System.out.println("Conta não encontrada.");
                }

            } else if (opcao == 4) {
                if (listaContas.isEmpty()) {
                    System.out.println("Não existem contas cadastradas");
                    continue;
                }
                System.out.print("Digite o número da conta para saque: ");
                String num = scanner.nextLine();
                ContaBancaria conta = procurarConta(listaContas, num);

                if (conta != null) {
                    System.out.print("Valor do saque: R$ ");
                    double val = scanner.nextDouble();
                    conta.sacar(val);
                } else {
                    System.out.println("Conta não encontrada.");
                }

            } else if (opcao == 5) {
                if (listaContas.size() < 2) {
                    System.out.println("É preciso ter no minimo 2 contas distintas para prosseguir com a operação.");
                    continue;
                }
                System.out.print("Número da conta de ORIGEM: ");
                String numOrigem = scanner.nextLine();
                ContaBancaria origem = procurarConta(listaContas, numOrigem);

                System.out.print("Número da conta de DESTINO: ");
                String numDestino = scanner.nextLine();
                ContaBancaria destino = procurarConta(listaContas, numDestino);

                if (origem != null && destino != null) {
                    System.out.print("Valor da transferência: R$ ");
                    double val = scanner.nextDouble();
                    origem.transferir(val, destino);
                } else {
                    System.out.println("Conta de origem ou destino não encontrada.");
                }

            } else if (opcao == 0) {
                System.out.println("Sistema encerrado!");
            } else {
                System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // Método auxiliar simples para procurar uma conta pelo número digitado
    public static ContaBancaria procurarConta(ArrayList<ContaBancaria> lista, String numero) {
        for (ContaBancaria c : lista) {
            if (c.getNumeroConta().equals(numero)) {
                return c;
            }
        }
        return null;
    }
}