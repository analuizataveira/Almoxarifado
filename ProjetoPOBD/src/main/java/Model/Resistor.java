package Model;

import java.sql.SQLOutput;

public class Resistor extends Componente{

    private float resistencia;

    private int qtd=0;

    public static int Componente_idComponente;


    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public void mostraInfo() {
        System.out.println("Resistencia: "+this.resistencia);
        System.out.println("Quantidade: "+this.qtd);

    }

    public float getResistencia() {
        return resistencia;
    }

    public void setResistencia(float resistencia) {
        this.resistencia = resistencia;
    }



}

