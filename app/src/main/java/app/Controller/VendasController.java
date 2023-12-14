package app.Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.Connection.ClientesDAO;
import app.Connection.ProdutosDAO;
import app.Connection.VendasDAO;
import app.Model.ClienteVIP;
import app.Model.Vendas;

public class VendasController {
    private List<Vendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;
    Vendas venda;

    public VendasController() {
    }

    public VendasController(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Vendas venda : vendas) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { venda.getCodigoProduto(), venda.getNomeProduto(),

                    venda.getQuantidadeProduto(), venda.getCliente(), venda.getValor(), venda.getData(), venda.getCodVenda() });
        }
    }

    // Método para cadastrar um novo cliente no banco de dados
    public void cadastrar(String codigoProduto, String nomeProduto, String quantidadeProduto, String cliente,
            String valor, String data) {
        int quantidade = Integer.parseInt(quantidadeProduto);
        Vendas novaVenda = new Vendas(codigoProduto, nomeProduto, quantidadeProduto, cliente, valor, data);
        new VendasDAO().cadastrar(novaVenda);
        // Chama o método de cadastro no banco de dados
        atualizarTabela();
        JOptionPane.showMessageDialog(null, "Venda realizada!");
        new ProdutosDAO().atualizarEstoque(codigoProduto, quantidade);
    }

    public void deletar(String codVenda) {
        int linhaSelecionada = table.getSelectedRow();
        try {Object[] opcoes = { "Sim", "Não" };
            if (linhaSelecionada >= 0) {
                int resposta = JOptionPane.showOptionDialog(null,
                        "Você tem certeza que quer excluir esta venda? ",
                        "Excluir Venda", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                if (resposta == JOptionPane.YES_OPTION) {
                    new VendasDAO().deletar(codVenda);
                    atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
                    JOptionPane.showMessageDialog(null, "Venda deletada com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma venda", "Erro nos dados",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpar(){
        new VendasDAO().limparTabela();
    }


    public boolean realizarVenda(String produtoSelecionado, String clienteSelecionado, int quantidade) {
        String[] infosProduto = produtoSelecionado.split(" - ");
        String codigoProduto = infosProduto[1].trim();
        int estoque = new ProdutosDAO().verificarEstoque(codigoProduto, quantidade);
        boolean clienteExiste = new ClientesDAO().consultarCliente(clienteSelecionado);
        boolean dadosValidos = true;
    
        try {
            if (produtoSelecionado.equals("Selecione o Produto:")) {
                throw new IllegalArgumentException("Por favor, selecione um produto!");
            }
            if (quantidade <= 0) {
                throw new IllegalArgumentException("Selecione uma quantidade do produto!");
            }
            if (estoque == -1) {
                throw new IllegalArgumentException("Quantidade do produto indisponível!");
            }
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
            dadosValidos = false;
        }
    
        return dadosValidos;
    }
    
    

}
