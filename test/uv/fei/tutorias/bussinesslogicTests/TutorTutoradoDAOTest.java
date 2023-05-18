package uv.fei.tutorias.bussinesslogicTests;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.TutorTutoradoDAO;
import java.sql.SQLException;
import static org.junit.Assert.*;

public class TutorTutoradoDAOTest {
    
    public TutorTutoradoDAOTest() {
    }

    @Test
    public void testAsignarTutorTutorado() throws SQLException {
       // System.out.println("eliminarProblematica");
        
        TutorTutoradoDAO instance = new TutorTutoradoDAO();
        int expResult = 1;
        String cuenta="maria";
        String matricula="S208998";
        int result = instance.asignarTutorTutorado(cuenta, matricula);
        assertEquals(expResult, result);
    }

    
}
