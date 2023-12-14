package app.Controller;

import javax.swing.JOptionPane;

import app.Connection.LoginDAO;
import app.Connection.VendasDAO;
import app.View.TelaDeInicio;
import app.View.Hierarquia.JanelaGerente;
import app.View.Hierarquia.JanelaCaixa;

public class LoginController {
    private TelaDeInicio loginView;
    private boolean isGerente;
    private boolean isCaixa;

    public LoginController(TelaDeInicio loginView) {
        this.loginView = loginView;
    }

    public LoginController() {
        super();
    }
    
    public void verificaLogin(String usuario, String senha, String tipoUsuario) {
        LoginDAO login = new LoginDAO();
        if (usuario == null || "".equals(usuario) || senha == null || "".equals(senha)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!", "",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            boolean loginValido = login.verificarLogin(usuario, senha, tipoUsuario);
    
            if (loginValido) {
                if (tipoUsuario.equals("gerente")) {
                    isGerente = true;
                    new JanelaGerente();
                } else if (tipoUsuario.equals("caixa")) {
                    isCaixa = true;
                    new JanelaCaixa();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    

    public void inicializacao(){
        LoginDAO init = new LoginDAO();
        init.criaTabela();
        init.inicializarBanco();
        TelaDeInicio loginView = new TelaDeInicio();//criando um objeto da pagina de login
        LoginController loginController = new LoginController(loginView);//iniciando a pagina de login a partir do controller
        VendasDAO ini = new VendasDAO();
        ini.criarTabela();
                

    }

    public boolean isGerente() {
        return isGerente;
    }

    public boolean isCaixa() {
        return isCaixa;
    }
}
