package app.Logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerarNotaFiscal {

    public static void gerarNotaFiscal(double valorTotal, String nomeCliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cupomfiscal.txt"))) {
            LocalDateTime dataAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dataAtual.format(formatter);

            String conteudoNota = "Cupom Fiscal\n\n" +
                    "Data: " + dataFormatada + "\n" +
                    "Cliente: " + nomeCliente + "\n" +
                    "Valor Total: R$" + valorTotal;

            writer.write(conteudoNota);
            System.out.println("Cupom fiscal gerado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o cupom fiscal: " + e.getMessage());
        }
    }
}

