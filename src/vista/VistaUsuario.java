package vista;

import java.util.Scanner;

import Usuarios.Cliente;
import Principal.ParquePrincipal;

public class VistaUsuario implements vistaGeneral {
	
	Cliente usuario;
	ParquePrincipal parque;


	public VistaUsuario(Cliente usuario,ParquePrincipal parque) {
		this.usuario = usuario;
		this.parque = parque;
	}

	@Override
	public void comprarTiquetes() {
		 boolean funcionando = true;
		while (funcionando) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Tipos de tiquetes");
			System.out.println("1. Tiquete Basico");
			System.out.println("2. Tiquete General");
			System.out.println("3. Tiquete Individual");
			System.out.println("4. Tiquete Temporada");
			System.out.println("Selecciona una opcion: ");
			String opcion = scanner.nextLine();
			switch (opcion) {
			case "1": {

				parque.comprarTiquetesBas();
			}
			case "2": {
				parque.comprarTiquetesBas();
			}
			case "3": {
				parque.comprarTiquetesIndividual(opcion);
			}
			case "4": {
				System.out.println("Que categoria quiere comprar: ");
				System.out.println("1. Diamante");
				System.out.println("2. Oro");
				System.out.println("3. Familiar");
				System.out.println("Selecciona una opcion: ");
				String cat = scanner.nextLine();

				System.out.println("Que temporada quiere comprar: ");
				System.out.println("Primavera");
				System.out.println("Verano");
				System.out.println("Otoño");
				System.out.println("Invierno");
				System.out.println("Selecciona una opcion: ");

				String tempo = scanner.nextLine();
				String iden = parque.comprarTiquetesTemp(cat, tempo);
				if (iden.equals("-1")) {
					System.out.println("Categoria Invalida");
					funcionando = false;
				} else if (iden.equals("-2")) {
					System.out.println("Categoria Temporada");
					funcionando = false;
				}
				else {
					System.out.println("El identificador de su tiquete es: " + iden);
					System.out.println("Desea seguir comprando? (si/no)");
					String resp = scanner.nextLine();
					
					if (resp.toLowerCase().equals("no")) {
						funcionando=false;
					}
				}
			}
			default:
				throw new IllegalArgumentException("Opcion inesperada " + opcion);
			}
		}
		
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
