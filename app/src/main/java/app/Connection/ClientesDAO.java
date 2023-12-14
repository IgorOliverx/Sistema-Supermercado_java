package app.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.Model.ClienteVIP;
import app.Connection.ConnectionFactory;
import app.Logs.Log;

public class ClientesDAO extends CrudDAO<ClienteVIP> {

    // Atributos de classe
    private Connection connection;
    private List<ClienteVIP> clientesVIP;
    

    
    // Chamando o construtor que inicializa a conexão com o BD
    public ClientesDAO(){
        this.connection = ConnectionFactory.getConnection();
    }
    // Construtor da Classe vazio
   
   
    
    /**
     * Herdando métodos abstratos da classe CrudDAO
     */
    @Override
    public void criarTabela() {// alinhar com o eduardo colunas da tabela
        String sql = "CREATE TABLE IF NOT EXISTS clientesVIP_infinity (NOME VARCHAR (255), CPF VARCHAR(20) PRIMARY KEY, TELEFONE VARCHAR(255), SEXO VARCHAR(20), EMAIL VARCHAR(255), DATA VARCHAR(255))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            new Log();
            Log.registrarOperacao("Tabela de clientes VIP criada com sucesso -> função criartabela ClientesDAO");
        } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("Erro ao criar tabela -> função criar tabela ClientesDAO");
            throw new RuntimeException("Erro ao criar tabela " + e.getMessage() + e.getSQLState());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(this.connection);
        }

    }

    /*
     * Implementação para Retornar um cliejte da lista a partir do cpf -> chave
     * primaria
     */
    @Override
    public ClienteVIP procurarPorId(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVIP cliente = null; 
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM clientesVIP_infinity WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
    
            if (rs.next()) { 
                cliente = new ClienteVIP(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("sexo"),
                    rs.getString("email"),
                    rs.getString("data")
                );
            }
        } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("Cliente NÃO ENCONTRADO -> função procurar por id ClientesDAO");
            throw new RuntimeException("Cliente não encontrado" + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        return cliente; 
    }
    

    /*
     * Implementação para Listar todos os clientes da lista
     */
    @Override
    public List<ClienteVIP> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        clientesVIP = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM clientesVIP_infinity");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteVIP clienteVIP = new ClienteVIP(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("sexo"),
                        rs.getString("email"),
                        rs.getString("data"));
                clientesVIP.add(clienteVIP);
            }
        } catch (SQLException e) {
           new Log();
            Log.registrarOperacao("Erro ao listar todos-> função deletar ClientesDAO");
            throw new RuntimeException("Erro ao listar todos os clientes "+ e.getMessage() + e.getSQLState());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientesVIP;
    }

    /*
     * Implementação para cadastrar novos clientesVIP
     */
    @Override
    public void cadastrar(ClienteVIP cliente) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clientesVIP_infinity (nome, cpf, telefone, sexo, email, data) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getSexo());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getData());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso - substituir por log do sistema");

        } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("Erro ao inserir dados -> função cadastrar ClientesDAO");
            throw new RuntimeException("Erro ao inserir dados " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    /*
     * Implementação para atualizar registros existentes no banco de dados
     */
    @Override
    public void atualizar(ClienteVIP entidade) {
        PreparedStatement stmt = null;

        String sql = "UPDATE clientesVIP_infinity SET nome = ?, telefone = ?, sexo = ? , email = ?,  data = ? WHERE cpf = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getSexo());
            stmt.setString(4, entidade.getEmail());
            stmt.setString(5, entidade.getData());
            stmt.setString(6, entidade.getCpf());
            stmt.executeUpdate();
            new Log();
            Log.registrarOperacao("Dado atualizado com sucesso -> função atualizar ClientesDAO");

        } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("Erro ao atualizar clientes -> função atualizar ClientesDAO");
            throw new RuntimeException("Erro ao atualizar cliente " + e.getMessage() + e.getSQLState());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public void deletar(String cpf) {
        // Implementação para deletar dados do banco a partir do cpf
        PreparedStatement stmt = null;

        String sql = "DELETE FROM clientesVIP_infinity WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            new Log();
            Log.registrarOperacao("Dado apagado com sucesso -> função deletar ClientesDAO");
        } catch (SQLException e) {
            new Log();
            Log.registrarOperacao("ERRO NA EXCLUSÃO DO CLIENTE -> função deletar ClientesDAO");
            throw new RuntimeException("Erro na exclusão do cliente: " + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }



    @Override
    public double consulta(String codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta'");
    }



    @Override
    public int verificarEstoque(String codigo, int quantidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verificarEstoque'");
    }

    public boolean consultarCliente(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean clienteExiste = false; 
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM clientesVIP_infinity WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
    
            clienteExiste = rs.next();
    
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar cliente: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        return clienteExiste;
    }
    

}
