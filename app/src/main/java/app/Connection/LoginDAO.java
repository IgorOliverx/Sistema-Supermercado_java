package app.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Logs.Log;

public class LoginDAO {
     // Atributo de conexão
     private Connection connection;
    
     // Construtor inicializando a conexão com o banco de dados
     public LoginDAO(){
         this.connection = ConnectionFactory.getConnection();
     }
 
      // Método de verificação de tabela existente
     private boolean tabelaExiste() {
         boolean tabelaExiste = false;
         String verificaTabela = "SELECT 1 FROM login LIMIT 1";
     
         try (Statement stmt = connection.createStatement()) {
             // Tenta executar a consulta para verificar se a tabela existe
             ResultSet rs = stmt.executeQuery(verificaTabela);
             tabelaExiste = true;
         } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("Tabela não existe!" + e.getMessage());
         }
     
         return tabelaExiste;
     }

      // Método para criar a tabela se ela não existir
     public void criaTabela(){
         String sql = "CREATE TABLE IF NOT EXISTS login (SENHA VARCHAR(30), USUARIO VARCHAR(25) PRIMARY KEY, SENHACAIXA VARCHAR(30), USUARIOCAIXA VARCHAR(30) )";
         try (Statement stmt = this.connection.createStatement()) {
             stmt.execute(sql);
             new Log();
            Log.registrarOperacao("Tabela Criada com sucesso!");
         } catch (SQLException e) {
             throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
         } 
     }

     // Método de inicialização do banco
     public void inicializarBanco() {
         try{
             // Verifica se a tabela existe; se não existir, cria e preenche com credenciais padrão
             if (!tabelaExiste()) {
                 criaTabela();
                 preencherTabela("adm", "adm", "caixa", "caixa");
                 System.out.println("tabela ta sendo preenchida");
             }
             preencherTabela("adm", "adm", "caixa", "caixa");
                 new Log();
            Log.registrarOperacao("Tabela sendo preenchida" );
         } catch(Exception e){
             new Log();
            Log.registrarOperacao("Não foi possível inserir credenciais" + e.getMessage());
         }
     }
 
    
 
    
 
     // Método para preencher a tabela com credenciais padrão
     public void preencherTabela(String usuario, String senha, String usuarioCaixa, String senhaCaixa) {
         PreparedStatement stmt = null;
         String sql = "INSERT INTO login (usuario, senha, usuariocaixa, senhacaixa) VALUES (?, ?, ?, ?)";
         try {
             stmt = connection.prepareStatement(sql);
             stmt.setString(1, usuario);
             stmt.setString(2, senha);
             stmt.setString(3, senhaCaixa);
             stmt.setString(4, usuarioCaixa);
             stmt.executeUpdate();
             System.out.println("Credenciais criadas");
         } catch (SQLException e) {
             throw new RuntimeException("Erro ao criar credenciais." + e.getMessage());
         } 
     }
 
     public boolean verificarLogin(String usuario, String senha, String tipoUsuario){
        PreparedStatement stmt = null;
        boolean loginValido = false;
        ResultSet resultado = null;
        String sql = "";
    
        if (tipoUsuario.equals("gerente")) {
            sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
        } else if (tipoUsuario.equals("caixa")) {
            sql = "SELECT * FROM login WHERE usuariocaixa = ? AND senhacaixa = ?";
        } 
    
        try {
            // Prepara a consulta SQL com os parâmetros de usuário e senha correspondentes ao tipo de usuário
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            resultado = stmt.executeQuery();
    
            // Verifica se a consulta retornou algum resultado válido
            if(resultado.next()){
                loginValido = true;
                if (tipoUsuario.equals("gerente")) {
                    new Log();
                    Log.registrarOperacao("Login Realizado com sucesos na conta de gerente!");
                } else if (tipoUsuario.equals("caixa")) {
                     new Log();
                    Log.registrarOperacao("Login Realizado com sucesos na conta de caixa!");
                } 
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar credenciais." + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return loginValido;
    }
    
}