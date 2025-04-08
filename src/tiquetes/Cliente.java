package tiquetes;

import java.util.ArrayList;

public class Cliente {
private ArrayList <Tiquete> tiquetes;

public Cliente(ArrayList<Tiquete> tiquetes) {
	super();
	this.tiquetes = tiquetes;
}

public ArrayList<Tiquete> getTiquetes() {
	return tiquetes;
}

public void setTiquetes(ArrayList<Tiquete> tiquetes) {
	this.tiquetes = tiquetes;
	//hola
}


}
