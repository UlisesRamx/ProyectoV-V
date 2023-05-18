package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Docente;

public class DocenteDAOTest {
    
    public DocenteDAOTest() {
    }

    @Test
    public void testRecuperarDocentesPorProgramaEducativo1() throws Exception {
        System.out.println("recuperarDocentesPorProgramaEducativo");
        int idPrograma = 1;
        int cantidadDocentes = 0;
        DocenteDAO instance = new DocenteDAO();
        int expResult = 3;
        ArrayList<Docente> result = instance.recuperarDocentesPorProgramaEducativo(idPrograma);
        for (Docente docente : result) {
            cantidadDocentes += 1;
        }
        assertEquals(expResult, cantidadDocentes);
    }
    
    @Test
    public void testRecuperarDocentesPorProgramaEducativo2() throws Exception {
        System.out.println("recuperarDocentesPorProgramaEducativo");
        int idPrograma = 0;
        DocenteDAO instance = new DocenteDAO();
        int expResult = 0;
        int cantidadDocentes = 0;
        ArrayList<Docente> result = instance.recuperarDocentesPorProgramaEducativo(idPrograma);
        for (Docente docente : result) {
            cantidadDocentes += 1;
        }
        assertEquals(expResult, cantidadDocentes);
    }
    
    @Test
    public void testRecuperarDocentesPorProgramaEducativo3() throws Exception {
        System.out.println("recuperarDocentesPorProgramaEducativo");
        int idPrograma = 2;
        DocenteDAO instance = new DocenteDAO();
        int expResult = 5;
        int cantidadDocentes = 0;
        ArrayList<Docente> result = instance.recuperarDocentesPorProgramaEducativo(idPrograma);
        for (Docente docente : result) {
            cantidadDocentes += 1;
        }
        assertEquals(expResult, cantidadDocentes);
    }
    
}
