package app.Model;


public class ClienteVIP {

    //Atributos do modelo
    String nome;
    String cpf;
    String telefone;
    String sexo;
    String email;
    String data;
    
    
    /*
    * Construtor inicializando atributos
    */
    public ClienteVIP(String nome, String cpf, String telefone, String sexo, String email, String data) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.email = email;
        this.data = data;
    }
    
    
    /*
    * Construtor vazio
    */
    public ClienteVIP() {}


   /*
    * Métodos modificadores de acesso
    */
    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }


    public String getData() {
        return data;
    }
    

   
    
    

 
    

    
}
