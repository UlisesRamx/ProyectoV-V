package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uv.fei.tutorias.domain.SesionTutoria;

public interface ISesionTutoriaDAO {
    public List<SesionTutoria> consultarSesionesTutoriaPorNumero(String tutoriaBuscada);

    public int registrarSesionTutoria(SesionTutoria sesionTutoria) throws SQLException;

    public ArrayList<SesionTutoria> consultarTutoriaPorPeriodo(int idPeriodo) throws SQLException;
    
    public List<SesionTutoria> consultarTodosLasSesiones();

    public int actualizarFechasDeSesionTutoria(SesionTutoria sesionTutoria, int idPeriodo, String numTutoria) throws SQLException;

    public int registrarFechaDeCierreDeReporte(SesionTutoria sesionTutoria, int idPeriodo, String numTutoria) throws SQLException;

}



    