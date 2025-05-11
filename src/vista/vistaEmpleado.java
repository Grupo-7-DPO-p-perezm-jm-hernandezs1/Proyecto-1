package vista;

public class vistaEmpleado extends vistaBasica {

    public vistaEmpleado() {
        super(null); // No requiere cliente
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Menú de Empleado");
        System.out.println("1. Registrar nueva atracción");
        System.out.println("2. Ver ventas del día");
        System.out.println("3. Salir");

        
    }
}
