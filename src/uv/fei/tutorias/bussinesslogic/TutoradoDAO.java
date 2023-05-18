package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Asistencia;
import uv.fei.tutorias.domain.SesionTutoria;
import uv.fei.tutorias.domain.Tutorado;
import org.apache.log4j.Logger;


public class TutoradoDAO implements ITutoradoDAO{
    
    final static Logger log = Logger.getLogger(TutoradoDAO.class);

    @Override
    public ArrayList<Tutorado> buscarTutoradoPorTutorPrograma(String cuentaUV, int idProgramaEducativo) {
        ArrayList<Tutorado> tutorados = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query= ("SELECT T.Matricula, T.Nombre, T.ApellidoPaterno, T.ApellidoMaterno FROM tutorados T  " +
                    "INNER JOIN tutoradosprogramas TP on TP.Matricula = T.Matricula  " +
                    "INNER JOIN tutorestutorados TT on TT.Matricula = T.Matricula " +
                    "WHERE TT.CuentaUV = ? AND TP.IdProgramaEducativo = ?");
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, cuentaUV);
            statement.setInt(2, idProgramaEducativo);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                String matricula;
                String nombre;
                String apellidoPaterno;
                String apellidoMaterno;
                do {
                    matricula = resultSet.getString("Matricula");
                    nombre = resultSet.getString("Nombre");
                    apellidoPaterno = resultSet.getString("ApellidoPaterno");
                    apellidoMaterno = resultSet.getString("ApellidoMaterno");
                    Tutorado tutoradoBuscado = new Tutorado();
                    tutoradoBuscado.setMatricula(matricula);
                    tutoradoBuscado.setNombre(nombre);
                    tutoradoBuscado.setApellidoPaterno(apellidoPaterno);
                    tutoradoBuscado.setApellidoMaterno(apellidoMaterno);
                    tutorados.add(tutoradoBuscado);
                }while (resultSet.next());
            }
        } catch (SQLException ex) {
            log.warn(ex);
        }
        return tutorados;
    }

    public ArrayList<Asistencia> obtenerTutoradosParaAsistencia(String cuentaUV, int idProgramaEducativo){
        ArrayList<Asistencia> tutoradosAsistencia = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query= ("SELECT T.Matricula, T.Nombre, T.ApellidoPaterno, T.ApellidoMaterno FROM tutorados T  " +
                    "INNER JOIN tutoradosprogramas TP on TP.Matricula = T.Matricula  " +
                    "INNER JOIN tutorestutorados TT on TT.Matricula = T.Matricula " +
                    "WHERE TT.CuentaUV = ? AND TP.IdProgramaEducativo = ?");
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, cuentaUV);
            statement.setInt(2, idProgramaEducativo);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                String matricula;
                String nombre;
                String apellidoPaterno;
                String apellidoMaterno;
                String nombreCompleto;
                do {
                    matricula = resultSet.getString("Matricula");
                    nombre = resultSet.getString("Nombre");
                    apellidoPaterno = resultSet.getString("ApellidoPaterno");
                    apellidoMaterno = resultSet.getString("ApellidoMaterno");
                    Asistencia asistencia = new Asistencia();
                    asistencia.setMatricula(matricula);
                    asistencia.setNombre(nombre);
                    asistencia.setApellidoPaterno(apellidoPaterno);
                    asistencia.setApellidoMaterno(apellidoMaterno);
                    asistencia.setNombreCompleto(nombre + " " + apellidoPaterno + " "+ apellidoPaterno);
                    tutoradosAsistencia.add(asistencia);
                }while (resultSet.next());
            }
        } catch (SQLException ex) {
            log.warn(ex);
        }
        return tutoradosAsistencia;
    }
    
    @Override
    public ArrayList<Tutorado> obtenerTutoradosConTutores() {
        ArrayList<Tutorado> tutoradosEncontrados = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query="select concat(t.nombre, ' ', t.ApellidoPaterno,' ', t.ApellidoMaterno) as nombreTutorado, t.Matricula, " +
                         "concat(u.nombre, ' ', u.ApellidoPaterno,' ', u.ApellidoMaterno) as nombreTutor, u.CuentaUV from tutorados t, usuarios u " +
                         "INNER JOIN tutorestutorados tt WHERE t.Matricula = tt.Matricula AND u.cuentaUV = tt.CuentaUV;";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No hay tutorados registrados");
            }else{
                String matricula;
                String nombresTutorados;
                String nombresTutores;
                String cuentaUv;
                
                do {
                    matricula = resultSet.getString("Matricula");
                    nombresTutorados = resultSet.getString("nombreTutorado");
                    nombresTutores = resultSet.getString("nombreTutor");
                    cuentaUv = resultSet.getString("cuentaUv");
                    
                    Tutorado tutoradoBuscado = new Tutorado();
                    tutoradoBuscado.setMatricula(matricula);
                    tutoradoBuscado.setNombre(nombresTutorados);
                    tutoradoBuscado.setNombreTutor(nombresTutores);
                    tutoradoBuscado.setCuentaUV(cuentaUv);
                    tutoradosEncontrados.add(tutoradoBuscado);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
            log.warn(ex);
        }
        return tutoradosEncontrados;
    }

    @Override
    public ArrayList<Tutorado> consultarTutoradosNoAsignados(int idprograma) throws SQLException {
        ArrayList<Tutorado> estudiantes= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
        String query= "select concat(t.nombre, ' ', t.ApellidoPaterno,' ', t.ApellidoMaterno) as nombre, t.matricula from tutorados t " +
                " inner join tutoradosprogramas tp on t.matricula=tp.matricula " +
                " where not exists (select * from tutorestutorados tt where t.matricula=tt.matricula) AND tp.idprogramaeducativo=?;";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1, idprograma);
        ResultSet resultSet=statement.executeQuery();
        if (resultSet.next()){
            String nombre;
            String matricula;
            do {
                nombre = resultSet.getString("Nombre");
                matricula = resultSet.getString("Matricula");
                Tutorado tutorado = new Tutorado();
                tutorado.setNombre(nombre);
                tutorado.setMatricula(matricula);
                estudiantes.add(tutorado);
            }while (resultSet.next());
        }
        return estudiantes;
    }

    @Override
    public ArrayList<Tutorado> obtenerTutoradosPorNombreCompleto() {
        ArrayList<Tutorado> tutorados = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query="SELECT Nombre , ApellidoPaterno, ApellidoMaterno from tutorados";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No hay tutorados registrados tutorados");
            }else{
                String nombre;
                String apellidoPaterno;
                String apellidoMaterno;

                do {
                    nombre = resultSet.getString("Nombre");
                    apellidoPaterno = resultSet.getString("ApellidoPaterno");
                    apellidoMaterno = resultSet.getString("ApellidoMaterno");
                    Tutorado tutoradoBuscado = new Tutorado();
                    tutoradoBuscado.setNombre(nombre);
                    tutoradoBuscado.setApellidoPaterno(apellidoPaterno);
                    tutoradoBuscado.setApellidoMaterno(apellidoMaterno);
                    tutorados.add(tutoradoBuscado);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
            log.warn(ex);
        }
        return tutorados;
    }
}
