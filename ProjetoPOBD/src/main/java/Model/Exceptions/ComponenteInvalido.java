package Model.Exceptions;

public class ComponenteInvalido extends Exception{

    public ComponenteInvalido(){
        System.out.println("Componentes desse tipo nao podem ser cadastrados no Sistema");
    }
}

