package tiquetes;

public class Exclusividad {
    private boolean familiar;
    private boolean oro;
    private boolean diamante;

    public Exclusividad(boolean familiar, boolean oro, boolean diamante) {
        this.familiar = familiar;
        this.oro = false;
        this.diamante = false;
        
        if (familiar) {
            setFamiliar(true);
        } else if (oro) {
            setOro(true);
        } else if (diamante) {
            setDiamante(true);
        }
    }

    public boolean isFamiliar() {
        return familiar;
    }

    public void setFamiliar(boolean familiar) {
        if (familiar) {
            this.familiar = true;
            this.oro = false;
            this.diamante = false;
        } else {
            this.familiar = false;
        }
    }

    public boolean isOro() {
        return oro;
    }

    public void setOro(boolean oro) {
        if (oro) {
            this.oro = true;
            this.familiar = false;
            this.diamante = false;
        } else {
            this.oro = false;
        }
    }

    public boolean isDiamante() {
        return diamante;
    }

    public void setDiamante(boolean diamante) {
        if (diamante) {
            this.diamante = true;
            this.familiar = false;
            this.oro = false;
        } else {
            this.diamante = false;
        }
    }

    public String getExclusividad() {
        if (isFamiliar()) {
            return "familiar";
        }
        if (isOro()) {
            return "oro";
        }
        if (isDiamante()) {
            return "diamante";
        }
        return "ninguno"; 
    }
   
}
