package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.ProblematicaReporte;


public class ProblematicaAcademicaDAO implements IPoblematicaAcademicaDAO{

    @Override
    public ArrayList<ProblematicaReporte> consultarTodasLasProblematicasPorProgramaEducativoCuenta(int idProgramaEducativo, String cuentaUv) throws SQLException {
        ArrayList<ProblematicaReporte> problematicasAcademicas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = (" SELECT tpa.*, t.FechaTutoria, d.Nombre Docente, ed.Nombre Experiencia FROM tutoriasproblematicasacademicas tpa " +
                "INNER JOIN tutoriasproblematicassesiones tps on tps.idproblemaacademica = tpa.IdProblemaAcademica  " +
                "INNER JOIN sesion s on s.idSesion = tps.idsesion  " +
                "INNER JOIN tutorias t on t.IdTutoria = s.IdTutoria  " +
                "INNER JOIN docenteseeprogramas deep on deep.IdDocenteEEPrograma = tpa.IdDocentesEEProgramas  " +
                "INNER JOIN experienciaseducativas ed on ed.NRC = deep.NRC  " +
                "INNER JOIN docentes d on d.NumPersonal = deep.NumPersonal " +
                "WHERE s.IdProgramaEducativo = ? AND s.cuentauv = ?;");
        PreparedStatement statement = connection.prepareStatement(query);
        if (idProgramaEducativo > 0 && !cuentaUv.isEmpty() && cuentaUv.length() < 51) {
            statement.setInt(1, idProgramaEducativo);
            statement.setString(2, cuentaUv);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idProblematicaAcademica;
                String titulo;
                String experiencia;
                String fecha;
                String descripcion;
                String docente;
                int cantidadTutorados;
                do {
                    idProblematicaAcademica = resultSet.getInt("IdProblemaAcademica");
                    titulo = resultSet.getString("Titulo");
                    fecha = resultSet.getString("FechaTutoria");
                    experiencia = resultSet.getString("Experiencia");
                    descripcion = resultSet.getString("Descripcion");
                    docente = resultSet.getString("Docente");
                    cantidadTutorados = resultSet.getInt("cantidadTutorados");
                    ProblematicaReporte problematicaAcademica = new ProblematicaReporte();
                    problematicaAcademica.setIdProblematicaAcademica(idProblematicaAcademica);
                    problematicaAcademica.setFecha(fecha);
                    problematicaAcademica.setExperiencia(experiencia);
                    problematicaAcademica.setTitulo(titulo);
                    problematicaAcademica.setDescripcion(descripcion);
                    problematicaAcademica.setNombreDocente(docente);
                    problematicaAcademica.setCantidadTutorados(cantidadTutorados);
                    problematicasAcademicas.add(problematicaAcademica);
                } while (resultSet.next());
            }
        }
        return problematicasAcademicas;
    }

    @Override
    public int registrarProblematicaAcademica(ProblematicaAcademica problematicaAcademica, int idSesion) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasInsertadas = 0;
        Connection connection = dataBaseConnection.getConnection();
        String descripcion = problematicaAcademica.getDescripcion();
        String titulo = problematicaAcademica.getTitulo();
        int idDocenteEePrograma = problematicaAcademica.getIdDocenteEePrograma();
        int cantidadTutorados = problematicaAcademica.getCantidadTutorados();
        String query = "INSERT INTO tutoriasproblematicasacademicas (Titulo, Descripcion, cantidadTutorados, IdDocentesEEProgramas) VALUES (?, ?, ?, ?)";
        if(titulo.length() < 100 && descripcion.length() < 500 && cantidadTutorados > 0 && idDocenteEePrograma > 0){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titulo);
            statement.setString(2, descripcion);
            statement.setInt(3, cantidadTutorados);
            statement.setInt(4, idDocenteEePrograma);
            filasInsertadas = statement.executeUpdate();
        }
        return filasInsertadas;
    }

    @Override
    public int vincularProblematicaSesion(int idProblematica, int idSesion) throws SQLException{
        int filasInsertadas = 0;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = "INSERT INTO tutoriasproblematicassesiones (idproblemaacademica, idsesion) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        if (idProblematica > 0 && idSesion > 0) {
            statement.setInt(1, idProblematica);
            statement.setInt(2, idSesion);
            filasInsertadas = statement.executeUpdate();
        }
        return filasInsertadas;
    }

    @Override
    public int obtenerIdProblematica(String titulo, int cantidadTutorados) throws SQLException{
        int idProblematica = 0;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT * FROM tutoriasproblematicasacademicas WHERE Titulo = ? AND cantidadTutorados = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, titulo);
        statement.setInt(2, cantidadTutorados);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            idProblematica = resultSet.getInt("IdProblemaAcademica");
        }
        return idProblematica;
    }

    @Override
    public int eliminarProblematica(ProblematicaAcademica problematicaAcademica) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasActualizadas = 0;
        Connection connection = dataBaseConnection.getConnection();
        int idProblematicaAcademica = problematicaAcademica.getIdProblematicaAcademica();
        String query;
        query = ("DELETE FROM `sistematutoriasfei`.`tutoriasproblematicasacademicas` WHERE (`IdProblemaAcademica` = ?);");
        PreparedStatement statement = connection.prepareStatement(query);
        if (idProblematicaAcademica > 0) {
            statement.setInt(1, idProblematicaAcademica);
            filasActualizadas = statement.executeUpdate();
        }
        return filasActualizadas;
    }

    @Override
    public int eliminarProblematicaSesiones(ProblematicaAcademica problematicaAcademica) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasActualizadas = 0;
        Connection connection = dataBaseConnection.getConnection();
        int idProblematicaAcademica = problematicaAcademica.getIdProblematicaAcademica();
        String query;
        query = ("DELETE FROM `sistematutoriasfei`.`tutoriasproblematicassesiones` WHERE (`IdProblemaAcademica` = ?);");
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idProblematicaAcademica);
        filasActualizadas = statement.executeUpdate();
        return filasActualizadas;
    }

    @Override
    public int actualizarProblematica(ProblematicaAcademica problematicaAcademica) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasActualizadas = 0;
        Connection connection = dataBaseConnection.getConnection();
        int idProblematicaAcademica = problematicaAcademica.getIdProblematicaAcademica();
        String descripcion = problematicaAcademica.getDescripcion();
        String titulo = problematicaAcademica.getTitulo();
        if(!titulo.isEmpty() && !descripcion.isEmpty() && !titulo.trim().isEmpty() && !descripcion.trim().isEmpty()){
            String query;
            query = ("UPDATE tutoriasproblematicasacademicas SET titulo = ?, descripcion = ? WHERE idproblemaacademica=?;");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titulo);
            statement.setString(2, descripcion);
            statement.setInt(3, idProblematicaAcademica);
            filasActualizadas = statement.executeUpdate();
            System.out.println(filasActualizadas + " filas modificadas");
            filasActualizadas=1;

        }else{
            filasActualizadas=0;
        }
        return filasActualizadas;

    }

}
