import Model.*;
import Model.Exceptions.CargoInvalido;
import Model.Exceptions.ComponenteInvalido;
import br.DAO.ConnectionDAO;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CargoInvalido {

        //Variável para armazenar entrada de dados
        Scanner input = new Scanner(System.in);


        //Objeto para conexão com banco de dados
        ConnectionDAO connect = new ConnectionDAO(){
        };

        ArrayList<Componente> componentes = new ArrayList<>();
        ArrayList<Colaborador> colaboradors = new ArrayList<>();

        boolean flag = true; // Flag de controle do while
        int op = 0;

        while (flag) {
            System.out.println("--- BEM VINDO ---");
            System.out.println("1 - Cadastrar componente");
            System.out.println("2 - Consultar componentes disponíveis");
            System.out.println("3 - Solicitar componente");
            System.out.println("4 - Sair do sistema");

            try {
                op = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println(e);
                op = 0;
                input.nextLine();
            }
            switch (op) {
                case 1:
                    try {

                        System.out.println("Entre com seu cargo: ");
                        String nomecolab = input.nextLine();

                        //Verificando se o funcionario é um colaborador do almoxarifado
                        if (nomecolab.equals("Colaborador")) {

                            //Objeto componente sendo criado
                            Componente c = new Componente();

                            System.out.println("É permitido que seja feito o cadastro");

                            System.out.println("Tipo do Componete: ");
                            String nomecomponente = input.nextLine();
                            c.setTipo(nomecomponente);

                            System.out.println("ID Componente");
                            int id = input.nextInt();
                            c.setIdComponente(id);

                            //Criando um componente no banco de dados
                            connect.insertComp(c);

                            if (nomecomponente.equals("Capacitor") || nomecomponente.equals("Resistor"))
                                System.out.println("EH POSSIVEL CADASTRAR COMPONENTES DESSE TIPO");
                            else
                                throw new ComponenteInvalido();

                            if (nomecomponente.equals("Resistor")) {

                                //Criando objeto resistor
                                Resistor re = new Resistor();
                                System.out.println("Resistencia do Resistor em omhs");
                                float res = input.nextFloat();
                                re.setResistencia(res);
                                System.out.println("Quantidade de resistores com a resistencia acima");
                                int qtd = input.nextInt();
                                re.setQtd(qtd);
                                re.Componente_idComponente= c.getIdComponente();

                                componentes.add(re);

                                connect.insertRes(re);

                            }


                            else if (nomecomponente.equals("Capacitor")) {
                                Capacitor cap = new Capacitor();
                                System.out.println("Capacitancia do Capacitor");
                                float capac = input.nextFloat();
                                cap.setCapacitancia(capac);

                                System.out.println("Quantidade de Capacitores com a capacitancia acima");
                                int qtd = input.nextInt();
                                cap.setQtd(qtd);

                                cap.Componente_idComponente=c.getIdComponente();

                                componentes.add(cap);

                                connect.insertCap(cap);

                            }


                        } else {
                            System.out.println("Você não pode realizar essa opção");
                        }


                    } catch (Exception e) {
                        System.out.println(e);
                        input.nextLine();
                    }

                    break;

                case 2:
                    System.out.println("COMPONENTES DISPONIVEIS NO ALMOXERIFADO");
                    for (int i = 0; i < componentes.size(); i++) {

                        if (componentes.get(i) instanceof Capacitor) {
                            System.out.println("CAPACITOR");
                            Capacitor aux = new Capacitor();
                            aux = (Capacitor) componentes.get(i);
                            aux.mostraInfo();
                        }

                        if (componentes.get(i) instanceof Resistor) {
                            System.out.println("RESISTOR");
                            Resistor aux = new Resistor();
                            aux = (Resistor) componentes.get(i);
                            aux.mostraInfo();

                        }

                    }
                    break;


                case 3:
                    Colaborador colab = new Colaborador();
                    System.out.println("Nome do colaborador: ");
                    String nomecolab = input.nextLine();
                    colab.setNome(nomecolab);
                    System.out.println("Cargo colaborador");
                    String cargocolab = input.nextLine();
                    if (cargocolab.equals("Aluno") || cargocolab.equals("Professor") || cargocolab.equals("Colaborador Almoxerifado"))
                        System.out.println("VOCE CONSEGUE SOLICITAR COMPONENTES!! ");
                    else
                        throw new CargoInvalido();

                    if (cargocolab.equals("Professor")) {
                        Professor prof = new Professor();
                        System.out.println("ID: ");
                        int id = input.nextInt();
                        prof.setId(id);
                        colaboradors.add(prof);
                    } else if (cargocolab.equals("Colaborador Almoxerifado")) {
                        ColabAlmo colabalmo = new ColabAlmo();
                        System.out.println("ID: ");
                        int id = input.nextInt();
                        colabalmo.setId(id);
                        colaboradors.add(colabalmo);
                    } else if (cargocolab.equals("Aluno")) {
                        Aluno aluno = new Aluno();
                        System.out.println("Matricula: ");
                        int matri = input.nextInt();
                        aluno.setMatricula(matri);
                        System.out.println("Periodo: ");
                        int peri = input.nextInt();
                        aluno.setPeriodo(peri);
                        input.nextLine();
                        System.out.println("Curso: ");
                        String cursoaluno = input.nextLine();
                        aluno.setCurso(cursoaluno);
                        colaboradors.add(aluno);
                    }
                    colaboradors.add(colab);

                    input.nextLine();
                    System.out.println("Componente a ser requerido:");
                    String nomecomporeq = input.nextLine();

                    for (int i = 0; i < componentes.size(); i++) {

                        if (nomecomporeq.equals("Capacitor")) {
                            if (componentes.get(i) instanceof Capacitor) {

                                Capacitor aux = new Capacitor();
                                aux = (Capacitor) componentes.get(i);

                                System.out.println("Digite o capacitancia requerida:");

                                int capacrequerida = input.nextInt();

                                if (aux.getCapacitancia() == capacrequerida) {
                                    System.out.println("Temos capacitor com essa capacitancia");
                                } else {
                                    System.out.println("Nao temos capacitor com essa capacitancia");
                                    break;
                                }

                                System.out.println("Digite a quantidade requerida:");

                                int quantrequerida = input.nextInt();

                                if (aux.getQtd() - quantrequerida < 0) {
                                    System.out.println("Quantidade indisponivel");
                                    break;
                                } else {
                                    System.out.println("Solicitacao confirmada");
                                    int quantnova = aux.getQtd() - quantrequerida;
                                    aux.setQtd(quantnova);
                                }

                            }
                            break;
                        } else if (nomecomporeq.equals("Resistor")) {

                            if (componentes.get(i) instanceof Resistor) {

                                Resistor aux = new Resistor();
                                aux = (Resistor) componentes.get(i);

                                input.nextLine();

                                System.out.println("Digite o Resistencia requerida:");


                                int resisrequerida = input.nextInt();

                                if (aux.getResistencia() == resisrequerida) {
                                    System.out.println("Temos resistor com essa resistencia");
                                } else {
                                    System.out.println("Nao temos resistor com essa resistencia");
                                    break;
                                }

                                System.out.println("Digite a quantidade requerida:");

                                int quantrequerida = input.nextInt();

                                if (aux.getQtd() - quantrequerida < 0) {
                                    System.out.println("Quantidade indisponivel");
                                    break;
                                } else {
                                    System.out.println("Solicitacao confirmada");
                                    int quantnova = aux.getQtd() - quantrequerida;
                                    aux.setQtd(quantnova);
                                }

                            }
                            break;
                        }
                        break;
                    }


                case 4 :
                    flag = false;
                    break;

            }
        }
    }
}

