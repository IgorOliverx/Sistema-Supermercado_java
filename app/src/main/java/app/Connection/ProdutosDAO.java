package app.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.Model.ClienteVIP;
import app.Model.Produtos;
import  app.Connection.ConnectionFactory;
import app.Logs.Log;

/**
 * ProdutosDAO
 */
public class ProdutosDAO extends CrudDAO<Produtos>{

     //Atributos de classe 
    private Connection connection;
    private List<Produtos> produtos;
    private List<Produtos> produto;


    //Construtor inicilizando conexão com o BD
    public ProdutosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }


     /*
    *Implementação para criar a tabela de produtos
    */
    @Override
    public void criarTabela() {
        //nome da tabela = produtos
       String sql = "CREATE TABLE IF NOT EXISTS produtos_infinity(CODIGO VARCHAR (20) PRIMARY KEY, NOME VARCHAR (255), QUANTIDADE VARCHAR (20), PclienteVIP VARCHAR (255), Pcliente VARCHAR(255), DESCRICAO VARCHAR(255))"; //Instruoção sql para criar a tabela

       try(Statement stmt = this.connection.createStatement()){
        stmt.execute(sql);
        System.out.println("tabela criada com sucesso -> substituir por log");
       }catch(SQLException e){
        throw new RuntimeException("Erro ao criar tabela");
       }finally{
        ConnectionFactory.closeConnection(this.connection);
       }
    }

     /*
    *Implementação para Retornar um produto da lista a partir do codigo de barras -> chave primaria
    */
    @Override
    public Produtos procurarPorId(String codigo) {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Produtos produto = null;

       try{
        stmt = connection.prepareStatement("SELECT * FROM produtos_infinity WHERE codigo = ? ");
        stmt.setString(1, codigo);
        rs = stmt.executeQuery();

        if(rs.next()){
            produto = new Produtos(
                rs.getString("codigo"),
                rs.getString("nome"),
                rs.getString("quantidade"),
                rs.getString("PclienteVIP"),
                rs.getString("pCliente"),
                rs.getString("descricao"));
        }

       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
        ConnectionFactory.closeConnection(connection, stmt, rs);
       }

       return produto;
       
    }

    @Override
    public List<Produtos> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        produtos = new ArrayList<>();

        try{
            stmt = connection.prepareStatement("SELECT * FROM produtos_infinity");
            rs = stmt.executeQuery();

            while(rs.next()){
                Produtos produto = new Produtos(
                    rs.getString("codigo"),
                rs.getString("nome"),
                rs.getString("quantidade"),
                rs.getString("PclienteVIP"),
                rs.getString("pCliente"),
                rs.getString("descricao"));
                produtos.add(produto);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;
    }

    @Override
    public void cadastrar(Produtos entidade) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO produtos_infinity (codigo, nome, quantidade, PclienteVIP, Pcliente, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entidade.getCodigo());
            stmt.setString(2, entidade.getNome());
            stmt.setString(3, entidade.getQuantidade());
            stmt.setString(4, entidade.getPrecoClienteVIP());
            stmt.setString(5, entidade.getPrecoCliente());
            stmt.setString(6, entidade.getDescricao());
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso substituir por log");

        }catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public void atualizar(Produtos entidade) {
        PreparedStatement stmt = null;
        String sql = "UPDATE produtos_infinity SET  nome = ?, quantidade = ?, PclienteVIP = ?, Pcliente = ?, descricao = ? WHERE codigo = ?" ;

        try{ 
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getQuantidade());
            stmt.setString(3, entidade.getPrecoClienteVIP());
            stmt.setString(4, entidade.getPrecoCliente());
            stmt.setString(5, entidade.getDescricao());
            stmt.setString(6, entidade.getCodigo());
            stmt.executeUpdate();
            System.out.println("Produto Atualizado com sucesso substituir por log");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(connection, stmt);
        }
        
    }

    @Override
    public void deletar(String codigo) {
       PreparedStatement stmt = null;

       String sql = "DELETE FROM produtos_infinity WHERE codigo = ?";

      try{stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            new Log();
            Log.registrarOperacao("Produto deletado com sucesso em ");
        } catch (SQLException e) {
            // substituir por log do sistema
            throw new RuntimeException("Erro na exclusão do cliente: " + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }


    @Override
    public double consulta(String codigo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT pclientevip, pcliente FROM produtos_infinity WHERE codigo = ?";
        double valor = 0;
    
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery(); // Execute a consulta de seleção
    
            // Verifique se há resultados e obtenha o valor
            if (rs.next()) {
                valor = rs.getDouble("pclientevip");
            }
    
        } catch (SQLException e) {
            System.out.println("PRODUTOSDAO(198) -> Erro:  " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        return valor; // Retorna o valor recuperado da consulta
    }


    @Override
    public int verificarEstoque(String codigoProduto, int quantidadeDesejada) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int estoqueDisponivel = 0;
    
        try {
            // Consulta para obter a quantidade disponível em estoque para o produto específico
            String sql = "SELECT quantidade FROM produtos_infinity WHERE codigo = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigoProduto);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                estoqueDisponivel = rs.getInt("quantidade");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o estoque: linha 225 produtos dao " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        // Verifica se a quantidade disponível em estoque é suficiente para a quantidade desejada
        if (estoqueDisponivel >= quantidadeDesejada) {
            return estoqueDisponivel; // Retorna a quantidade disponível em estoque
        } else {
            return -1; // Retorna um valor indicando que o estoque não é suficiente
        }
    }

    public void atualizarEstoque(String codigoProduto, int quantidadeVendida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int quantidadeAtual = 0;
    
        try {
            // Primeiro, consulte a quantidade atual no estoque do produto
            stmt = connection.prepareStatement("SELECT quantidade FROM produtos_infinity WHERE codigo = ?");
            stmt.setString(1, codigoProduto);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                quantidadeAtual = rs.getInt("quantidade");
            }
    
            // Verifique se a quantidade em estoque é suficiente para a venda
            if (quantidadeAtual >= quantidadeVendida) {
                // Atualize o estoque subtraindo a quantidade vendida
                int novoEstoque = quantidadeAtual - quantidadeVendida;
                stmt = connection.prepareStatement("UPDATE produtos_infinity SET quantidade = ? WHERE codigo = ?");
                stmt.setInt(1, novoEstoque);
                stmt.setString(2, codigoProduto);
                stmt.executeUpdate();
                System.out.println("Estoque atualizado com sucesso!");
            } else {
                System.out.println("Quantidade insuficiente em estoque para realizar a venda.");
                // Aqui você pode lançar uma exceção ou fazer o tratamento adequado para indicar a falta de estoque
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar estoque: " + ex.getMessage());
            // Trate a exceção de acordo com a estrutura do seu projeto
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    }
    
    
    

    
}