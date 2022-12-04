package Model;

public class CI extends Componente{

    private String PortaLogica;
    private String Nome;

    public int idCi;

    public int Componente_numSerie;


    private int qtd=0;
    public String getPortaLogica() {
        return PortaLogica;
    }


    public void setQtd(int qtd) {
        qtd = qtd;
    }

    public void setPortaLogica(String portaLogica) {
        PortaLogica = portaLogica;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public void mostraInfo() {
        System.out.println("Porta lógica: "+this.PortaLogica);
        System.out.println("Nome :"+this.Nome);
    }


    public int getQtd() {
        return qtd;
    }
}
