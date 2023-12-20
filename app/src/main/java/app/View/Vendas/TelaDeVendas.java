package app.View.Vendas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class TelaDeVendas extends JPanel {

      private Date dataAtual;
    public TelaDeVendas() {

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatar);
        

        // valorVenda

        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "dataAtual", dataFormatada);
        
           


       


        JFreeChart chart = ChartFactory.createBarChart3D("VEndas", "dias", "valores", dataset,PlotOrientation.VERTICAL , isBackgroundSet(), getIgnoreRepaint(), getFocusTraversalKeysEnabled());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        add(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Chart Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel panel = new TelaDeVendas();
            frame.add(panel);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
