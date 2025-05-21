package vista;

import java.util.Scanner;

import Usuarios.Cliente;
import Principal.ParquePrincipal;

public class VistaUsuario implements vistaGeneral {
	//TODO Solo es hacer los metodos lol
	Cliente usuario;
	ParquePrincipal parque;


	public VistaUsuario(Cliente usuario,ParquePrincipal parque) {
		this.usuario = usuario;
		this.parque = parque;
	}

	@Override
	public void comprarTiquetes() {
		System.out.println("Tipos de tiquetes");
        System.out.println("1. Tiquete Basico");
        System.out.println("2. Tiquete General");
        System.out.println("3. Tiquete Individual");
        System.out.println("4. Tiquete Temporada");
        System.out.println("Selecciona una opcion: ");
		
		
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
	        	 parque.printCompras(usuario);
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
	    scanner.close();  // Cerrar el scanner al salir
	}

	@Override
	public void comprarFastPass() {
		// TODO Auto-generated method stub
		
	}
}
