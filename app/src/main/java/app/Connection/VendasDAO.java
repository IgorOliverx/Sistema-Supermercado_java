package app.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.Model.ClienteVIP;
import app.Model.Vendas;

public class VendasDAO extends CrudDAO<Vendas> {

    private Connection connection;

    private List<Vendas> vendas;

    private int codVenda;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    /*
     * Implementação para criar tabela no banco de dados
     */
    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas_infinity (CODIGOPRODUTO VARCHAR(255), NOMEPRODUTO VARCHAR(255), QUANTIDADEPRODUTO VARCHAR(255), CLIENTE VARCHAR(255), VALOR VARCHAR (255), DATA VARCHAR(255), CODVENDA SERIAL PRIMARY KEY)";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.executeQuery(sql);
            System.out.println("Tabela de vendas criada com sucesso e sequência alterada.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    /*
     * Implementação para atualizar registros existentes no banco de dados
     */
    @Override
    public void atualizar(Vendas venda) {
        PreparedStatement stmt = null;

        String sql = "UPDATE vendas_infinity SET CODIGOPRODUTO = ?, NOMEPRODUTO = ?, QUANTIDADEPRODUTO = ? , CLIENTE = ?,  VALOR = ?, DATA = ? WHERE CODVENDA = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, venda.getCodigoProduto());
            stmt.setString(2, venda.getNomeProduto());
            stmt.setString(3, venda.getQuantidadeProduto());
            stmt.setString(4, venda.getCliente());
            stmt.setString(5, venda.getValor());
            stmt.setString(6, venda.getData());

            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(connection, stmt);
        }

    }

    /*
     * Implementação para cadastrar novas vendas
     */
    @Override
    public void cadastrar(Vendas venda) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO vendas_infinity (CODIGOPRODUTO, NOMEPRODUTO, QUANTIDADEPRODUTO, CLIENTE, VALOR, DATA, CODVENDA) VALUES (?, ?, ?, ?, ?, ?, nextval('codigo_venda_seq'))";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, venda.getCodigoProduto());
            stmt.setString(2, venda.getNomeProduto());
            stmt.setString(3, venda.getQuantidadeProduto());
            stmt.setString(4, venda.getCliente());
            stmt.setString(5, (venda.getValor()));
            stmt.setString(6, venda.getData());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso - substituir por logs");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    /*
     * Implementação para deletar registros existentes no banco de dados
     */
    @Override
    public void deletar(String codVenda) {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM vendas_infinity WHERE CODVENDA = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(codVenda));
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso - Substituir por log do sistema");
        } catch (SQLException e) {
            // substituir por log do sistema
            throw new RuntimeException("Erro na exclusão da venda: " + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }

    }

    /*
     * Implementação para listar todos registros existentes no banco de dados
     */
    @Override
    public List<Vendas> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        vendas = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM vendas_infinity");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Vendas venda = new Vendas(
                        rs.getString("codigoProduto"),
                        rs.getString("nomeProduto"),
                        rs.getString("quantidadeProduto"),
                        rs.getString("cliente"),
                        rs.getString("valor"),
                        rs.getString("data"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.out.println("Erro 140 vendas dao" + e.getMessage());
        } finally {
            app.Connection.ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return vendas;
    }

    /*
     * Implementação para listar um registro existente no banco de dados
     */
    @Override
    public Vendas procurarPorId(String codVenda) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vendas venda = null;

        try {
            stmt = connection.prepareStatement("SELECT * FROM vendas_infinity WHERE CODVENDA = ?");
            stmt.setString(1, codVenda);
            rs = stmt.executeQuery();

            if (rs.next()) {
                venda = new Vendas(
                        rs.getString("codigoProduto"),
                        rs.getString("nomeProduto"),
                        rs.getString("quantidadeProduto"),
                        rs.getString("cliente"),
                        rs.getString("valor"),
                        rs.getString("data")

                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }

        return venda;
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

    /*
     * Implementação para recuperar codigo da venda no banco de dados
     */
    public int recuperaCodigo() {
        String sql = "SELECT CODVENDA FROM vendas_infinity";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        codVenda = 0;

        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                codVenda = rs.getInt("codvenda");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao retornar cod venda - 203 vendasDAO" + e.getMessage());
        }

        return codVenda;
    }

    /*
     * Implementação para deletar registros existentes no banco de dados depois que
     * a compra concluir
     */
    public void limparTabela() {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM vendas_infinity";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Tabela de vendas limpa com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar a tabela de vendas: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}