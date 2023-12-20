package app.View.Cadastro;

import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.Connection.ClientesDAO;
import app.Controller.ClientesController;
import app.Logs.Log;
import app.Model.ClienteVIP;

public class TelaDeCadastroClienteVIP extends JPanel {
    // Atributos(componentes)
    private JButton cadastrar, apagar, editar, limpar;
    private JFormattedTextField carNomeField, carCpfField, carTelefoneField, carSexoField,
            carEmailField, carDataField;
    private List<ClienteVIP> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor(GUI-JPanel)
    
    public TelaDeCadastroClienteVIP() {
        super();
        // entrada de dados

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel painelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("Cadastro de Clientes");
        labelTitulo.setFont(new java.awt.Font("Segoe UI Historic", 2, 18));

        painelTitulo.add(labelTitulo);
        add(painelTitulo);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Nome"));
        carNomeField = new JFormattedTextField();
        inputPanel.add(carNomeField);

        inputPanel.add(new JLabel("Cpf"));
        carCpfField = new JFormattedTextField(formatar("###.###.###-##"));
        inputPanel.add(carCpfField);

        inputPanel.add(new JLabel("Telefone"));
        carTelefoneField = new JFormattedTextField(formatar("(##) #####-####"));
        inputPanel.add(carTelefoneField);

        inputPanel.add(new JLabel("Sexo"));
        carSexoField = new JFormattedTextField();
        inputPanel.add(carSexoField);

        inputPanel.add(new JLabel("Email"));
        carEmailField = new JFormattedTextField();
        inputPanel.add(carEmailField);
        add(inputPanel);

        inputPanel.add(new JLabel("Data"));
        carDataField = new JFormattedTextField();
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatar);
       carDataField.setText(dataFormatada);
        carDataField.setText(dataFormatada);
        inputPanel.add(carDataField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        cadastrar.setBackground(Color.GREEN);
        cadastrar.setOpaque(true);
       

        
        botoes.add(editar = new JButton("Editar"));




        botoes.add(apagar = new JButton("Apagar"));
        apagar.setBackground(Color.red);
        apagar.setOpaque(true);
      

        botoes.add(limpar = new JButton("Limpar"));

        add(botoes);

        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Nome", "Cpf", "Telefone", "Sexo", "Email", "Data" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // criar a tabela caso nao exista
        new ClientesDAO().criarTabela();
        // atualizar a tabela na abertura da janela
        atualizarTabela();

        // tratamento de eventos do construtor
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    carNomeField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    carCpfField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    carTelefoneField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    carSexoField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    carEmailField.setText((String) table.getValueAt(linhaSelecionada, 4));
                    carDataField.setText((String) table.getValueAt(linhaSelecionada, 5));
                }
            }
        });

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no
        // banco de dados
        ClientesController operacoes = new ClientesController(clientes, tableModel, table);

        // ---- AÇÃO DE CADASTRAR CLIENTE ----
        cadastrar.addActionListener(e -> {
            try {

                if (operacoes.validarDados(carNomeField.getText(), carCpfField.getText(), carTelefoneField.getText(),
                        carSexoField.getText(), carEmailField.getText(), carDataField.getText())) {
                    operacoes.cadastrar(carNomeField.getText(), carCpfField.getText(), carTelefoneField.getText(),
                            carSexoField.getText(), carEmailField.getText(), carDataField.getText());
                    limparCampos();
                }
            } catch (Exception ex) {
                // excessões genericas
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o cliente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

        });

        // ---- AÇÃO DE EDITAR CARRO ----
        editar.addActionListener(e -> {
            try {
                if (operacoes.validarDados(carNomeField.getText(), carCpfField.getText(), carTelefoneField.getText(),
                        carSexoField.getText(), carEmailField.getText(), carDataField.getText())) {
                    operacoes.atualizar(carNomeField.getText(), carCpfField.getText(), carTelefoneField.getText(),
                            carSexoField.getText(), carEmailField.getText(), carDataField.getText());
                    limparCampos();
                    atualizarTabela();
                }
            } catch (Exception ex) {
                // excessões genericas
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o cliente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(e -> {
            operacoes.deletar(carCpfField.getText());
            limparCampos();
            atualizarTabela();
        });

        limpar.addActionListener(e -> {
            limparCampos();
        });

    }

    // método(atualizar tabela)
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos();
        // Obtém os clientes atualizados do banco de dados
        for (ClienteVIP carro : clientes) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { carro.getNome(), carro.getCpf(),

                    carro.getTelefone(), carro.getSexo(), carro.getEmail(), carro.getData() });
        }
    }

    private void limparCampos() {
        carNomeField.setText("");
        carCpfField.setText("");
        carTelefoneField.setText("");
        carSexoField.setText("");
        carEmailField.setText("");
        carDataField.setText("");
    }

    private MaskFormatter formatar(String mascara) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(mascara);
        } catch (ParseException e) {
            new Log();
            Log.registrarOperacao("Formatação com erro" + e.getMessage());
        }
        return mask;
    }

    private void campos() {
        carNomeField.getText();
        carCpfField.getText();
        carTelefoneField.getText();
        carSexoField.getText();
        carEmailField.getText();
        carDataField.getText();
    }
 


}