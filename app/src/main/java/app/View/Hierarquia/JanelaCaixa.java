package app.View.Hierarquia;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;


import app.View.Cadastro.TelaDeCadastroClienteVIP;
import app.View.Compras.PanelCompra;

/**
 * JanelaCaixa
 */
public class JanelaCaixa extends JFrame{
    private JTabbedPane jTPane;
    public JanelaCaixa() {
        super("Dashboard - Caixa");
         setResizable(false);
        setIcon();

        jTPane = new JTabbedPane();
        add(jTPane);
        // criandos as tabs
        // tab1 carros
        TelaDeCadastroClienteVIP tab1 = new TelaDeCadastroClienteVIP();
        jTPane.add("Cad. Clientes", tab1);
        PanelCompra tab2 = new PanelCompra();
        jTPane.add("Vendas", tab2);
        setBounds(200, 200, 832, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    jTPane.addChangeListener(e -> {
        tab2.atualizarComboBox();
    });   
       
    }

    

    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../resources/i.jpg")));

    }


    
}