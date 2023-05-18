package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.domain.Asistencia;
import uv.fei.tutorias.domain.ReporteTutor;
import uv.fei.tutorias.domain.ProblematicaReporte;

public interface IReporteTutorDAO {

    public ArrayList<Asistencia> obtenerTutoradosParaAsistencia(String cuentaUV, int idProgramaEducativo);

    public int eliminarReporteIncompleto(int idSesion) throws SQLException;

    public int registrarReporte(ReporteTutor reporteTutor) throws SQLException;

    public int obtenerIdReporte(ReporteTutor reporteBuscado) throws SQLException;

    public int registrarAsistencia(Asistencia listaAsistencia, int idSesion) throws SQLException;

    public ArrayList<ReporteTutor> consultarReportesTutor(int idProgramaEducativo) throws SQLException;

    public ReporteTutor cargarEncabezadoReporte(int idsesion) throws SQLException;

    public ArrayList<Asistencia> cargarListaAsistencia(int idsesion) throws SQLException;

    public String cargarComentariosGenerales(int idsesion) throws SQLException;

    public ArrayList<ProblematicaReporte> cargarProblematicasReportadas(int idsesion) throws SQLException;
      
}
