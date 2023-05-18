
package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Docente;
import uv.fei.tutorias.domain.TutorTutorado;

/**
 *
 * @author Valea
 */
public class TutorTutoradoDAOTest {
    
    public TutorTutoradoDAOTest() {
    }

    /**
     * Test of asignarTutorTutorado method, of class TutorTutoradoDAO.
     */
    @Test
    public void testAsignarTutorTutorado() throws Exception {
        System.out.println("asignarTutorTutorado");
        String cuentauv = "arivera@uv.mx";
        String Matricula = "S2001579";
        TutorTutoradoDAO instance = new TutorTutoradoDAO();
        int expResult = 1;
        int result = instance.asignarTutorTutorado(cuentauv, Matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of consultarTodosDocentes method, of class TutorTutoradoDAO.
     */
    @Test
    public void testConsultarTodosDocentes() throws Exception {
        System.out.println("consultarTodosDocentes");
        TutorTutoradoDAO instance = new TutorTutoradoDAO();
        int expResult = 5;
        ArrayList<Docente> result = instance.consultarTodosDocentes();
        assertEquals(expResult, result.size());
       
      
    }

    /**
     * Test of consultarTutoradosporTutor method, of class TutorTutoradoDAO.
     */
    @Test
    public void testConsultarTutoradosporTutor() throws Exception {
        System.out.println("consultarTutoradosporTutor");
        TutorTutoradoDAO instance = new TutorTutoradoDAO();
        int expResult = 4;
        ArrayList<TutorTutorado> result = instance.consultarTutoradosporTutor();
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
 
    }
    
}
