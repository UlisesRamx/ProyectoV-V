/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package uv.fei.tutorias.bussinesslogic;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Usuario;

import java.sql.SQLException;

public class UsuarioDAOTest {

    @Test
    public void testRecuperarSesion() throws SQLException {
        System.out.println("recuperarSesion");
        String cuentauv = "arivera";
        String contrasena = "234";
        UsuarioDAO instance = new UsuarioDAO();
        int expResult =  1;
        int resultado = 0;
        Usuario result = instance.recuperarSesion(cuentauv, contrasena);
        if (result.getNombre() != null){
            resultado += 1;
        }
        System.out.println(result.getCuentaUV() + " " + " " + result.getContrasenia());
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testRecuperarSesion1() throws SQLException {
        System.out.println("recuperarSesion");
        String cuentauv = "jbarrera";
        String contrasena = "1234";
        UsuarioDAO instance = new UsuarioDAO();
        int expResult =  1;
        int resultado = 0;
        Usuario result = instance.recuperarSesion(cuentauv, contrasena);
        if (result.getNombre() != null){
            resultado += 1;
        }
        System.out.println(result.getCuentaUV() + " " + " " + result.getContrasenia());
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testRecuperarSesion2() throws SQLException {
        System.out.println("recuperarSesion");
        String cuentauv = "kbravo";
        String contrasena = "1234";
        UsuarioDAO instance = new UsuarioDAO();
        int expResult =  1;
        int resultado = 0;
        Usuario result = instance.recuperarSesion(cuentauv, contrasena);
        if (result.getNombre() != null){
            resultado += 1;
        }
        System.out.println(result.getCuentaUV() + " " + " " + result.getContrasenia());
        assertEquals(expResult, resultado);
    }
    
        @Test
    public void testRecuperarSesion3() throws SQLException {
        System.out.println("recuperarSesion");
        String cuentauv = "phernandez";
        String contrasena = "1234";
        UsuarioDAO instance = new UsuarioDAO();
        int expResult =  1;
        int resultado = 0;
        Usuario result = instance.recuperarSesion(cuentauv, contrasena);
        if (result.getNombre() != null){
            resultado += 1;
        }
        System.out.println(result.getCuentaUV() + " " + " " + result.getContrasenia());
        assertEquals(expResult, resultado);
    }
    
            @Test
    public void testRecuperarSesion4() throws SQLException {
        System.out.println("recuperarSesion");
        String cuentauv = "phernandez";
        String contrasena = "234";
        UsuarioDAO instance = new UsuarioDAO();
        int expResult =  0;
        int resultado = 0;
        Usuario result = instance.recuperarSesion(cuentauv, contrasena);
        if (result.getNombre() != null){
            resultado += 1;
        }
        System.out.println(result.getCuentaUV() + " " + " " + result.getContrasenia());
        assertEquals(expResult, resultado);
    }
    
}
