package app.View.Hierarquia;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import app.Controller.ProdutosController;
import app.View.Cadastro.TelaCadastroProdutos;
import app.View.Cadastro.TelaDeCadastroClienteVIP;
import app.View.Compras.PanelCompra;

/**
 * JanelaGerente
 */
public class JanelaGerente extends JFrame{
    private JTabbedPane jTPane;
    public JanelaGerente() {
        super("Dashboard - Gerente");
         setResizable(false);
         setIcon();

        jTPane = new JTabbedPane();
        add(jTPane);
        // criandos as tabs
        // tab1 carros
        TelaDeCadastroClienteVIP tab1 = new TelaDeCadastroClienteVIP();
        jTPane.add("Cad. Clientes", tab1);
        setBounds(200, 200, 832, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TelaCadastroProdutos tab3 = new TelaCadastroProdutos();
        jTPane.add("Cad. Produtos", tab3);
        PanelCompra tab4 = new PanelCompra();
        jTPane.add("Vendas", tab4);
       
 

 

// evento pra att combobox
jTPane.addChangeListener(event-> {
    tab4.atualizarComboBox();
    tab3.atualizarTabela();
}); 

       
    }



    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../resources/i.jpg")));
    }


    
}