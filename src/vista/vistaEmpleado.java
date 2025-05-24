package vista;

import java.util.Map;

import java.util.Scanner;

import Principal.ParquePrincipal;
import Turnos.DiaTurnos;
import Turnos.Turno;
import Usuarios.Empleado;
import Turnos.TipoTurno;

public class vistaEmpleado implements vistaGeneral{

	Empleado usuario;
	ParquePrincipal parque;

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

	@Override
	public void verMenu() {
		Scanner scanner = new Scanner(System.in);
	    boolean funciona = true;
	    
	    while (funciona) {
	        System.out.println("1. Ver compras: ");
	        System.out.println("2. Ver Atracciones y Espectaculos: ");
	        System.out.println("3. Comprar Tiquetes");
	        System.out.println("4. Comprar FastPass");
	        System.out.println("5. Comprar FastPass");
	        System.out.println("6. Salir");
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
	        	verTurnos();
	        }
	        else if (opcion.equals("6")) {
	        	 funciona = false;
	        	 break;
	    	}else {
	            System.out.println("Opción no válida. Intente de nuevo.");
	        }
	    }
	    scanner.close(); 
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
	
	public void verTurnos() {

		Map <String, DiaTurnos> dias = parque.verTurnos();
		for (Map.Entry<String, DiaTurnos> entry : dias.entrySet()) {
            String dia = entry.getKey();
            DiaTurnos turnos = entry.getValue();

            System.out.println("\nTurnos del " + dia + ":");

            for (Map.Entry<String, Turno> maniana : turnos.getMañana().entrySet()) {
                Turno t = maniana.getValue();
                System.out.println("Franja: Mañana");
                System.out.println("Login Empleado: " + t.empleado.getLogin());
                System.out.println("Tipo de Turno: " + t.getTipo());
                System.out.println("Lugar de trabajo: " + (t.getTipo() == TipoTurno.ATRACCION ? t.getAtraccion().getNombre() : t.lugarTrabajo.getNombre()));
                System.out.println("-----------------------------");
            }

            for (Map.Entry<String, Turno> tarde : turnos.getTarde().entrySet()) {
                Turno t = tarde.getValue();
                System.out.println("Franja: Tarde");
                System.out.println("Login Empleado: " + t.empleado.getLogin());
                System.out.println("Tipo de Turno: " + t.getTipo());
                System.out.println("Lugar de trabajo: " + (t.getTipo() == TipoTurno.ATRACCION ? t.getAtraccion().getNombre() : t.lugarTrabajo.getNombre()));
                System.out.println("-----------------------------");
            }
        }
		
	}
	

}
