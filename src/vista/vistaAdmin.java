package vista;

public class vistaAdmin extends vistaBasica {

    public vistaAdmin() {
        super(null); // No requiere cliente
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Menú de Administrador");
        System.out.println("1. Gestionar usuarios");
        System.out.println("2. Ver reportes");
        System.out.println("3. Salir");

        
    }
}
