package vista;

import Usuarios.Cliente;

public class VistaUsuario implements vistaGeneral {
	//TODO Solo es hacer los metodos lol
	Cliente usuario;
	@Override
	public void verAtracciones() {
		
		//TODO LUCASSSSSSS
		
	}

	public VistaUsuario(Cliente usuario) {
		this.usuario = usuario;
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
		System.out.println("menu clientes");
		// TODO Auto-generated method stub
		
	}
}
