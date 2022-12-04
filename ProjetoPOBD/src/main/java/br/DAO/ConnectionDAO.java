package br.DAO;

import Model.*;

import java.sql.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class ConnectionDAO {

    Connection connection; // objeto responsável por fazer a conexão com mysql
    Statement statement; // objeto responsável por preparar consultas "SELECT"
    ResultSet result; // objeto responsável por executar consultas "SELECT"
    PreparedStatement pst; // objeto responsável por preparar querys de manipulação dinâmica(INSERT, UPDATE, DELETE)

    static final String user = "root"; // usuário da instância local do servidor
    static final String password = "root"; // senha do usuário da instância local do servidor
    static final String database = "BancoAlmoxarifado"; // nome do banco de dados a ser utilizado

    // String com URL de conexão com o servidor
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;

    public void connect(){

        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }


    //--------------------INSERINDO NOVO REGISTRO DE CAPACITOR--------------------
    public boolean insertCap(Capacitor capacitor){
        connect();
        String sql = "INSERT INTO Capacitor(capacitancia,qtd,Componente_idComponente) VALUES (?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setFloat(1,capacitor.getCapacitancia());// concatena nome no primeiro ? do comando
            pst.setInt(2,capacitor.getQtd());
            pst.setInt(3,capacitor.Componente_idComponente);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }


    //--------------------INSERINDO NOVO REGISTRO DE RESISTOR--------------------
    public boolean insertRes(Resistor res){
        connect();
        String sql = "INSERT INTO Resistor(resistencia,qtd, Componente_idComponente) VALUES (?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setFloat(1,res.getResistencia());// concatena nome no primeiro ? do comando
            pst.setInt(2,res.getQtd());
            pst.setInt(3, res.Componente_idComponente);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }



    //--------------------INSERINDO NOVO REGISTRO DE COMPONENTE--------------------
    public boolean insertComp(Componente c){
        connect();
        String sql = "INSERT INTO componente(Tipo, idComponente) VALUES (?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,c.getTipo());// concatena nome no primeiro ? do comando
            pst.setInt(2, c.getIdComponente());
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }


   /* --------------------BUSCANDO NOVO REGISTRO--------------------
    public ArrayList<Componente> reserchComponente(){
        connect();
        ArrayList<Componente> componentes = new ArrayList<>();

        String sql = "SELECT * FROM Componente";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Componente c = new Componente();
                result.getString("Tipo");
                result.getInt("idComponente");

                c.setTipo(result.getString("Tipo"));
                c.setIdComponente(result.getInt("idComponente"));

                System.out.println("---- COMPONENTES ---- ");
                System.out.println("Tipo = " +c.getTipo());
                System.out.println("ID = " +c.getIdComponente());
                System.out.println("---------------------------------");

            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return componentes;

    }


  --------------------BUSCANDO NOVO REGISTRO--------------------
    public ArrayList<Resistor> reserchResistor(){
        connect();
        ArrayList<Resistor> resistors = new ArrayList<>();

        String sql = "SELECT * FROM Resistor";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Resistor r = new Resistor();
                result.getFloat("resistencia");
                result.getInt("qtd");

                r.setTipo(result.getString("resistencia"));
                r.setQtd(result.getInt("qtd"));

                System.out.println("------ RESISTORES ----- ");
                System.out.println("Resistencia = " +r.getResistencia());
                System.out.println("Qtd = " +r.getQtd());
                System.out.println("---------------------------------");

            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return resistors;

    }

    //--------------------BUSCANDO NOVO REGISTRO--------------------
    public ArrayList<Capacitor> reserchCapcitor(){
        connect();
        ArrayList<Capacitor> capacitors = new ArrayList<>();

        String sql = "SELECT * FROM Capacitor";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Capacitor cap = new Capacitor();
                result.getFloat("capacitancia");
                result.getInt("qtd");

                cap.setTipo(result.getString("capacitancia"));
                cap.setQtd(result.getInt("qtd"));

                System.out.println("------ Capacitores ----- ");
                System.out.println("Capacitancia = " +cap.getCapacitancia());
                System.out.println("Qtd = " +cap.getQtd());
                System.out.println("---------------------------------");

            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return capacitors;

    }

*/


}
