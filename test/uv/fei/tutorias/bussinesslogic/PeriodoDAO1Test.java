
package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Periodo;

public class PeriodoDAO1Test {
    
    public PeriodoDAO1Test() {
    }

    @Test
    public void testRegistrarPeriodo() {
        System.out.println("registrarPeriodo");
        Periodo periodo = null;
        PeriodoDAO instance = new PeriodoDAO();
        int expResult = 0;
        int result = instance.registrarPeriodo(periodo);
        assertEquals(expResult, result);
    }

    @Test
    public void testConsultarPeriodoPorId() throws SQLException {
        System.out.println("consultarPeriodoPorId");
        int idPeriodoBuscado = 0;
        PeriodoDAO instance = new PeriodoDAO();
        Periodo expResult = null;
        Periodo result = instance.consultarPeriodoActivo();
        System.out.println(result.getIdPeriodo());
        assertEquals(result, result);
    }
    
}
