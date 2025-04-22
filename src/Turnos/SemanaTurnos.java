package Turnos;

public class SemanaTurnos {
    public DiaTurnos lunes = new DiaTurnos();
    public DiaTurnos martes = new DiaTurnos();
    public DiaTurnos miercoles = new DiaTurnos();
    public DiaTurnos jueves = new DiaTurnos();
    public DiaTurnos viernes = new DiaTurnos();
    public DiaTurnos sabado = new DiaTurnos();
    public DiaTurnos domingo = new DiaTurnos();

    public void reiniciarSemana() {
        lunes.limpiar();
        martes.limpiar();
        miercoles.limpiar();
        jueves.limpiar();
        viernes.limpiar();
        sabado.limpiar();
        domingo.limpiar();
    }

	public DiaTurnos getLunes() {
		return lunes;
	}

	public DiaTurnos getMartes() {
		return martes;
	}

	public DiaTurnos getMiercoles() {
		return miercoles;
	}

	public DiaTurnos getJueves() {
		return jueves;
	}

	public DiaTurnos getViernes() {
		return viernes;
	}

	public DiaTurnos getSabado() {
		return sabado;
	}

	public DiaTurnos getDomingo() {
		return domingo;
	}
    
}
