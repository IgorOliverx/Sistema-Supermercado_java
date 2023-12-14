package app.View.Cadastro;

import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import app.Connection.ClientesDAO;
import app.Connection.ProdutosDAO;
import app.Controller.ClientesController;
import app.Controller.ProdutosController;
import app.Model.ClienteVIP;
import app.Model.Produtos;

public class TelaCadastroProdutos extends JPanel {

    // Atributos(componentes)
    private JButton cadastrar, apagar, editar, limpar, adicionar;
    private JLabel labelImg;
    private JFormattedTextField carCodigoField, carNomeField, carQuantidadeField, carPClienteVIPField,
            carPClienteField, carDescricaoField;
    private int contador = 11111;
    private List<Produtos> produtos;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    
    // Construtor(GUI-JPanel)
    public TelaCadastroProdutos() {
        super();
        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Cadastro Produtos"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 3));
        // mascara não mexer 
        inputPanel.add(new JLabel("Código"));
        carCodigoField = new JFormattedTextField(formatar("#####"));
        inputPanel.add(carCodigoField);

        inputPanel.add(new JLabel("Nome"));
        carNomeField = new JFormattedTextField();
        inputPanel.add(carNomeField);

        inputPanel.add(new JLabel("Quantidade"));
        carQuantidadeField = new JFormattedTextField();
        inputPanel.add(carQuantidadeField);

        inputPanel.add(new JLabel("Preço Cliente VIP"));
        carPClienteVIPField = new JFormattedTextField();
        inputPanel.add(carPClienteVIPField);



        inputPanel.add(new JLabel("Preço Cliente"));
        carPClienteField = new JFormattedTextField();
        inputPanel.add(carPClienteField);



        add(inputPanel);

        inputPanel.add(new JLabel("Descrição"));
        carDescricaoField = new JFormattedTextField();
        inputPanel.add(carDescricaoField);
        add(inputPanel);

        JPanel botoes = new JPanel();

        botoes.add(cadastrar = new JButton("Cadastrar"));
        cadastrar.setBackground(Color.GREEN);
        cadastrar.setForeground(Color.WHITE); 

        

 // Adiciona um ActionListener para o botão "Cadastrar"
        cadastrar.addActionListener(e->  {
                // Adiciona o código ao campo
                String proximoCodigo = formatasr(String.valueOf(contador));
                carCodigoField.setValue(proximoCodigo);

        }); 


        //============================================

        
        //
        
        botoes.add(apagar = new JButton("Apagar"));
        Color vermelhoColor = new Color(196, 1, 8); // para mudar a cor necessitava criar um obj e dar o set para esse
        // obj
        apagar.setBackground(vermelhoColor);
        apagar.setForeground(Color.WHITE);
        // apagar.setBorder((new java.awt.Color(0, 0, 0), 2));
        
        //
        botoes.add(editar = new JButton("Editar"));

        botoes.add(limpar = new JButton("Limpar"));

        botoes.add(adicionar = new JButton("Add. Imagem"));
        botoes.add(labelImg = new JLabel());
        labelImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        add(botoes);

        // tabela de produtos
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Cód", "Nome", "Quantidade", "Preço Cliente VIP", "Preço Cliente", "Descrição", "Estoque" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // criar a tabela caso nao exista
        new ProdutosDAO().criarTabela();
        // atualizar a tabela na abertura da janela
        atualizarTabela();

        // tratamento de eventos do construtor
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    carCodigoField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    carNomeField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    carQuantidadeField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    carPClienteVIPField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    carPClienteField.setText((String) table.getValueAt(linhaSelecionada, 4));
                    carDescricaoField.setText((String) table.getValueAt(linhaSelecionada, 5));
                }
            }
        });

        table.getColumnModel().getColumn(6).setCellRenderer(
            new DefaultTableCellRenderer(){
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
             
                       Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                       if (value != null && value instanceof Boolean) {
                        boolean statusEstoque = (Boolean) value;
                        if (!statusEstoque) {
                            c.setBackground(vermelhoColor);
                            
                        } else {
                            c.setBackground(Color.GREEN);
                            
                        }
                    }
               
                return c;
                }
            }
        );

        
        ProdutosController operacoes = new ProdutosController(produtos, tableModel, table);




        cadastrar.addActionListener(e -> {
            try {

                if (operacoes.validarDados(carCodigoField.getText(), carNomeField.getText(),
                        carQuantidadeField.getText(), carPClienteVIPField.getText(), carPClienteField.getText(),
                        carDescricaoField.getText())) {
                    operacoes.cadastrar(carCodigoField.getText(), carNomeField.getText(), carQuantidadeField.getText(),
                            carPClienteVIPField.getText(), carPClienteField.getText(), carDescricaoField.getText());
                    limparCampos();
                    atualizarTabela();
                   
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Produto.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
            }

        });

        // ---- AÇÃO DE EDITAR Produto ----
        editar.addActionListener(e -> {
            
            
            try {

                if (operacoes.validarDados(carCodigoField.getText(), carNomeField.getText(),
                        carQuantidadeField.getText(), carPClienteVIPField.getText(), carPClienteField.getText(),
                        carDescricaoField.getText())) {
                    operacoes.atualizar(carCodigoField.getText(), carNomeField.getText(), carQuantidadeField.getText(),
                            carPClienteVIPField.getText(), carPClienteField.getText(), carDescricaoField.getText());
                    limparCampos();
                     atualizarTabela();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Produto.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(e -> {
            // Chama o método "apagar" do objeto operacoes com o valor do campo de
            // entrada "placa"
            operacoes.apagar(carCodigoField.getText());
            // Limpa os campos de entrada após a operação de exclusão
            limparCampos();
             atualizarTabela();
        });

        limpar.addActionListener(e -> {
            limparCampos();
        });

        adicionar.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(TelaCadastroProdutos.this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                exibirImagem(file);
            }

        });

    }

    // método(atualizar tabela)
    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        produtos = new ProdutosDAO().listarTodos();
        // Obtém os produtos atualizados do banco de dados
        for (Produtos produto : produtos) {
            String quantidadeEstoqueString = produto.getQuantidade();
            int quantidadeEstoque = Integer.parseInt(quantidadeEstoqueString);
            boolean statusEstoque = true;
            if(quantidadeEstoque < 10){
                statusEstoque = false;
            } 
            // Adiciona os dados de cada produto como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { 
                produto.getCodigo(),
                 produto.getNome(),
                    produto.getQuantidade(),
                     produto.getPrecoClienteVIP(),
                      produto.getPrecoCliente(),
                       produto.getDescricao(),
                        statusEstoque
                     });
        }
    }

    private void limparCampos() {
        carCodigoField.setText("");
        carNomeField.setText("");
        carQuantidadeField.setText("");
        carPClienteVIPField.setText("");
        carPClienteField.setText("");
        carDescricaoField.setText("");
    }

    private MaskFormatter formatar(String mascara) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(mascara);
        } catch (ParseException e) {
            System.out.println("Formatacao com erro" + e);
        }
        return mask;
    }

    private void exibirImagem(File file) {
        ImageIcon icon = new ImageIcon(file.getPath());
        labelImg.setIcon(icon);
    }

    private String formatasr(String pattern){
          // Formata o padrão usando DecimalFormat para garantir 5 dígitos
        NumberFormat nf = new DecimalFormat(pattern);
        return nf.format(contador);
    }

}