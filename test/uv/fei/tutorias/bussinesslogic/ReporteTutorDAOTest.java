package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Asistencia;
import uv.fei.tutorias.domain.ProblematicaReporte;
import uv.fei.tutorias.domain.ReporteTutor;

public class ReporteTutorDAOTest {
    
    @Test
    public void testConsultarReportesTutor() throws Exception {
        System.out.println("consultarReportesTutor");
        int idProgramaEducativo = 2;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 2;
        ArrayList<ReporteTutor> result = instance.consultarReportesTutor(idProgramaEducativo);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testEncabezadoReporte() throws Exception {
        System.out.println("encabezadoReporte");
        int idsesion = 3;
        String fecha ="05-06-2022";
        String nombreTutor="Alan Rivera Gracia";
        String periodo="2022-07-25 - 2023-01-26";
        String programaeducativo="Ingeniería de Software";
        
        ReporteTutorDAO instance = new ReporteTutorDAO();
        ReporteTutor expResult = new ReporteTutor();
        expResult.setFecha(fecha);
        expResult.setIdsesion(idsesion);
        expResult.setNombreTutor(nombreTutor);
        expResult.setNumTutoria(1);
        expResult.setPeriodo(periodo);
        expResult.setProgramaeducativo(programaeducativo);
        ReporteTutor result = instance.cargarEncabezadoReporte(idsesion);
        assertEquals(expResult, result);
    }

    @Test
    public void testListaAsistencia() throws Exception {
        System.out.println("listaAsistencia");
        int idsesion = 3;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int  expResult = 4;
        ArrayList<Asistencia> result = instance.cargarListaAsistencia(idsesion);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    @Test
    public void testComentariosGenerales() throws Exception {
        System.out.println("comentariosGenerales");
        int idsesion = 3;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        String expResult = "El esquema en línea es cansado para los estudiantes";
        String result = instance.cargarComentariosGenerales(idsesion);
        assertEquals(expResult, result);
    }

    @Test
    public void testProblematicasReportadas() throws Exception {
        System.out.println("problematicasReportadas");
        int idsesion = 3;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 3;
        ArrayList<ProblematicaReporte> result = instance.cargarProblematicasReportadas(idsesion);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testObtenerTutoradosParaAsistencia() {
        System.out.println("obtenerTutoradosParaAsistencia");
        String cuentaUV = "arivera";
        int idProgramaEducativo = 2;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 2;
        ArrayList<Asistencia> result = instance.obtenerTutoradosParaAsistencia(cuentaUV, idProgramaEducativo);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testEliminarReporteIncompleto() throws Exception {
        System.out.println("eliminarReporteIncompleto");
        int idSesion = 10;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 0;
        int result = instance.eliminarReporteIncompleto(idSesion);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarReporte() throws Exception {
        System.out.println("registrarReporte");
        ReporteTutor reporteTutor = new ReporteTutor();
        reporteTutor.setCuentaUv("pgonzalez");
        reporteTutor.setIdProgramaEducativo(3);
        reporteTutor.setIdTutoria(109);
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 1;
        int result = instance.registrarReporte(reporteTutor);
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerIdReporte() throws Exception {
        System.out.println("obtenerIdReporte");
        ReporteTutor reporteBuscado = new ReporteTutor();
        reporteBuscado.setCuentaUv("pgonzalez");
        reporteBuscado.setIdProgramaEducativo(3);
        reporteBuscado.setIdTutoria(109);
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 32;
        int result = instance.obtenerIdReporte(reporteBuscado);
        assertEquals(expResult, result);
    }
       @Test
    public void testObtenerIdReporteFallida() throws Exception {
        System.out.println("obtenerIdReporte");
        ReporteTutor reporteBuscado = new ReporteTutor();
        reporteBuscado.setCuentaUv("pgonzalez");
        reporteBuscado.setIdProgramaEducativo(2);
        reporteBuscado.setIdTutoria(109);
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 32;
        int result = instance.obtenerIdReporte(reporteBuscado);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarAsistencia() throws Exception {
        System.out.println("registrarAsistencia");
        Asistencia listaAsistencia = null;
        int idSesion = 0;
        ReporteTutorDAO instance = new ReporteTutorDAO();
        int expResult = 0;
        int result = instance.registrarAsistencia(listaAsistencia, idSesion);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
