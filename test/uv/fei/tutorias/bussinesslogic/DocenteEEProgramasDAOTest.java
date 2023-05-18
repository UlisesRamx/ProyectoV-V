package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.DocenteEEPrograma;
import uv.fei.tutorias.domain.ProgramaEducativo;

public class DocenteEEProgramasDAOTest {
    
    @Test
    public void testAsignarEEProfesor() throws Exception {
        System.out.println("consultarTodasLasProblematicasPorProgramaEducativoCuenta");
        int expResult = 1;
        int nrc=66;
        int numpersonal=45678;
        int programa=4;
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int  result = instance.asignarEEProfesor(nrc, numpersonal,programa);

        assertEquals(expResult, result);
    }

    @Test
     public void testConsultarProgramas() throws Exception {
        System.out.println("consultarTodasLasProblematicasPorProgramaEducativoCuenta");     
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();     
        ArrayList<ProgramaEducativo> result = instance.consultarProgramas();
        int expResult=3;
        assertEquals(expResult, result.size());
    }

    @Test
    public void testConsultarProgramaSeleccionado() throws Exception {
        String programa = "Estadística";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 3;
        int result = instance.consultarProgramaSeleccionado(programa);
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerOfertaAcademicaGeneral() {
        System.out.println("obtenerOfertaAcademicaGeneral");
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 15;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.obtenerOfertaAcademicaGeneral();
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    }

    @Test
    public void testConsultarOfertaAcademicaPorProgramaEducativo() {
        System.out.println("consultarOfertaAcademicaPorProgramaEducativo");
        String programaEducativoSeleccionado = "Redes y Servicio de Computo";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 4;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.consultarOfertaAcademicaPorProgramaEducativo(programaEducativoSeleccionado);
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    }
    
        @Test
    public void testConsultarOfertaAcademicaPorProgramaEducativo2() {
        System.out.println("consultarOfertaAcademicaPorProgramaEducativo");
        String programaEducativoSeleccionado = "";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 0;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.consultarOfertaAcademicaPorProgramaEducativo(programaEducativoSeleccionado);
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    }

    @Test
    public void testConsultarOfertaAcademicaPorProgramaEducativo3() {
        System.out.println("consultarOfertaAcademicaPorProgramaEducativo");
        String programaEducativoSeleccionado = "Ingeniería de Software";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 6;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.consultarOfertaAcademicaPorProgramaEducativo(programaEducativoSeleccionado);
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    }    


    @Test
    public void testConsultarOfertaAcademicaPorProgramaEducativo4() {
        System.out.println("consultarOfertaAcademicaPorProgramaEducativo");
        String programaEducativoSeleccionado = "Estadística";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 1;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.consultarOfertaAcademicaPorProgramaEducativo(programaEducativoSeleccionado);
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    } 
    
    @Test
    public void testConsultarOfertaAcademicaPorProgramaEducativo5() {
        System.out.println("consultarOfertaAcademicaPorProgramaEducativo");
        String programaEducativoSeleccionado = "Tecnologías Computacionales";
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 4;
        int cantidadDocentes = 0;
        ArrayList<DocenteEEPrograma> result = instance.consultarOfertaAcademicaPorProgramaEducativo(programaEducativoSeleccionado);
        for (DocenteEEPrograma docenteEEPrograma : result) {
            cantidadDocentes += 1;
        }
        System.out.println(cantidadDocentes);
        assertEquals(expResult, cantidadDocentes);
    }     
    
    @Test
    public void testObtenerIdDocenteEEPrograma() throws Exception {
        System.out.println("obtenerIdDocenteEEPrograma");
        int numPersonal = 45678;
        int nrc = 45;
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 2;
        int result = instance.obtenerIdDocenteEEPrograma(numPersonal, nrc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerIdDocenteEEPrograma1() throws Exception {
        System.out.println("obtenerIdDocenteEEPrograma");
        int numPersonal = 123456;
        int nrc = 100;
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 18;
        int result = instance.obtenerIdDocenteEEPrograma(numPersonal, nrc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerIdDocenteEEPrograma2() throws Exception {
        System.out.println("obtenerIdDocenteEEPrograma");
        int numPersonal = 3456;
        int nrc = 22;
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 12;
        int result = instance.obtenerIdDocenteEEPrograma(numPersonal, nrc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerIdDocenteEEPrograma3() throws Exception {
        System.out.println("obtenerIdDocenteEEPrograma");
        int numPersonal = 3456;
        int nrc = 99;
        DocenteEEProgramasDAO instance = new DocenteEEProgramasDAO();
        int expResult = 15;
        int result = instance.obtenerIdDocenteEEPrograma(numPersonal, nrc);
        assertEquals(expResult, result);
    }
    
}
