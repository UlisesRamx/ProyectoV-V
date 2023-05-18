package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Tutor;
import org.apache.log4j.Logger;

public class TutorDAO implements ITutorDAO{

final static Logger log = Logger.getLogger(TutorDAO.class);

    @Override
    public ArrayList<Tutor> consultarTodoslosTutoresPorProgramaEducativo(int idProgramaEducativo) throws SQLException {
        ArrayList<Tutor> tutores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
            String query="SELECT U.* FROM usuarios U " +
                    "INNER JOIN usuariosroles UR on UR.CuentaUV = U.CuentaUV  " +
                    "INNER JOIN usuariosprogramas UP on UP.CuentaUV = U.CuentaUV  " +
                    "INNER JOIN roles R on R.IdRol = UR.IdRol  " +
                    "WHERE R.Descripcion = 'Tutor' AND UP.IdProgramaEducativo = ?";
            PreparedStatement statement=connection.prepareStatement(query);
            if (idProgramaEducativo > 0) {
                statement.setInt(1, idProgramaEducativo);
                ResultSet resultSet=statement.executeQuery();
                if (resultSet.next()){
                    String cuentaUV;
                    String nombre;
                    String apellidoPaterno;
                    String apellidoMaterno;
                    String correo;
                    do {
                        cuentaUV = resultSet.getString("CuentaUV");
                        nombre = resultSet.getString("Nombre");
                        apellidoPaterno = resultSet.getString("ApellidoPaterno");
                        apellidoMaterno = resultSet.getString("ApellidoMaterno");
                        correo = resultSet.getString("Correo");
                        Tutor tutor = new Tutor();
                        tutor.setCuentaUV(cuentaUV);
                        tutor.setNombre(nombre);
                        tutor.setApellidoPaterno(apellidoPaterno);
                        tutor.setApellidoMaterno(apellidoMaterno);
                        tutor.setCorreo(correo);
                        tutores.add(tutor);
                    }while (resultSet.next());
                }
            }
        return tutores;
    }
    
    @Override
    public int registrarTutor(Tutor tutor) throws SQLException {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    int filasInsertadas = 0;
    Connection connection=dataBaseConnection.getConnection();
            String cuentaUv = tutor.getCuentaUV();
            String contrasena = tutor.getPassword();
            String nombre = tutor.getNombre();
            String apellidoPaterno = tutor.getApellidoPaterno();
            String apellidoMaterno = tutor.getApellidoMaterno();
            String correo = tutor.getCorreo();
            String query = "INSERT INTO usuarios (cuentauv, contrasena, Nombre, ApellidoPaterno, ApellidoMaterno, correo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cuentaUv);
            statement.setString(2, contrasena);
            statement.setString(3, nombre);
            statement.setString(4, apellidoPaterno);
            statement.setString(5, apellidoMaterno);
            statement.setString(6, correo);
            filasInsertadas = statement.executeUpdate();
            System.out.println(filasInsertadas + " Fila insertada ");
    return filasInsertadas;
    }

    }
