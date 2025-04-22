package vista;

import Usuarios.Cliente;

public class vistaBasica {
    protected Cliente cliente;

    public vistaBasica(Cliente cliente) {
        this.cliente = cliente;
    }

    public void mostrarMenu() {
        System.out.println("Bienvenido, " + cliente.getLogin());
        System.out.println("1. Ver historial de compras");
        System.out.println("2. Salir");

        
    }
}
