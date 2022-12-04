package Model;

public class Componente {

    private String Tipo;

    private int idComponente;


    public Componente() {
        idComponente++;
    }

    public void mostraInfo()
    {
        System.out.println("Tipo: "+Tipo);
    }


    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }
}
