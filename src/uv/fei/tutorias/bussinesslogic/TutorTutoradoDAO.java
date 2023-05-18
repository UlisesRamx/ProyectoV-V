
package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Docente;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.Tutor;
import uv.fei.tutorias.domain.TutorTutorado;


/**
 *
 * @author Valea
 */
public class TutorTutoradoDAO implements ITutorTutoradoDAO {
    
     @Override
    public int asignarTutorTutorado(String cuentauv, String Matricula)throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
        int filasInsertadas = 0;
        //try(Connection connection=dataBaseConnection.getConnection()){
            String CuentaUV=cuentauv;
            String matricula =Matricula;
            
            String query =
                 
                    ("INSERT INTO tutorestutorados (CuentaUV, Matricula) VALUES (?,?) " );
           
            PreparedStatement statement = connection.prepareStatement(query);
             
            statement.setString(1,CuentaUV);
            statement.setString(2,matricula);
            
            
            filasInsertadas = statement.executeUpdate();
            System.out.println(filasInsertadas + " Fila insertada ");
      
        return filasInsertadas;
    }


    @Override
    public ArrayList<Docente> consultarTodosDocentes()throws SQLException  {
        ArrayList<Docente> docentes= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
            String query= "SELECT concat (nombre, ' ',apellidopaterno, ' ', apellidomaterno)as nombre, numpersonal, correo FROM docentes;";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                 int numpersonal;
                 String nombre;
                 String correo;
                
                do {
                   
                  
                    numpersonal=resultSet.getInt("NumPersonal");
                    
                    nombre = resultSet.getString("Nombre");
                  
                    correo=resultSet.getString("Correo");
                    
                    
                    
                    
                    Docente docente = new Docente();
                    docente.setNumPersonal(numpersonal);
                    docente.setNombre(nombre);
                    docente.setNombre(nombre);
                    docente.setCorreo(correo);
                    
                    
                    docentes.add(docente);
                }while (resultSet.next());
            }
        
        return docentes;
    }

    @Override
    public ArrayList<TutorTutorado> consultarTutoradosporTutor()throws SQLException {
        ArrayList<TutorTutorado> tutores= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
                
            String query="select concat(u.nombre,' ', u.ApellidoPaterno,' ', u.ApellidoMaterno) as nombre, u.cuentauv,(select count(tt.matricula) from tutorestutorados tt where u.cuentauv=tt.cuentauv) as numTutorados from usuarios u inner join usuariosroles ur on  u.cuentauv=ur.cuentauv where  ur.idRol=4;";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se encontraron Tutores");
            }else{
                String nombre;
                String cuentauv;
                int numTutorados;
                
                do {
                    numTutorados= resultSet.getInt("NumTutorados");
                    nombre = resultSet.getString("Nombre");
                    cuentauv = resultSet.getString("Cuentauv");
                    
                    TutorTutorado tutor = new TutorTutorado();
                    tutor.setNombre(nombre);
                    tutor.setCuentaUv(cuentauv);
                    tutor.setNumTutorados(numTutorados);
                    
                    tutores.add(tutor);
                }while (resultSet.next());
            }
        
        return tutores;
    
    
    

}

  /*  @Override
    public List<TutorTutorado> consultarTutoresTutoradosporId(int idTutorTutorado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } */

}