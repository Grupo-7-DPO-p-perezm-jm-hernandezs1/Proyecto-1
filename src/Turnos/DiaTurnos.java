package Turnos;

import java.util.HashMap;

public class DiaTurnos {
    public HashMap<String, Turno> mañana = new HashMap<>();
    public HashMap<String, Turno> tarde = new HashMap<>();

    public void limpiar() {
        mañana.clear();
        tarde.clear();
    }

	public HashMap<String, Turno> getMañana() {
		return mañana;
	}

	public HashMap<String, Turno> getTarde() {
		return tarde;
	}
	public boolean tieneTurno(String login, String franja) {
        if (franja.equalsIgnoreCase("mañana")) {
            return mañana.containsKey(login);
        } else if (franja.equalsIgnoreCase("tarde")) {
            return tarde.containsKey(login);
        }
        return false;
    }
    
}
