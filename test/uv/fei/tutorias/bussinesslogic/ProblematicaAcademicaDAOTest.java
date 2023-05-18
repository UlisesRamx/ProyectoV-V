package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.ProblematicaReporte;

public class ProblematicaAcademicaDAOTest {
    
    public ProblematicaAcademicaDAOTest() {
    }

    @Test
    public void testActualizarProblematica() throws Exception {
        System.out.println("actualizarProblematica");
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        problematicaAcademica.setIdProblematicaAcademica(5);
        problematicaAcademica.setDescripcion("AHacer planas");
        problematicaAcademica.setTitulo("Compñaero molestoso");
        problematicaAcademica.setCantidadTutorados(12);
        problematicaAcademica.setIdDocenteEePrograma(11);
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        int expResult = 1;
        
        int result=instance.actualizarProblematica(problematicaAcademica);
        assertEquals(expResult, result);
        System.out.print(result);
    }

    @Test
    public void testConsultarTodasLasProblematicasPorProgramaEducativoCuenta() throws Exception {
        System.out.println("consultarTodasLasProblematicasPorProgramaEducativoCuenta");
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        ArrayList<ProblematicaReporte> result = instance.consultarTodasLasProblematicasPorProgramaEducativoCuenta(2,"arivera");
        int expResult=1;
        assertEquals(expResult, result.size());    
    }
    
        @Test
    public void testConsultarTodasLasProblematicasPorProgramaEducativoCuenta1() throws Exception {
        System.out.println("consultarTodasLasProblematicasPorProgramaEducativoCuenta");
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        ArrayList<ProblematicaReporte> result = instance.consultarTodasLasProblematicasPorProgramaEducativoCuenta(3,"arivera");
        int expResult=0;
        assertEquals(expResult, result.size());    
    }

    @Test
    public void testConsultarTodasLasProblematicasPorProgramaEducativoCuenta2() throws Exception {
        System.out.println("consultarTodasLasProblematicasPorProgramaEducativoCuenta");
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        ArrayList<ProblematicaReporte> result = instance.consultarTodasLasProblematicasPorProgramaEducativoCuenta(2,"kbravo");
        int expResult=0;
        assertEquals(expResult, result.size());    
    }
    
    @Test
    public void testEliminarProblematica() throws Exception {
        System.out.println("eliminarProblematica");
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        problematicaAcademica.setIdProblematicaAcademica(5);
        problematicaAcademica.setDescripcion("");
        problematicaAcademica.setSolucion("");
        problematicaAcademica.setCantidadTutorados(12);
         problematicaAcademica.setIdDocenteEePrograma(11);
   
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        int expResult = 1;
        int result = instance.eliminarProblematica(problematicaAcademica);
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminarProblematicaSesiones() throws Exception {
        System.out.println("eliminarProblematicaSesiones");
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        problematicaAcademica.setIdProblematicaAcademica(5);
        problematicaAcademica.setDescripcion("");
        problematicaAcademica.setSolucion("");
        problematicaAcademica.setCantidadTutorados(12);
         problematicaAcademica.setIdDocenteEePrograma(11);
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        int expResult = 1;
        int result = instance.eliminarProblematicaSesiones(problematicaAcademica);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarProblematicaAcademica() throws Exception {
        System.out.println("registrarProblematicaAcademica");
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
        problematicaAcademica.setDescripcion("El profesor no esta siguiendo el plan del curso");
        problematicaAcademica.setTitulo("Plan de curso no respetado");
        problematicaAcademica.setCantidadTutorados(10);
        problematicaAcademica.setIdDocenteEePrograma(19);
        int idSesion = 0;
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        int expResult = 1;
        int result = instance.registrarProblematicaAcademica(problematicaAcademica, idSesion);
        assertEquals(expResult, result);
    }

    @Test
    public void testVincularProblematicaSesionFallida() throws Exception {
        System.out.println("vincularProblematicaSesion");
        int idProblematica = 19;
        int idSesion = 31;
        ProblematicaAcademicaDAO instance = new ProblematicaAcademicaDAO();
        int expResult = 1;
        int result = instance.vincularProblematicaSesion(idProblematica, idSesion);
        assertEquals(expResult, result);
    }
    
}
