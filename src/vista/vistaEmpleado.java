package vista;

import java.util.Scanner;

import Principal.ParquePrincipal;
import Usuarios.Empleado;

public class vistaEmpleado implements vistaGeneral{

	Empleado usuario;
	ParquePrincipal parque;

	@Override
	public void comprarTiquetes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verMenu() {
		Scanner scanner = new Scanner(System.in);
	    boolean funciona = true;
	    
	    while (funciona) {
	        System.out.println("1. Ver compras: ");
	        System.out.println("2. Ver Atracciones y Espectaculos: ");
	        System.out.println("3. Comprar Tiquetes");
	        System.out.println("4. Comprar FastPass");
	        System.out.println("5. salir");
	        System.out.println("Selecciona una opcion: ");
	        
	        String opcion = scanner.nextLine();  // Leer la opción dentro del bucle
	        
	        if (opcion.equals("1")) {
	        	 parque.printComprasE(usuario);
	        } else if (opcion.equals("2")) {
	            parque.printAtraEsp();
	        } else if (opcion.equals("3")) {
	        	comprarTiquetes();
	        } else if (opcion.equals("4")) {
	        	comprarFastPass();
	        }else if (opcion.equals("5")) {
	        	 funciona = false;
	    	}else {
	            System.out.println("Opción no válida. Intente de nuevo.");
	        }
	    }
	    scanner.close(); 
	}
		
	

	@Override
	public void comprarFastPass() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Falta Ver turnos 
	 */

}
