package Model;

public class Capacitor extends Componente{

    private float capacitancia;

    private int qtd=0;

    public int Componente_idComponente;

    @Override
    public void mostraInfo() {
        System.out.println("Capacitancia :"+this.capacitancia);
        System.out.println("Quantidade: " +this.qtd);
    }

    public float getCapacitancia() {
        return capacitancia;
    }

    public void setCapacitancia(float capacitancia) {
        this.capacitancia = capacitancia;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
