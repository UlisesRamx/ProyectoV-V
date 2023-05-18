
package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.DocenteEEPrograma;
import uv.fei.tutorias.domain.ProgramaEducativo;
import org.apache.log4j.Logger;

public class DocenteEEProgramasDAO implements IDocenteEEProgramasDAO {

    final static Logger log = Logger.getLogger(DocenteEEProgramasDAO.class);
    
    @Override
    public int asignarEEProfesor(int nrc, int numpersonal, int idprogramaeducativo) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasInsertadas = 0;
        Connection connection = dataBaseConnection.getConnection();
        int Numpersonal = numpersonal;
        int NRC = nrc;
        String query = ("INSERT INTO docenteseeprogramas (numpersonal, nrc, idPeriodo, idProgramaEducativo ) VALUES (?,?,(select p.idperiodo from periodo p where p.activo=1),?) ");
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, Numpersonal);
        statement.setInt(2, NRC);
        statement.setInt(3, idprogramaeducativo);
        filasInsertadas = statement.executeUpdate();
        System.out.println(filasInsertadas + " Fila insertada ");
        return filasInsertadas;
    }

    @Override
    public ArrayList<ProgramaEducativo> consultarProgramas() throws SQLException {
        ArrayList<ProgramaEducativo> programasEducativos = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT * FROM programaseducativos;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int idprogramaeducativo;
            String nombre;
            do {
                idprogramaeducativo = resultSet.getInt("idprogramaeducativo");
                nombre = resultSet.getString("Nombre");
                ProgramaEducativo programaeducativo = new ProgramaEducativo();
                programaeducativo.setIdProgramaEducativo(idprogramaeducativo);
                programaeducativo.setNombre(nombre);
                programasEducativos.add(programaeducativo);
            } while (resultSet.next());
        }
        return programasEducativos;
    }

    @Override
    public int consultarProgramaSeleccionado(String programa) throws SQLException {
        int idprograma = 0;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT idprogramaeducativo FROM programaseducativos where nombre=?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, programa);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            idprograma = resultSet.getInt("idprogramaeducativo");
        }
        return idprograma;
    }
    
    @Override
    public ArrayList<DocenteEEPrograma> obtenerOfertaAcademicaGeneral() {
        ArrayList<DocenteEEPrograma> ofertaAcademicas= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query=
                    ("SELECT ee.NRC NRC, deep.IdDocenteEEPrograma, ee.Nombre Experiencia, " +
                            "CONCAT_WS(' ', d.Nombre, d.ApellidoPaterno, d.APellidoMaterno) Docente, pe.Nombre Programa FROM docenteseeprogramas deep " +
                            "right join experienciaseducativas ee on ee.NRC = deep.NRC " +
                            "right join docentes d on d.NumPersonal = deep.NumPersonal " +
                            "inner join programaseducativos pe on pe.IdProgramaEducativo = deep.IdProgramaEducativo");
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se encontraron datos");
            }else{
                int idOfertaAcademica;
                String nrc;
                String ee;
                String docente;
                String programaEducativo;
                do {
                    nrc = resultSet.getString("NRC");
                    idOfertaAcademica = resultSet.getInt("IdDocenteEEPrograma");
                    ee = resultSet.getString("Experiencia");
                    docente = resultSet.getString("Docente");
                    programaEducativo = resultSet.getString("Programa");
                    DocenteEEPrograma ofertaAcademica = new DocenteEEPrograma();
                    ofertaAcademica.setNrc(nrc);
                    ofertaAcademica.setIdOfertaAcademica(idOfertaAcademica);
                    ofertaAcademica.setEe(ee);
                    ofertaAcademica.setDocente(docente);
                    ofertaAcademica.setProgramaEducativo(programaEducativo);
                    ofertaAcademicas.add(ofertaAcademica);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
            log.warn(ex);
        }
        return ofertaAcademicas;
    }

    @Override
    public ArrayList<DocenteEEPrograma> consultarOfertaAcademicaPorProgramaEducativo(String programaEducativoSeleccionado) {
        ArrayList<DocenteEEPrograma> ofertaAcademicas= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query=
                    ("SELECT ee.NRC NRC, deep.IdDocenteEEPrograma, ee.Nombre Experiencia, CONCAT_WS(' ', d.Nombre, d.ApellidoPaterno, d.APellidoMaterno) Docente, pe.Nombre Programa " +
                            "FROM docenteseeprogramas deep " +
                            "right join experienciaseducativas ee on ee.NRC = deep.NRC " +
                            "right join docentes d on d.NumPersonal = deep.NumPersonal " +
                            "inner join programaseducativos pe on pe.IdProgramaEducativo = deep.IdProgramaEducativo " +
                            "Where pe.Nombre = ?");
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, programaEducativoSeleccionado);
            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se encontraron datos");
            }else{
                int idOfertaAcademica;
                String ee;
                String docente;
                String programaEducativo;
                String nrc;
                do {
                    nrc = resultSet.getString("NRC");
                    idOfertaAcademica = resultSet.getInt("IdDocenteEEPrograma");
                    ee = resultSet.getString("Experiencia");
                    docente = resultSet.getString("Docente");
                    programaEducativo = resultSet.getString("Programa");
                    DocenteEEPrograma docenteEEPrograma = new DocenteEEPrograma();
                    docenteEEPrograma.setNrc(nrc);
                    docenteEEPrograma.setIdOfertaAcademica(idOfertaAcademica);
                    docenteEEPrograma.setEe(ee);
                    docenteEEPrograma.setDocente(docente);
                    docenteEEPrograma.setProgramaEducativo(programaEducativo);
                    ofertaAcademicas.add(docenteEEPrograma);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
            log.warn(ex);
        }
        return ofertaAcademicas;
    }

    public int obtenerIdDocenteEEPrograma (int numPersonal, int nrc) throws SQLException{
        int idDocenteEEPrograma = 0;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = ("SELECT IdDocenteEEPrograma FROM docenteseeprogramas " +
                "WHERE NRC = ? AND NumPersonal = ?;");
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, nrc);
        statement.setInt(2, numPersonal);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            idDocenteEEPrograma = resultSet.getInt("IdDocenteEEPrograma");
        }
        return idDocenteEEPrograma;
    }
}
