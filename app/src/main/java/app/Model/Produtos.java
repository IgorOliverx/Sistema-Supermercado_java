package app.Model;

/**
 * Entidade de Produtos
 */
public class Produtos {

    // Atributos do modelo
    private String codigo;
    private String nome;
    private String quantidade;
    private String precoClienteVIP;
    private String precoCliente;
    private String descricao;


     /*
    * Construtor vazio
    */
    public Produtos(){}

     /*
    * Construtor inicializando atributos
    */
    public Produtos(String codigo, String nome, String quantidade, String precoClienteVIP, String precoCliente, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoClienteVIP = precoClienteVIP;
        this.precoCliente = precoCliente;
        this.descricao = descricao;
    }

    /*
    * Métodos modificadores de acesso
    */
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getPrecoClienteVIP(){
        return precoClienteVIP;
    }
    public String getPrecoCliente(){
        return precoCliente;
    }

    public String getDescricao(){
        return descricao;
    }
}
