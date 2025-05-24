package vista;

import java.io.IOException;
import java.util.Scanner;

import Principal.ParquePrincipal;
import Turnos.GestorTurnos;
import Usuarios.Administrador;
import Usuarios.Empleado;

public class vistaAdmin implements vistaGeneral {
	Administrador admin;
	ParquePrincipal parque;

	public vistaAdmin(ParquePrincipal parque) {
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
			System.out.println("5. Salir");
			System.out.println("Selecciona una opcion: ");
			String opcion = scanner.nextLine();
			switch (opcion) {
			case "5": {
				funcionando = false;
				break;
			}
			case "1": {
				System.out.println(parque.comprarTiquetesBas());
				System.out.println("Desea seguir comprando? (si/no)");
				String resp = scanner.nextLine();

				if (resp.toLowerCase().equals("no")) {
					funcionando = false;
				}
				break;
			}
			case "2": {
				System.out.println("Que categoria quiere comprar: ");
				System.out.println("1. Diamante");
				System.out.println("2. Oro");
				System.out.println("3. Familiar");
				System.out.println("Selecciona una opcion: ");
				String cat = scanner.nextLine();
				parque.comprarTiquetesGen(cat);
				System.out.println("Desea seguir comprando? (si/no)");
				String resp = scanner.nextLine();

				if (resp.toLowerCase().equals("no")) {
					funcionando = false;
					break;
				}
				break;
			}
			case "3": {
				parque.comprarTiquetesIndividual(opcion);
				System.out.println("Desea seguir comprando? (si/no)");
				String resp = scanner.nextLine();

				if (resp.toLowerCase().equals("no")) {
					funcionando = false;
				}
				break;
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
				} else {
					System.out.println("El identificador de su tiquete es: " + iden);
					System.out.println("Desea seguir comprando? (si/no)");
					String resp = scanner.nextLine();

					if (resp.toLowerCase().equals("no")) {
						funcionando = false;
					}
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Opcion inesperada " + opcion);
			}
		}

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
	public void verMenu() throws IOException {
		Scanner scanner = new Scanner(System.in);
	    boolean funciona = true;
	    
	    while (funciona) {
	        System.out.println("1. Agregar Atraccion: ");
	        System.out.println("2. Ver Atracciones y Espectaculos: ");
	        System.out.println("3. Comprar Tiquetes");
	        System.out.println("4. Comprar FastPass");
	        System.out.println("5. Ver restricciones Clima: ");
	        System.out.println("6. Ver restricciones salud: ");
	        System.out.println("7. Agregar restricciones clima: ");
	        System.out.println("8. Añadir Turnos: ");
	        System.out.println("9. salir");
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
	        }else if (opcion.equals("9")) {
	        	 funciona = false;
	        	 break;
	    	}else if(opcion.equals("5")){
	        parque.verRestriccionClima();
	    	}else if(opcion.equals("6")){
		        parque.verRestriccionSalud();
		    }else if(opcion.equals("7")){
	
		    parque.crearRestriccionClima();;
		    	}
		    else if(opcion.equals("9")){
		        crearTurno();
		    	}
	    	else {
	            System.out.println("Opción no válida. Intente de nuevo.");
	        }
	    }
	    //scanner.close(); 

		
	}

	private void crearTurno() {
		Scanner scanner = new Scanner(System.in);
		String login = scanner.nextLine();
		System.out.println("Escriba el log in para el que va a crear el turno: ");
		Empleado empleado = (Empleado) parque.gestorUsuarios.getPersona(login);
	    if (empleado == null) {
	        System.out.println("Empleado no encontrado.");
	        return;
	    }
		System.out.println("¿Es turno para atracción o lugar de trabajo? (atraccion/lugar_trabajo)");
	    String tipoStr = scanner.nextLine();

	    Object destino = null;
	    if (tipoStr.equalsIgnoreCase("atraccion")) {
	        System.out.println("Ingrese nombre de la atracción:");
	        String nombre = scanner.nextLine();
	        destino = parque.gestorAtracciones.buscarAtraccionPorNombre(nombre);
	        if (destino == null) {
	            System.out.println("Atracción no encontrada.");
	            return;
	        }
	    } else if (tipoStr.equalsIgnoreCase("lugar_trabajo")) {
	        System.out.println("Ingrese nombre del lugar de trabajo:");
	        String nombre = scanner.nextLine();
	        destino = nombre;
	        if (destino == null) {
	            System.out.println("Lugar de trabajo no encontrado.");
	            return;
	        }
	    } else {
	        System.out.println("Tipo inválido.");
	        return;
	    }

	    System.out.println("Ingrese el día (lunes, martes, ...):");
	    String dia = scanner.nextLine();

	    System.out.println("Ingrese la franja horaria (mañana/tarde):");
	    String franja = scanner.nextLine();

	    parque.crearTurno(empleado, destino, tipoStr, dia, franja);
	}
	@Override
	public void comprarFastPass() {
		Scanner scanner = new Scanner(System.in);
		boolean funcio = true;
		while(funcio) {
			System.out.println("Para que fecha quiere comprar su FastPass? Responda año,mes,dia separados por ,");
			String fecha = scanner.nextLine();
			String retorno = parque.comprarFastPass(fecha);
			if (retorno.equals("-1")) {
				System.out.println("Algo salio mal, vuelva a intentarlo, recuerde usar el formato año,mes,dia");
				funcio = false;
				break;
			}
			else {
				System.out.println("Su fastPass es: "+retorno);
				System.out.println("Quiere comprar otro FastPass?");
				String wa = scanner.nextLine();
				if (wa.toLowerCase().equals("no")) {
					funcio = false;
					break;
				}
			}
		}

	}
}
