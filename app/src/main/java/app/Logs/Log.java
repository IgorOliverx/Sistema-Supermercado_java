package app.Logs;


import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;

public class Log {

    private static final String CAMINHO_ARQUIVO = "./registros.txt";
     private static final String CAMINHO_ARQUIVO2 = "./registros2.txt";
    static File arquivo = new File(CAMINHO_ARQUIVO);
    static File arquivo2 = new File(CAMINHO_ARQUIVO2);

    public Log() {
        try {
            // Verifique se o arquivo de registro já existe
            if (!arquivo.exists() && !arquivo2.exists()) {
                arquivo.createNewFile();
                arquivo2.createNewFile();
            }
        } catch (IOException e) {//Tratamento do erro se houver
            e.printStackTrace();
            }

    }

    public static void registrarOperacao(String mensagem) {
        try {
            
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escreva a mensagem no arquivo
            bw.write(mensagem);
            bw.newLine(); // Adicione uma quebra de linha para separar as operações

            // Feche o arquivo
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Lide com possíveis erros ao abrir/gravar no arquivo, por exemplo, lançando
            // exceções personalizadas ou registrando erros
        }
    }

    public static void registrarLogin(String mensagem, Date data){
          try {
            
            FileWriter fw = new FileWriter(arquivo2, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escreva a mensagem no arquivo
            bw.write(mensagem + data);
            bw.newLine(); // Adicione uma quebra de linha para separar as operações

            // Feche o arquivo
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Lide com possíveis erros ao abrir/gravar no arquivo, por exemplo, lançando
            // exceções personalizadas ou registrando erros
        }
    }
}