package Principal;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Usuarios.Cliente;
import persistencia.LectorUsuario;
import persistencia.LectorUsuario;
import vista.vistaAdmin;
import vista.vistaBasica;
import vista.vistaEmpleado;

public class ParquePrincipal {

    private static ArrayList<Cliente> clientes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LectorUsuario lector = new LectorUsuario();

        try {
            clientes = lector.leerClientes("./data/Clientes.txt");
        } catch (IOException e) {
            System.err.println("No se pudo cargar la información de los usuarios.");
            return;
        }

        System.out.println("Bienvenido al Sistema del Parque de Diversiones");

        boolean loginExitoso = false;
        while (!loginExitoso) {
            System.out.print("Ingrese su usuario: ");
            String login = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            if (login.equals("admin") && password.equals("admin")) {
                vistaAdmin admin = new vistaAdmin();
                admin.mostrarMenu();
                loginExitoso = true;
            } else if (login.equals("empleado") && password.equals("empleado")) {
                vistaEmpleado empleado = new vistaEmpleado();
                empleado.mostrarMenu();
                loginExitoso = true;
            } else {
                Cliente cliente = autenticarCliente(login, password);
                if (cliente != null) {
                    vistaBasica vistaCliente = new vistaBasica(cliente);
                    vistaCliente.mostrarMenu();
                    loginExitoso = true;
                } else {
                    System.out.println("Credenciales incorrectas. Intente de nuevo.");
                }
            }
        }
    }

    private static Cliente autenticarCliente(String login, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login) && cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        return null;
    }
}  