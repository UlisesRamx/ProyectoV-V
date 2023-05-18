package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.ExperienciaEducativa;

public class ExperienciaEducativaDAOTest {

    @Test
    public void testConsultarExperienciasNoAsignadas() throws Exception {
        System.out.println("consultarExperienciasNoAsignadas");
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 4;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasNoAsignadas();
        assertEquals(expResult, result.size());
    }

    @Test
    public void testConsultarExperienciasPorPrograma() throws Exception {
        System.out.println("consultarExperienciasPorPrograma");
        int idProgramaEducativo = 1;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 0;
        int resultado = 4;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorPrograma(idProgramaEducativo);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        System.out.println(resultado);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConsultarExperienciasPorPrograma1() throws Exception {
        System.out.println("consultarExperienciasPorPrograma");
        int idProgramaEducativo = 2;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 6;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorPrograma(idProgramaEducativo);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        System.out.println(resultado);
        assertEquals(expResult, resultado);
    }
    
    @Test
    public void testConsultarExperienciasPorPrograma2() throws Exception {
        System.out.println("consultarExperienciasPorPrograma");
        int idProgramaEducativo = 3;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 1;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorPrograma(idProgramaEducativo);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        System.out.println(resultado);
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testConsultarExperienciasPorPrograma3() throws Exception {
        System.out.println("consultarExperienciasPorPrograma");
        int idProgramaEducativo = 0;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 0;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorPrograma(idProgramaEducativo);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        System.out.println(resultado);
        assertEquals(expResult, resultado);
    }

    @Test
    public void testConsultarExperienciasPorDocente() throws Exception {
        System.out.println("consultarExperienciasPorDocente");
        int numPersonal = 3434;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 2;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorDocente(numPersonal);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
    
    @Test
    public void testConsultarExperienciasPorDocente1() throws Exception {
        System.out.println("consultarExperienciasPorDocente");
        int numPersonal = 45678;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 7;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorDocente(numPersonal);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
    
    @Test
    public void testConsultarExperienciasPorDocente2() throws Exception {
        System.out.println("consultarExperienciasPorDocente");
        int numPersonal = 123456;
        ExperienciaEducativaDAO instance = new ExperienciaEducativaDAO();
        int expResult = 3;
        int resultado = 0;
        ArrayList<ExperienciaEducativa> result = instance.consultarExperienciasPorDocente(numPersonal);
        for(ExperienciaEducativa experiencia : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
}
