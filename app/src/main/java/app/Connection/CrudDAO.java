package app.Connection;

import java.sql.Connection;
import java.util.List;


/**
 * Classe abstrata que define todas as operações básicas CRUD
 * Todas as classes DAO devem herdar desta(exceto login).
 */
public abstract class CrudDAO<Modelo> {

     private Connection connection;

  // Construtor vazio
    public CrudDAO() {
        // Pode permanecer vazio ou com alguma inicialização padrão, se necessário
    }

    // Construtor para inicializar conexão
    public CrudDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void criarTabela();
    
    public abstract Modelo procurarPorId(String id);

    public abstract List<Modelo> listarTodos();

    public abstract void cadastrar(Modelo entidade);

    public abstract void atualizar(Modelo entidade);

    public abstract void deletar (String id);

    public abstract double consulta (String codigo);

    public abstract int verificarEstoque(String codigo, int quantidade);



}
