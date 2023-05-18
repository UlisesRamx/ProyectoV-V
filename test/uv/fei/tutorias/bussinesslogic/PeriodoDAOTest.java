package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Periodo;

public class PeriodoDAOTest {
    
    public PeriodoDAOTest() {
    }

    @Test
    public void testConsultarTodosLosPeriodos() {
        System.out.println("consultarTodosLosPeriodos");
        PeriodoDAO instance = new PeriodoDAO();
        int expResult = 3;
        int resultado = 0;
        ArrayList<Periodo> result = instance.consultarTodosLosPeriodos();
        for(Periodo periodo : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testConsultarTodosLosPeriodos1Fallida() {
        System.out.println("consultarTodosLosPeriodos");
        PeriodoDAO instance = new PeriodoDAO();
        int expResult = 2;
        int resultado = 0;
        ArrayList<Periodo> result = instance.consultarTodosLosPeriodos();
        for(Periodo periodo : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }

    @Test
    public void testConsultarPeriodoActivo() throws Exception {
        System.out.println("consultarPeriodoActivo");
        PeriodoDAO instance = new PeriodoDAO();
        Periodo expResult = new Periodo();
        expResult.setFechaFin("2023-01-26");
        expResult.setFechaInicio("2022-07-25");
        expResult.setIdPeriodo(13);
        Periodo result = instance.consultarPeriodoActivo();
        assertEquals(expResult, result);
    }
    
        @Test
    public void testConsultarPeriodoActivo1Fallida() throws Exception {
        System.out.println("consultarPeriodoActivo");
        PeriodoDAO instance = new PeriodoDAO();
        Periodo expResult = new Periodo();
        expResult.setFechaFin("2023-01-26");
        expResult.setFechaInicio("2022-07-25");
        expResult.setIdPeriodo(10);
        Periodo result = instance.consultarPeriodoActivo();
        assertEquals(expResult, result);
    }
}
