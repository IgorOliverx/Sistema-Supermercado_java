package app.Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.Connection.ClientesDAO;
import app.Model.ClienteVIP;
import app.View.Cadastro.TelaDeCadastroClienteVIP;

public class ClientesController {
    private List<ClienteVIP> clientesVIP;
    private DefaultTableModel tableModel;
    private JTable table;
    ClienteVIP cliente;

    //Construtor inicializando atributos
    public ClientesController(List<ClienteVIP> clientesVIP, DefaultTableModel tableModel, JTable table){
        this.clientesVIP = clientesVIP;
        this.tableModel = tableModel;
        this.table = table;

    }
    //Construtor vazio
    public ClientesController() {}

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientesVIP = new ClientesDAO().listarTodos();
        // Obtém os clientes atualizados do banco de dados
        for (ClienteVIP cliente : clientesVIP) {
            // Adiciona os dados de cada cliente como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getCpf(),

                cliente.getTelefone(), cliente.getSexo(), cliente.getEmail(), cliente.getData()});
        }
    }

    // Método para cadastrar um novo cliente no banco de dados
     public void cadastrar(String nome, String cpf, String telefone,String sexo, String email, String data) {
        ClienteVIP novoCliente = new ClienteVIP(nome, cpf, telefone, sexo, email, data);
        new ClientesDAO().cadastrar(novoCliente);
        // Chama o método de cadastro no banco de dados
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        atualizarTabela();
        
    }

    public void deletar(String cpf){
        Object[] opcoes = { "Sim", "Não" };

      int linhaSelecionada = table.getSelectedRow();

      try{
      if(linhaSelecionada >= 0){
        int resposta = JOptionPane.showOptionDialog(null,
        "Você tem certeza que quer excluir este cliente? ",
        "Excluir Cliente", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if(resposta == JOptionPane.YES_OPTION){
            new ClientesDAO().deletar(cpf);
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
            atualizarTabela();
        }
    }else{
         JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente", "Erro nos dados", JOptionPane.ERROR_MESSAGE);
    }
      }catch (IllegalArgumentException e) {
            // Exibe um JOptionPane para campos vazios ou incorretos
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
        }



    }

    public void atualizar(String nome, String cpf, String telefone,String sexo, String email, String data){
        ClienteVIP clienteAtualizado = new ClienteVIP(nome, cpf, telefone, sexo, email, data);
        new ClientesDAO().atualizar(clienteAtualizado);
        JOptionPane.showMessageDialog(null, "Cliente editado com sucesso");

    }

    public boolean validarDados(String nome, String cpf, String telefone,String sexo, String email, String data){
        boolean dadosValidos = true;

        try {
            // Verifica se algum campo está vazio
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || sexo.isEmpty() || email.isEmpty() || data.isEmpty()) {
                throw new IllegalArgumentException("Por favor, preencha todos os campos.");
            }

            if(!validarEmail(email)){
                throw new IllegalArgumentException("Por favor, preencha corretamente o Email");
            }

        
        } catch (IllegalArgumentException e) {
            // Exibe um JOptionPane para campos vazios ou incorretos
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
            dadosValidos = false;
        }


        return dadosValidos;
    }

    public boolean validarEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(regex);
    }
}
