/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uv.fei.tutorias.bussinesslogicTests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import uv.fei.tutorias.bussinesslogic.SesionTutoriaDAO;
import uv.fei.tutorias.domain.SesionTutoria;

/**
 *
 * @author Usuario
 */
public class SesionTutoriaDAOTest {
    
    public SesionTutoriaDAOTest() {
    }

    @Test
    public void testRegistrarSesionTutoria() throws SQLException {
        System.out.println("registrarSesionTutoria");
        SesionTutoriaDAO sesionTutoriaDAO = new SesionTutoriaDAO();
        SesionTutoria nuevaSesionTutoria = new SesionTutoria();
        nuevaSesionTutoria.setNumTutoria("2");
        nuevaSesionTutoria.setFechaTutoria("2022-08-12");
        
        sesionTutoriaDAO.registrarSesionTutoria(nuevaSesionTutoria);

        String tutoriaBuscada = "2";
        ArrayList<SesionTutoria> expResult = new ArrayList<>();
        SesionTutoria tutoriaEsperada = new SesionTutoria();
        tutoriaEsperada.setNumTutoria("2");
        tutoriaEsperada.setFechaTutoria("2022-08-12");
        
        expResult.add(tutoriaEsperada);
        ArrayList<SesionTutoria> result = (ArrayList<SesionTutoria>) sesionTutoriaDAO.consultarSesionesTutoriaPorNumero(tutoriaBuscada);
        
        for(SesionTutoria sesionTutoria : result){
            System.out.println(String.format("%s %s", sesionTutoria.getNumTutoria(), sesionTutoria.getFechaTutoria()));
        }
        for(SesionTutoria sesionTutoria : expResult){
            System.out.println(String.format("%s %s", sesionTutoria.getNumTutoria(), sesionTutoria.getFechaTutoria()));
        }
        assertEquals(expResult, result);
    }


    @Test
    public void testConsultarSesionesTutoriaPorNumero1() {
        System.out.println("consultarSesionesTutoriaPorNumero");
        String tutoriaBuscada = "0";
        SesionTutoriaDAO instance = new SesionTutoriaDAO();
        List<SesionTutoria> expResult = null;
        List<SesionTutoria> result = instance.consultarSesionesTutoriaPorNumero(tutoriaBuscada);
        assertEquals(expResult, result);
    }

    @Test
    public void testConsultarSesionesTutoriaPorNumero() {
        System.out.println("consultarSesionesTutoriaPorNumero");
        String tutoriaBuscada = "";
        SesionTutoriaDAO instance = new SesionTutoriaDAO();
        List<SesionTutoria> expResult = null;
        List<SesionTutoria> result = instance.consultarSesionesTutoriaPorNumero(tutoriaBuscada);
        assertEquals(expResult, result);
    }

}
