package vista;

import java.util.Scanner;

import Usuarios.Cliente;
import Principal.ParquePrincipal;

public class VistaUsuario implements vistaGeneral {
	//TODO Solo es hacer los metodos lol
	Cliente usuario;
	ParquePrincipal parque;
	@Override
	public void verAtracciones() {
		
		//TODO LUCASSSSSSS
		
	}

	public VistaUsuario(Cliente usuario,ParquePrincipal parque) {
		this.usuario = usuario;
		this.parque = parque;
	}

	@Override
	public void comprarTiquetes() {
		// TODO Auto-generated method stub
		
	}
	public void verTiquetesComprados(){
		//TODO Falta ver tiquetes comprados
	}
	

	@Override
	public void verMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Ver compras: ");
		System.out.println("2. Ver Atracciones y Espectaculos: ");
		System.out.println("Selecciona una opcion: ");
		String opcion = scanner.nextLine();
		if(opcion.equals("1")) {
			System.out.println("hola");
		}
		if(opcion.equals("2")) {
			parque.printAtraEsp();
		}
		// TODO Auto-generated method stub
		
	}
}
