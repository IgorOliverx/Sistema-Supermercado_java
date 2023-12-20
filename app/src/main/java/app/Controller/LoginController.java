package app.Controller;

import javax.swing.JOptionPane;

import app.Connection.LoginDAO;
import app.Connection.VendasDAO;
import app.Logs.Log;
import app.View.TelaDeInicio;
import app.View.Hierarquia.JanelaGerente;
import app.View.Hierarquia.JanelaCaixa;

public class LoginController {
    private TelaDeInicio loginView;
    private boolean isGerente;
    private boolean isCaixa;

    // Construtor recebendo a view de login como parâmetro
    public LoginController(TelaDeInicio loginView) {
        this.loginView = loginView;
    }

    // Construtor padrão
    public LoginController() {
        super();
    }

    // Método para verificar o login
    public void verificaLogin(String usuario, String senha, String tipoUsuario) {
        LoginDAO login = new LoginDAO();

        // Verifica se os campos de usuário e senha estão preenchidos
        if (usuario == null || "".equals(usuario) || senha == null || "".equals(senha)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!", "",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Verifica se o login é válido
            boolean loginValido = login.verificarLogin(usuario, senha, tipoUsuario);

            if (loginValido) {
                // Caso o login seja válido, abre a janela correspondente ao tipo de usuário
                if (tipoUsuario.equals("gerente")) {
                    isGerente = true;
                    new JanelaGerente();
                    new Log();
                    Log.registrarOperacao("Login feito como gerente");
                } else if (tipoUsuario.equals("caixa")) {
                    isCaixa = true;
                    new JanelaCaixa();
                    new Log();
                    Log.registrarOperacao("Login feito como caixa");
                }
            } else {
                // Exibe mensagem de erro se o login for inválido
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Método para inicializar o sistema
    public void inicializacao() {
        // Inicializa o banco de dados e a tabela de login
        LoginDAO init = new LoginDAO();
        init.criaTabela();
        init.inicializarBanco();

        // Cria a tela de login
        TelaDeInicio loginView = new TelaDeInicio();
        
        // Inicia a tela de login a partir do controller
        LoginController loginController = new LoginController(loginView);
        
        // Inicializa a tabela de vendas
        VendasDAO ini = new VendasDAO();
        ini.criarTabela();
    }

    // Métodos para verificar se o usuário logado é um gerente ou caixa
    public boolean isGerente() {
        return isGerente;
    }

    public boolean isCaixa() {
        return isCaixa;
    }
}
