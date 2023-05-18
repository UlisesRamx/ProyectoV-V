
package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Problematica;

/**
 *
 * @author Valea
 */
public class SolucionesProblematicaAcademicaDAOTest {
    
    public SolucionesProblematicaAcademicaDAOTest() {
    }

    /**
     * Test of registrarSolucion method, of class SolucionesProblematicaAcademicaDAO.
     */
    @Test
    public void testRegistrarSolucion() throws Exception {
        System.out.println("registrarSolucion");
        String solucion = "Esto es del test";
        int idProblematicaA = 5;
        SolucionesProblematicaAcademicaDAO instance = new SolucionesProblematicaAcademicaDAO();
        int expResult = 1;
        int result = instance.registrarSolucion(solucion, idProblematicaA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultarProblematicasSinSolucion method, of class SolucionesProblematicaAcademicaDAO.
     */
    @Test
    public void testConsultarProblematicasSinSolucion() throws Exception {
        System.out.println("consultarProblematicasSinSolucion");
        int idProgramaEducativo = 0;
        SolucionesProblematicaAcademicaDAO instance = new SolucionesProblematicaAcademicaDAO();
        int expResult = 3;
        ArrayList<Problematica> result = instance.consultarProblematicasSinSolucion(idProgramaEducativo);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of consultarSoluciones method, of class SolucionesProblematicaAcademicaDAO.
     */
    @Test
    public void testConsultarSoluciones() throws Exception {
        System.out.println("consultarSoluciones");
        int idProgramaEducativo = 0;
        SolucionesProblematicaAcademicaDAO instance = new SolucionesProblematicaAcademicaDAO();
        int expResult = 3;
        ArrayList<Problematica> result = instance.consultarSoluciones(idProgramaEducativo);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
