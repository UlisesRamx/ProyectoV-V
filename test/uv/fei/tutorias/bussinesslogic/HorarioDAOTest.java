package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Horario;

public class HorarioDAOTest {
    
    public HorarioDAOTest() {
    }

    @Test
    public void testConsultarHorariosporIdTutoria() {
        System.out.println("consultarHorariosporIdTutoria");
        String cuentauv = "arivera";
        int idTutoria = 63;
        int idProgramaEducativo = 2;
        HorarioDAO instance = new HorarioDAO();
        int expResult = 1;
        int resultado = 0;
        ArrayList<Horario> result = instance.consultarHorariosporIdTutoria(cuentauv, idTutoria, idProgramaEducativo);
        for(Horario horario : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
    
    @Test
    public void testConsultarHorariosporIdTutoria1() {
        System.out.println("consultarHorariosporIdTutoria");
        String cuentauv = "arivera";
        int idTutoria = 109;
        int idProgramaEducativo = 2;
        HorarioDAO instance = new HorarioDAO();
        int expResult = 2;
        int resultado = 0;
        ArrayList<Horario> result = instance.consultarHorariosporIdTutoria(cuentauv, idTutoria, idProgramaEducativo);
        for(Horario horario : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
  
    @Test
    public void testConsultarHorariosporIdTutoria2() {
        System.out.println("consultarHorariosporIdTutoria");
        String cuentauv = "arivera";
        int idTutoria = 110;
        int idProgramaEducativo = 2;
        HorarioDAO instance = new HorarioDAO();
        int expResult = 2;
        int resultado = 0;
        ArrayList<Horario> result = instance.consultarHorariosporIdTutoria(cuentauv, idTutoria, idProgramaEducativo);
        for(Horario horario : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }

    @Test
    public void testRegistrarHorario1() {
        System.out.println("registrarHorario");
        Horario horario = new Horario();
        horario.setMatricula("S20045891");
        horario.setCuentauv("arivera");
        horario.setHora("10:20:00");
        horario.setIdTutoria(63);
        horario.setIdProgramaEducativo(2);
        HorarioDAO instance = new HorarioDAO();
        int expResult = 1;
        int result = instance.registrarHorario(horario);
        assertEquals(expResult, result);
    }

    @Test
    public void testActualizarHorario() {
        System.out.println("actualizarHorario");
        Horario horario = new Horario();
        horario.setHora("10:20:00");
        horario.setIdHorario(5);
        HorarioDAO instance = new HorarioDAO();
        int expResult = 1;
        int result = instance.actualizarHorario(horario);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testActualizarHorario1() {
        System.out.println("actualizarHorario");
        Horario horario = new Horario();
        horario.setHora("10:40:00");
        horario.setIdHorario(5);
        HorarioDAO instance = new HorarioDAO();
        int expResult = 1;
        int result = instance.actualizarHorario(horario);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testActualizarHorario2() {
        System.out.println("actualizarHorario");
        Horario horario = new Horario();
        horario.setHora("10:20:00");
        horario.setIdHorario(100);
        HorarioDAO instance = new HorarioDAO();
        int expResult = 0;
        int result = instance.actualizarHorario(horario);
        assertEquals(expResult, result);
    }
       
    @Test
    public void testObtenerTutoradosParaRegistrodeHorario() {
        System.out.println("obtenerTutoradosParaRegistrodeHorario");
        String cuentaUV = "arivera";
        int idProgramaEducativo = 2;
        HorarioDAO instance = new HorarioDAO();
        int expResult = 3;
        int resultado = 0;
        ArrayList<Horario> result = instance.obtenerTutoradosParaRegistrodeHorario(cuentaUV, idProgramaEducativo);
        for (Horario horario : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testObtenerTutoradosParaRegistrodeHorario2() {
        System.out.println("obtenerTutoradosParaRegistrodeHorario");
        String cuentaUV = "pgonzalez";
        int idProgramaEducativo = 3;
        HorarioDAO instance = new HorarioDAO();
        int expResult = 2;
        int resultado = 0;
        ArrayList<Horario> result = instance.obtenerTutoradosParaRegistrodeHorario(cuentaUV, idProgramaEducativo);
        for (Horario horario : result) {
            resultado += 1;
        }
        assertEquals(expResult, resultado);
    }
}
