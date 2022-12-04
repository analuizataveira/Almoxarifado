package Model.Exceptions;

public class CargoInvalido extends Exception{

    public CargoInvalido(){
        System.out.println("Voce nao consegue solicitar nenhum componente");
    }
}
