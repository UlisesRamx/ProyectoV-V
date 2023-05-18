package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uv.fei.tutorias.domain.Tutor;


public interface ITutorDAO {
        
    public ArrayList<Tutor> consultarTodoslosTutoresPorProgramaEducativo(int idProgramaEducativo) throws SQLException;

    public int registrarTutor(Tutor tutor) throws SQLException;

}
