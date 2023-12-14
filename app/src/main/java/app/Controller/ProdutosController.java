package app.Controller;

import app.Connection.ProdutosDAO;
import app.Model.Produtos;

import java.text.NumberFormat;
import java.util.*;
/**
 * ProdutosController
 */

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProdutosController {

    // Atributos
    private List<Produtos> produtos;
    private DefaultTableModel tableModel;
    private JTable table;
    private Produtos produto;

    public ProdutosController() {
        super();
    }

    public ProdutosController(List<Produtos> produtos, DefaultTableModel tableModel, JTable table) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        produtos = new ProdutosDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Produtos produto : produtos) {
            // Adiciona os dados de cada produto como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNome(),

                    produto.getQuantidade(), produto.getPrecoClienteVIP(), produto.getPrecoCliente(),
                    produto.getDescricao() });
        }
    }

    // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String codigoBarras, String nome, String quantidade, String custoClienteVIP,
            String custoCliente, String descricao) {
        // Criar um novo objeto de Produtos com os dados recebidos
        Produtos novoProduto = new Produtos(codigoBarras, nome, quantidade, custoClienteVIP, custoCliente, descricao);

        // Chama o método de cadastro no banco de dados
        new ProdutosDAO().cadastrar(novoProduto);
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        // Atualiza a tabela de exibição após o cadastro
        atualizarTabela();
    }

    public void atualizar(String codigoBarras, String nome, String quantidade, String custoClienteVIP,
            String custoCliente, String descricao) {
        // Criar um novo objeto de Produtos com os dados recebidos
        Produtos produtoAtualizado = new Produtos(codigoBarras, nome, quantidade, custoClienteVIP, custoCliente,
                descricao);

        // Chama o método de atualização no banco de dados
        new ProdutosDAO().atualizar(produtoAtualizado);
        JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
        // Atualiza a tabela de exibição após a atualização
        atualizarTabela();
    }

    // Método para apagar um carro do banco de dados

    public void apagar(String codigo) {
        Object[] opcoes = { "Sim", "Não" };
        int linhaSelecionada = table.getSelectedRow();

        try {
            if (linhaSelecionada >= 0) {
                int resposta = JOptionPane.showOptionDialog(null,
                        "Você tem certeza que quer excluir este cliente? ",
                        "Excluir Cliente", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                if (resposta == JOptionPane.YES_OPTION) {
                    new ProdutosDAO().deletar(codigo);
                    atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
                    JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione um Produto", "Erro nos dados",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean validarDados(String codigoBarras, String nome, String quantidade, String custoClienteVIP,
            String custoCliente, String descricao) {
        boolean dadosValidos = true;

        if (codigoBarras.isEmpty() || nome.isEmpty() || quantidade.isEmpty() || custoClienteVIP.isEmpty() ||
                custoCliente.isEmpty() || descricao.isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os dados corretamente!");
        }

        // Verificação do código de barras (apenas números e tamanho 5)
        if (!codigoBarras.matches("\\d{5}")) {
            throw new IllegalArgumentException("Código de barras deve conter 5 dígitos!");
        }

        // Verificação se a quantidade é um número inteiro e tem até 5 dígitos
        try {
            int qtd = Integer.parseInt(quantidade);
            if (qtd < 0 || qtd > 99999) { // Verifica se está no intervalo permitido (0 a 99999)
                throw new IllegalArgumentException("Quantidade deve ser um número inteiro entre 0 e 99999");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantidade deve ser um número inteiro válido");
        }

        return dadosValidos;
    }

}