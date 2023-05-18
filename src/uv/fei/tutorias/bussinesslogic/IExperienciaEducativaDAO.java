package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.domain.ExperienciaEducativa;

public interface IExperienciaEducativaDAO {

    public ArrayList<ExperienciaEducativa> consultarExperienciasNoAsignadas()throws SQLException;

    public ArrayList<ExperienciaEducativa> consultarExperienciasPorPrograma(int idProgramaEducativo) throws SQLException;

    public ArrayList<ExperienciaEducativa> consultarExperienciasPorDocente(int numPersonal) throws SQLException;

}
