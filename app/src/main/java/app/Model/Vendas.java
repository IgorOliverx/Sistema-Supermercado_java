package app.Model;

public class Vendas {
    // atributos

    private String codigoProduto;
    private String nomeProduto;
    private String quantidadeProduto;
    private String cliente;
    private String valor;
    private String data;


    public Vendas() {
        super();
    }

    public Vendas(String codigoProduto, String nomeProduto, String quantidadeProduto, String cliente, String valor, String data) {
                this.codigoProduto = codigoProduto;
                this.nomeProduto = nomeProduto;
                this.quantidadeProduto = quantidadeProduto;
                this.cliente = cliente;
                this.valor = valor;
                this.data = data;
                
    }

    public String getCodigoProduto(){
        return codigoProduto;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }
    public String getQuantidadeProduto(){
        return quantidadeProduto;
    }
    public String getCliente(){
        return cliente;
    }
    public String getValor(){
        return valor;
    }
    public String getData(){
        return data;
    }



}
