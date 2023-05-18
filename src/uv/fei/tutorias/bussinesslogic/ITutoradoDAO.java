package uv.fei.tutorias.bussinesslogic;
import java.sql.SQLException;
import java.util.ArrayList;

import uv.fei.tutorias.domain.Asistencia;
import uv.fei.tutorias.domain.Tutorado;
import java.util.List;


public interface ITutoradoDAO {

    public ArrayList<Tutorado> buscarTutoradoPorTutorPrograma(String cuentaUV, int idProgramaEducativo);

    public ArrayList<Tutorado> obtenerTutoradosConTutores();

    public ArrayList<Asistencia> obtenerTutoradosParaAsistencia(String cuentaUV, int idProgramaEducativo);

    public ArrayList<Tutorado> consultarTutoradosNoAsignados(int programa)throws SQLException;

    public ArrayList<Tutorado> obtenerTutoradosPorNombreCompleto();
}

