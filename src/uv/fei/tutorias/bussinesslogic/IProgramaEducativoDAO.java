package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ProgramaEducativo;

import java.sql.SQLException;
import java.util.List;

public interface IProgramaEducativoDAO {

    public List<ProgramaEducativo> consultarTodosLosProgramasEducativos() throws SQLException;

    public ProgramaEducativo obtenerProgramaEducativodeUsuario(String cuentaUv);
}
