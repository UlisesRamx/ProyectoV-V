package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ExperienciaEducativa;

public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO{

    @Override
    public ArrayList<ExperienciaEducativa> consultarExperienciasNoAsignadas() throws SQLException {
        ArrayList<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT ee.* FROM experienciaseducativas ee WHERE NOT EXISTS (SELECT * FROM Docenteseeprogramas dep WHERE ee.nrc = dep.nrc );";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            throw new SQLException("No se encontraron experiencias");
        } else {
            int nrc;
            String nombre;
            do {
                nrc = resultSet.getInt("NRC");
                nombre = resultSet.getString("Nombre");
                ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                experienciaEducativa.setNrc(nrc);
                experienciaEducativa.setNombre(nombre);
                experienciasEducativas.add(experienciaEducativa);
            } while (resultSet.next());
        }
        return experienciasEducativas;
    }

    @Override
    public ArrayList<ExperienciaEducativa> consultarExperienciasPorPrograma(int idProgramaEducativo) throws SQLException {
        ArrayList<ExperienciaEducativa> experienciaEducativas = new ArrayList<ExperienciaEducativa>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
            String query= "(SELECT ee.*  FROM experienciaseducativas ee " +
                    "inner join docenteseeprogramas deep on deep.NRC = ee.NRC " +
                    "WHERE deep.IdProgramaEducativo = ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                int nrc;
                String nombre;
                do {
                    nrc = resultSet.getInt("NRC");
                    nombre = resultSet.getString("Nombre");
                    ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                    experienciaEducativa.setNrc(nrc);
                    experienciaEducativa.setNombre(nombre);
                    experienciaEducativas.add(experienciaEducativa);
                }while (resultSet.next());
            }
        return experienciaEducativas;
    }

    @Override
    public ArrayList<ExperienciaEducativa> consultarExperienciasPorDocente(int numPersonal) throws SQLException{
        ArrayList<ExperienciaEducativa> experienciaEducativas = new ArrayList<ExperienciaEducativa>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection=dataBaseConnection.getConnection();
        String query= "(SELECT ee.*  FROM experienciaseducativas ee " +
                "inner join docenteseeprogramas deep on deep.NRC = ee.NRC " +
                "WHERE deep.NumPersonal = ?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1, numPersonal);
        ResultSet resultSet=statement.executeQuery();
        if (resultSet.next()){
            int nrc;
            String nombre;
            do {
                nrc = resultSet.getInt("NRC");
                nombre = resultSet.getString("Nombre");
                ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                experienciaEducativa.setNrc(nrc);
                experienciaEducativa.setNombre(nombre);
                experienciaEducativas.add(experienciaEducativa);
            }while (resultSet.next());
        }
        return experienciaEducativas;
    }

}
