package vista;

import java.util.Scanner;

import Principal.ParquePrincipal;
import Usuarios.Administrador;

public class vistaAdmin implements vistaGeneral {
	Administrador admin;
	ParquePrincipal parque;

	public vistaAdmin(ParquePrincipal parque) {
		this.parque = parque;
	}
	@Override
	public void comprarTiquetes() {
		
		// TODO Hacer
		
	}
	public void eliminar() {
		
		
	}
	//TODO Necesita los metodos del administrador 
	/*
	 * Añadir / eliminar atraccion
	 * Añadir / eliminar empleados
	 * 
	 * Metodos heredados de vistaGeneral
	 * 
	 * */

	@Override
	public void verMenu() {
		Scanner scanner = new Scanner(System.in);
	    boolean funciona = true;
	    
	    while (funciona) {
	        System.out.println("1. Agregar Atraccion ");
	        System.out.println("2. Ver Atracciones y Espectaculos: ");
	        System.out.println("3. Comprar Tiquetes");
	        System.out.println("4. Comprar FastPass");
	        System.out.println("5. salir");
	        System.out.println("Selecciona una opcion: ");
	        
	        String opcion = scanner.nextLine();  // Leer la opción dentro del bucle
	        
	        if (opcion.equals("1")) {
	        	parque.crearAtraccion();
	        	 
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
}
