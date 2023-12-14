package app;

import app.Connection.LoginDAO;
import app.Controller.LoginController;
import app.View.TelaDeInicio;

public class Main {
    public static void main(String[] args) {

       new LoginController().inicializacao();
       
    }
}