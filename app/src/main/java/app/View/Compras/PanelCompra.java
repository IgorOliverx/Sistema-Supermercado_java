/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.View.Compras;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import app.Connection.ClientesDAO;
import app.Connection.ProdutosDAO;
import app.Connection.VendasDAO;
import app.Controller.VendasController;
import app.Model.ClienteVIP;
import app.Model.Produtos;
import app.Model.Vendas;

/**
 *
 * @author Igor Oliveira
 */
public class PanelCompra extends javax.swing.JPanel {

    /**
     * Creates new form PanelCompra
     */
    public PanelCompra() {
        initComponents();
        preencherDataSistema();
    }

    private void preencherDataSistema() {
        LocalDate dataAtual = LocalDate.now();

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatar);

        labelData1.setText(dataFormatada);
    }//da pra passar isso pro controller

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        ImageIcon imagemCliente = new ImageIcon(getClass().getResource(caminhoImgCliente));
        ImageIcon imagemClienteVIP = new ImageIcon(getClass().getResource(caminhoImgVIP));
        jPanel1 = new javax.swing.JPanel();
        labelTotal = new javax.swing.JFormattedTextField();
        labelBruto = new javax.swing.JFormattedTextField();
        labelDesconto = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        saidaProduto = new javax.swing.JFormattedTextField();
        jSpinnerQuantidade = new javax.swing.JSpinner();
        inputPesquisaProduto = new javax.swing.JFormattedTextField();
        inputPesquisaCliente = new javax.swing.JFormattedTextField(formatar("###.###.###-##"));
        comboBoxProduto = new javax.swing.JComboBox<>();
        comboBoxCliente = new javax.swing.JComboBox<>();
        labelImg = new javax.swing.JLabel(imagemCliente);
        btnVender = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        labelData1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(830, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(830, 600));

        labelTotal.setBackground(new java.awt.Color(204, 255, 204));
        labelTotal.setText("TOTAL: R$" + labelTotalValor);
        labelTotal.setEditable(false);

        labelBruto.setBackground(new java.awt.Color(204, 255, 204));
        labelBruto.setText("TOTAL BRUTO: R$" + labelBrutoValor);
        labelBruto.setEditable(false);

        labelDesconto.setBackground(new java.awt.Color(204, 255, 204));
        labelDesconto.setText("DESCONTO VIP R$" + labelDescontoValor);
        labelDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelDescontoActionPerformed(evt);
            }
        });
        labelDesconto.setEditable(false);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        saidaProduto.setBackground(new java.awt.Color(255, 255, 0));
         

        inputPesquisaProduto.setBackground(new java.awt.Color(255, 255, 204));

        inputPesquisaCliente.setBackground(new java.awt.Color(255, 255, 204));

        comboBoxProduto.setBackground(new java.awt.Color(255, 255, 0));
        produtos = new ProdutosDAO().listarTodos();
        comboBoxProduto.addItem("Selecione o Produto");
        for(Produtos produto : produtos){
            comboBoxProduto.addItem(produto.getNome()
            +" - "+ produto.getCodigo());
        }


        comboBoxCliente.setBackground(new java.awt.Color(255, 255, 0));
         clientes = new ClientesDAO().listarTodos();
        comboBoxCliente.addItem("Cliente");
        for(ClienteVIP cliente : clientes){
            comboBoxCliente.addItem(cliente.getNome()
            +"   -   "+ cliente.getCpf()
            );
        }

        labelImg.setIcon(imagemCliente);
        labelImg.revalidate();
        labelImg.repaint();
        


        btnVender.setText("Vender");

        btnCancelar.setText("Excluir");

        btnFinalizar.setText("Finalizar venda");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setText("Pesquisa do Cliente:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setText("Pesquisa do Produto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(saidaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(btnVender)
                        .addGap(18, 18, 18)
                        
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinalizar)
                        .addGap(208, 208, 208))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(saidaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFinalizar)
                        .addComponent(btnCancelar)
                        
                        .addComponent(btnVender)))
                .addGap(14, 14, 14))
        );

       
        tableModel = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Cod. Produto", "Nome Produto", "Quantidade", "Cliente", "Valor", "Data", "Cod. Venda"
            }
        );
        table = new JTable(tableModel);
        table.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jSPane.setViewportView(table);

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setText("DATA:");

        labelData1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        labelData1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelBruto)
                            .addComponent(labelDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                            .addComponent(labelTotal))
                        .addContainerGap())
                    .addComponent(jSPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(labelDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSPane, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addComponent(labelData1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        VendasController operacoes = new VendasController(vendas, tableModel, table);

        btnVender.addActionListener(e -> {
            String produtoSelecionado = comboBoxProduto.getSelectedItem().toString();
            String clienteSelecionado = comboBoxCliente.getSelectedItem().toString();
            int qnt = (int) jSpinnerQuantidade.getValue();
        
            if (operacoes.realizarVenda(produtoSelecionado, clienteSelecionado, qnt)) {
                String quantidade = String.valueOf(qnt);
                String[] produtoInfo = produtoSelecionado.split(" - ");
                String nomeProduto = produtoInfo[0].trim();
                String codigoProduto = produtoInfo[1].trim();
        
                double precoProduto = new ProdutosDAO().consulta(codigoProduto);
                double valorTotalINT = precoProduto * qnt;
                String valorTotal = String.valueOf(valorTotalINT);

                        saidaProduto.setText(nomeProduto + "                        R$"+ valorTotal);
                        saidaProduto.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
                operacoes.cadastrar(codigoProduto, nomeProduto, quantidade, clienteSelecionado, valorTotal, labelData1.getText());
                atualizarTabela();
                calcularValoresTabela();
                if(comboBoxCliente.getSelectedIndex() > 0){
                labelImg.setIcon(imagemClienteVIP);
                labelImg.revalidate();
                labelImg.repaint();
        }
                int index = comboBoxCliente.getSelectedIndex();
                comboBoxCliente.setEnabled(false);
                comboBoxCliente.setEditable(false);
            }
        });

        btnCancelar.addActionListener(e -> {
         int cod = new VendasDAO().recuperaCodigo();
         String codigoVenda = String.valueOf(cod);
            operacoes.deletar(codigoVenda);
            atualizarTabela();
            calcularValoresTabela();
        });
        
        btnFinalizar.addActionListener(e -> {
            operacoes.limpar();
            new TeladeCompras().setVisible(true);

        });
        
        
    }// </editor-fold>//GEN-END:initComponents

    private void labelDescontoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_labelDescontoActionPerformed

    }

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private MaskFormatter formatar(String mensagem) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(mensagem);
        } catch (ParseException e) {
            throw new RuntimeException("Erro na mascara" + e.getMessage());
        }
        return mask;
    }

   
    public void atualizarComboBox() {

        clientes = new ClientesDAO().listarTodos();
        comboBoxCliente.removeAllItems();
        comboBoxCliente.addItem("Selecione o cliente");
        for (ClienteVIP cliente : clientes) {
            comboBoxCliente.addItem(cliente.getNome()
                    + "   -   " + cliente.getCpf());
        }

        produtos = new ProdutosDAO().listarTodos();
        comboBoxProduto.removeAllItems();
        comboBoxProduto.addItem("Selecione o produto:");
        for (Produtos produto : produtos) {
            comboBoxProduto.addItem(produto.getNome()
                    + " - " + produto.getCodigo());
        }

    }

    // Calcule os valores da tabela para atualizar as labels
    private void calcularValoresTabela() {
        double total = 0;

        // Percorre todas as linhas da tabela
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String valorString = tableModel.getValueAt(i, 4).toString();
            double valor = Double.parseDouble(valorString);

            total += valor;
        }

        labelTotalValor = total;
        labelTotal.setText("TOTAL: " + total);
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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
   
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> comboBoxCliente;
    private List<ClienteVIP> clientes;
    private javax.swing.JComboBox<String> comboBoxProduto;
    private List<Produtos> produtos;
    private javax.swing.JFormattedTextField inputPesquisaCliente;
    private javax.swing.JFormattedTextField inputPesquisaProduto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jSPane;
    private javax.swing.JSpinner jSpinnerQuantidade;
    private javax.swing.JTable table;
    private javax.swing.JFormattedTextField labelBruto;
    private javax.swing.JLabel labelData1;
    private javax.swing.JFormattedTextField labelDesconto;
    private javax.swing.JLabel labelImg;
    public javax.swing.JFormattedTextField labelTotal;
    private javax.swing.JFormattedTextField saidaProduto;
    private DefaultTableModel tableModel;
    private List<Vendas> vendas;
    private double labelBrutoValor = 0;
    private double labelDescontoValor = 0;
    public double labelTotalValor = 0;
    String caminhoImgCliente = "./../resources/cliente.png";
    String caminhoImgVIP = "./../resources/cartao-vip.png";

    

    private int linhaSelecionada = -1;
    // End of variables declaration//GEN-END:variables
}
