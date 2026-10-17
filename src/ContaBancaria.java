public class ContaBancaria {
    // Atributos privados
    private String numeroConta;
    private double saldo;
    private Cliente titular; // Relacionamento com a classe Cliente

    // Construtor Padrão
    public ContaBancaria() {
    }

    // Construtor Parametrizado
    public ContaBancaria(String numeroConta, double saldo, Cliente titular) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.titular = titular;
    }

    // Métodos Get e Set
    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    // Operações bancárias simples
    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saque não permitido. Saldo insuficiente ou valor inválido.");
        }
    }

    // Metodo de Transferência
    public void transferir(double valor, ContaBancaria contaDestino) {
        if (contaDestino == null) {
            System.out.println("Conta de destino inválida.");
        } else if (valor > 0 && valor <= saldo) {
            this.saldo = this.saldo - valor;
            contaDestino.saldo = contaDestino.saldo + valor;
            System.out.println("Transferência de R$ " + valor + " para " + contaDestino.getTitular().getNome() + " realizada com sucesso!");
        } else {//resposta de sucesso,mostra o nome do titular e o valor transferido
            System.out.println("Transferência não realizada. Saldo insuficiente ou valor inválido.");
        }// chamada de erro, caso queria trasnferir oque nao tem.
    }

}