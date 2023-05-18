package uv.fei.tutorias.bussinesslogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.SesionTutoria;

public class SesionTutoriaDAO implements ISesionTutoriaDAO {
    
    final static Logger log = Logger.getLogger(TutorDAO.class);
    
    @Override
    public int registrarSesionTutoria(SesionTutoria sesionTutoria) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasInsertadas = 0;
        Connection connection=dataBaseConnection.getConnection();
            String fechaTutoria = sesionTutoria.getFechaTutoria();
            String numTutoria = sesionTutoria.getNumTutoria();
            String query = "INSERT INTO tutorias (NumeroTutoria, FechaTutoria) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            if (!fechaTutoria.isEmpty() && !numTutoria.isEmpty()) {
                statement.setString(1, numTutoria);
                statement.setString(2, fechaTutoria);
                filasInsertadas = statement.executeUpdate();
            }
        return filasInsertadas;
    }

    @Override
    public List<SesionTutoria> consultarSesionesTutoriaPorNumero(String tutoriaBuscada) {
        ArrayList<SesionTutoria> sesiones= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query="SELECT * FROM tutorias WHERE NumeroTutoria = ?";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, tutoriaBuscada);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                String numTutoria;
                String fechaTutoria;
                do {
                    numTutoria = resultSet.getString("NumeroTutoria");
                    fechaTutoria = resultSet.getString("FechaTutoria");
                    SesionTutoria sesionTutoria = new SesionTutoria();
                    sesionTutoria.setNumTutoria(numTutoria);
                    sesionTutoria.setFechaTutoria(fechaTutoria);
                    sesiones.add(sesionTutoria);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
            log.warn(ex);
        }
        return sesiones;
    }

    @Override
    public ArrayList<SesionTutoria> consultarTutoriaPorPeriodo(int idPeriodo) throws SQLException{
        ArrayList<SesionTutoria> sesiones= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
            String query="SELECT * FROM tutorias WHERE IdPeriodo = ?";
            PreparedStatement statement=connection.prepareStatement(query);
            if (idPeriodo > 0) {
                statement.setInt(1, idPeriodo);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int idSesionTutoria;
                    String fechaTutoria;
                    String numTutoria;
                    String fechaCierre;
                    do {
                        idSesionTutoria = resultSet.getInt("IdTutoria");
                        numTutoria = resultSet.getString("NumeroTutoria");
                        fechaTutoria = resultSet.getString("FechaTutoria");
                        fechaCierre = resultSet.getString("FechaCierreReportes");
                        SesionTutoria sesionTutoria = new SesionTutoria();
                        sesionTutoria.setIdSesionTutoria(idSesionTutoria);
                        sesionTutoria.setNumTutoria(numTutoria);
                        sesionTutoria.setFechaTutoria(fechaTutoria);
                        sesionTutoria.setFechaCierreReportes(fechaCierre);
                        sesiones.add(sesionTutoria);
                    } while (resultSet.next());
                }
            }
        return sesiones;
    }
    
    @Override
    public List<SesionTutoria> consultarTodosLasSesiones() {
        ArrayList<SesionTutoria> sesiones= new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection=dataBaseConnection.getConnection()){
            String query="SELECT U.FechaInicio, U.FechaFin, CU.NumeroTutoria, CU.FechaTutoria, U.IdPeriodo " +
                         "FROM periodo U join tutorias CU ON CU.IdPeriodo  = U.IdPeriodo;";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                int idPeriodo;
                String fechaInicioTutoria;
                String fechaInicioPeriodo;
                String fechaFinPeriodo;
                String numeroTutoria;
                String fechaPeriodoCompleta;
                do{    
                    idPeriodo = resultSet.getInt("IdPeriodo");
                    fechaInicioTutoria = resultSet.getString("FechaTutoria");
                    fechaInicioPeriodo =resultSet.getString("FechaInicio");
                    fechaFinPeriodo = resultSet.getString("FechaFin");
                    numeroTutoria = resultSet.getString("NumeroTutoria");
                    fechaPeriodoCompleta = fechaInicioPeriodo + " - " + fechaFinPeriodo;
                    SesionTutoria sesionEncontrada = new SesionTutoria();
                    sesionEncontrada.setIdPeriodo(idPeriodo);
                    sesionEncontrada.setFechaTutoria(fechaInicioTutoria);
                    sesionEncontrada.setFechaInicio(fechaInicioPeriodo);
                    sesionEncontrada.setFechaFin(fechaFinPeriodo);
                    sesionEncontrada.setFechaCompleta(fechaPeriodoCompleta);
                    sesionEncontrada.setNumTutoria(numeroTutoria);
                    sesiones.add(sesionEncontrada);
                }while (resultSet.next());
            }
        }catch (SQLException ex) {
           log.warn(ex);
        }
        return sesiones;
    }
    
    @Override
    public int actualizarFechasDeSesionTutoria(SesionTutoria sesionTutoria, int idPeriodo, String numTutoria) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasActualizadas = 0;
        try(Connection connection=dataBaseConnection.getConnection()){
            String fechaTutoria = sesionTutoria.getFechaTutoria();
            String query = "UPDATE tutorias SET FechaTutoria = ? WHERE tutorias.IdPeriodo = ? AND tutorias.NumeroTutoria = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            if (!fechaTutoria.isEmpty() && idPeriodo > 0 && !numTutoria.isEmpty()) {
                statement.setString(1, fechaTutoria);
                statement.setInt(2, idPeriodo);
                statement.setString(3, numTutoria);
                filasActualizadas = statement.executeUpdate();
                System.out.println(filasActualizadas + " filas modificadas");
            }
        } catch (SQLException ex) {
            log.error(ex);
        }
        return filasActualizadas;
    }

    @Override
    public int registrarFechaDeCierreDeReporte(SesionTutoria sesionTutoria, int idPeriodo, String numTutoria) throws SQLException{
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int filasInsertadas = 0;
        try(Connection connection=dataBaseConnection.getConnection()){
            String fechaCierreReporte = sesionTutoria.getFechaCierreReportes();
            String query = "UPDATE tutorias SET FechaCierreReportes = ? WHERE tutorias.IdPeriodo = ? AND tutorias.NumeroTutoria = ?";
            PreparedStatement statement=connection.prepareStatement(query);
            if (idPeriodo > 0 && !numTutoria.isEmpty()) {
                statement.setString(1, fechaCierreReporte);
                statement.setInt(2, idPeriodo);
                statement.setString(3, numTutoria);
                filasInsertadas = statement.executeUpdate();
            }
        } catch (SQLException ex) {
            log.error(ex);
        }
        return filasInsertadas;
    }
    
}
