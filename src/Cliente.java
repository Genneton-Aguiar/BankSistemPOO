public class Cliente {
    // Atributos privados (Encapsulamento)
    private String nome;
    private String cpf;

    // Construtor Padrão (sem parâmetros)
    public Cliente() {
    }

    // Construtor Parametrizado
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Métodos Get e Set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //exibir dados do cliente
    public void exibirInformacoes() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf);
    }
}